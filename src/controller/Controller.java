package controller;

import model.FrageSaveLoad;
import model.Fragenpool;
import view.RSTBLFrame;
import view.QuizPanel;

import java.io.IOException;

public class Controller {
    private RSTBLFrame view;

    // Konstruktor
    public Controller() {
        // Frame erstellen
        this.view = new RSTBLFrame(null, null); // QuizLogic kann später hinzugefügt werden

        // EventHandler verbinden
        EventHandler handler = new EventHandler(this, view);
        view.setActionListener(handler);

        // Frame sichtbar machen
        view.setVisible(true);
    }

    // Hauptmethode
    public static void main(String[] args) {
        new Controller();
    }

    // Quiz mit ausgewähltem Fragenpool starten
    public void startQuiz(String selectedPool) {
        if (selectedPool == null || selectedPool.isEmpty()) {
            // Warnung, falls kein Pool ausgewählt
            javax.swing.JOptionPane.showMessageDialog(
                    view,
                    "Bitte wähle zuerst einen Fragenpool aus!",
                    "Kein Pool ausgewählt",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            // Fragenpool laden
            FrageSaveLoad loader = new FrageSaveLoad();
            String cleanFilename = selectedPool.replace(".txt", "");
            FrageSaveLoad.FragenpoolWithStat result = loader.loadFragenpool("questionPools", cleanFilename);
            Fragenpool pool = result.getPool();

            // QuizPanel erstellen und anzeigen
            QuizPanel quizPanel = new QuizPanel(null, pool);
            view.setQuizPanel(quizPanel);

        } catch (IOException e) {
            // Fehler anzeigen
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                    view,
                    "Fehler beim Laden des Fragepools: " + e.getMessage(),
                    "Fehler",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
