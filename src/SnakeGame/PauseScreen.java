package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.io.File;


// Die Klasse PauseScreen erweitert JPanel und repräsentiert den Pausebildschirm des Spiels.
public class PauseScreen extends JPanel {
    private final SnakeGame game;      // Referenz auf das SnakeGame-Objekt
    private JButton saveButton;        // Button zum Speichern des Spielstands
    private JButton exitButton;        // Button zum Beenden des Spiels
    private JLabel scoreLabel;         // Anzeige für die aktuelle Punktzahl

    // Konstruktor für den Pausebildschirm
    public PauseScreen(SnakeGame game) {
        this.game = game;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        add(Box.createRigidArea(new Dimension(0, 250)));

        scoreLabel = new JLabel("Punkte: 0");
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 50));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scoreLabel);

        add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);

        // Initialisierung des Speichern-Buttons
        saveButton = new JButton("Speichern");
        saveButton.setPreferredSize(new Dimension(150, 50));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);



        // Initialisierung des Fortsetzen-Buttons
        JButton resumeButton = new JButton("Fortsetzen");
        resumeButton.setPreferredSize(new Dimension(150, 50));
        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resumeButton.addActionListener(e -> {
            game.togglePauseScreen();
            setVisible(false);
        });

        // Initialisierung des Neues Spiel-Buttons
        JButton newGameButton = new JButton("Neues Spiel");
        newGameButton.setPreferredSize(new Dimension(150, 50));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(e -> {
            game.togglePauseScreen();
            setVisible(false);
            game.dispose();
            game.startNewGame();
        });

        // Initialisierung des Beenden-Buttons
        exitButton = new JButton("Beenden");
        exitButton.setPreferredSize(new Dimension(150, 50));
        exitButton.setForeground(Color.RED);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));

        add(buttonPanel);

        buttonPanel.add(resumeButton);

        buttonPanel.add(newGameButton);

        buttonPanel.add(saveButton);

        buttonPanel.add(exitButton);

        add(buttonPanel);

        add(Box.createRigidArea(new Dimension(100, 160)));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JLabel creatorLabel = new JLabel("®Metzger Game Studios");
        creatorLabel.setFont(new Font("Serif", Font.BOLD, 12));
        creatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(creatorLabel);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel);
        

        setBackground(new Color(169, 169, 169, 150));
    }

    // Gibt den Speichern-Button zurück
    public JButton getSaveButton() {
        return saveButton;
    }

    // Aktualisiert die Anzeige für die Punktzahl
    public void updateScoreLabel(int score) {
        scoreLabel.setText("Punkte: " + score);
    }
}
