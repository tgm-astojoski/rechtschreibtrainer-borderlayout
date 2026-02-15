package view;

import controller.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class ManageQuestionPoolPanel extends JPanel {
    private JComboBox questionPoolComboBox;
    private JButton questionPoolFragenVerwalten;

    public ManageQuestionPoolPanel(ActionListener actionListener) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 5),   // outer visual border
                BorderFactory.createEmptyBorder(10, 15, 10, 15)  // inner spacing (padding)
        ));
        this.setBackground(Color.LIGHT_GRAY);

        JLabel questionPoolLabel1 = new JLabel(" Fragepool verwalten");
        JLabel questionPoolLabel2 = new JLabel(" Pool auswählen:");
        questionPoolLabel1.setFont(new Font(questionPoolLabel1.getFont().getName(), Font.BOLD, 20));
        questionPoolLabel2.setFont(new Font(questionPoolLabel2.getFont().getName(), Font.BOLD, 15));
        questionPoolLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionPoolLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);

        questionPoolComboBox = new JComboBox(getQuestionPools());
        questionPoolComboBox.addActionListener(actionListener);
        questionPoolComboBox.setActionCommand("questionPoolComboBox");
        questionPoolComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionPoolComboBox.setPreferredSize(new Dimension(this.getWidth(), 25));
        questionPoolComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        //questionPoolComboBox.setMaximumSize(new Dimension(this.getWidth(), (int) (this.getHeight()*0.2)));

        questionPoolFragenVerwalten = new JButton("Fragen verwalten");
        questionPoolFragenVerwalten.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionPoolFragenVerwalten.setActionCommand(ActionCommands.manageQuestionPoolBtn.name());
        questionPoolFragenVerwalten.addActionListener(actionListener);
        questionPoolFragenVerwalten.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        this.add(Box.createVerticalStrut(10));
        this.add(questionPoolLabel1);
        this.add(Box.createVerticalStrut(10));
        this.add(questionPoolLabel2);
        this.add(Box.createVerticalStrut(10));
        this.add(questionPoolComboBox);
        this.add(Box.createVerticalStrut(5));
        this.add(questionPoolFragenVerwalten);
    }

    public String[] getQuestionPools(){
        String[] questionPools = new String[0];
        String directoryPath = "questionPools";

        File directory = new File(directoryPath);

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                questionPools = Arrays.copyOf(questionPools, questionPools.length + 1);
                questionPools[questionPools.length - 1] = file.getName();
            }
        }
        return  questionPools;
    }
}
