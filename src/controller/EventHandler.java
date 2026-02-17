package controller;
import model.*;
import view.RSTBLFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EventHandler implements ActionListener {

    private RSTBLFrame view;
    private Controller controller;

    public EventHandler(Controller controller, RSTBLFrame view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ActionCommands ac = ActionCommands.valueOf(e.getActionCommand());

        switch (ac) {

            case quiz:
                System.out.println("Quiz");
                if(view.getSelectedFragePool() == null) {
                    JOptionPane.showMessageDialog(null, "Es wurde kein Fragepool gefunden, erstellen sie einen", "Warnung", JOptionPane.ERROR_MESSAGE);
                }
                String selectedPool = view.getSelectedFragePool();
                if (selectedPool != null && !selectedPool.isEmpty()) {
                    controller.ladeQuiz(selectedPool);
                    view.setQuizPanel();
                }
                break;

            case game:
                System.out.println("Game");
                if(view.getSelectedFragePool() == null) {
                    JOptionPane.showMessageDialog(null, "Es wurde kein Fragepool gefunden, erstellen sie einen", "Warnung", JOptionPane.ERROR_MESSAGE);
                }
                String selectedGamePool = view.getSelectedFragePool();
                if (selectedGamePool != null && !selectedGamePool.isEmpty()) {
                    controller.ladeGame(selectedGamePool, 7); // 7 Versuche
                    view.setGamePanel();
                }
                break;

            case statistic:
                System.out.println("Statistic");
                view.toggleEastPanel();
                break;

            case quizAnswer:
                String input = view.getUserQuizInput();
                QuizLogic quiz = controller.getQuizModel();
                Frage aktuelleFrage = quiz.getAktuelleFrage();

                // Statistik prüft Antwort automatisch
                boolean richtig = quiz.getPool().getStatistik().pruefeAntwort(aktuelleFrage, input);

                // Popup für richtig/falsch
                if (richtig) {
                    JOptionPane.showMessageDialog(
                            view,
                            "Richtig!",
                            "Antwort",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            view,
                            "Falsch!",
                            "Antwort",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

                // Nächste Frage
                quiz.naechsteFrage();
                view.naechsteQuizFrage();

                // Prüfen, ob Quiz beendet ist
                if (quiz.istBeendet()) {
                    Statistik stat = quiz.getPool().getStatistik();
                    int richtigCount = stat.getRichtigeFragen();
                    int gesamt = quiz.getGesamtFragen();

                    JOptionPane.showMessageDialog(
                            view,
                            "Quiz beendet! Du hast " + richtigCount + "/" + gesamt + " Fragen richtig beantwortet.",
                            "Ergebnis",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    // Zurück zum Hauptmenü
                    view.setMainPanel();
                }
                break;


            case gameAnswer:
                String buchstabe = view.getUserGameInput();

                if (buchstabe != null && !buchstabe.isEmpty()) {
                    boolean gefunden = controller.getGameModel().versucheBuchstabe(buchstabe);

                    view.updateGameDisplay(
                            controller.getGameModel().getBekanntesBuchstaben(),
                            controller.getGameModel().getVersucheBuchstaben(),
                            controller.getGameModel().getVersucheUebrig(),
                            controller.getGameModel().getAktuelleFrage()
                    );

                    view.clearGameInput();

                    // Prüfe auf Gewinn oder Verlust
                    if (controller.getGameModel().istGewonnen()) {
                        view.showGameMessage(
                                "Glückwunsch! Du hast das Wort erraten: " +
                                        controller.getGameModel().getAktuelleAntwort(),
                                "Gewonnen!",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        if (controller.getGameModel().hatNochFragen()) {
                            int option = JOptionPane.showConfirmDialog(
                                    view,
                                    "Möchtest du die nächste Frage spielen?",
                                    "Weiter?",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (option == JOptionPane.YES_OPTION) {
                                controller.getGameModel().naechsteFrage();
                                view.updateGameDisplay(
                                        controller.getGameModel().getBekanntesBuchstaben(),
                                        controller.getGameModel().getVersucheBuchstaben(),
                                        controller.getGameModel().getVersucheUebrig(),
                                        controller.getGameModel().getAktuelleFrage()
                                );
                            } else {
                                view.setMainPanel();
                            }
                        } else {
                            view.showGameMessage(
                                    "Du hast alle Fragen beantwortet!",
                                    "Spiel beendet",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            view.setMainPanel();
                        }
                    } else if (controller.getGameModel().istVerloren()) {
                        view.showGameMessage(
                                "Leider verloren! Das Wort war: " +
                                        controller.getGameModel().getAktuelleAntwort(),
                                "Verloren!",
                                JOptionPane.ERROR_MESSAGE
                        );

                        if (controller.getGameModel().hatNochFragen()) {
                            int option = JOptionPane.showConfirmDialog(
                                    view,
                                    "Möchtest du die nächste Frage spielen?",
                                    "Weiter?",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (option == JOptionPane.YES_OPTION) {
                                controller.getGameModel().naechsteFrage();
                                view.updateGameDisplay(
                                        controller.getGameModel().getBekanntesBuchstaben(),
                                        controller.getGameModel().getVersucheBuchstaben(),
                                        controller.getGameModel().getVersucheUebrig(),
                                        controller.getGameModel().getAktuelleFrage()
                                );
                            } else {
                                view.setMainPanel();
                            }
                        } else {
                            view.showGameMessage(
                                    "Spiel beendet!",
                                    "Ende",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            view.setMainPanel();
                        }
                    }
                }
                break;

            case gameHint:
                String hinweis = controller.getGameModel().getHinweis();
                view.showGameMessage(hinweis, "Hinweis", JOptionPane.INFORMATION_MESSAGE);

                view.updateGameDisplay(
                        controller.getGameModel().getBekanntesBuchstaben(),
                        controller.getGameModel().getVersucheBuchstaben(),
                        controller.getGameModel().getVersucheUebrig(),
                        controller.getGameModel().getAktuelleFrage()
                );

                // Prüfe auf Auto-Gewinn durch Hinweis
                if (controller.getGameModel().istGewonnen()) {
                    view.showGameMessage(
                            "Du hast das Wort erraten: " +
                                    controller.getGameModel().getAktuelleAntwort(),
                            "Gewonnen!",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                break;

            case menuBarZurueck:
                view.setMainPanel();
                break;

            case manageQuestionPoolBtn:
                System.out.println("Fragepool verwalten");
                if(view.getSelectedFragePool() == null) {
                    JOptionPane.showMessageDialog(null, "Es wurde kein Fragepool gefunden, erstellen sie einen", "Warnung", JOptionPane.ERROR_MESSAGE);
                }
                view.openManageQuestionsPanel();
                break;

            case fragenVerwalten:
                System.out.println("Fragenverwalten");
                view.toggleWestPanel();
                break;

            case createQuestionPoolBtn:
                System.out.println("create Fragepool");
                createFragePool();
        }
    }

    public void createFragePool(){

        String neuerPoolName = JOptionPane.showInputDialog(null, "Geben Sie den Namen des neu zu erstellenden Fragepools an", "Eingabe", JOptionPane.QUESTION_MESSAGE);
        try{
            new FrageSaveLoad().saveFragenpool(new Fragenpool(neuerPoolName), new Statistik(), neuerPoolName);
            view.setQuestionPools(view.getQuestionPools());
        }catch (IOException ioe){
            System.out.println("Fehler beim Erstellen vom neuen Fragepool");
        }
    }
}