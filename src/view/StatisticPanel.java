package view;

import javax.swing.*;
import java.awt.*;

public class StatisticPanel extends JPanel {

    public StatisticPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
        this.setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel("Statistik Panel - Coming Soon", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        this.add(label, BorderLayout.CENTER);
    }
}