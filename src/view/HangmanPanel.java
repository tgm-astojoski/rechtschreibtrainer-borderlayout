package view;

import javax.swing.*;
import java.awt.*;

public class HangmanPanel extends JPanel {
    private int triesLeft;
    private final int maxTries;

    public HangmanPanel(int maxTries) {
        this.maxTries = maxTries;
        this.triesLeft = maxTries;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(300, 400));
    }

    public void setTriesLeft(int triesLeft) {
        this.triesLeft = triesLeft;
        repaint();
    }

    public int getTriesLeft() {
        return triesLeft;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3));

        int width = getWidth();
        int height = getHeight();

        // Berechne die Anzahl der Fehler
        int mistakes = maxTries - triesLeft;

        // Galgen-Basis
        if (mistakes >= 0) {
            g2d.setColor(new Color(101, 67, 33)); // Braun
            // Basis
            g2d.fillRect(width/4, height - 50, width/2, 10);
            // Vertikaler Balken
            g2d.fillRect(width/3, 50, 10, height - 60);
            // Horizontaler Balken
            g2d.fillRect(width/3, 50, width/3, 10);
            // Seil
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(width*2/3, 60, width*2/3, 100);
        }

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));

        // Kopf
        if (mistakes >= 1) {
            g2d.drawOval(width*2/3 - 25, 100, 50, 50);
        }

        // Körper
        if (mistakes >= 2) {
            g2d.drawLine(width*2/3, 150, width*2/3, 230);
        }

        // Linker Arm
        if (mistakes >= 3) {
            g2d.drawLine(width*2/3, 170, width*2/3 - 40, 200);
        }

        // Rechter Arm
        if (mistakes >= 4) {
            g2d.drawLine(width*2/3, 170, width*2/3 + 40, 200);
        }

        // Linkes Bein
        if (mistakes >= 5) {
            g2d.drawLine(width*2/3, 230, width*2/3 - 30, 280);
        }

        // Rechtes Bein
        if (mistakes >= 6) {
            g2d.drawLine(width*2/3, 230, width*2/3 + 30, 280);
        }

        // Gesicht bei Verlust
        if (mistakes >= 7) {
            g2d.setColor(Color.RED);
            // Traurige Augen (X X)
            g2d.drawLine(width*2/3 - 15, 120, width*2/3 - 10, 125);
            g2d.drawLine(width*2/3 - 10, 120, width*2/3 - 15, 125);
            g2d.drawLine(width*2/3 + 10, 120, width*2/3 + 15, 125);
            g2d.drawLine(width*2/3 + 15, 120, width*2/3 + 10, 125);
            // Trauriger Mund
            g2d.drawArc(width*2/3 - 15, 130, 30, 15, 0, -180);
        } else if (mistakes > 0 && mistakes < 7) {
            // Normale Augen
            g2d.fillOval(width*2/3 - 15, 120, 5, 5);
            g2d.fillOval(width*2/3 + 10, 120, 5, 5);
            // Neutraler Mund
            g2d.drawLine(width*2/3 - 10, 135, width*2/3 + 10, 135);
        }

        // Verbleibende Versuche anzeigen
        g2d.setColor(new Color(50, 50, 50));
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        String text = "Versuche übrig: " + triesLeft;
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        g2d.drawString(text, (width - textWidth) / 2, height - 20);
    }
}