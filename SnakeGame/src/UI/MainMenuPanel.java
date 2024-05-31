package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;

/**
 * Main menu panel for the game.
 */
public class MainMenuPanel extends JPanel {
    private MainFrame mainFrame;
    private static final int NUM_SQUARES = 100;
    private Random random;

    /**
     * Constructs a MainMenuPanel.
     * @param mainFrame the main frame of the application
     */
    public MainMenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        random = new Random();
        JLabel titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 100, 500, 60);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);

        JButton playButton = createStyledButton("Play", 300);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showDifficultySelection();
            }
        });
        this.add(playButton);

        JButton mapEditorButton = createStyledButton("Map Editor", 400);
        mapEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.openMapEditor();
            }
        });
        this.add(mapEditorButton);

        JButton loadMapButton = createStyledButton("Load Map and Play", 500);
        loadMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.loadMapAndPlay();
            }
        });
        this.add(loadMapButton);
    }

    /**
     * Creates a styled JButton with the specified text and vertical position.
     * @param text the text to display on the button
     * @param yPosition the vertical position of the button
     * @return the styled JButton
     */
    private JButton createStyledButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBounds(325, yPosition, 350, 80);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);

        // Draw random gray squares as background pattern
        for (int i = 0; i < NUM_SQUARES; i++) {
            int x = random.nextInt(this.getWidth());
            int y = random.nextInt(this.getHeight());
            int size = random.nextInt(20) + 5;
            g.fillRect(x, y, size, size);
        }
    }
}