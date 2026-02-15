package controller;

import view.MainPanel;
import view.RSTBLFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    private RSTBLFrame view;

    public EventHandler(RSTBLFrame view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ActionCommands ac = null;
        for (ActionCommands a : ActionCommands.values()) {
            if(a.name().equals(e.getActionCommand())) {
                ac = a;
            }
        }
        switch (ac) {
            case quiz:
                System.out.println("Quiz");
                break;
            case game:
                System.out.println("Game");
                break;
            case fragenVerwalten:
                System.out.println("FragenVerwalten");
                view.mainHideWestPanel();
                break;
            case statistic:
                System.out.println("Statistik");
                view.mainHideEastPanel();
                break;

            case menuBarSpeichern:
                System.out.println("Speichern");
                break;
            case menuBarLaden:
                System.out.println("Laden");
                break;
            case menuBarEinstellungen:
                System.out.println("Einstellungen");
                break;
            case menuBarMehr:
                System.out.println("Mehr");
                break;
            case menuBarDarkMode:
                System.out.println("Dark mode");
                break;
            case menuBarAnsichtseinstellungen:
                System.out.println("Ansichtseinstellungen");
                break;
            case menuBarGithub:
                System.out.println("GitHub");
                break;
            case menuBarAnleitung:
                System.out.println("Anleitung");
                break;
            case menuBarZurueck:
                System.out.println("Zurück");
                break;

            case manageQuestionPoolBtn:
                System.out.println("Fragepool verwalten verwalten");
        }
    }
}
