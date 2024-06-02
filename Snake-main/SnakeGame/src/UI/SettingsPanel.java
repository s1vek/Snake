package UI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JColorChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;

public class SettingsPanel extends JPanel {

    private MainFrame mainFrame;
    private Settings settings;
    private static final int NUM_SQUARES = 100;
    private Random random;

    public SettingsPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.settings = new Settings();
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        random = new Random();

        JLabel titleLabel = new JLabel("Settings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(250, 100, 500, 60);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);

        JButton snakeColorButton = createStyledButton("Change Snake Color", 300);
        snakeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Choose Snake Color", settings.getSnakeColor());
                if (newColor != null) {
                    settings.setSnakeColor(newColor);
                    mainFrame.updateGamePanelColors();
                }
            }
        });
        this.add(snakeColorButton);

        JButton backgroundColorButton = createStyledButton("Change Background Color", 400);
        backgroundColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Choose Background Color", settings.getBackgroundColor());
                if (newColor != null) {
                    settings.setBackgroundColor(newColor);
                    mainFrame.updateGamePanelColors();
                }
            }
        });
        this.add(backgroundColorButton);

        JButton backButton = createStyledButton("Back to Menu", 500);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.backToMenu();
            }
        });
        this.add(backButton);
    }

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
