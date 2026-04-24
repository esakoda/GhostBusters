import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    private Arrow arrow;
    private Ghost[][] ghosts;

    private LaserPack laserPack;
    private int score;
    private boolean gameOver;
    private int spawnInterval;
    private GameView window;

    private int state;

    // Magic Numbers for 2D Array
    public static final int GHOST_ROWS = 7;
    public static final int GHOST_COL = 4;

    public static final int GHOST_X_SPACING = 110;
    public static final int GHOST_Y_SPACING = 100;
    public static final int GHOST_X_INITIAL = 550;
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
                ghosts[row][col] = new Ghost(randomColorIndex, startX, startY);
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

    public void deleteGhost(){

    }

    public void spawnGhost(){

    }

    public void update(){

    }

    public void rampUpDifficulty(){

    }

    public void checkGameOver(){

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
        }
        window.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
