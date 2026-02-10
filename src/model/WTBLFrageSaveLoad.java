package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WTBLFrageSaveLoad {
    private String defaultPfad;

    public WTBLFrageSaveLoad(String defaultPfad) {
        setDefaultPfad(defaultPfad);
    }

    public void saveQuestion(String path, String filename, Frage question) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path+"\\"+filename + ".txt")))){
            bw.write(question.toString());
        }
    }

    public String loadQuestion(String filename, String pfad){
        return "";
    }

    public String getDefaultPfad() {
        return defaultPfad;
    }

    public void setDefaultPfad(String defaultPfad) {
        this.defaultPfad = defaultPfad;
    }
}
