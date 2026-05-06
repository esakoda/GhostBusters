import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private double dx;
    private double dy;
    private boolean isActive;

    // Ball dimensions
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
        if (!this.isActive) {
            return;
        }

        // Increase position by speed - so it moves
        this.x += this.dx;
        this.y += this.dy;

        // Bounce off left/right walls
        if (this.x - RADIUS <= LEFT_WALL) {
            this.x = LEFT_WALL + RADIUS;
            this.dx = -this.dx;
        } else if (x + RADIUS >= RIGHT_WALL) {
            this.x = RIGHT_WALL - RADIUS;
            this.dx = -this.dx;
        }

        // Bounce off top/bottom walls
        if (this.y - RADIUS <= TOP_WALL) {
            this.y = TOP_WALL + RADIUS;
            this.dy = -this.dy;
        } else if (this.y + RADIUS >= BOTTOM_WALL) {
            this.y = BOTTOM_WALL - RADIUS;
            this.dy = -this.dy;
        }
    }
    // TODO: Checks if ball hit a ghost

    public void draw(Graphics g){
        // Don't draw if the ball is dead
        if (!this.isActive){
            return;
        }
        // Ball draws itself
        g.setColor(Color.WHITE);
        g.fillOval((int)(this.x - RADIUS), (int)(this.y - RADIUS), RADIUS * 2, RADIUS * 2);
    }

    // Getters and Setters
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isActive(){
        return this.isActive;
    }

    public void setActive(boolean active){
        this.isActive = active;
    }
}
