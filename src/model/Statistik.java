package model;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    public static void openURI(String uri) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(uri));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(new String[]{"xdg-open", uri});
            } catch (IOException e) {
                try {
                    runtime.exec(new String[]{"open", uri});
                }
                catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
            }
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
