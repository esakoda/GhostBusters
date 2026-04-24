import java.awt.*;
import java.util.ArrayList;

public class Ghost {
    private int x;
    private int y;
    private int colorIndex;

    private double speed;

    private ArrayList<Ghost> neighbors;
    private boolean isAlive;

    public Ghost(int colorIndex, int x, int y){
        this.colorIndex = colorIndex;
        this.x = x;
        this.y = y;
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
