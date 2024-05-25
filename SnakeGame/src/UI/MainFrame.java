package UI;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.io.File;
import javax.swing.JFileChooser;
import Model.Game;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;
    private MapEditorPanel mapEditorPanel;
    private int score;

    public MainFrame() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        mainMenuPanel = new MainMenuPanel(this);
        mapEditorPanel = new MapEditorPanel(this);

        this.add(mainMenuPanel, "MainMenu");
        this.add(mapEditorPanel, "MapEditor");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setVisible(true);

        cardLayout.show(this.getContentPane(), "MainMenu");
        score = 0;
    }

    public void startGame(String difficulty) {
        if (gamePanel != null) {
            this.remove(gamePanel);
        }
        gamePanel = new GamePanel(difficulty, this);
        this.add(gamePanel, "Game");
        gamePanel.getGame().setScore(score); // Set score when starting game
        cardLayout.show(this.getContentPane(), "Game");
        gamePanel.requestFocusInWindow();
    }

    public void startGameWithMap(String mapFilePath) {
        if (gamePanel != null) {
            this.remove(gamePanel);
        }
        gamePanel = new GamePanel("custom", this, mapFilePath);
        this.add(gamePanel, "Game");
        gamePanel.getGame().setScore(score); // Set score when starting game
        cardLayout.show(this.getContentPane(), "Game");
        gamePanel.requestFocusInWindow();
    }

    public void openMapEditor() {
        cardLayout.show(this.getContentPane(), "MapEditor");
    }

    public void loadMapAndPlay() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Map");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/maps"));
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            startGameWithMap(file.getAbsolutePath());
        }
    }

    public void backToMenu() {
        if (gamePanel != null) {
            score = gamePanel.getGame().getScore(); // Save score before returning to menu
        }
        cardLayout.show(this.getContentPane(), "MainMenu");
    }

    public Game getGame() {
        if (gamePanel != null) {
            return gamePanel.getGame();
        }
        return null;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
