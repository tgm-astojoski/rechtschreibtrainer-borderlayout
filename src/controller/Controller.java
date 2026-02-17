package controller;

import model.FrageSaveLoad;
import model.Fragenpool;
import model.GameLogic;
import model.QuizLogic;
import view.RSTBLFrame;

import java.io.IOException;

public class Controller {

    private QuizLogic quizModel;
    private GameLogic gameModel;
    private RSTBLFrame view;

    public Controller() {

        this.view = new RSTBLFrame(null, null);

        EventHandler handler = new EventHandler(this, view);
        view.setActionListener(handler);

        view.setVisible(true);
    }

    public void ladeQuiz(String poolName) {

        try {
            FrageSaveLoad loader = new FrageSaveLoad();

            // Entferne .txt Extension falls vorhanden
            String cleanFilename = poolName.replace(".txt", "");

            FrageSaveLoad.FragenpoolWithStat data =
                    loader.loadFragenpool("questionPools", cleanFilename);

            Fragenpool pool = data.getPool();

            quizModel = new QuizLogic(pool);

            view.setFragenpool(pool);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ladeGame(String poolName, int maxVersuche) {
        try {
            FrageSaveLoad loader = new FrageSaveLoad();

            // Entferne .txt Extension falls vorhanden
            String cleanFilename = poolName.replace(".txt", "");

            FrageSaveLoad.FragenpoolWithStat data =
                    loader.loadFragenpool("questionPools", cleanFilename);

            Fragenpool pool = data.getPool();

            gameModel = new GameLogic(pool, maxVersuche);

            // Initialisiere GamePanel mit der ersten Frage
            view.initializeGamePanel(
                    gameModel.getBekanntesBuchstaben(),
                    gameModel.getVersucheBuchstaben(),
                    gameModel.getVersucheUebrig(),
                    gameModel.getAktuelleFrage()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QuizLogic getQuizModel() {
        return quizModel;
    }

    public GameLogic getGameModel() {
        return gameModel;
    }

    public static void main(String[] args) {
        new Controller();
    }
}