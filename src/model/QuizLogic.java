package model;

public class QuizLogic {

    private Fragenpool pool;
    private int aktuelleFrageIndex;

    public QuizLogic(Fragenpool pool) {
        this.pool = pool;
        this.aktuelleFrageIndex = 0;
    }

    public Frage getAktuelleFrage() {
        Frage[] fragen = pool.getFragen();

        if (aktuelleFrageIndex < fragen.length) {
            return fragen[aktuelleFrageIndex];
        }

        return null;
    }

    public boolean pruefeAntwort(String benutzerAntwort) {

        Frage frage = getAktuelleFrage();

        if (frage == null) {
            return false;
        }

        if (frage.getAnswer().equals(benutzerAntwort)) {
            return true;
        } else {
            return false;
        }
    }

    public void naechsteFrage() {
        aktuelleFrageIndex = aktuelleFrageIndex + 1;
    }

    public boolean istBeendet() {
        return aktuelleFrageIndex >= pool.getFragen().length;
    }

    public int getAktuelleFrageNummer() {
        return aktuelleFrageIndex + 1;
    }

    public int getGesamtFragen() {
        return pool.getFragen().length;
    }

    public Fragenpool getPool() {
        return this.pool;
    }

}
