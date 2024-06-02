package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Model.Position;

/**
 * Panel for editing and saving custom maps.
 */
public class MapEditorPanel extends JPanel {
    private List<Position> obstacles;
    private JButton saveButton;
    private JButton menuButton;
    private MainFrame mainFrame;

    /**
     * Constructs a MapEditorPanel.
     * @param mainFrame the main frame of the application
     */
    public MapEditorPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1000, 800));
        this.setBackground(Color.BLACK);
        this.obstacles = new ArrayList<>();

        this.setLayout(null);

        JLabel titleLabel = new JLabel("Map Editor");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(400, 20, 200, 40);
        this.add(titleLabel);

        saveButton = new JButton("Save Map");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBounds(750, 10, 100, 40);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMap();
            }
        });
        this.add(saveButton);

        menuButton = new JButton("Menu");
        menuButton.setFont(new Font("Arial", Font.BOLD, 14));
        menuButton.setBounds(860, 10, 100, 40);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.backToMenu();
            }
        });
        this.add(menuButton);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / 10;
                int y = e.getY() / 10;
                Position pos = new Position(x, y);
                if (obstacles.contains(pos)) {
                    obstacles.remove(pos);
                } else {
                    obstacles.add(pos);
                }
                repaint();
            }
        });
    }

    /**
     * Saves the current map to a file.
     */
    private void saveMap() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Map");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/maps"));
        fileChooser.setSelectedFile(new File("map.txt"));
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                for (Position pos : obstacles) {
                    writer.write(pos.getX() + "," + pos.getY() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        for (Position pos : obstacles) {
            g.fillRect(pos.getX() * 10, pos.getY() * 10, 10, 10);
        }
    }
}