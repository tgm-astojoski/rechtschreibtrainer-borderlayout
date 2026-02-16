package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ManageQuestionsPanel extends JPanel {

    private DefaultListModel<String> questionsModel;
    private DefaultListModel<String> answersModel;
    private JList<String> questionsList;
    private JList<String> answersList;

    public ManageQuestionsPanel() {
        setLayout(new BorderLayout());

        // Hauptpanel mit den beiden Listen
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        listsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Linkes Panel für Fragen
        JPanel questionsPanel = createListPanel("Fragen", true);

        // Rechtes Panel für Antworten
        JPanel answersPanel = createListPanel("Antworten", false);

        listsPanel.add(questionsPanel);
        listsPanel.add(answersPanel);

        add(listsPanel, BorderLayout.CENTER);

        // Beispieldaten hinzufügen
        addSampleData();
    }

    private JPanel createListPanel(String title, boolean isQuestions) {
        JPanel panel = new JPanel(new BorderLayout());

        // Nummerierung links
        DefaultListModel<String> numberModel = new DefaultListModel<>();
        JList<String> numberList = new JList<>(numberModel);
        numberList.setFixedCellWidth(30);
        numberList.setEnabled(false);
        numberList.setBackground(Color.LIGHT_GRAY);
        numberList.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Hauptliste
        DefaultListModel<String> mainModel = new DefaultListModel<>();
        JList<String> mainList = new JList<>(mainModel);
        mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainList.setFont(new Font("SansSerif", Font.PLAIN, 12));

        if (isQuestions) {
            questionsModel = mainModel;
            questionsList = mainList;
        } else {
            answersModel = mainModel;
            answersList = mainList;
        }

        // ScrollPane für die Hauptliste
        JScrollPane mainScrollPane = new JScrollPane(mainList);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // ScrollPane für die Nummerierung
        JScrollPane numberScrollPane = new JScrollPane(numberList);
        numberScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        numberScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Synchronisierung der Scrollbars
        mainScrollPane.getVerticalScrollBar().setModel(numberScrollPane.getVerticalScrollBar().getModel());

        // Layout
        JPanel listContainer = new JPanel(new BorderLayout());
        listContainer.add(numberScrollPane, BorderLayout.WEST);
        listContainer.add(mainScrollPane, BorderLayout.CENTER);

        panel.add(listContainer, BorderLayout.CENTER);

        // Modell-Listener für automatische Nummerierung
        mainModel.addListDataListener(new javax.swing.event.ListDataListener() {
            public void intervalAdded(javax.swing.event.ListDataEvent e) { updateNumbers(numberModel, mainModel); }
            public void intervalRemoved(javax.swing.event.ListDataEvent e) { updateNumbers(numberModel, mainModel); }
            public void contentsChanged(javax.swing.event.ListDataEvent e) { updateNumbers(numberModel, mainModel); }
        });

        return panel;
    }

    private void updateNumbers(DefaultListModel<String> numberModel, DefaultListModel<String> mainModel) {
        numberModel.clear();
        for (int i = 0; i < mainModel.size(); i++) {
            numberModel.addElement(String.valueOf(i));
        }
        if (mainModel.size() > 0) {
            numberModel.addElement("...");
        }
    }

    private void addSampleData() {
        questionsModel.addElement("Frage1");
        questionsModel.addElement("Frage2");
        questionsModel.addElement("Frage3");

        answersModel.addElement("Antwort1");
        answersModel.addElement("Antwort2");
        answersModel.addElement("Antwort3");
    }

    // Öffentliche Methoden zum Hinzufügen/Entfernen von Elementen
    public void addQuestion(String question) {
        questionsModel.addElement(question);
    }

    public void addAnswer(String answer) {
        answersModel.addElement(answer);
    }

    public void removeSelectedQuestion() {
        int index = questionsList.getSelectedIndex();
        if (index != -1) {
            questionsModel.remove(index);
        }
    }

    public void removeSelectedAnswer() {
        int index = answersList.getSelectedIndex();
        if (index != -1) {
            answersModel.remove(index);
        }
    }

    public String getSelectedQuestion() {
        return questionsList.getSelectedValue();
    }

    public String getSelectedAnswer() {
        return answersList.getSelectedValue();
    }

    // Test-Methode
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fragen/Antworten Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ManageQuestionsPanel panel = new ManageQuestionsPanel();
            frame.add(panel);

            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
