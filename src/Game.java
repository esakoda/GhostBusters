import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Game implements KeyListener, ActionListener {
    private Arrow arrow;
    private Ghost[][] ghosts;

    private Ball activeBall;
    private Timer gameTimer;
    private double shiftAmount;
    private int timer;

    private GameView window;

    private int state;

    public static final int GAME_OVER_BOUNDS = 185;

    // Magic Numbers for 2D Array
    public static final int GHOST_ROWS = 7;
    public static final int GHOST_COL = 12;

    public static final int GHOST_X_SPACING = 110;
    public static final int GHOST_Y_SPACING = 100;
    public static final int GHOST_X_INITIAL = 960;
    public static final int GHOST_Y_INITIAL = 75;


    public static final int STATE_TITLE = 0;
    public static final int STATE_INSTRUCTIONS = 1;
    public static final int STATE_GAME = 2;
    public static final int STATE_END = 3;

    public static final int SLEEP_TIME = 16;
    public static final int INCREASE_SPEED_TIME = 313;
    public static final double INCREASE_SPEED_AMOUNT = 1.15;
    public static final double STARTING_SHIFT_AMOUNT = 0.2;

    public Game(){
        state = STATE_GAME;
        arrow = new Arrow();
        shiftAmount = STARTING_SHIFT_AMOUNT;
        timer = INCREASE_SPEED_TIME;

        // Create a 2D array, representing a grid of ghosts
        this.ghosts = new Ghost[GHOST_ROWS][GHOST_COL];

        // Index through the empty array to create individual ghost objects
        for (int row = 0; row < GHOST_ROWS; row++) {
            for (int col = 0; col < GHOST_COL; col++) {
                int randomColorIndex = (int) (Math.random() * 6);

                // Calculate each ghost's starting x and y positions based on row/col
                int startX = col * GHOST_X_SPACING + GHOST_X_INITIAL;
                int startY = row * GHOST_Y_SPACING + GHOST_Y_INITIAL;
                ghosts[row][col] = new Ghost(randomColorIndex, startX, startY, row, col, true);
            }
        }

        this.window = new GameView(this);
        window.addKeyListener(this);

        // Creates a timer that tells the game to do the actionPerformed method every SLEEP_TIME seconds
        gameTimer = new Timer(SLEEP_TIME, this);
        gameTimer.start();

        // TODO: add constants for the row/col size
    }

    // Getter methods
    public Ghost[][] getGhosts() {
        return ghosts;
    }

    // Recursive method that deletes ghost neighbors of same color
    public void ghostPop(int colorIndex, int row, int col) {
        // If the row and col are outside the bounds of the ghosts on the screen, no need to check
        // TODO: find how to keep a counter of the number of cols increases as the screen moves
        // TODO: for now keep it stagnant within the 4 by 7 bounds
        if (row < 0 || row >= GHOST_ROWS || col < 0 || col >= GHOST_COL) {
            return;
        }

        Ghost currGhost = ghosts[row][col];
        // If the ghost is not alive, no need to check
        if (!currGhost.isAlive()) {
            return;
        }

        // If the current ghost is not the right color, not valid
        if (currGhost.getColorIndex() != colorIndex) {
            return;
        }

        // If passes the checks, then the ghost must be deleted; update status
        currGhost.setIsAlive(false);

        // Find if the currGhost's neighbors on all four sides fit the criteria
        ghostPop(colorIndex, row - 1, col);
        ghostPop(colorIndex, row + 1, col);
        ghostPop(colorIndex, row, col - 1);
        ghostPop(colorIndex, row, col + 1);
    }


    public Ball getActiveBall(){
        return activeBall;
    }

    // Function for checking if a ball has hit a ghost
    public Ghost checkBallHitGhost(){
        // Ball dimensions
        int bx1 = activeBall.getX() - Ball.RADIUS;
        int bx2 = activeBall.getX() + Ball.RADIUS;
        int by1 = activeBall.getY() - Ball.RADIUS;
        int by2 = activeBall.getY() + Ball.RADIUS;

        // Nested loop that goes through every ghost and checks if ball hit - columns first for efficiency
        for (int col = 0; col < GHOST_COL; col++){
            for (int row = 0; row < GHOST_ROWS; row++){
                Ghost currGhost = ghosts[row][col];

                // Skip if ghost is null (already hit or removed)
                if (!currGhost.isAlive()){
                    continue;
                }

                // Current Ghost dimensions
                double gx1 = currGhost.getX();
                double gx2 = currGhost.getX() + Ghost.GHOST_WIDTH;
                double gy1 = currGhost.getY();
                double gy2 = currGhost.getY() + Ghost.GHOST_HEIGHT;

                // Checks if the ball has collided with the Ghost and if so returns that Ghost
                if (bx2 >= gx1 && bx1 <= gx2 && by1 <= gy2 && by2 >= gy1){
                    return currGhost;
                }
            }
        }

        // No collision found
        return null;
    }

    // Checks if the ghosts have hit the player. If so, game over.
    public void checkGameOver(){
        // TODO: are there any edge cases? I feel like this is a little dangerous for index error
        for (int row = 0; row < GHOST_ROWS; row++) {
            Ghost currGhost = ghosts[row][0];

            // If there is a ghost isAlive and inside the boundaries of the person, game over
            if (currGhost.isAlive() && currGhost.getX() <= GAME_OVER_BOUNDS) {
                // Update the state
                this.state = STATE_END;

                // Early exit to prevent redundancy
                return;
            }
        }
    }

    // TODO: integrate with Ellen's code
    // Updates the front column each time a ghost is hit
    public void updateFrontCol() {
        // Continues to index through the array, if we haven't found a single valid column
        // Acts as an infinite loop with a limit to prevent index out of bounds error
        for (int col = 0; col < GHOST_COL; col++) {
            // Loop through the first column to find a valid, alive Ghost
            for (int row = 0; row < GHOST_ROWS; row++) {
                // If we find a valid living ghost, can early exit
                if (ghosts[row][0].isAlive()) {
                    return;
                }
            }

            // Delete the first column if no valid ghost
            reindexGhosts();
        }
    }

    public void reindexGhosts() {
        // Create temp 2D array
        Ghost[][] condenseGhost = new Ghost[GHOST_ROWS][GHOST_COL];

        // Determine new width based on starting point: frontCol
        for (int row = 0; row < GHOST_ROWS; row++) {
            for (int col = 1; col < GHOST_COL; col++) {
                // Update the ghosts column value
                Ghost currGhost = ghosts[row][col];

                // Shift the currGhost column value
                currGhost.setCol(col - 1);

                condenseGhost[row][col - 1] = currGhost;
            }
        }

        // Make the new last column of ghosts
        int lastCol = GHOST_COL - 1;
        for (int row = 0; row < GHOST_ROWS; row++) {
            int randomColorIndex = (int) (Math.random() * 6);

            // Calculate each ghost's starting x and y positions based on row/col
            double startX = lastCol * GHOST_X_SPACING + condenseGhost[0][0].getX();
            double startY = row * GHOST_Y_SPACING + GHOST_Y_INITIAL;
            condenseGhost[row][lastCol] = new Ghost(randomColorIndex, startX, startY, row, lastCol, true);
        }

        ghosts = condenseGhost;
    }

    // Move the array of ghosts toward the user
    public void moveGhosts(){
        for (int i = 0; i < ghosts.length; i++){
            for (int j = 0; j < ghosts[i].length; j++){
                ghosts[i][j].setX(ghosts[i][j].getX() - shiftAmount);
            }
        }
    }

    public int getState() {
        return state;
    }

    public Arrow getArrow(){
        return arrow;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    // Moves the ball / ball animation function
    // Called every SLEEP_TIME by the Timer. If a ball is currently moving, move it one step and then repaint
    public void actionPerformed(ActionEvent e){
        // CHeck if game over
        if (state != STATE_GAME) {
            return;
        }
        // Decrease timer
        timer--;
        if (timer <= 0){
            // Increase speed of ghosts
            shiftAmount = shiftAmount * INCREASE_SPEED_AMOUNT;
            // Reset timer
            timer = INCREASE_SPEED_TIME;
        }
        // Move the ghosts across the screen every time
        moveGhosts();

        // Given ghosts just moved, checked if it crossed the bounds
        checkGameOver();

        // Check if there is an active ball because if there is no ball we cant call checkBallHitGhost()
        if (activeBall != null) {
            activeBall.move();

            // Check if ghost was hit
            Ghost hitGhost = checkBallHitGhost();
            if (hitGhost == null){
                window.repaint();
                return;
            }
            if (hitGhost.isAlive()){
                // Mark the ghost as not visible and the neighbor ghosts of the same color
                ghostPop(hitGhost.getColorIndex(), hitGhost.getRow(), hitGhost.getCol());

                // Check if clearing those ghosts emptied the front column
                updateFrontCol();

                // Delete the ball
                activeBall = null;
            }
        }
        window.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                // Shifts angle up
                arrow.shiftAngle(-1);
                break;
            case KeyEvent.VK_DOWN:
                // Shifts angle down
                arrow.shiftAngle(1);
                break;
            case KeyEvent.VK_SPACE:
                // Shoots out a ball
                if (activeBall == null){
                    activeBall = new Ball (arrow.getStartX(), arrow.getStartY(), arrow.getAngle());
                }
                break;
            case KeyEvent.VK_Q:
                state = STATE_END;
        }
        window.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args){
        Game ghostBusters = new Game();
    }
}
