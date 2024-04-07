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

    public void pridajKandidata(Kandidat kandidat) {
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

    public void setKandidati(ArrayList<Kandidat> kandidati) {
        this.kandidati = kandidati;
    }

    public void spocitajHlasy() {
        // Vynulovanie počtu hlasov len ak je to potrebné
        if (this.pocetHlasov == 0) {
            for (Kandidat kandidat : kandidati) {
                this.pocetHlasov += kandidat.getPocetHlasov();
            }
        }
    }
}
