import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game backend;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;

    public GameView(Game backend){
        this.backend = backend;

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
            // TODO
        } else if (state == Game.STATE_END) {

        }
    }

    public void drawTitle(Graphics g) {

    }
    public void drawInstructions(Graphics g) {

    }
}
