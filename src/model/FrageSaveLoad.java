package model;

import java.io.*;
import java.util.Scanner;

// --------------------- FrageSaveLoad ---------------------
public class FrageSaveLoad {
    private String defaultPfad = "./questionPools";

    // ---------------- SAVE ----------------
    public void saveFragenpool(Fragenpool pool, Statistik statistik, String pfad, String filename) throws IOException {
        File file = new File(pfad, filename + ".txt");
        file.getParentFile().mkdirs();

        try (PrintWriter outputStream = new PrintWriter(file)) {
            // Titel
            outputStream.println(pool.getTitel());

            // Statistik
            outputStream.println("++++++++++++++++++++++");
            outputStream.println("fragenGesamt#" + statistik.getGesamt());
            outputStream.println("fragenRichtig#" + statistik.getRichtigeFragen());
            outputStream.println("fragenFalsch#" + statistik.getFalscheFragen());
            outputStream.println("++++++++++++++++++++++");

            // Fragen
            Frage[] fragen = pool.getFragen();
            for (int i = 0; i < fragen.length; i++) {
                Frage f = fragen[i];
                if (f != null) {
                    outputStream.println((i + 1) + "#" + f.getQuestion() + "#" + f.getAnswer());
                }
            }
        }
    }

    public void saveFragenpool(Fragenpool pool, Statistik statistik, String filename) throws IOException {
        saveFragenpool(pool, statistik, defaultPfad, filename);
    }

    // ---------------- LOAD ----------------
    public FragenpoolWithStat loadFragenpool(String pfad, String filename) throws IOException {
        File file = new File(pfad, filename + ".txt");
        if (!file.exists()) {
            throw new FileNotFoundException("Datei nicht gefunden: " + file.getAbsolutePath());
        }

        Fragenpool pool = null;
        Statistik statistik = new Statistik();

        try (Scanner inputStream = new Scanner(new BufferedReader(new FileReader(file)))) {
            if (!inputStream.hasNextLine()) return null;

            // Titel
            String titel = inputStream.nextLine();
            pool = new Fragenpool(titel);

            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine().trim();
                if (line.isEmpty() || line.startsWith("++++++++++++++++")) continue;

                if (line.startsWith("fragenGesamt#")) {
                    statistik.setRichtigeFragen(0); // dummy
                    statistik.setFalscheFragen(0); // wird gleich überschrieben
                } else if (line.startsWith("fragenRichtig#")) {
                    int richtig = Integer.parseInt(line.split("#")[1]);
                    statistik.setRichtigeFragen(richtig);
                } else if (line.startsWith("fragenFalsch#")) {
                    int falsch = Integer.parseInt(line.split("#")[1]);
                    statistik.setFalscheFragen(falsch);
                } else {
                    // Frage laden
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter("#");
                    lineScanner.next(); // Nummer überspringen
                    String question = lineScanner.next();
                    String answer = lineScanner.next();
                    pool.addFrage(new Frage(question, answer));
                    lineScanner.close();
                }
            }
        }

        return new FragenpoolWithStat(pool, statistik);
    }

    public FragenpoolWithStat loadFragenpool(String filename) throws IOException {
        return loadFragenpool(defaultPfad, filename);
    }

    // Hilfsklasse, um Pool + Statistik zusammen zurückzugeben
    public static class FragenpoolWithStat {
        private final Fragenpool pool;
        private final Statistik statistik;

        public FragenpoolWithStat(Fragenpool pool, Statistik statistik) {
            this.pool = pool;
            this.statistik = statistik;
        }

        public Fragenpool getPool() {
            return pool;
        }

        public Statistik getStatistik() {
            return statistik;
        }
    }
}
