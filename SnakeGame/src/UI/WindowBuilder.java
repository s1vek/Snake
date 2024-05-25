package UI;

import javax.swing.JFrame;

public class WindowBuilder {
    private JFrame frame;
    private GamePanel gamePanel;

    public WindowBuilder() {
        frame = new JFrame("Snake Game");
        gamePanel = new GamePanel("easy", new MainFrame());
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new WindowBuilder();
    }
}
