package view;

import controller.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.Arrays;

public class MainPanel extends JPanel implements ComponentListener {

    private ActionListener actionListener;

    private JPanel centerPanel;
    private ManageQuestionPoolPanel westPanel;
    private JPanel eastPanel;

    private JButton quizBtn;
    private JButton gameBtn;
    private JButton fragenVerwaltenBtn;
    private JButton statisticBtn;

    public MainPanel(ActionListener actionListener) {
        this.actionListener = actionListener;

        this.setLayout(new BorderLayout(7, 7));
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        this.setBackground(Color.DARK_GRAY);
        this.setMinimumSize(new Dimension(600,400));
        this.setPreferredSize(new Dimension(600,400));
        this.addComponentListener(this);

        createCenter();
        createWest();
        createEast();

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
        this.add(eastPanel, BorderLayout.EAST);
    }

    public void createCenter(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 2, 2));
        centerPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        centerPanel.setOpaque(true);

        quizBtn = new JButton("Quiz");
        gameBtn = new JButton("Game");
        fragenVerwaltenBtn = new JButton("Fragen verwalten");
        statisticBtn = new JButton("Statistik");

        quizBtn.setActionCommand(ActionCommands.quiz.name());
        gameBtn.setActionCommand(ActionCommands.game.name());
        fragenVerwaltenBtn.setActionCommand(ActionCommands.fragenVerwalten.name());
        statisticBtn.setActionCommand(ActionCommands.statistic.name());

        Font fontBtn = new Font("Arial", Font.BOLD, 30);
        quizBtn.setFont(fontBtn);
        gameBtn.setFont(fontBtn);
        fragenVerwaltenBtn.setFont(fontBtn);
        statisticBtn.setFont(fontBtn);

        centerPanel.add(quizBtn);
        centerPanel.add(gameBtn);
        centerPanel.add(fragenVerwaltenBtn);
        centerPanel.add(statisticBtn);
    }

    public void createWest(){
        westPanel = new ManageQuestionPoolPanel(this.actionListener);
        westPanel.setVisible(false);
    }

    public void createEast(){
        eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(2, 2, 2, 2));
        eastPanel.setBackground(Color.LIGHT_GRAY);
        eastPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        eastPanel.setOpaque(true);

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");

        btn1.setFont(new Font("Arial", Font.BOLD, 30));
        btn2.setFont(new Font("Arial", Font.BOLD, 30));
        btn3.setFont(new Font("Arial", Font.BOLD, 30));
        btn4.setFont(new Font("Arial", Font.BOLD, 30));

        eastPanel.add(btn1);
        eastPanel.add(btn2);
        eastPanel.add(btn3);
        eastPanel.add(btn4);

        eastPanel.setVisible(false);
    }

    public void hideWestPanel(){
        if(westPanel.isVisible()){
            westPanel.setVisible(false);
        }else{
            westPanel.setVisible(true);
        }
    }

    public void hideEastPanel(){
        if(eastPanel.isVisible()){
            eastPanel.setVisible(false);
        }else{
            eastPanel.setVisible(true);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        westPanel.setPreferredSize(new Dimension((int) (this.getWidth() * 0.25), this.getHeight()));
        eastPanel.setPreferredSize(new Dimension((int) (this.getWidth() * 0.25), this.getHeight()));
        this.revalidate();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}
    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}

    public void setActionListener(ActionListener listener) {
        this.quizBtn.addActionListener(listener);
        this.gameBtn.addActionListener(listener);
        this.fragenVerwaltenBtn.addActionListener(listener);
        this.statisticBtn.addActionListener(listener);

        this.westPanel.addActionListener(listener);
    }

    public String getSelectedFragePool(){
        return westPanel.getSelectedFragePool();
    }

    public String[] getQuestionPools(){
        return westPanel.getQuestionPools();
    }

    public void setQuestionPools(String[] questionPools){
        westPanel.setQuestionPools(questionPools);
    }
}