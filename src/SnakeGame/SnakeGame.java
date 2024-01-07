package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

// Die Klasse SnakeGame erweitert JFrame und stellt das Hauptfenster für das Spiel dar.
public class SnakeGame extends JFrame {
    // Konstanten für die Größe der Kacheln und des Spielfelds
    private static final int TILE_SIZE = 25;
    private static final int GRID_SIZE = 30;

    // Aufzählung für die Richtungen der Schlange
    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction direction;         // Aktuelle Richtung der Schlange
    private ArrayList<Point> snake;     // Liste der Schlangen-Koordinaten
    private Point food;                 // Koordinaten des Futters
    private int score;                   // Punktzahl des Spielers
    boolean paused;                      // Flag, um das Spiel zu pausieren
    private boolean gameStarted;         // Flag, um zu überprüfen, ob das Spiel gestartet ist
    private Image bufferImage;           // Double Buffering für flüssige Darstellung
    private Graphics bufferGraphics;     // Grafikobjekt für den Double Buffer
    private JLayeredPane layeredPane;    // Schicht für Überlagerungen (z.B., Pausebildschirm)
    private PauseScreen pauseScreen;     // Pausebildschirm für das Spiel
    private StartScreen startScreen;     // Startbildschirm für das Spiel

    // Konstruktor für das SnakeGame
    public SnakeGame() {
        initializeFrame();       // Initialisiert das Hauptfenster
        initializeGame();        // Initialisiert das Spiel

        layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        pauseScreen = new PauseScreen(this);
        initializePauseScreen();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });

        setFocusable(true);

        Timer timer = new Timer(100, e -> gameLoop());
        timer.start();

        repaint();
    }

    // Initialisiert das Hauptfenster
    private void initializeFrame() {
        setTitle("Snake Game");
        setSize((GRID_SIZE * TILE_SIZE), (GRID_SIZE * TILE_SIZE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setIconImage(new ImageIcon("src/imgs/icon_30.png").getImage());

        direction = Direction.RIGHT;
        snake = new ArrayList<>();
        snake.add(new Point(5, 5)); // Startposition der Schlange

        spawnFood();

        score = 0;
        paused = false;
        gameStarted = false;
    }

    // Initialisiert das Spiel
    private void initializeGame() {
        startScreen = new StartScreen(this);
        startScreen.setVisible(true);
    }

    // Initialisiert den Pausebildschirm
    private void initializePauseScreen() {
        layeredPane.add(pauseScreen, JLayeredPane.POPUP_LAYER);
        pauseScreen.setBounds(0, 0, getWidth(), getHeight());
    }

    // Startet das Spiel
    void startGame() {
        gameStarted = true;

        SwingUtilities.invokeLater(() -> {
            startScreen.setVisible(false);
            setVisible(true);
        });
    }

    // Speichert den aktuellen Spielstand in einer Datei
    void saveGameState(File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(snake);
            outputStream.writeObject(food);
            outputStream.writeObject(score);
            outputStream.writeObject(direction);
            outputStream.writeObject(paused);
            outputStream.writeObject(gameStarted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lädt den gespeicherten Spielstand aus einer Datei
    void loadGameState(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            snake = (ArrayList<Point>) inputStream.readObject();
            food = (Point) inputStream.readObject();
            score = (int) inputStream.readObject();
            direction = (Direction) inputStream.readObject();
            paused = (boolean) inputStream.readObject();
            gameStarted = (boolean) inputStream.readObject();

            showPauseScreen();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Hauptspielschleife
    private void gameLoop() {
        if (gameStarted && !paused) {
            moveSnake();
            checkCollisions();
            repaint();
        }
    }

    // Bewegt die Schlange entsprechend der aktuellen Richtung
    private void moveSnake() {
        Point head = snake.get(0);
        Point newHead;

        switch (direction) {
            case UP:
                newHead = new Point(head.x, (head.y - 1 + GRID_SIZE) % GRID_SIZE);
                break;
            case DOWN:
                newHead = new Point(head.x, (head.y + 1) % GRID_SIZE);
                break;
            case LEFT:
                newHead = new Point((head.x - 1 + GRID_SIZE) % GRID_SIZE, head.y);
                break;
            case RIGHT:
                newHead = new Point((head.x + 1) % GRID_SIZE, head.y);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

        snake.add(0, newHead);

        if (newHead.equals(food)) {
            spawnFood();
            score++;
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    // Zeigt den Pausebildschirm an
    void showPauseScreen() {
        pauseScreen.setBounds(0, 0, getWidth(), getHeight());
        pauseScreen.updateScoreLabel(score);
        pauseScreen.setVisible(true);

        layeredPane.moveToFront(pauseScreen);

        pauseScreen.paint(pauseScreen.getGraphics());
    }

    // Überprüft Kollisionen der Schlange
    private void checkCollisions() {
        Point head = snake.get(0);

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver();
                return;
            }
        }
    }

    // Platziert zufällig neues Futter auf dem Spielfeld
    private void spawnFood() {
        Random random = new Random();
        int x, y;

        do {
            x = random.nextInt(GRID_SIZE - 2) + 2;
            y = random.nextInt(GRID_SIZE - 2) + 2;
        } while (isFoodOnSnake(x, y));

        food = new Point(x, y);
    }

    // Überprüft, ob das Futter auf der Schlange liegt
    private boolean isFoodOnSnake(int x, int y) {
        for (Point point : snake) {
            if (point.x == x && point.y == y) {
                return true;
            }
        }
        return false;
    }

    // Verarbeitet Tastenanschläge des Spielers
    private void handleKeyPress(int keyCode) {
        if (!gameStarted) {
            if (keyCode == KeyEvent.VK_SPACE) {
                gameStarted = true;
                return;
            }
        }

        switch (keyCode) {
            case KeyEvent.VK_UP, KeyEvent.VK_W:
                if (direction != Direction.DOWN) {
                    direction = Direction.UP;
                }
                break;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S:
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                }
                break;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                }
                break;
            case KeyEvent.VK_SPACE:
                togglePauseScreen();
                break;
        }
    }

    // Zeigt oder versteckt den Pausebildschirm
    void togglePauseScreen() {
        paused = !paused;
        if (paused) {
            showPauseScreen();
        } else {
            pauseScreen.setVisible(false);
            repaint();
        }
    }

    // Zeigt das Game Over-Dialogfeld an
    private void gameOver() {
        pauseScreen.setVisible(false);
        // Setzt das Icon für das Game Over-Dialogfeld
        JFrame dummyFrame = new JFrame();
        dummyFrame.setIconImage(new ImageIcon("src/imgs/icon_30.png").getImage());
        Object[] options = {"Neues Spiel", "Beenden"};
        int result = JOptionPane.showOptionDialog(
                dummyFrame,
                "Game Over! Deine Punkte: " + score,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("src/imgs/icon_30.png"), // Setzt das Icon für das Dialogfeld
                options,
                options[0]);

        if (result == JOptionPane.YES_OPTION) {
            startNewGame();
            dispose();
        } else {
            System.exit(0);
        }
    }

    // Zeichnet das Spielfeld und die Schlange
    @Override
    public void paint(Graphics g) {
        if (bufferImage == null) {
            bufferImage = createImage(getWidth(), getHeight());
            bufferGraphics = bufferImage.getGraphics();
        }

        Graphics offScreenBuffer = bufferImage.getGraphics();
        offScreenBuffer.clearRect(0, 0, getWidth(), getHeight());

        // Zeichnet das Gittermuster des Spielfelds
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                offScreenBuffer.setColor((i + j) % 2 == 0 ? new Color(200, 200, 200) : new Color(150, 150, 150));
                offScreenBuffer.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // Zeichnet die Schlange in grüner Farbe
        offScreenBuffer.setColor(new Color(50, 200, 50));
        for (Point point : snake) {
            offScreenBuffer.fillRect(point.x * TILE_SIZE, point.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // Zeichnet das Futter in roter Farbe
        offScreenBuffer.setColor(new Color(200, 50, 50));
        offScreenBuffer.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Zeigt die Punktzahl an
        offScreenBuffer.setColor(Color.BLACK);
        Font font = new Font("Serif", Font.PLAIN, 15);
        offScreenBuffer.setFont(font);
        offScreenBuffer.drawString("Punkte: " + score, 5, 45);

        // Zeichnet das Double Buffer auf das eigentliche Grafikobjekt
        g.drawImage(bufferImage, 0, 0, this);

        offScreenBuffer.dispose();
    }

    // Hauptmethode zum Starten des Spiels
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame snakeGame = new SnakeGame();
            snakeGame.initializeSaveButton();
            snakeGame.initializeImportButton();
        });
    }

    // Initialisiert den Speichern-Button
    private void initializeSaveButton() {
        pauseScreen.getSaveButton().addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                saveGameState(file);
                JOptionPane.showMessageDialog(this, "Spielstand erfolgreich gespeichert!", "Spiel speichern", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // Initialisiert den Import-Button
    private void initializeImportButton() {
        startScreen.getImportButton().addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                paused=true;
                File file = fileChooser.getSelectedFile();
                loadGameState(file);
                startGame();
                JOptionPane.showMessageDialog(this, "Spielstand erfolgreich importiert!", "Spiel importieren", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
    }

    // Startet ein neues Spiel
    public void startNewGame() {
        snake.clear();
        snake.add(new Point(5, 5));
        spawnFood();
        score = 0;
        paused = false;
        gameStarted = false;
        startScreen.setVisible(true);
        repaint();
    }
}
