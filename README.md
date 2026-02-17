# Rechtschreibtrainer

Ein Java-Swing-Lernprogramm zum Üben von Rechtschreibung und Vokabular mit Quiz- und Hangman-Modus.

## Features

- **Quiz** – Fragen aus einem Fragenpool beantworten
- **Hangman** – Antwort Buchstabe für Buchstabe erraten
- **Fragenpools verwalten** – Fragen hinzufügen, löschen, speichern
- **Mehrere Fragenpools** – beliebig viele Pools als `.txt`-Dateien

## Projektstruktur

```
├── controller/
│   ├── ActionCommands.java
│   ├── Controller.java
│   └── EventHandler.java
├── model/
│   ├── Frage.java
│   ├── Fragenpool.java
│   ├── FrageSaveLoad.java
│   ├── GameLogic.java
│   ├── QuizLogic.java
│   └── Statistik.java
├── view/
│   ├── GamePanel.java
│   ├── HangmanPanel.java
│   ├── MainPanel.java
│   ├── ManageQuestionPoolPanel.java
│   ├── ManageQuestionsPanel.java
│   ├── QuizPanel.java
│   ├── RSTBLFrame.java
│   ├── RSTBLMenu.java
│   └── StatisticPanel.java
└── questionPools/
    └── MeinPool.txt
```

## Starten

```bash
javac controller/*.java model/*.java view/*.java
java controller.Controller
```

Java 8 oder höher erforderlich. Keine externen Bibliotheken nötig.

## Fragenpool-Format

Fragenpools liegen als `.txt`-Dateien im Ordner `questionPools/`:

```
Titel des Pools
++++++++++++++++++++++
fragenGesamt#0
fragenRichtig#0
fragenFalsch#0
++++++++++++++++++++++
1#Frage?#Antwort
2#Nächste Frage?#Antwort
```

## Spielmodi

**Quiz** – Die Frage wird angezeigt, die Antwort wird freigetextet und geprüft.

**Hangman** – Die Frage wird angezeigt, die Antwort muss Buchstabe für Buchstabe erraten werden. Bei 7 Fehlern ist das Spiel vorbei.

## Architektur

Das Projekt folgt dem MVC-Pattern:

- **Model** – Spiellogik, Datenverwaltung, Speichern/Laden
- **View** – Swing-Oberfläche
- **Controller** – Verbindet Model und View über einen EventHandler
