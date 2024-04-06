public class Kandidat {
    private String meno;
    private String priezvisko;
    private int pocetHlasov;
    private Strana strana;

    public Kandidat(String meno, String priezvisko, Strana strana) {
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.strana = strana;
        this.pocetHlasov = 0; // Počiatočný počet hlasov je 0
    }

    // Getters a setters

    public void pridajHlas() {
        this.pocetHlasov++;
        this.strana.pridajHlas(); // Zvýšenie počtu hlasov pre stranu
    }

    public int getPocetHlasov() {
        return this.pocetHlasov;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public Strana getStrana() {
        return strana;
    }

    // Metóda pre získanie pohlavia kandidáta
    public String getPohlavie() {
        if (meno.endsWith("a") || priezvisko.endsWith("a")) {
            return "zena";
        } else {
            return "muz";
        }
    }
}
