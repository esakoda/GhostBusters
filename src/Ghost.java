public class Ghost {
    private int x;
    private int y;
    private int colorIndex;

    private double speed;

    private boolean isAlive;

    public Ghost(int colorIndex, int x, int y, boolean isAlive){
        this.colorIndex = colorIndex;
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    // Getter methods
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

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
