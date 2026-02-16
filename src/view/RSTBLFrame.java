package view;

import model.Fragenpool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RSTBLFrame extends JFrame {

    private MainPanel mainPanel;
    private QuizPanel quizPanel;
    private GamePanel gamePanel;
    private RSTBLMenu menu;

    public RSTBLFrame(ActionListener actionListener,
                      Fragenpool fragenpool) {

        this.setTitle("Rechtschreibtrainer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);

        mainPanel = new MainPanel(actionListener);
        quizPanel = new QuizPanel(actionListener, null);
        gamePanel = new GamePanel(actionListener, null, 5);

        this.setContentPane(mainPanel);
    }

    public void setActionListener(ActionListener listener) {

        mainPanel.setActionListener(listener);
        quizPanel.setActionListener(listener);
        gamePanel.setActionListener(listener);

        menu = new RSTBLMenu(listener);
        this.setJMenuBar(menu);
    }

    public String getSelectedFragePool(){
        return mainPanel.getSelectedFragePool();
    }

    public void setQuizPanel() {
        this.setContentPane(quizPanel);
        this.revalidate();
        this.repaint();
    }

    public void setFragenpool(Fragenpool pool){
        quizPanel.setFragenpool(pool);
    }

    public void naechsteQuizFrage(){
        quizPanel.naechsteFrage();
    }

    public String getUserQuizInput(){
        return quizPanel.getUserQuizInput();
    }
}
