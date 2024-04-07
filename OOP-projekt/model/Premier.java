package model;
import java.util.Comparator;

public class Premier extends Kandidat {
    private int pocetHlasov; // Pocet hlasov pre premiera

    public Premier(String meno, String priezvisko, Strana strana, int pocetHlasov) {
        super(meno, priezvisko, strana);
        this.pocetHlasov = pocetHlasov;
    }

    // Metóda pre získanie počtu hlasov
    public int getPocetHlasov() {
        return this.pocetHlasov;
    }

    // Metóda, ktorá vytvorí objekt typu Premier z objektu typu Kandidat
    public static Premier vytvorPremiera(Kandidat kandidat, int pocetHlasov) {
        return new Premier(kandidat.getMeno(), kandidat.getPriezvisko(), kandidat.getStrana(), pocetHlasov);
    }

    // Metóda na určenie premiéra
    public static Premier urciPremiera(Strana vitaznaStrana, Kandidat prezident) {
        Kandidat maxKandidat = vitaznaStrana.getKandidati().stream()
                .filter(kandidat -> !kandidat.equals(prezident))
                .max(Comparator.comparingInt(Kandidat::getPocetHlasov))
                .orElse(null);

        if (maxKandidat != null) {
            return vytvorPremiera(maxKandidat, maxKandidat.getPocetHlasov());
        } else {
            return null;
        }
    }
}
