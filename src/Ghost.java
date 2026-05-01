public class Ghost {
    private int x;
    private int y;
    private int row;
    private int col;
    private int colorIndex;

    private double speed;

    private boolean isAlive;

    public static final int GHOST_WIDTH = 80;
    public static final int GHOST_HEIGHT = 80;

    public Ghost(int colorIndex, int x, int y, int row, int col, boolean isAlive){
        this.colorIndex = colorIndex;
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.isAlive = isAlive;
    }

    // Getter methods
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getCol() { return this.col; }

    public int getRow() { return this.row; }

    public int getColorIndex() {
        return this.colorIndex;
    }

    public boolean isAlive() {
        return isAlive;
    }

    // Setter methods
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }


    public void hasValidNeighbor(){

    }

    public void hasNeighbor(){

    }

    public void moveLeft(){

    }

    public void increaseSpeed(double amt){

    }

    public void hitLine(){

    }
}
