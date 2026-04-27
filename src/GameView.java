import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game backend;
    public final int WINDOW_WIDTH = 1000;
    public final int WINDOW_HEIGHT = 800;
    private Image background;

    private Image[] ghostImages = new Image[6];

    public GameView(Game backend){
        this.backend = backend;

        // Initialize background
        this.background = new ImageIcon("resources/GhostBustersBackground.png").getImage();

        // Initialize an array of different colored ghost images
        ghostImages[0] = new ImageIcon("resources/redGhost.png").getImage();
        ghostImages[1] = new ImageIcon("resources/orangeGhost.png").getImage();
        ghostImages[2] = new ImageIcon("resources/yellowGhost.png").getImage();
        ghostImages[3] = new ImageIcon("resources/greenGhost.png").getImage();
        ghostImages[4] = new ImageIcon("resources/blueGhost.png").getImage();
        ghostImages[5] = new ImageIcon("resources/purpleGhost.png").getImage();


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GhostBusters");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        int state = backend.getState();

        if (state == Game.STATE_TITLE) {
            drawTitle(g);
        } else if (state == Game.STATE_INSTRUCTIONS) {
            drawInstructions(g);
        } else if (state == Game.STATE_GAME) {
            drawGame(g);
            Ghost[][] ghosts = backend.getGhosts();

            // Iterate through 2D Ghost array and print it out
            for (Ghost[] ghost_row: ghosts) {
                for (Ghost currGhost : ghost_row) {
                    Image ghostImage = ghostImages[currGhost.getColorIndex()];

                    // Draw ghost with it's x and y coor
                    g.drawImage(ghostImage, currGhost.getX(), currGhost.getY(), this);
                }
            }

        } else if (state == Game.STATE_END) {
            drawEnd(g);
        }
    }

    public void drawTitle(Graphics g) {

    }
    public void drawInstructions(Graphics g) {

    }
    public void drawGame(Graphics g){
        // Draw background imgage
        g.drawImage(background,0,0,this);

        // Draw arrow
        backend.getArrow().draw(g);

        // Get current ball from backend and draw it
        Ball ball = backend.getActiveBall();
        if (ball != null){
            ball.draw(g);
        }
    }
    public void drawEnd(Graphics g){

    }
}
