public class Kandidat {
    private String meno;
    private String prezvisko;
    private int pocetHlasov;
    private Strana strana;

    public Kandidat(String meno, String prezvisko, Strana strana) {
        this.meno = meno;
        this.prezvisko = prezvisko;
        this.strana = strana;
        this.pocetHlasov = 0; // Počiatočný počet hlasov je 0
    }

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
        return prezvisko;
    }
    
    public Strana getStrana() {
        return strana;
    }
}