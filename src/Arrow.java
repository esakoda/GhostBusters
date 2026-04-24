import java.awt.*;

public class Arrow{
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private double angle;
    private double sensitivity;
    private int arrowLength;
    private Laser[] lasers;

    public Arrow(){
        this.startX = 265;
        this.startY = 370;
        this.arrowLength = 100;
        this.endX = startX + arrowLength;
        this.endY = startY;
        this.angle = 0;
        this.sensitivity = 0.05;
    }

    public void followCursor(int mouseX, int mouseY){

    }

    public void shoot(){

    }

    public void moveLasers(){

    }

    public void shiftAngle(int shift) {
        // Shifts angle up or down by a specific sensitivity
        angle += shift * sensitivity;
        // Calculates new end x and end y by using cosine and sine to get the new horizontal and vertical positions
        // of the arrow based on the new angle
        endX = (int)(startX + arrowLength * Math.cos(angle));
        endY = (int)(startY + arrowLength * Math.sin(angle));
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.drawLine(startX, startY, endX, endY);
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
