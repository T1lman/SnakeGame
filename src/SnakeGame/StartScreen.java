package SnakeGame;


import javax.swing.*;
import java.awt.*;

// Die Klasse StartScreen erweitert JFrame und repräsentiert den Startbildschirm des Spiels.
public class StartScreen extends JFrame {
    private final SnakeGame game;      // Referenz auf das SnakeGame-Objekt
    public JButton importButton;       // Button zum Importieren des Spielstands
    public JButton startButton;         // Button zum Starten des Spiels
    public JButton exitButton;          // Button zum Beenden des Spiels

    // Konstruktor für den Startbildschirm
    public StartScreen(SnakeGame game) {
        this.game = game;

        setTitle("Snake Launcher");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("src/imgs/icon_30.png").getImage());

        setLayout(new BorderLayout());

        // Füge ein neues JLabel für den Titel "Snake Launcher" hinzu
        JLabel titleLabel = new JLabel("Snake Launcher");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);





        JLabel creatorLabel = new JLabel("®Metzger Game Studios");
        creatorLabel.setFont(new Font("Serif", Font.BOLD, 12));
        creatorLabel.setHorizontalAlignment(JLabel.CENTER);
        add(creatorLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(150, 0, 100, 0)); // Füge einen leeren Rand für Abstand hinzu

        // Initialisierung des Start-Buttons
        startButton = new JButton("Spiel starten");
        startButton.setPreferredSize(new Dimension(150, 50));
        startButton.addActionListener(e -> {
            game.startGame();
            dispose();
        });

        // Initialisierung des Importieren-Buttons
        importButton = new JButton("Importieren");
        importButton.setPreferredSize(new Dimension(150, 50));
        importButton.addActionListener(e -> {
            dispose();
            game.loadGameState(null);
            game.paused = true;
        });

        // Initialisierung des Beenden-Buttons
        exitButton = new JButton("Beenden");
        exitButton.setPreferredSize(new Dimension(150, 50));
        exitButton.setForeground(Color.RED);
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(importButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    // Gibt den Importieren-Button zurück
    public JButton getImportButton() {
        return importButton;
    }
}
