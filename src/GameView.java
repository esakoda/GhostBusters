import javax.swing.*;

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
}
