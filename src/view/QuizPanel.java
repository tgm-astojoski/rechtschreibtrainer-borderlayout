package view;

import controller.ActionCommands;
import model.Fragenpool;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;

public class QuizPanel extends JPanel {

    private ActionListener actionListener;
    private Fragenpool fragenpool;

    private JLabel questionPoolLabel;
    private JLabel questionNrLabel;
    private JTextPane questionTextPane;
    private JTextPane answerTextPane;

    private int questionNumber = 0;
    private int maxQuestions = 0;

    private JPanel questionPanel;
    private JPanel answerPanel;
    private JPanel buttonPanel;

    private JButton quizHint;
    private JButton quizAnswer;

    public QuizPanel(ActionListener actionListener, Fragenpool fragenpool) {
        this.actionListener = actionListener;
        this.fragenpool = fragenpool;

        this.setLayout(new GridLayout(2, 1, 7, 7));
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        this.setBackground(Color.DARK_GRAY);
        this.setMinimumSize(new Dimension(600, 400));
        this.setPreferredSize(new Dimension(600, 400));

        createQuestionPanel();
        createAnswerPanel();

        this.add(questionPanel);
        this.add(answerPanel);
    }

    public void createQuestionPanel() {
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(Color.LIGHT_GRAY);
        questionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        questionPanel.setOpaque(true);

        String frage = "Keine Frage gefunden";
        if (fragenpool != null) {
            this.questionPoolLabel = new JLabel(fragenpool.getTitel());
            frage = fragenpool.getFragen()[questionNumber].getQuestion();
            this.maxQuestions = fragenpool.getFragen().length;
        } else {
            this.questionPoolLabel = new JLabel("Titel nicht gefunden");
        }
        this.questionPoolLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.questionPoolLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.questionNrLabel = new JLabel("Frage " + (questionNumber + 1) + "/" + maxQuestions);
        this.questionNrLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.questionNrLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        initializeQuestionTextPane(frage);

        questionPanel.add(Box.createVerticalGlue());
        questionPanel.add(this.questionPoolLabel);
        questionPanel.add(Box.createVerticalGlue());
        questionPanel.add(this.questionNrLabel);
        questionPanel.add(Box.createVerticalGlue());
        questionPanel.add(questionTextPane);
        questionPanel.add(Box.createVerticalGlue());
    }

    public void initializeQuestionTextPane(String frage) {
        questionTextPane = new JTextPane();
        questionTextPane.setText(frage);
        questionTextPane.setFont(new Font("Arial", Font.PLAIN, 20));
        questionTextPane.setEditable(false);
        questionTextPane.setBorder(null);
        questionTextPane.setOpaque(false);
        questionTextPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionTextPane.setMargin(new Insets(10, 20, 10, 20));
        questionTextPane.setMaximumSize(new Dimension(800, (int) questionTextPane.getPreferredSize().getHeight()));

        // Caret verstecken, damit kein Scrollen passiert
        DefaultCaret caret = new DefaultCaret() {
            @Override
            public void paint(Graphics g) {}
        };
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        questionTextPane.setCaret(caret);
        questionTextPane.getCaret().setVisible(false);

        // Text zentrieren
        StyledDocument doc = questionTextPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    public void createAnswerPanel() {
        answerPanel = new JPanel();
        answerPanel.setLayout(new BorderLayout(5, 5));
        answerPanel.setBackground(Color.LIGHT_GRAY);
        answerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        answerPanel.setOpaque(true);

        createButtonPanel();
        answerPanel.add(buttonPanel, BorderLayout.EAST);

        answerPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = (int) (answerPanel.getWidth() * 0.20);
                buttonPanel.setPreferredSize(new Dimension(w, answerPanel.getHeight()));
                answerPanel.revalidate();
            }
        });

        this.answerTextPane = new JTextPane();
        this.answerTextPane.setFont(new Font("Arial", Font.PLAIN, 20));
        this.answerTextPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 20));
        answerPanel.add(answerTextPane, BorderLayout.CENTER);
    }

    public void createButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 5, 5));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));
        buttonPanel.setOpaque(true);

        quizHint = new JButton("Tipp");
        quizHint.setFont(new Font("Arial", Font.BOLD, 22));
        quizHint.setActionCommand(ActionCommands.quizHint.name());

        quizAnswer = new JButton("Prüfen");
        quizAnswer.setFont(new Font("Arial", Font.BOLD, 22));
        quizAnswer.setActionCommand(ActionCommands.quizAnswer.name());

        buttonPanel.add(quizHint);
        buttonPanel.add(quizAnswer);
    }

    public void setActionListener(ActionListener listener) {
        this.quizHint.addActionListener(listener);
        this.quizAnswer.addActionListener(listener);
    }

    public void setFragenpool(Fragenpool pool) {
        this.fragenpool = pool;
        this.questionNumber = 0;
        this.maxQuestions = pool.getFragen().length;
        questionPoolLabel.setText(pool.getTitel());
        zeigeFrage();
    }

    private void zeigeFrage() {
        if (questionNumber < maxQuestions) {
            questionTextPane.setText(fragenpool.getFragen()[questionNumber].getQuestion());
            questionTextPane.setCaretPosition(0); // scrollt nicht mehr nach unten
            questionNrLabel.setText("Frage " + (questionNumber + 1) + "/" + maxQuestions);
        } else {
            questionTextPane.setText("Quiz beendet!");
            questionTextPane.setCaretPosition(0);
        }
    }

    public void naechsteFrage() {
        questionNumber++;
        zeigeFrage();
        answerTextPane.setText("");
    }

    public String getUserQuizInput() {
        return answerTextPane.getText();
    }
}