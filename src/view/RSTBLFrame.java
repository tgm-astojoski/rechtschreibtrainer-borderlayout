package view;

import model.Fragenpool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RSTBLFrame extends JFrame {
    MainPanel mainPanel;
    QuizPanel quizPanel;
    GamePanel gamePanel;
    RSTBLMenu menu;
    int triesLeft;

    public RSTBLFrame(ActionListener actionListener, Fragenpool fragenpool) {
        this.setTitle("Rechtschreibtrainer-Borderlayout");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setMinimumSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        try {
            this.setIconImage(ImageIO.read(new File("assets/icon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainPanel = new MainPanel(actionListener);
        quizPanel = new QuizPanel(actionListener, fragenpool);
        this.triesLeft = 5;
        gamePanel = new GamePanel(actionListener, fragenpool, triesLeft);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void mainHideWestPanel(){
        mainPanel.hideWestPanel();
    }

    public void mainHideEastPanel(){
        mainPanel.hideEastPanel();
    }

    public void setActionListener(ActionListener listener) {
        mainPanel.setActionListener(listener);
        quizPanel.setActionListener(listener);
        gamePanel.setActionListener(listener);
        menu = new  RSTBLMenu(listener);
        this.setJMenuBar(menu);
    }

    public String getSelectedFragePool(){
        return mainPanel.getSelectedFragePool();
    }

    public void setQuizPanel(QuizPanel panel){
        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    public void setMainPanel() {
        this.setContentPane(mainPanel);
        this.revalidate();
    }

    public void setGamePanel() {
        this.setContentPane(gamePanel);
        this.revalidate();
    }

    public String getUserQuizInput(){
        return quizPanel.getUserQuizInput();
    }

    public String getUserGameInput(){
        return gamePanel.getUserGameInput();
    }
}