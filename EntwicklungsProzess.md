# Snake Game - Entwicklungsbericht

Die Entwicklung des SnakeGame-Projekts in Java mit der Swing-Bibliothek stellte einige Herausforderungen dar, die erfolgreich gemeistert wurden. Im Folgenden sind einige der Schlüsselprobleme und die Lösungen dokumentiert:

## 1. Steuerung und Bewegung der Schlange

### Problem:
Die korrekte Steuerung und Bewegung der Schlange in den vier Richtungen erforderte eine sorgfältige Handhabung von Benutzereingaben.

### Lösung:
Implementierung einer `KeyListener`-Schnittstelle für die Verarbeitung von Tastatureingaben. Die `handleKeyPress`-Methode wurde entwickelt, um die Richtung der Schlange basierend auf den Tastenanschlägen zu aktualisieren. Auch die Vermeidung von 180-Grad-Wenden wurde berücksichtigt.

## 2. Kollisionserkennung

### Problem:
Die Erkennung von Kollisionen der Schlange mit sich selbst oder den Spielfeldrändern war entscheidend für das Spiel.

### Lösung:
Eine `checkCollisions`-Methode wurde implementiert, um die Position der Schlange zu überprüfen und das Spiel entsprechend zu beenden, wenn Kollisionen auftreten. Dies schließt sowohl Selbstkollisionen als auch Kollisionen mit den Spielfeldrändern ein.

## 3. Darstellung und Aktualisierung

### Problem:
Die flüssige Darstellung des Spiels erforderte eine effiziente Aktualisierung der Grafiken.

### Lösung:
Die Implementierung von Double Buffering mit den Elementen `bufferImage` und `bufferGraphics` ermöglichte eine reibungslose Aktualisierung des Spielfelds, indem Grafiken zunächst in einem Zwischenspeicher gezeichnet und dann auf das eigentliche Grafikobjekt übertragen wurden.

## 4. Überlagerung von Bildschirmen

### Problem:
Die nahtlose Überlagerung von Start- und Pausebildschirmen über das Spielfeld erforderte spezielle Swing-Komponenten.

### Lösung:
Die Verwendung von `JLayeredPane` ermöglichte die einfache Verwaltung von Überlagerungen. Insbesondere der `PauseScreen` wurde als Pop-up-Layer hinzugefügt, um das Spiel während der Pause zu überlagern.

## 5. Speichern und Laden von Spielständen

### Problem:
Die Implementierung einer zuverlässigen Speicher- und Ladefunktion für Spielstände war komplex.

### Lösung:
Die Verwendung von `ObjectOutputStream` und `ObjectInputStream` ermöglichte das Speichern und Laden von Spielständen in Dateien. Dies ermöglicht dem Spieler, das Spiel jederzeit zu speichern und später fortzusetzen.

## Fazit:

Die erfolgreiche Überwindung dieser Herausforderungen führte zu einer funktionalen und unterhaltsamen Snake-Game-Anwendung. Durch die klare Strukturierung des Codes und die Verwendung von Swing-Elementen wurde eine benutzerfreundliche und ansprechende Spielerfahrung geschaffen. Der Entwicklungsprozess betonte die Bedeutung von gründlicher Planung, umfangreicher Tests und einer durchdachten Benutzeroberfläche für die Qualität einer Spieleanwendung.