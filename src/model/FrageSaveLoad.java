package model;

import java.io.*;
import java.util.Scanner;

public class FrageSaveLoad {

    private String defaultPfad = "./";

    // ---------------- SAVE ----------------

    public void saveFragenpool(Fragenpool pool, String filename) throws IOException {

        try (PrintWriter outputStream = new PrintWriter(filename + ".txt")) {

            // Titel speichern
            outputStream.println(pool.getTitel());

            // Trennlinie
            outputStream.println("++++++++++");

            // Fragen speichern
            for (Frage f : pool.getFragen()) {
                if (f != null) {
                    outputStream.println(f.toString());
                }
            }
        }
    }

    public void saveFragenpool(Fragenpool pool, String filename, String pfad) throws IOException {
        saveFragenpool(pool, pfad + File.separator + filename);
    }

    // ---------------- LOAD ---------------

    public Fragenpool loadFragenpool(String filename) throws IOException {

        Fragenpool pool = null;

        try (Scanner inputStream =
                     new Scanner(new BufferedReader(new FileReader(filename + ".txt")))) {

            if (!inputStream.hasNextLine()) {
                return null;
            }

            // Titel lesen
            String titel = inputStream.nextLine();
            pool = new Fragenpool(titel);

            while (inputStream.hasNextLine()) {

                String line = inputStream.nextLine();

                if (line.equals("++++++++++") || line.trim().isEmpty()) {
                    continue;
                }

                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("#");

                int imageFlag = Integer.parseInt(lineScanner.next());
                String question = lineScanner.next();
                String answer = lineScanner.next();

                boolean image = (imageFlag == 0);

                Frage frage = new Frage(question, answer, image);
                pool.addFrage(frage);

                lineScanner.close();
            }
        }

        return pool;
    }

    public Fragenpool loadFragenpool(String filename, String pfad) throws IOException {
        return loadFragenpool(pfad + File.separator + filename);
    }
}
