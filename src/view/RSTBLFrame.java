package view;

import model.Fragenpool;

import javax.swing.*;
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
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);

        mainPanel = new MainPanel(actionListener);
        quizPanel = new QuizPanel(actionListener, null);
        gamePanel = new GamePanel(actionListener, null, 7);

        this.setContentPane(mainPanel);
    }

    public void setActionListener(ActionListener listener) {

        mainPanel.setActionListener(listener);
        quizPanel.setActionListener(listener);
        gamePanel.setActionListener(listener);

        menu = new RSTBLMenu(listener);
        this.setJMenuBar(menu);
    }

    public String getSelectedFragePool() {
        return mainPanel.getSelectedFragePool();
    }

    public void setMainPanel() {
        this.setContentPane(mainPanel);
        this.revalidate();
        this.repaint();
    }

    public void setQuizPanel() {
        this.setContentPane(quizPanel);
        this.revalidate();
        this.repaint();
    }

    public void setGamePanel() {
        this.setContentPane(gamePanel);
        this.revalidate();
        this.repaint();
    }

    public void setFragenpool(Fragenpool pool) {
        quizPanel.setFragenpool(pool);
    }

    public void initializeGamePanel(String bekanntesBuchstaben, String versucheBuchstaben,
                                    int versucheUebrig, String frage) {
        gamePanel.updateDisplay(bekanntesBuchstaben, versucheBuchstaben, versucheUebrig, frage);
    }

    public void naechsteQuizFrage() {
        quizPanel.naechsteFrage();
    }

    public String getUserQuizInput() {
        return quizPanel.getUserQuizInput();
    }

    public String getUserGameInput() {
        return gamePanel.getUserGameInput();
    }

    public void clearGameInput() {
        gamePanel.clearInput();
    }

    public void updateGameDisplay(String bekanntesBuchstaben, String versucheBuchstaben,
                                  int versucheUebrig, String frage) {
        gamePanel.updateDisplay(bekanntesBuchstaben, versucheBuchstaben, versucheUebrig, frage);
    }

    public void showGameMessage(String message, String title, int messageType) {
        gamePanel.showMessage(message, title, messageType);
    }

    public void openManageQuestionsPanel() {
        String selectedPool = getSelectedFragePool();
        if (selectedPool != null && !selectedPool.isEmpty()) {
            ManageQuestionsPanel managePanel = new ManageQuestionsPanel(selectedPool);
            managePanel.setVisible(true);
        }
    }

    public void toggleWestPanel() {
        mainPanel.hideWestPanel();
    }
}