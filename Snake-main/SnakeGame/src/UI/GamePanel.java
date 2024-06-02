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
    private Color snakeColor;
    private Color backgroundColor;
    private MainFrame mainFrame;
    private Settings settings;

    public GamePanel(String difficulty, MainFrame mainFrame, Settings settings) {
        this.mainFrame = mainFrame;
        this.settings = settings;
        this.snakeColor = settings.getSnakeColor();
        this.backgroundColor = settings.getBackgroundColor();
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

    public GamePanel(String difficulty, MainFrame mainFrame, String mapFilePath, Settings settings) {
        this(difficulty, mainFrame, settings);
        game = new Game(difficulty, mapFilePath);
    }

    public Game getGame() {
        return game;
    }

    public void resetGame() {
        game.reset();
        isPaused = false;
        pauseButton.setText("Pause");
        setBackground(settings.getBackgroundColor());
    }

    public void updateColors() {
        this.snakeColor = settings.getSnakeColor();
        this.backgroundColor = settings.getBackgroundColor();
        setBackground(backgroundColor);
        repaint();
    }

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

    private void pauseGame() {
        isPaused = true;
        timer.stop();
        pauseButton.setText("Resume");
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getSnake().draw(g, snakeColor);
        game.drawPowerUps(g);
        game.drawFruits(g);
        game.drawObstacles(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        String scoreText = "Score: " + game.getScore();
        int x = (getWidth() - metrics.stringWidth(scoreText)) / 2;
        g.drawString(scoreText, x, 20);

        String highScoreText = "High Score: " + game.getHighScore();
        int y = 40;
        g.drawString(highScoreText, x, y);

        String livesText = "Lives: " + game.getSnake().getLives();
        g.drawString(livesText, x, y + 20);

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
