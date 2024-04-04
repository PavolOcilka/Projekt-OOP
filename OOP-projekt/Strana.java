import java.util.ArrayList;

public class Strana {
    private String nazov;
    private int pocetHlasov;
    private int pocetKandidatov;
    private ArrayList<Kandidat> kandidati;

    public Strana(String nazov) {
        this.nazov = nazov;
        this.pocetHlasov = 0;
        this.pocetKandidatov = 0;
        this.kandidati = new ArrayList<>();
    }

    public void pridajKandidata(Kandidat kandidat) { //Pridanie kandidáta do strany
        this.kandidati.add(kandidat);
        this.pocetKandidatov++;
    }

    public String getNazov() {
        return nazov;
    }

    public int getPocetHlasov() {
        return this.pocetHlasov;
    }

    public void pridajHlas() {
        this.pocetHlasov++;
    }

    public int getPocetKandidatov() {
        return pocetKandidatov;
    }

    public ArrayList<Kandidat> getKandidati() {
        return kandidati;
    }
    
    public void spocitajHlasy() { //Získame počet hlasov strany na základe počtu hlasov kandidátov
        this.pocetHlasov = 0; // Resetujeme počet hlasov
        for (Kandidat kandidat : kandidati) {
            this.pocetHlasov += kandidat.getPocetHlasov();
        }
    }
}
