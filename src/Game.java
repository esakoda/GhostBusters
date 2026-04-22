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
    public static final String[] ghost_colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple"};

    public Game(){
        this.state = STATE_GAME;
        this.window = new GameView(this);

        // TODO: add constants for the row/col size
        // Create a 2D array, representing a grid of ghosts
        this.ghosts = new Ghost[3][7];

        // Index through the empty array to create individual ghost objects
        for (Ghost[] row: ghosts) {
            for (Ghost g: row) {
                int random_color_index = (int) (Math.random() * 6);
                g = new Ghost(window, ghost_colors[random_color_index]);
            }
        }
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
