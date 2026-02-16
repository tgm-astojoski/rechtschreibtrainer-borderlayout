package model;

public class GameLogic {
    private Fragenpool pool;
    private Frage aktuelleFrage;
    private String aktuelleAntwort;
    private StringBuilder bekanntesBuchstaben;
    private StringBuilder versucheBuchstaben;
    private int versuche;
    private int maxVersuche;
    private boolean gewonnen;
    private boolean verloren;
    private int aktuelleFrageIndex;

    public GameLogic(Fragenpool pool, int maxVersuche) {
        this.pool = pool;
        this.maxVersuche = maxVersuche;
        this.versuche = 0;
        this.aktuelleFrageIndex = 0;
        this.gewonnen = false;
        this.verloren = false;
        this.versucheBuchstaben = new StringBuilder();

        neuesFrage();
    }

    private void neuesFrage() {
        if (pool != null && pool.getFragen() != null && aktuelleFrageIndex < pool.getFragen().length) {
            aktuelleFrage = pool.getFragen()[aktuelleFrageIndex];
            aktuelleAntwort = aktuelleFrage.getAnswer().toUpperCase();

            // Initialisiere bekanntesBuchstaben mit Unterstrichen
            bekanntesBuchstaben = new StringBuilder();
            for (int i = 0; i < aktuelleAntwort.length(); i++) {
                char c = aktuelleAntwort.charAt(i);
                if (Character.isLetter(c)) {
                    bekanntesBuchstaben.append('_');
                } else {
                    // Leerzeichen und Sonderzeichen direkt anzeigen
                    bekanntesBuchstaben.append(c);
                }
            }

            versuche = 0;
            versucheBuchstaben = new StringBuilder();
            gewonnen = false;
            verloren = false;
        }
    }

    public boolean versucheBuchstabe(String eingabe) {
        if (eingabe == null || eingabe.isEmpty()) {
            return false;
        }

        char buchstabe = eingabe.toUpperCase().charAt(0);

        // Prüfe ob Buchstabe bereits versucht wurde
        if (versucheBuchstaben.indexOf(String.valueOf(buchstabe)) != -1) {
            return false;
        }

        versucheBuchstaben.append(buchstabe);

        // Prüfe ob Buchstabe in der Antwort vorkommt
        boolean gefunden = false;
        for (int i = 0; i < aktuelleAntwort.length(); i++) {
            if (aktuelleAntwort.charAt(i) == buchstabe) {
                bekanntesBuchstaben.setCharAt(i, buchstabe);
                gefunden = true;
            }
        }

        if (!gefunden) {
            versuche++;
            if (versuche >= maxVersuche) {
                verloren = true;
            }
        }

        // Prüfe ob gewonnen
        if (bekanntesBuchstaben.indexOf("_") == -1) {
            gewonnen = true;
        }

        return gefunden;
    }

    public String getHinweis() {
        // Finde einen zufälligen nicht aufgedeckten Buchstaben
        for (int i = 0; i < bekanntesBuchstaben.length(); i++) {
            if (bekanntesBuchstaben.charAt(i) == '_') {
                char hinweisBuchstabe = aktuelleAntwort.charAt(i);
                versucheBuchstabe(String.valueOf(hinweisBuchstabe));
                return "Hinweis: " + hinweisBuchstabe;
            }
        }
        return "Keine Hinweise mehr verfügbar";
    }

    public void naechsteFrage() {
        aktuelleFrageIndex++;
        neuesFrage();
    }

    public String getBekanntesBuchstaben() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bekanntesBuchstaben.length(); i++) {
            result.append(bekanntesBuchstaben.charAt(i));
            if (i < bekanntesBuchstaben.length() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public String getVersucheBuchstaben() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < versucheBuchstaben.length(); i++) {
            result.append(versucheBuchstaben.charAt(i));
            if (i < versucheBuchstaben.length() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    public int getVersuche() {
        return versuche;
    }

    public int getMaxVersuche() {
        return maxVersuche;
    }

    public int getVersucheUebrig() {
        return maxVersuche - versuche;
    }

    public boolean istGewonnen() {
        return gewonnen;
    }

    public boolean istVerloren() {
        return verloren;
    }

    public boolean istBeendet() {
        return gewonnen || verloren;
    }

    public String getAktuelleFrage() {
        return aktuelleFrage != null ? aktuelleFrage.getQuestion() : "";
    }

    public String getAktuelleAntwort() {
        return aktuelleAntwort;
    }

    public boolean hatNochFragen() {
        return aktuelleFrageIndex < pool.getFragen().length - 1;
    }
}