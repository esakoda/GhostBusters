import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame {
    private Game backend;
    public final int WINDOW_WIDTH = 1400;
    public final int WINDOW_HEIGHT = 880;
    private Image background;

    private Image[] ghostImages = new Image[6];

    public GameView(Game backend){
        this.backend = backend;

        // Initialize background
        this.background = new ImageIcon("resources/GhostBusterBackground (1400 x 880 px).png").getImage();

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

        createBufferStrategy(2);
    }

    // Paint method manages buffer
    public void paint(Graphics g){
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    // myPaint holds all drawing logic
    public void myPaint(Graphics g) {
        int state = backend.getState();

        if (state == Game.STATE_TITLE) {
            drawTitle(g);
        } else if (state == Game.STATE_INSTRUCTIONS) {
            drawInstructions(g);
        } else if (state == Game.STATE_GAME) {
            drawGame(g);
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

        Ghost[][] ghosts = backend.getGhosts();

        // Iterate through 2D Ghost array and print it out
        for (Ghost[] ghost_row: ghosts) {
            for (Ghost currGhost : ghost_row) {
                if (currGhost.isAlive()) {
                    Image ghostImage = ghostImages[currGhost.getColorIndex()];

                    // Draw ghost with it's x and y coor
                    g.drawImage(ghostImage, (int) currGhost.getX(), (int) currGhost.getY(), this);
                }
            }
        }
    }
    public void drawEnd(Graphics g){
        //
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}
