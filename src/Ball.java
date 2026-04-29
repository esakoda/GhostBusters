import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private double dx;
    private double dy;
    private boolean isActive;

    public static final int RADIUS = 8;
    public static final double SPEED = 12.0;

    // Wall boundaries
    public static final int LEFT_WALL = 0;
    public static final int RIGHT_WALL = 1400;
    public static final int TOP_WALL = 0;
    public static final int BOTTOM_WALL = 880;

    public Ball(int startX, int startY, double angle){
        this.x = startX;
        this.y = startY;
        // Velocity depends on the angle of the arrow
        this.dx = SPEED * Math.cos(angle);
        this.dy = SPEED * Math.sin(angle);
        this.isActive = true;
    }

    public void move() {
        // If the ball is dead
        if (!isActive) {
            return;
        }

        // Increase position by speed - so it moves
        x += dx;
        y += dy;

        // Bounce off left/right walls
        if (x - RADIUS <= LEFT_WALL) {
            x = LEFT_WALL + RADIUS;
            dx = -dx;
        } else if (x + RADIUS >= RIGHT_WALL) {
            x = RIGHT_WALL - RADIUS;
            dx = -dx;
        }

        // Bounce off top/bottom walls
        if (y - RADIUS <= TOP_WALL) {
            y = TOP_WALL + RADIUS;
            dy = -dy;
        } else if (y + RADIUS >= BOTTOM_WALL) {
            y = BOTTOM_WALL - RADIUS;
            dy = -dy;
        }
    }
    // TODO: Checks if ball hit a ghost

    public void draw(Graphics g){
        // Don't draw if the ball is dead
        if (!isActive){
            return;
        }
        // Ball draws itself
        g.setColor(Color.WHITE);
        g.fillOval((int)(x - RADIUS), (int)(y - RADIUS), RADIUS * 2, RADIUS * 2);
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isActive(){
        return isActive;
    }

    public void setActive(boolean active){
        this.isActive = active;
    }
}
