package UI;

import java.awt.Color;
import java.io.*;

/**
 * Manages the settings for the game, including snake and background colors.
 */
public class Settings {

    private static final String SETTINGS_FILE = "settings.txt";

    private Color snakeColor;
    private Color backgroundColor;

    /**
     * Constructs a Settings object and loads settings from a file.
     */
    public Settings() {
        // Default colors
        snakeColor = new Color(0, 255, 0);
        backgroundColor = Color.BLACK;
        loadSettings();
    }

    /**
     * Gets the snake color.
     * @return the snake color
     */
    public Color getSnakeColor() {
        return snakeColor;
    }

    /**
     * Sets the snake color and saves the settings.
     * @param snakeColor the new snake color
     */
    public void setSnakeColor(Color snakeColor) {
        this.snakeColor = snakeColor;
        saveSettings();
    }

    /**
     * Gets the background color.
     * @return the background color
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the background color and saves the settings.
     * @param backgroundColor the new background color
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        saveSettings();
    }

    /**
     * Saves the settings to a file.
     */
    private void saveSettings() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SETTINGS_FILE))) {
            writer.println(snakeColor.getRGB());
            writer.println(backgroundColor.getRGB());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the settings from a file.
     */
    private void loadSettings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            snakeColor = new Color(Integer.parseInt(reader.readLine()));
            backgroundColor = new Color(Integer.parseInt(reader.readLine()));
        } catch (IOException | NumberFormatException e) {
            snakeColor = new Color(0, 255, 0);
            backgroundColor = Color.BLACK;
        }
    }
}
