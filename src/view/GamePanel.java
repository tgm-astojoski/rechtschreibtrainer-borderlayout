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
    private JPanel hangmanPanel;

    private JTextField currentKnownTextField;
    private JTextField tryLetterTextFieldLabel;
    private JTextField tryLetterTextField;
    private JButton gameTry;
    private JButton gameHint;
    private JTextField triedLettersTextFieldLabel;
    private JTextField triedLettersTextField;
    private GridBagConstraints gbc;

    private String triedLetters;
    private int amountOfTries;
    private int triesLeft;

    public GamePanel(ActionListener actionListener, Fragenpool fragenpool, int triesLeft) {
    this.actionListener = actionListener;
    this.fragenpool = fragenpool;

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

    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    this.add(this.hangmanPanel, gbc);

    gbc.gridx = 0;
    gbc.gridwidth = 1;

    this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
    this.setBackground(Color.DARK_GRAY);
    this.setMinimumSize(new Dimension(600,400));
    this.setPreferredSize(new Dimension(600,400));
    }

    public void createCanvasPanel(){
        this.canvasPanel = new JPanel();
        this.canvasPanel.setLayout(new BoxLayout(this.canvasPanel, BoxLayout.Y_AXIS));
        this.canvasPanel.setBackground(Color.WHITE);
        this.canvasPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        this.canvasPanel.setOpaque(true);
    }

    public void createHangmanPanel(){
        hangmanPanel = new JPanel();
        hangmanPanel.setBackground(Color.LIGHT_GRAY);
        hangmanPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
        hangmanPanel.setOpaque(true);
        hangmanPanel.setLayout(new GridBagLayout());

        Font f = new Font("Arial", Font.PLAIN, 20);

        this.currentKnownTextField = new JTextField();
        this.currentKnownTextField.setEditable(false);
        this.currentKnownTextField.setHorizontalAlignment(JTextField.CENTER);
        this.currentKnownTextField.setFont(f);


        this.tryLetterTextFieldLabel = new JTextField();
        this.tryLetterTextFieldLabel.setText("Versuch einen Buchstaben (" + triedLetters + " Versuche übrig)");
        this.tryLetterTextFieldLabel.setEditable(false);
        this.tryLetterTextFieldLabel.setHorizontalAlignment(JTextField.CENTER);
        this.tryLetterTextFieldLabel.setFont(f);

        this.tryLetterTextField = new JTextField();
        this.tryLetterTextField.setHorizontalAlignment(JTextField.CENTER);
        this.tryLetterTextField.setFont(f);

        this.gameTry = new JButton("Versuchen");
        this.gameTry.setActionCommand(ActionCommands.gameAnswer.name());
        this.gameTry.setFont(new Font("Arial", Font.BOLD, 22));

        this.gameHint = new JButton("Hinweis");
        this.gameHint.setActionCommand(ActionCommands.gameHint.name());
        this.gameHint.setFont(new Font("Arial", Font.BOLD, 22));

        this.triedLettersTextFieldLabel = new JTextField();
        this.triedLettersTextFieldLabel.setText("Versuchte Buchstaben:");
        this.triedLettersTextFieldLabel.setEditable(false);
        this.triedLettersTextFieldLabel.setHorizontalAlignment(JTextField.CENTER);
        this.triedLettersTextFieldLabel.setFont(f);

        this.triedLettersTextField = new JTextField();
        this.triedLettersTextField.setText("Test");
        this.triedLettersTextField.setEditable(false);
        this.triedLettersTextField.setHorizontalAlignment(JTextField.CENTER);
        this.triedLettersTextField.setFont(f);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        hangmanPanel.add(this.currentKnownTextField, gbc);

        gbc.gridy = 1;
        hangmanPanel.add(this.tryLetterTextFieldLabel,gbc);

        gbc.gridy = 2;
        hangmanPanel.add(this.tryLetterTextField,gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        hangmanPanel.add(this.gameTry,gbc);

        gbc.gridx = 1;
        hangmanPanel.add(this.gameHint,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        hangmanPanel.add(this.triedLettersTextFieldLabel,gbc);

        gbc.gridy = 5;
        hangmanPanel.add(this.triedLettersTextField,gbc);
    }

    public String getUserGameInput(){
        return this.tryLetterTextField.getText();
    }

    public void setActionListener(ActionListener listener) {
        this.gameHint.addActionListener(listener);
        this.gameTry.addActionListener(listener);
    }
}
