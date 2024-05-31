package UI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import Model.Game;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Model.Directions;

/**
 * Represents the game panel for displaying and interacting with the game.
 */
public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Game game;
    private boolean isPaused;
    private JButton pauseButton;
    private JButton menuButton;
    private Color snakeColor = new Color(0, 255, 0);
    private Color backgroundColor = Color.BLACK;
    private MainFrame mainFrame;

    /**
     * Constructs a GamePanel instance with a specified difficulty and main frame.
     *
     * @param difficulty the difficulty level of the game
     * @param mainFrame the main frame of the game
     */
    public GamePanel(String difficulty, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1000, 800));
        this.setBackground(backgroundColor);

        game = new Game(difficulty);
        isPaused = false;

        this.setLayout(null);
        pauseButton = new JButton("Pause");
        pauseButton.setBounds(750, 10, 100, 40);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePause();
            }
        });
        this.add(pauseButton);

        menuButton = new JButton("Menu");
        menuButton.setBounds(860, 10, 100, 40);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseGame();
                mainFrame.backToMenu();
            }
        });
        this.add(menuButton);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (game.isGameOver() && e.getKeyCode() == KeyEvent.VK_SPACE) {
                    game.reset();
                } else if (!isPaused) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            game.getSnake().setDirection(Directions.UP);
                            break;
                        case KeyEvent.VK_DOWN:
                            game.getSnake().setDirection(Directions.DOWN);
                            break;
                        case KeyEvent.VK_LEFT:
                            game.getSnake().setDirection(Directions.LEFT);
                            break;
                        case KeyEvent.VK_RIGHT:
                            game.getSnake().setDirection(Directions.RIGHT);
                            break;
                    }
                }
            }
        });

        timer = new Timer(100, this);
        timer.start();
    }

    /**
     * Constructs a GamePanel instance with a specified difficulty, main frame, and map file path.
     *
     * @param difficulty the difficulty level of the game
     * @param mainFrame the main frame of the game
     * @param mapFilePath the file path of the map
     */
    public GamePanel(String difficulty, MainFrame mainFrame, String mapFilePath) {
        this(difficulty, mainFrame);
        game = new Game(difficulty, mapFilePath);
    }

    public Game getGame() {
        return game;
    }

    /**
     * Resets the game and panel state.
     */
    public void resetGame() {
        game.reset();
        isPaused = false;
        pauseButton.setText("Pause");
        setBackground(backgroundColor);
    }

    /**
     * Toggles the pause state of the game.
     */
    private void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            pauseButton.setText("Resume");
        } else {
            timer.start();
            pauseButton.setText("Pause");
        }
        requestFocusInWindow();
    }

    /**
     * Pauses the game.
     */
    private void pauseGame() {
        isPaused = true;
        timer.stop();
        pauseButton.setText("Resume");
        requestFocusInWindow();
    }

    /**
     * Sets the color of the snake.
     *
     * @param color the color to set
     */
    public void setSnakeColor(Color color) {
        this.snakeColor = color;
        repaint();
    }

    /**
     * Sets the background color of the panel.
     *
     * @param color the color to set
     */
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        setBackground(color);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getSnake().draw(g, snakeColor);
        game.drawPowerUps(g);
        game.drawFruits(g); // Draw all fruits
        game.drawObstacles(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        String scoreText = "Score: " + game.getScore();
        int x = (getWidth() - metrics.stringWidth(scoreText)) / 2;
        g.drawString(scoreText, x, 20);

        String highScoreText = "High Score: " + game.getHighScore(); // Display high score
        int y = 40;
        g.drawString(highScoreText, x, y);

        if (game.isGameOver()) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            metrics = g.getFontMetrics(g.getFont());
            String gameOverText = "Game Over! Press Space to Restart";
            x = (getWidth() - metrics.stringWidth(gameOverText)) / 2;
            g.drawString(gameOverText, x, 300);
        }

        if (isPaused) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            metrics = g.getFontMetrics(g.getFont());
            String pausedText = "Paused";
            x = (getWidth() - metrics.stringWidth(pausedText)) / 2;
            g.drawString(pausedText, x, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isPaused && !game.isGameOver()) {
            game.update();
        }
        repaint();
    }
}
