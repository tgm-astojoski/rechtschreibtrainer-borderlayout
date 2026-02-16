package controller;

import view.MainPanel;
import view.ManageQuestionsPanel;
import view.RSTBLFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    private RSTBLFrame view;
    private Controller controller;

    public EventHandler(Controller controller, RSTBLFrame view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ActionCommands ac = null;
        for (ActionCommands a : ActionCommands.values()) {
            if(a.name().equals(e.getActionCommand())) {
                ac = a;
            }
        }

        // EINMALIGE Deklaration der Variable selectedPool
        String selectedPool = null;


        switch(ac) {
            case quiz:
                System.out.println("Quiz gestartet");
                selectedPool = view.getSelectedFragePool(); // nur Zuweisung, kein "String" davor
                if(selectedPool != null) {
                    controller.startQuiz(selectedPool);
                }
                break;
            case game:
                System.out.println("Game");
                view.setGamePanel();
                break;
            case fragenVerwalten:
                System.out.println("FragenVerwalten");
                view.mainHideWestPanel();
                break;
            case statistic:
                System.out.println("Statistik");
                view.mainHideEastPanel();
                break;

            case menuBarSpeichern:
                System.out.println("Speichern");
                break;
            case menuBarLaden:
                System.out.println("Laden");
                break;
            case menuBarEinstellungen:
                System.out.println("Einstellungen");
                break;
            case menuBarMehr:
                System.out.println("Mehr");
                break;
            case menuBarDarkMode:
                System.out.println("Dark mode");
                break;
            case menuBarAnsichtseinstellungen:
                System.out.println("Ansichtseinstellungen");
                break;
            case menuBarGithub:
                System.out.println("GitHub");
                break;
            case menuBarAnleitung:
                System.out.println("Anleitung");
                break;
            case menuBarZurueck:
                System.out.println("Zurück");
                view.setMainPanel();
                break;

            case manageQuestionPoolBtn:
                System.out.println("Fragepool verwalten");
                selectedPool = view.getSelectedFragePool(); // nur Zuweisung, kein "String"
                if (selectedPool != null && !selectedPool.isEmpty()) {
                    ManageQuestionsPanel panel = new ManageQuestionsPanel(selectedPool);
                    panel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                            view,
                            "Bitte wählen Sie erst einen Fragenpool aus!",
                            "Kein Pool ausgewählt",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
                break;
            case manageQuestionPoolComboBox:
                selectedPool = view.getSelectedFragePool(); // nur Zuweisung, kein "String"
                System.out.println("ComboBox Fragepool " + selectedPool + " ausgewählt");
                break;


            case quizHint:
                System.out.println("Tipp");
                break;
            case quizAnswer:
                System.out.println("Eingegebene Antwort ist " + view.getUserQuizInput());

            case gameHint:
                System.out.println("GameHint");
                break;
            case gameAnswer:
                System.out.println("Eingegebene Antwort ist " + view.getUserGameInput());
                break;
        }
    }
}
