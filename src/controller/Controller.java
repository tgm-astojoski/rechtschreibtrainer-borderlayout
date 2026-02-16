package controller;

import model.QuizLogic;
import model.Statistik;
import view.RSTBLFrame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;


public class Controller {
    private QuizLogic model;
    private RSTBLFrame view;

    public Controller(){
        this.model = new QuizLogic();
        this.view = new RSTBLFrame(null, null);
        EventHandler handler = new EventHandler(view);
        view.setActionListener(handler);
        view.setVisible(true);
    }


    public static void main(String[] args) {
        Statistik.openURI();
        new Controller();

    }
}