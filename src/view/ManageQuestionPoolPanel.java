package view;

import controller.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class ManageQuestionPoolPanel extends JPanel {
    private JComboBox questionPoolComboBox;
    private JButton questionPoolFragenVerwaltenBtn;
    private JButton createQuestionPoolBtn;

    public ManageQuestionPoolPanel(ActionListener actionListener) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 5),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        this.setBackground(Color.LIGHT_GRAY);

        JLabel questionPoolLabel1 = new JLabel("Fragepool verwalten");
        JLabel questionPoolLabel2 = new JLabel("Pool auswählen:");
        questionPoolLabel1.setFont(new Font(questionPoolLabel1.getFont().getName(), Font.BOLD, 20));
        questionPoolLabel2.setFont(new Font(questionPoolLabel2.getFont().getName(), Font.BOLD, 15));
        questionPoolLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionPoolLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);

        questionPoolComboBox = new JComboBox(getQuestionPools());
        questionPoolComboBox.setActionCommand(ActionCommands.manageQuestionPoolComboBox.name());
        questionPoolComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionPoolComboBox.setPreferredSize(new Dimension(this.getWidth(), 25));
        questionPoolComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        questionPoolFragenVerwaltenBtn = new JButton("Fragen verwalten");
        questionPoolFragenVerwaltenBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionPoolFragenVerwaltenBtn.setActionCommand(ActionCommands.manageQuestionPoolBtn.name());
        questionPoolFragenVerwaltenBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        createQuestionPoolBtn = new JButton("Fragepool erstellen");
        createQuestionPoolBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        createQuestionPoolBtn.setActionCommand(ActionCommands.createQuestionPoolBtn.name());
        createQuestionPoolBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        this.add(Box.createVerticalStrut(10));
        this.add(questionPoolLabel1);
        this.add(Box.createVerticalStrut(10));
        this.add(questionPoolLabel2);
        this.add(Box.createVerticalStrut(10));
        this.add(questionPoolComboBox);
        this.add(Box.createVerticalStrut(5));
        this.add(questionPoolFragenVerwaltenBtn);
        this.add(Box.createVerticalStrut(5));
        this.add(createQuestionPoolBtn);
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

    public void setQuestionPools(String[] questionPools){
        this.questionPoolComboBox.setModel(new DefaultComboBoxModel(questionPools));
    }

    public void addActionListener(ActionListener actionListener) {
        questionPoolFragenVerwaltenBtn.addActionListener(actionListener);
        questionPoolComboBox.addActionListener(actionListener);
        createQuestionPoolBtn.addActionListener(actionListener);
    }

    public String getSelectedFragePool(){
        Object selectedItem = questionPoolComboBox.getSelectedItem();
        if (selectedItem == null) {
            return null;
        }
        return selectedItem.toString();
    }
}
