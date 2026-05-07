import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame {
    private Game backend;

    private Image[] ghostImages;

    // Constants
    public final int WINDOW_WIDTH = 1400;
    public final int WINDOW_HEIGHT = 880;

    private final int SCORE_X = 60;
    private final int SCORE_Y = 600;
    private final int FINAL_SCORE_X = 600;
    private final int FINAL_SCORE_Y = 700;
    private final int FONT_SIZE = 30;

    private final Image TITLE_IMAGE = new ImageIcon("resources/GhostBustersTitleScreen.png").getImage();
    private final Image END_IMAGE = new ImageIcon("resources/GhostBustersGameOver.png").getImage();
    private final Image INSTURCTIONS_IMAGE = new ImageIcon("resources/GhostBustersInstructions.png").getImage();
    private final Image GAME_IMAGE = new ImageIcon("resources/GameBackground2.png").getImage();

    public GameView(Game backend){
        this.backend = backend;

        // Initialize an array of different colored ghost images
        this.ghostImages = new Image[6];
        this.ghostImages[0] = new ImageIcon("resources/redGhost.png").getImage();
        this.ghostImages[1] = new ImageIcon("resources/orangeGhost.png").getImage();
        this.ghostImages[2] = new ImageIcon("resources/yellowGhost.png").getImage();
        this.ghostImages[3] = new ImageIcon("resources/greenGhost.png").getImage();
        this.ghostImages[4] = new ImageIcon("resources/blueGhost.png").getImage();
        this.ghostImages[5] = new ImageIcon("resources/purpleGhost.png").getImage();


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

    // MyPaint holds all drawing logic
    public void myPaint(Graphics g) {
        int state = backend.getState();

        // Case statement based on states
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
        // Display title image
        g.drawImage(TITLE_IMAGE, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public void drawInstructions(Graphics g) {
        // Display instructions
        g.drawImage(INSTURCTIONS_IMAGE, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public void drawGame(Graphics g){
        // Display background image
        g.drawImage(GAME_IMAGE, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        // Draw current time survived
        Font timeFont = new Font("Serif", Font.ITALIC, FONT_SIZE);
        g.setFont(timeFont);
        g.setColor(Color.RED);
        g.drawString("Score: " + backend.getScore(), SCORE_X,SCORE_Y);

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
                // Only draw the ghost if it is alive
                if (currGhost.isAlive()) {
                    Image ghostImage = ghostImages[currGhost.getColorIndex()];
                    // Draw ghost with it's x and y coordinate
                    g.drawImage(ghostImage, (int) currGhost.getX(), (int) currGhost.getY(), this);
                }
            }
        }
    }

    public void drawEnd(Graphics g){
        // Display game over image
        g.drawImage(END_IMAGE, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        // Draw final score
        Font timeFont = new Font("Serif", Font.ITALIC, FONT_SIZE);
        g.setFont(timeFont);
        g.setColor(Color.RED);
        g.drawString("FINAL SCORE: " + backend.getScore(), FINAL_SCORE_X,FINAL_SCORE_Y);
    }
}