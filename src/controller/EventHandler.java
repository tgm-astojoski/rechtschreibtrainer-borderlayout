package controller;

import view.RSTBLFrame;

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

        ActionCommands ac = ActionCommands.valueOf(e.getActionCommand());

        switch (ac) {

            case quiz:
                String selectedPool = view.getSelectedFragePool();
                controller.ladeQuiz(selectedPool);
                view.setQuizPanel();
                break;

            case quizAnswer:

                String input = view.getUserQuizInput();

                boolean richtig =
                        controller.getModel().pruefeAntwort(input);

                if(richtig){
                    System.out.println("Richtig");
                } else {
                    System.out.println("Falsch");
                }

                controller.getModel().naechsteFrage();
                view.naechsteQuizFrage();

                break;
        }
    }
}
