package model;

import java.util.Arrays;

// --------------------- Fragenpool ---------------------
public class Fragenpool {
    private Frage[] fragen;
    private String titel;
    private Statistik statistik; // Statistik für richtig/falsch

    public Fragenpool(String titel) {
        setTitel(titel);
        setFragen(new Frage[0]);
        this.statistik = new Statistik();
    }

    public Fragenpool(String titel, Frage[] fragen) {
        setTitel(titel);
        setFragen(fragen);
        this.statistik = new Statistik();
    }

    public void addFrage(Frage question) {
        this.fragen = Arrays.copyOf(fragen, fragen.length + 1);
        this.fragen[fragen.length - 1] = question;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Frage[] getFragen() {
        return fragen;
    }

    public void setFragen(Frage[] fragen) {
        this.fragen = fragen;
    }

    public Statistik getStatistik() {
        return statistik;
    }
}
