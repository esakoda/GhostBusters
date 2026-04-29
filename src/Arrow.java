import java.awt.*;

public class Arrow{
    private int endX;
    private int endY;
    private double angle;
    private Ball[] balls;
    private static final int START_X = 282;
    private static final int START_Y = 407;
    private static final double SENSITIVITY = 0.05;
    private static final int ARROW_LENGTH = 100;

    public Arrow(){
        this.endX = START_X + ARROW_LENGTH;
        this.endY = START_Y;
        this.angle = 0;
    }

    public void shoot(){

    }

    public void moveLasers(){

    }

    // Shift the angle of the arrow based on user input
    public void shiftAngle(int shift) {
        // Shifts angle up or down by a specific sensitivity
        angle += shift * SENSITIVITY;
        // Calculates new end x and end y by using cosine and sine to get the new horizontal and vertical positions
        // of the arrow based on the new angle
        endX = (int)(START_X + ARROW_LENGTH * Math.cos(angle));
        endY = (int)(START_Y + ARROW_LENGTH * Math.sin(angle));
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.drawLine(START_X, START_Y, endX, endY);
    }

    // Getter methods
    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public double getAngle() {
        return angle;
    }

    public int getStartX() {
        return START_X;
    }

    public int getStartY() {
        return START_Y;
    }
}
