# Snake Game - Code-Dokumentation

Die Snake-Game-Anwendung ist in Java implementiert und verwendet die Swing-Bibliothek für die grafische Benutzeroberfläche. Die Anwendung besteht aus drei Hauptklassen: `SnakeGame`, `PauseScreen`, und `StartScreen`. Hier wird die Struktur und Funktionalität dieser Klassen dokumentiert.

## SnakeGame-Klasse

Die `SnakeGame`-Klasse ist die Hauptklasse, die das Spiel steuert und das Hauptfenster bereitstellt. Hier sind die wichtigsten Elemente und Funktionen dieser Klasse:

### Elemente:

- `TILE_SIZE` und `GRID_SIZE`: Konstanten für die Größe der Kacheln und des Spielfelds.
- `Direction`: Eine Aufzählung für die Richtungen der Schlange.
- `direction`: Die aktuelle Richtung der Schlange.
- `snake`: Eine Liste von `Point`-Objekten, die die Koordinaten der Schlange repräsentieren.
- `food`: Ein `Point`-Objekt, das die Koordinaten des Futters repräsentiert.
- `score`: Die Punktzahl des Spielers.
- `paused`: Ein Flag, um das Spiel zu pausieren.
- `gameStarted`: Ein Flag, um zu überprüfen, ob das Spiel gestartet ist.
- `bufferImage` und `bufferGraphics`: Elemente für das Double Buffering der Grafik.
- `layeredPane`: Ein `JLayeredPane` für Überlagerungen wie den Pausebildschirm.
- `pauseScreen` und `startScreen`: Instanzen der Klassen `PauseScreen` und `StartScreen`.

### Methoden:

- `initializeFrame()`: Initialisiert das Hauptfenster.
- `initializeGame()`: Initialisiert das Spiel und zeigt den Startbildschirm an.
- `initializePauseScreen()`: Initialisiert den Pausebildschirm.
- `startGame()`: Startet das Spiel und zeigt das Spielfeld an.
- `saveGameState(File file)`: Speichert den aktuellen Spielstand in einer Datei.
- `loadGameState(File file)`: Lädt den gespeicherten Spielstand aus einer Datei.
- `gameLoop()`: Die Hauptspielschleife, die die Schlange bewegt und Kollisionen überprüft.
- `moveSnake()`: Bewegt die Schlange entsprechend der aktuellen Richtung.
- `showPauseScreen()`: Zeigt den Pausebildschirm an.
- `checkCollisions()`: Überprüft Kollisionen der Schlange.
- `spawnFood()`: Platziert zufällig neues Futter auf dem Spielfeld.
- `isFoodOnSnake(int x, int y)`: Überprüft, ob das Futter auf der Schlange liegt.
- `handleKeyPress(int keyCode)`: Verarbeitet Tastenanschläge des Spielers.
- `togglePauseScreen()`: Zeigt oder versteckt den Pausebildschirm.
- `gameOver()`: Zeigt das Game Over-Dialogfeld an.
- `paint(Graphics g)`: Zeichnet das Spielfeld und die Schlange.
- `initializeSaveButton()`: Initialisiert den Speichern-Button.
- `initializeImportButton()`: Initialisiert den Import-Button.
- `startNewGame()`: Startet ein neues Spiel.

## PauseScreen-Klasse

Die `PauseScreen`-Klasse repräsentiert den Pausebildschirm während des Spiels. Hier sind die wichtigsten Elemente und Funktionen dieser Klasse:

### Elemente:

- `game`: Eine Referenz auf die `SnakeGame`-Instanz.
- `saveButton`, `exitButton`: `JButton`-Objekte für Aktionen wie Speichern und Beenden.
- `scoreLabel`: Ein `JLabel` zur Anzeige der aktuellen Punktzahl.

### Methoden:

- Konstruktor: Initialisiert die Elemente und erstellt die Benutzeroberfläche des Pausebildschirms.
- `getSaveButton()`: Gibt den Speichern-Button zurück.
- `updateScoreLabel(int score)`: Aktualisiert die Anzeige der Punktzahl.

## StartScreen-Klasse

Die `StartScreen`-Klasse repräsentiert den Startbildschirm vor dem Spiel. Hier sind die wichtigsten Elemente und Funktionen dieser Klasse:

### Elemente:

- `game`: Eine Referenz auf die `SnakeGame`-Instanz.
- `importButton`, `startButton`, `exitButton`: `JButton`-Objekte für Aktionen wie Importieren, Starten und Beenden.

### Methoden:

- Konstruktor: Initialisiert die Elemente und erstellt die Benutzeroberfläche des Startbildschirms.
- `getImportButton()`: Gibt den Import-Button zurück.

Diese Struktur ermöglicht eine klare Trennung der Verantwortlichkeiten und erleichtert die Wartung und Erweiterung des Codes.