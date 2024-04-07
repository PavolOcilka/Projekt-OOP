import java.util.Comparator;
import java.util.List;

public class Prezident extends Kandidat {
    private int pocetHlasov;

    public Prezident(String meno, String priezvisko, Strana strana) {
        super(meno, priezvisko, strana);
    }

    // Metóda pre nastavenie počtu hlasov prezidenta
    public void setPocetHlasov(int pocetHlasov) {
        this.pocetHlasov = pocetHlasov;
    }

    // Metóda pre získanie počtu hlasov
    public int getPocetHlasov() {
        return this.pocetHlasov;
    }
    
    // Metóda, ktorá vytvorí objekt typu Prezident z objektu typu Kandidat
    public static Prezident vytvorPrezidenta(Kandidat kandidat) {
        return new Prezident(kandidat.getMeno(), kandidat.getPriezvisko(), kandidat.getStrana());
    }

    public static Prezident urciPrezidenta(List<Strana> parlament) {
        Kandidat maxKandidat = parlament.stream()
                .flatMap(strana -> strana.getKandidati().stream())
                .max(Comparator.comparingInt(Kandidat::getPocetHlasov))
                .orElse(null);
    
        if (maxKandidat != null) {
            Prezident prezident = vytvorPrezidenta(maxKandidat);
            prezident.setPocetHlasov(maxKandidat.getPocetHlasov());
            return prezident;
        } else {
            return null;
        }
    }  
}
