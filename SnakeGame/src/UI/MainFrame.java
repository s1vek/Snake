package UI;

import Model.Game;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * Main frame of the application containing different panels for the game.
 */
public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private MainMenuPanel mainMenuPanel;
    private DifficultySelectionPanel difficultySelectionPanel;
    private GamePanel gamePanel;
    private MapEditorPanel mapEditorPanel;
    private int score;

    /**
     * Constructs the main frame and initializes the panels.
     */
    public MainFrame() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        mainMenuPanel = new MainMenuPanel(this);
        difficultySelectionPanel = new DifficultySelectionPanel(this);
        mapEditorPanel = new MapEditorPanel(this);

        this.add(mainMenuPanel, "MainMenu");
        this.add(difficultySelectionPanel, "DifficultySelection");
        this.add(mapEditorPanel, "MapEditor");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setVisible(true);

        cardLayout.show(this.getContentPane(), "MainMenu");
        score = 0;
    }

    /**
     * Starts the game with the specified difficulty.
     * @param difficulty the difficulty level to start the game with
     */
    public void startGame(String difficulty) {
        if (gamePanel != null) {
            this.remove(gamePanel);
        }
        gamePanel = new GamePanel(difficulty, this);
        this.add(gamePanel, "Game");
        gamePanel.getGame().setScore(score);
        cardLayout.show(this.getContentPane(), "Game");
        gamePanel.requestFocusInWindow();
    }

    /**
     * Shows the difficulty selection panel.
     */
    public void showDifficultySelection() {
        cardLayout.show(this.getContentPane(), "DifficultySelection");
    }

    /**
     * Opens the map editor panel.
     */
    public void openMapEditor() {
        cardLayout.show(this.getContentPane(), "MapEditor");
    }

    /**
     * Loads a map from a file and starts the game with it.
     */
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

    /**
     * Starts the game with a custom map.
     * @param mapFilePath the file path of the custom map
     */
    public void startGameWithMap(String mapFilePath) {
        if (gamePanel != null) {
            this.remove(gamePanel);
        }
        gamePanel = new GamePanel("custom", this, mapFilePath);
        this.add(gamePanel, "Game");
        gamePanel.getGame().setScore(score);
        cardLayout.show(this.getContentPane(), "Game");
        gamePanel.requestFocusInWindow();
    }

    /**
     * Returns to the main menu panel.
     */
    public void backToMenu() {
        if (gamePanel != null) {
            score = gamePanel.getGame().getScore();
        }
        cardLayout.show(this.getContentPane(), "MainMenu");
    }

    /**
     * Gets the current game instance.
     * @return the current game instance, or null if no game is running
     */
    public Game getGame() {
        if (gamePanel != null) {
            return gamePanel.getGame();
        }
        return null;
    }

    /**
     * Gets the current game panel.
     * @return the current game panel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}