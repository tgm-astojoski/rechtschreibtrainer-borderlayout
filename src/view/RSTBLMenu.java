package view;

import controller.ActionCommands;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RSTBLMenu extends JMenuBar {
    public RSTBLMenu(ActionListener actionListener) {

        JMenu fileMenu = new JMenu("Datei");
        JMenuItem fileMenuItem1 = new JMenuItem("Speichern");
        JMenuItem fileMenuItem2 = new JMenuItem("Laden");
        fileMenuItem1.setActionCommand(ActionCommands.menuBarSpeichern.name());
        fileMenuItem2.setActionCommand(ActionCommands.menuBarLaden.name());
        fileMenuItem1.addActionListener(actionListener);
        fileMenuItem2.addActionListener(actionListener);
        fileMenu.add(fileMenuItem1);
        fileMenu.add(fileMenuItem2);
        this.add(fileMenu);

        JMenu optionsMenu = new JMenu("Optionen");
        JMenuItem optionsMenuItem1 = new JMenuItem("Einstellungen");
        JMenuItem optionsMenuItem2 = new JMenuItem("Mehr");
        optionsMenuItem1.setActionCommand(ActionCommands.menuBarEinstellungen.name());
        optionsMenuItem2.setActionCommand(ActionCommands.menuBarMehr.name());
        optionsMenuItem1.addActionListener(actionListener);
        optionsMenuItem2.addActionListener(actionListener);
        optionsMenu.add(optionsMenuItem1);
        optionsMenu.add(optionsMenuItem2);
        this.add(optionsMenu);

        JMenu ansichtMenu = new JMenu("Ansicht");
        JMenuItem ansichtMenuItem1 = new JMenuItem("Dark mode");
        JMenuItem ansichtMenuItem2 = new JMenuItem("Ansichtseinstellungen");
        ansichtMenuItem1.setActionCommand(ActionCommands.menuBarDarkMode.name());
        ansichtMenuItem2.setActionCommand(ActionCommands.menuBarAnsichtseinstellungen.name());
        ansichtMenuItem1.addActionListener(actionListener);
        ansichtMenuItem2.addActionListener(actionListener);
        ansichtMenu.add(ansichtMenuItem1);
        ansichtMenu.add(ansichtMenuItem2);
        this.add(ansichtMenu);

        JMenu githubMenu = new JMenu("GitHub");
        JMenuItem githubMenuItem1 = new JMenuItem("GitHub");
        githubMenuItem1.setActionCommand(ActionCommands.menuBarGithub.name());
        githubMenuItem1.addActionListener(actionListener);
        githubMenu.add(githubMenuItem1);
        this.add(githubMenu);

        JMenu anleitungMenu = new JMenu("Anleitung");
        JMenuItem anleitungMenuItem1 = new JMenuItem("Anleitung");
        anleitungMenuItem1.setActionCommand(ActionCommands.menuBarAnleitung.name());
        anleitungMenuItem1.addActionListener(actionListener);
        anleitungMenu.add(anleitungMenuItem1);
        this.add(anleitungMenu);

        JMenu zurueckMenu = new JMenu("Zurück");
        JMenuItem zurueckMenuItem1 = new JMenuItem("Zurück");
        zurueckMenuItem1.setActionCommand(ActionCommands.menuBarZurueck.name());
        zurueckMenuItem1.addActionListener(actionListener);
        zurueckMenu.add(zurueckMenuItem1);
        this.add(zurueckMenu);
    }
}