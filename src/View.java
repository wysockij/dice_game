import javax.swing.*;

public abstract class View extends JFrame {
    private final int width = 1280;
    private final int height = 720;
    private final JFrame frame;

    public View() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(width, height);
    }

    public JFrame getFrame() {
        return frame;
    }

}
