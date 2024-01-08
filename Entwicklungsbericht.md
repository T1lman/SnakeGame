# Snake Game - Entwicklungsbericht

Die Entwicklung des SnakeGame-Projekts in Java mit der Swing-Bibliothek und Awt-Bibliothek stellte einige Herausforderungen dar, die erfolgreich gemeistert wurden. Im Folgenden sind einige der Schlüsselprobleme und die Lösungen dokumentiert:

## 1. Steuerung und Bewegung der Schlange

#### Problem:
Die korrekte Steuerung und Bewegung der Schlange in den vier Richtungen erforderte eine sorgfältige Handhabung von Benutzereingaben.

#### Lösung:
Implementierung einer `KeyListener`-Schnittstelle für die Verarbeitung von Tastatureingaben. Die `handleKeyPress`-Methode wurde entwickelt, um die Richtung der Schlange basierend auf den Tastenanschlägen zu aktualisieren. Auch die Vermeidung von 180-Grad-Wenden wurde berücksichtigt.

## 2. Kollisionserkennung

#### Problem:
Die Erkennung von Kollisionen der Schlange mit sich selbst, der entscheidende Unterschied zwischen Leben und Tod im Spiel Snake.

#### Lösung:
Eine `checkCollisions`-Methode wurde implementiert, um die Position der Schlange zu überprüfen und das Spiel entsprechend zu beenden, wenn Kollisionen auftreten. Dafür wurde die Positionen der Punkte von Kopf und Körper der Schlange verglichen.

## 3. Darstellung und Aktualisierung

#### Problem:
Die flüssige Darstellung des Spiels erforderte eine effiziente Aktualisierung der Grafiken.

#### Lösung:
Um diese Herausforderung zu meistern wurde eine individuelle `paint` Methode integriert welche jeglichen Aufgaben des reinen Spielfeld übernimmt (`StartScreen` sowie `PauseScreen` konnten auf eine solche verzichten, da ihre Komplexität sie nicht erforderten). Die Implementierung von Double Buffering mit den Elementen `bufferImage` und `bufferGraphics` ermöglichte eine reibungslose Aktualisierung des Spielfelds, indem Grafiken zunächst in einem Zwischenspeicher gezeichnet und dann auf das eigentliche Grafikobjekt übertragen wurden.

## 4. Überlagerung von Bildschirmen

#### Problem:
Die nahtlose Überlagerung von Start- und Pausebildschirmen über das Spielfeld erforderte spezielle Swing-Komponenten.

#### Lösung:
Die Verwendung von `JLayeredPane` ermöglichte die einfache Verwaltung von Überlagerungen. Insbesondere der `PauseScreen` wurde als Pop-up-Layer hinzugefügt, um das Spiel während der Pause zu überlagern. Der Startscreen wurde als eigenständiger Launcher implementiert wie es be ähnlichen Spielen üblich ist. Dementsprechend wurde ihm ein eigener `JFrame` zugewiesen.

## 5. Speichern und Laden von Spielständen

#### Problem:
Die Implementierung einer zuverlässigen Speicher- und Ladefunktion (Persistenz).

#### Lösung:
Die Verwendung von `ObjectOutputStream` und `ObjectInputStream` ermöglichte das Speichern und Laden von Spielständen in Dateien. Dies ermöglicht dem Spieler, das Spiel jederzeit zu speichern und später fortzusetzen. Dabei stehen dem Spieler Ort und Dateityp frei zur Wahl.

## 6. Essensgenerierung auf freien Flächen

#### Problem:
Die zufällige Generierung von Essen auf dem Spielfeld musste sicherstellen, dass das Essen nicht auf der Schlange erschien.

#### Lösung:
Die Methode `spawnFood` verwendet eine Schleife, um zufällige Koordinaten zu generieren, und überprüft, ob diese auf der Schlange liegen. Wenn ja, wird die Generierung wiederholt, bis eine freie Position gefunden wird.

## 7. Bewegung des Schlangenkörpers

#### Problem:
Die Bewegung des Schlangenkörpers musste reibungslos erfolgen, wobei sich jedes Körperteil der Schlange an die vorherige Position des vorherigen Teils anfügt.

#### Lösung:
Die Methode `moveSnake` fügt ein neues Kopfelement zur Schlange hinzu und entfernt gleichzeitig das letzte Schwanzelement. Dadurch entsteht der Eindruck, dass sich die Schlange fortbewegt. Wenn die Schlange ein Stück Essen frisst, wird der Schwanz nicht entfernt, was zu einer Verlängerung führt.

## 8. Spielpause und Fortsetzung

#### Problem:
Die Pause-Funktion und das Fortsetzen des Spiels erforderten die nahtlose Unterbrechung und Wiederaufnahme des Spielzyklus.

#### Lösung:
Die Methode `togglePauseScreen` setzt das Flag für den Spielstatus und zeigt den Pausebildschirm an oder versteckt ihn entsprechend. Dies ermöglicht es dem Spieler, das Spiel jederzeit zu pausieren und fortzusetzen.

## 9. Verbesserung der Benutzeroberfläche

#### Problem:
Die Benutzeroberfläche sollte klar und ansprechend gestaltet sein, um eine positive Spielerfahrung zu gewährleisten.

#### Lösung:
Durch die Verwendung von Swing-Elementen wurden Start- und Pausebildschirme mit intuitiven Schaltflächen erstellt. Die Anzeige der Punktzahl und das optische Feedback bei Kollisionen trugen ebenfalls zur Verbesserung der Benutzeroberfläche bei.

## 10. Spielergebnis-Dialog und Spielneustart

#### Problem:
Nach einem Spielende muss dem Spieler die Möglichkeit gegeben werden, ein neues Spiel zu starten oder das Spiel zu beenden.

#### Lösung:
Der `gameOver`-Dialog zeigt dem Spieler die Punktzahl an und bietet die Optionen "New Game" und "Exit". Je nach Auswahl startet entweder ein neues Spiel oder das Spiel wird beendet.


## Fazit:

Die erfolgreiche Überwindung dieser Herausforderungen führte zu einer funktionalen und unterhaltsamen Snake-Game-Anwendung. Durch die klare Strukturierung des Codes und die Verwendung von Swing-Elementen wurde eine benutzerfreundliche und ansprechende Spielerfahrung geschaffen. Der Entwicklungsprozess war aufschlussreich das Programmieren eines im Vergleich zu bekannten Spielen einfach wirkenden Snake enthielt viele Nuancen und Herausforderungen die nicht sofortig und perfekt gelöst werden konnten. Beispiel dafür ist die Benutzeroberfläche (Gui) welche viel Ausprobieren und Bastellei benötigte um zu ihrer jetzigen Form zu gelangen.