package model;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

// --------------------- Statistik ---------------------
public class Statistik {
    private int richtigeFragen;
    private int falscheFragen;

    public Statistik() {
        richtigeFragen = 0;
        falscheFragen = 0;
    }

    public boolean pruefeAntwort(Frage frage, String benutzerAntwort) {
        if (frage.getAnswer().equalsIgnoreCase(benutzerAntwort)) {
            richtigeFragen++;
            return true;
        } else {
            falscheFragen++;
            return false;
        }
    }

    public int getRichtigeFragen() {
        return richtigeFragen;
    }

    public int getFalscheFragen() {
        return falscheFragen;
    }

    public int getGesamt() {
        return richtigeFragen + falscheFragen;
    }

    public void setRichtigeFragen(int richtigeFragen) {
        this.richtigeFragen = richtigeFragen;
    }

    public void setFalscheFragen(int falscheFragen) {
        this.falscheFragen = falscheFragen;
    }
}
