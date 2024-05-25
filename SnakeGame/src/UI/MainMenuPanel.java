package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;

public class MainMenuPanel extends JPanel {
    private MainFrame mainFrame;

    public MainMenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(350, 50, 300, 60);
        this.add(titleLabel);

        JButton startEasyButton = new JButton("Start Easy Level");
        startEasyButton.setFont(new Font("Arial", Font.BOLD, 20));
        startEasyButton.setBounds(375, 150, 250, 60);
        startEasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startGame("easy");
            }
        });
        this.add(startEasyButton);

        JButton startMediumButton = new JButton("Start Medium Level");
        startMediumButton.setFont(new Font("Arial", Font.BOLD, 20));
        startMediumButton.setBounds(375, 250, 250, 60);
        startMediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startGame("medium");
            }
        });
        this.add(startMediumButton);

        JButton startHardButton = new JButton("Start Hard Level");
        startHardButton.setFont(new Font("Arial", Font.BOLD, 20));
        startHardButton.setBounds(375, 350, 250, 60);
        startHardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startGame("hard");
            }
        });
        this.add(startHardButton);

        JButton mapEditorButton = new JButton("Map Editor");
        mapEditorButton.setFont(new Font("Arial", Font.BOLD, 20));
        mapEditorButton.setBounds(375, 450, 250, 60);
        mapEditorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.openMapEditor();
            }
        });
        this.add(mapEditorButton);

        JButton loadMapButton = new JButton("Load Map and Play");
        loadMapButton.setFont(new Font("Arial", Font.BOLD, 20));
        loadMapButton.setBounds(375, 550, 250, 60);
        loadMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.loadMapAndPlay();
            }
        });
        this.add(loadMapButton);
    }
}
