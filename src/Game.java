public class Game {
    private Ghost[] ghosts;
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

    public Game(){
        state = STATE_GAME;
        window = new GameView(this);
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
