public class Game {
    private Ghost[] ghosts;
    private LaserPack laserPack;
    private int score;
    private boolean gameOver;
    private int spawnInterval;
    private GameView window;

    public Game(){
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
}
