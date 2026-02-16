package view;

import controller.ActionCommands;
import model.Fragenpool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuizPanel extends JPanel {

    private Fragenpool fragenpool;

    private JLabel questionPoolLabel;
    private JLabel questionNrLabel;
    private JTextPane questionTextPane;
    private JTextPane answerTextPane;

    private int questionNumber = 0;
    private int maxQuestions = 0;

    private JButton quizAnswer;

    public QuizPanel(ActionListener actionListener,
                     Fragenpool fragenpool) {

        this.fragenpool = fragenpool;

        this.setLayout(new BorderLayout());

        questionPoolLabel = new JLabel("Kein Fragenpool");
        questionNrLabel = new JLabel("Frage 0/0");
        questionTextPane = new JTextPane();
        questionTextPane.setEditable(false);

        answerTextPane = new JTextPane();

        quizAnswer = new JButton("Prüfen");
        quizAnswer.setActionCommand(ActionCommands.quizAnswer.name());

        JPanel top = new JPanel(new GridLayout(3,1));
        top.add(questionPoolLabel);
        top.add(questionNrLabel);
        top.add(questionTextPane);

        this.add(top, BorderLayout.CENTER);
        this.add(answerTextPane, BorderLayout.SOUTH);
        this.add(quizAnswer, BorderLayout.EAST);
    }

    public void setActionListener(ActionListener listener){
        quizAnswer.addActionListener(listener);
    }

    public void setFragenpool(Fragenpool pool){
        this.fragenpool = pool;
        this.questionNumber = 0;
        this.maxQuestions = pool.getFragen().length;
        questionPoolLabel.setText(pool.getTitel());
        zeigeFrage();
    }

    private void zeigeFrage(){

        if(questionNumber < maxQuestions){

            questionTextPane.setText(
                    fragenpool.getFragen()[questionNumber].getQuestion()
            );

            questionNrLabel.setText(
                    "Frage " + (questionNumber + 1)
                            + "/" + maxQuestions
            );

        } else {
            questionTextPane.setText("Quiz beendet!");
        }
    }

    public void naechsteFrage(){
        questionNumber++;
        zeigeFrage();
        answerTextPane.setText("");
    }

    public String getUserQuizInput(){
        return answerTextPane.getText();
    }
}
