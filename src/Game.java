import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    private Arrow arrow;
    private Ghost[][] ghosts;

    private int score;
    // TODO: do we even need this?
    private boolean gameOver;
    private int spawnInterval;
    private GameView window;

    // Keeps track of the first column in the 2D array with a visible ghost
    private int frontCol = 0;

    private int state;

    public static final int GAME_OVER_BOUNDS = 150;

    // Magic Numbers for 2D Array
    public static final int GHOST_ROWS = 7;
    public static final int GHOST_COL = 4;

    public static final int GHOST_X_SPACING = 110;
    public static final int GHOST_Y_SPACING = 100;
    public static final int GHOST_X_INITIAL = 960;
    public static final int GHOST_Y_INITIAL = 50;


    public static final int STATE_TITLE = 0;
    public static final int STATE_INSTRUCTIONS = 1;
    public static final int STATE_GAME = 2;
    public static final int STATE_END = 3;

    public static final int STEP_SIZE = 10;

    // Create a constant array of ghost colors
    public static final String[] ghostColors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple"};

    public Game(){
        state = STATE_GAME;
        arrow = new Arrow();

        // TODO: TEMPORARY SOLUTION
        // Create a 2D array, representing a grid of ghosts
        this.ghosts = new Ghost[GHOST_ROWS][GHOST_COL];

        // Index through the empty array to create individual ghost objects
        for (int row = 0; row < ghosts.length; row++) {
            for (int col = 0; col < ghosts[row].length; col++) {
                int randomColorIndex = (int) (Math.random() * 6);

                // Calculate each ghost's starting x and y positions based on row/col
                int startX = col * GHOST_X_SPACING + GHOST_X_INITIAL;
                int startY = row * GHOST_Y_SPACING + GHOST_Y_INITIAL;
                ghosts[row][col] = new Ghost(randomColorIndex, startX, startY, true);
            }
        }

        this.window = new GameView(this);
        window.addKeyListener(this);

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


    public void deleteGhost(){

    }

    public void spawnGhost(){

    }

    public void update(){

    }

    public void rampUpDifficulty(){

    }

    // TODO: in the future, we can make this more efficient, by deleting the rows or adding this into Ellens code
    // Checks if the ghosts have hit the player. If so, game over.
    public void checkGameOver(){
        // TODO: are there any edge cases? I feel like this is a little dangerous for index error
        for (int row = 0; row < GHOST_ROWS; row++) {
            Ghost currGhost = ghosts[row][frontCol];

            // If there is a ghost isAlive and inside the boundaries of the person, game over
            if (currGhost.isAlive() && currGhost.getX() <= GAME_OVER_BOUNDS) {
                // Update the game state and boolean
                this.state = STATE_END;
                this.gameOver = true;

                // Early exit to prevent redundancy
                return;
            }
        }
    }

    // TODO: integrate with Ellen's code
    // Updates the front column each time a ghost is hit
    public void updateFrontCol() {
        // Index through the different columns until we find the first alive Ghost
        for (int col = 0; col < ghosts[0].length; col++) {
            // For loop, goes through the current front column to check if it is still truly the front column
            // If it is, can early exit

            // if not, update frontCol++
            // delate the front column

        }
    }

    public void playGame(){

    }


    public static void main(String[] args){
        Game ghostBusters = new Game();
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
            // TODO: get rid of, this is just a tester
            case KeyEvent.VK_SPACE:
                // Enact that the top ghost was hit
                Ghost deadGhost = ghosts[0][0];
                ghostPop(deadGhost.getColorIndex(), 0, 0);
                break;
        }
        window.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
