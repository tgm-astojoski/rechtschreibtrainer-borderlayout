package view;

import controller.ActionCommands;
import model.Frage;
import model.FrageSaveLoad;
import model.Fragenpool;
import model.Statistik;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManageQuestionsPanel extends JFrame {

    private Fragenpool fragenpool;
    private Statistik statistik;
    private String filename;

    private DefaultListModel<String> questionsModel;
    private DefaultListModel<String> answersModel;
    private JList<String> questionsList;
    private JList<String> answersList;

    private JButton addButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JButton closeButton;

    public ManageQuestionsPanel(String filename) {
        this.filename = filename;
        loadFragenpool();

        setTitle("Fragenverwaltung - " + (fragenpool != null ? fragenpool.getTitel() : ""));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initComponents();
        loadQuestions();
    }

    private void loadFragenpool() {
        FrageSaveLoad loader = new FrageSaveLoad();
        try {
            // Entferne .txt Extension falls vorhanden
            String cleanFilename = filename.replace(".txt", "");

            // Lade aus dem questionPools Ordner
            FrageSaveLoad.FragenpoolWithStat result = loader.loadFragenpool("questionPools", cleanFilename);
            this.fragenpool = result.getPool();
            this.statistik = result.getStatistik();
        } catch (IOException e) {
            // Falls nicht gefunden, erstelle neuen Pool
            String poolName = filename.replace(".txt", "");
            this.fragenpool = new Fragenpool(poolName);
            this.statistik = new Statistik();

            JOptionPane.showMessageDialog(
                    null,
                    "Datei nicht gefunden. Ein neuer Fragenpool wurde erstellt.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            // Bei jedem anderen Fehler (z.B. NoSuchElementException beim Parsen)
            String poolName = filename.replace(".txt", "");
            this.fragenpool = new Fragenpool(poolName);
            this.statistik = new Statistik();

            JOptionPane.showMessageDialog(
                    null,
                    "Fehler beim Laden der Datei: " + e.getMessage() + "\nEin neuer Fragenpool wurde erstellt.",
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Linkes Panel für Fragen
        JPanel questionsPanel = createListPanel("Fragen", true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;
        mainPanel.add(questionsPanel, gbc);

        // Rechtes Panel für Antworten
        JPanel answersPanel = createListPanel("Antworten", false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        mainPanel.add(answersPanel, gbc);

        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
    }

    private JPanel createListPanel(String title, boolean isQuestions) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

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

            // Synchronisiere Auswahl zwischen Fragen und Antworten
            questionsList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && answersList != null) {
                    answersList.setSelectedIndex(questionsList.getSelectedIndex());
                }
            });
        } else {
            answersModel = mainModel;
            answersList = mainList;

            // Synchronisiere Auswahl zwischen Antworten und Fragen
            answersList.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && questionsList != null) {
                    questionsList.setSelectedIndex(answersList.getSelectedIndex());
                }
            });
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

        // Nummerierung links hinzufügen
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        panel.add(numberScrollPane, gbc);

        // Hauptliste rechts hinzufügen
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(mainScrollPane, gbc);

        // Modell-Listener für automatische Nummerierung
        mainModel.addListDataListener(new javax.swing.event.ListDataListener() {
            public void intervalAdded(javax.swing.event.ListDataEvent e) { updateNumbers(numberModel, mainModel); }
            public void intervalRemoved(javax.swing.event.ListDataEvent e) { updateNumbers(numberModel, mainModel); }
            public void contentsChanged(javax.swing.event.ListDataEvent e) { updateNumbers(numberModel, mainModel); }
        });

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        addButton = new JButton("Hinzufügen");
        deleteButton = new JButton("Löschen");
        saveButton = new JButton("Speichern");
        closeButton = new JButton("Schließen");

        addButton.addActionListener(e -> addQuestion());
        deleteButton.addActionListener(e -> deleteSelectedQuestion());
        saveButton.addActionListener(e -> saveFragenpool());
        closeButton.addActionListener(e -> dispose());

        // Platzhalter links für Rechtsausrichtung
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        buttonPanel.add(Box.createHorizontalGlue(), gbc);

        // Buttons
        gbc.weightx = 0.0;

        gbc.gridx = 1;
        buttonPanel.add(addButton, gbc);

        gbc.gridx = 2;
        buttonPanel.add(deleteButton, gbc);

        gbc.gridx = 3;
        buttonPanel.add(saveButton, gbc);

        gbc.gridx = 4;
        buttonPanel.add(closeButton, gbc);

        return buttonPanel;
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

    private void loadQuestions() {
        questionsModel.clear();
        answersModel.clear();

        if (fragenpool != null && fragenpool.getFragen() != null) {
            for (Frage frage : fragenpool.getFragen()) {
                if (frage != null) {
                    questionsModel.addElement(frage.getQuestion());
                    answersModel.addElement(frage.getAnswer());
                }
            }
        }
    }

    private void addQuestion() {
        JTextField questionField = new JTextField();
        JTextField answerField = new JTextField();

        Object[] message = {
                "Frage:", questionField,
                "Antwort:", answerField
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Neue Frage hinzufügen",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            String question = questionField.getText().trim();
            String answer = answerField.getText().trim();

            if (!question.isEmpty() && !answer.isEmpty()) {
                Frage newFrage = new Frage(question, answer);
                fragenpool.addFrage(newFrage);
                questionsModel.addElement(question);
                answersModel.addElement(answer);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Frage und Antwort dürfen nicht leer sein!",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void deleteSelectedQuestion() {
        int selectedIndex = questionsList.getSelectedIndex();

        if (selectedIndex != -1) {
            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Möchten Sie diese Frage wirklich löschen?",
                    "Löschen bestätigen",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
                // Entferne aus den Models
                questionsModel.remove(selectedIndex);
                answersModel.remove(selectedIndex);

                // Erstelle neues Fragen-Array ohne die gelöschte Frage
                Frage[] alleFragen = fragenpool.getFragen();
                Frage[] neueFragen = new Frage[alleFragen.length - 1];

                int neuIndex = 0;
                for (int i = 0; i < alleFragen.length; i++) {
                    if (i != selectedIndex) {
                        neueFragen[neuIndex++] = alleFragen[i];
                    }
                }

                fragenpool.setFragen(neueFragen);
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Bitte wählen Sie eine Frage zum Löschen aus!",
                    "Keine Auswahl",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void saveFragenpool() {
        FrageSaveLoad saver = new FrageSaveLoad();
        try {
            String cleanFilename = filename.replace(".txt", "");

            // Speichere in den questionPools Ordner
            saver.saveFragenpool(fragenpool, statistik, "questionPools", cleanFilename);

            JOptionPane.showMessageDialog(
                    this,
                    "Fragenpool erfolgreich gespeichert!",
                    "Erfolg",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Fehler beim Speichern: " + e.getMessage(),
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}