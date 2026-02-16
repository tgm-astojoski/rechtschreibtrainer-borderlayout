package controller;

import model.FrageSaveLoad;
import model.Fragenpool;
import model.QuizLogic;
import view.RSTBLFrame;

import java.io.IOException;

public class Controller {

    private QuizLogic model;
    private RSTBLFrame view;

    public Controller(){

        this.view = new RSTBLFrame(null, null);

        EventHandler handler = new EventHandler(this, view);
        view.setActionListener(handler);

        view.setVisible(true);
    }

    public void ladeQuiz(String poolName){

        try {
            FrageSaveLoad loader = new FrageSaveLoad();
            FrageSaveLoad.FragenpoolWithStat data =
                    loader.loadFragenpool(poolName);

            Fragenpool pool = data.getPool();

            model = new QuizLogic(pool);

            view.setFragenpool(pool);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QuizLogic getModel() {
        return model;
    }

    public static void main(String[] args) {
        new Controller();
    }
}
