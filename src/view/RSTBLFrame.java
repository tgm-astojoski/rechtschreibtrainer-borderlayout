package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RSTBLFrame extends JFrame {
    MainPanel mainPanel;
    RSTBLMenu menu;

    public RSTBLFrame(ActionListener actionListener) {
        this.setTitle("Rechtschreibtrainer-Borderlayout");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setMinimumSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        try {
            this.setIconImage(ImageIO.read(new File("assets/icon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainPanel = new MainPanel(actionListener);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void mainHideWestPanel(){
        mainPanel.hideWestPanel();
    }

    public void mainHideEastPanel(){
        mainPanel.hideEastPanel();
    }

    public void setActionListener(ActionListener listener) {
        mainPanel.setActionListener(listener);
        menu = new  RSTBLMenu(listener);
        this.setJMenuBar(menu);
    }
}