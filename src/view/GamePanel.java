package view;

import controller.ActionCommands;
import model.Fragenpool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private ActionListener actionListener;
    private Fragenpool fragenpool;

    private JPanel canvasPanel;
    private HangmanPanel hangmanDrawingPanel;
    private JPanel hangmanPanel;

    private JLabel questionLabel;
    private JTextField currentKnownTextField;
    private JTextField tryLetterTextFieldLabel;
    private JTextField tryLetterTextField;
    private JButton gameTry;
    private JButton gameHint;
    private JTextField triedLettersTextFieldLabel;
    private JTextField triedLettersTextField;
    private GridBagConstraints gbc;

    private int triesLeft;

    public GamePanel(ActionListener actionListener, Fragenpool fragenpool, int triesLeft) {
        this.actionListener = actionListener;
        this.fragenpool = fragenpool;
        this.triesLeft = triesLeft;

        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        createCanvasPanel();
        createHangmanPanel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 3.0;
        gbc.weighty = 1.0;

        this.add(this.canvasPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        this.add(this.hangmanPanel, gbc);

        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        this.setBackground(Color.DARK_GRAY);
        this.setMinimumSize(new Dimension(800, 500));
        this.setPreferredSize(new Dimension(800, 500));
    }

    public void createCanvasPanel() {
        this.canvasPanel = new JPanel();
        this.canvasPanel.setLayout(new BorderLayout());
        this.canvasPanel.setBackground(Color.WHITE);
        this.canvasPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        this.canvasPanel.setOpaque(true);

        // Erstelle HangmanPanel für die Zeichnung
        this.hangmanDrawingPanel = new HangmanPanel(triesLeft);
        this.canvasPanel.add(hangmanDrawingPanel, BorderLayout.CENTER);
    }

    public void createHangmanPanel() {
        hangmanPanel = new JPanel();
        hangmanPanel.setBackground(Color.LIGHT_GRAY);
        hangmanPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        hangmanPanel.setOpaque(true);
        hangmanPanel.setLayout(new GridBagLayout());

        Font f = new Font("Arial", Font.PLAIN, 16);
        Font fBold = new Font("Arial", Font.BOLD, 18);

        // Frage Label - mehrzeilig und scrollbar
        this.questionLabel = new JLabel("", SwingConstants.CENTER);
        this.questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.questionLabel.setForeground(new Color(50, 50, 150));
        this.questionLabel.setVerticalAlignment(SwingConstants.TOP);

        this.currentKnownTextField = new JTextField();
        this.currentKnownTextField.setEditable(false);
        this.currentKnownTextField.setHorizontalAlignment(JTextField.CENTER);
        this.currentKnownTextField.setFont(new Font("Courier New", Font.BOLD, 24));
        this.currentKnownTextField.setBackground(Color.WHITE);

        this.tryLetterTextFieldLabel = new JTextField();
        this.tryLetterTextFieldLabel.setText("Versuch einen Buchstaben (" + triesLeft + " Versuche übrig)");
        this.tryLetterTextFieldLabel.setEditable(false);
        this.tryLetterTextFieldLabel.setHorizontalAlignment(JTextField.CENTER);
        this.tryLetterTextFieldLabel.setFont(f);
        this.tryLetterTextFieldLabel.setBorder(null);
        this.tryLetterTextFieldLabel.setBackground(Color.LIGHT_GRAY);

        this.tryLetterTextField = new JTextField();
        this.tryLetterTextField.setHorizontalAlignment(JTextField.CENTER);
        this.tryLetterTextField.setFont(new Font("Arial", Font.BOLD, 20));

        this.gameTry = new JButton("Versuchen");
        this.gameTry.setActionCommand(ActionCommands.gameAnswer.name());
        this.gameTry.setFont(new Font("Arial", Font.BOLD, 16));
        this.gameTry.setBackground(new Color(100, 180, 100));

        this.gameHint = new JButton("Hinweis");
        this.gameHint.setActionCommand(ActionCommands.gameHint.name());
        this.gameHint.setFont(new Font("Arial", Font.BOLD, 16));
        this.gameHint.setBackground(new Color(100, 150, 200));

        this.triedLettersTextFieldLabel = new JTextField();
        this.triedLettersTextFieldLabel.setText("Versuchte Buchstaben:");
        this.triedLettersTextFieldLabel.setEditable(false);
        this.triedLettersTextFieldLabel.setHorizontalAlignment(JTextField.CENTER);
        this.triedLettersTextFieldLabel.setFont(f);
        this.triedLettersTextFieldLabel.setBorder(null);
        this.triedLettersTextFieldLabel.setBackground(Color.LIGHT_GRAY);

        this.triedLettersTextField = new JTextField();
        this.triedLettersTextField.setText("");
        this.triedLettersTextField.setEditable(false);
        this.triedLettersTextField.setHorizontalAlignment(JTextField.CENTER);
        this.triedLettersTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        this.triedLettersTextField.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5; // Mehr Platz für die Frage

        hangmanPanel.add(this.questionLabel, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.4;
        hangmanPanel.add(this.currentKnownTextField, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.2;
        hangmanPanel.add(this.tryLetterTextFieldLabel, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.3;
        hangmanPanel.add(this.tryLetterTextField, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.weighty = 0.4;
        hangmanPanel.add(this.gameTry, gbc);

        gbc.gridx = 1;
        hangmanPanel.add(this.gameHint, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        hangmanPanel.add(this.triedLettersTextFieldLabel, gbc);

        gbc.gridy = 6;
        gbc.weighty = 0.3;
        hangmanPanel.add(this.triedLettersTextField, gbc);
    }

    public String getUserGameInput() {
        return this.tryLetterTextField.getText();
    }

    public void clearInput() {
        this.tryLetterTextField.setText("");
    }

    public void updateDisplay(String bekanntesBuchstaben, String versucheBuchstaben, int versucheUebrig, String frage) {
        this.currentKnownTextField.setText(bekanntesBuchstaben);
        this.triedLettersTextField.setText(versucheBuchstaben);
        this.tryLetterTextFieldLabel.setText("Versuch einen Buchstaben (" + versucheUebrig + " Versuche übrig)");

        // Zeige die Frage in einem mehrzeiligen Format
        String wrappedQuestion = "<html><div style='text-align: center; padding: 5px;'>" + frage + "</div></html>";
        this.questionLabel.setText(wrappedQuestion);

        this.hangmanDrawingPanel.setTriesLeft(versucheUebrig);
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public void setActionListener(ActionListener listener) {
        this.gameHint.addActionListener(listener);
        this.gameTry.addActionListener(listener);
    }
}