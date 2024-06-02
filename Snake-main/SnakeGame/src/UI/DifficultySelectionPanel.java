package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.util.Random;

/**
 * Panel for selecting the difficulty of the game.
 */
public class DifficultySelectionPanel extends JPanel {

    private MainFrame mainFrame;
    private Random random;

    /**
     * Constructs a DifficultySelectionPanel.
     * @param mainFrame the main frame of the application
     */
    public DifficultySelectionPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        random = new Random();

        JLabel titleLabel = new JLabel("Select Difficulty");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 100, 500, 60);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);

        JButton easyButton = createStyledButton("Easy", 300);
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startGame("easy");
            }
        });
        this.add(easyButton);

        JButton mediumButton = createStyledButton("Medium", 400);
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startGame("medium");
            }
        });
        this.add(mediumButton);

        JButton hardButton = createStyledButton("Hard", 500);
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startGame("hard");
            }
        });
        this.add(hardButton);

        JButton backButton = createStyledButton("Back to Menu", 600);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.backToMenu();
            }
        });
        this.add(backButton);
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

    private static final int squares = 100;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);

        // Draw random gray squares as background pattern
        for (int i = 0; i < squares; i++) {
            int x = random.nextInt(this.getWidth());
            int y = random.nextInt(this.getHeight());
            int size = random.nextInt(20) + 5;
            g.fillRect(x, y, size, size);
        }
    }
}
