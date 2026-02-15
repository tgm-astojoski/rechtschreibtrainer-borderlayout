package controller;

import model.QuizLogic;
import view.RSTBLFrame;

public class Controller {
    private QuizLogic model;
    private RSTBLFrame view;

    public Controller(){
        this.model = new QuizLogic();
        this.view = new RSTBLFrame(null);
        EventHandler handler = new EventHandler(view);
        view.setActionListener(handler);
        view.setVisible(true);
    }

    public static void main(String[] args) {
        new Controller();
    }
}