public class Game {
    private Ghost[][] ghosts;

    private LaserPack laserPack;
    private int score;
    private boolean gameOver;
    private int spawnInterval;
    private GameView window;

    private int state;

    public static final int STATE_TITLE = 0;
    public static final int STATE_INSTRUCTIONS = 1;
    public static final int STATE_GAME = 2;
    public static final int STATE_END = 3;

    // Create a constant array of ghost colors
    public static final String[] ghostColors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple"};

    public Game(){
        this.state = STATE_GAME;
        // TODO: TEMPORARY SOLUTION
        // Create a 2D array, representing a grid of ghosts
        this.ghosts = new Ghost[7][4];

        // Index through the empty array to create individual ghost objects
        for (int row = 0; row < ghosts.length; row++) {
            for (int col = 0; col < ghosts[row].length; col++) {
                int randomColorIndex = (int) (Math.random() * 6);

                // Calculate each ghost's starting x and y positions based on row/col
                int startX = col * 110 + 550;
                int startY = row * 100 + 50;
                ghosts[row][col] = new Ghost(randomColorIndex, startX, startY);
            }
        }

        this.window = new GameView(this);

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
}
