package volebne;
import javax.swing.JOptionPane;

import model.Premier;
import model.Prezident;
import model.Strana;

import java.util.ArrayList;
import java.util.Comparator;

public class Vysledky {
    private ArrayList<Strana> parlament;
    private Strana vitaznaStrana;
    private Premier premier;
    private Prezident prezident;

    public void urciVysledky(ArrayList<Strana> strany) {
        // Zoradenie strán podľa počtu hlasov
        strany.sort(Comparator.comparingInt(Strana::getPocetHlasov).reversed());

        // Určenie víťaznej strany
        vitaznaStrana = strany.get(0);

        // Priradenie prvých 5 strán do parlamentu
        parlament = new ArrayList<>(strany.subList(0, Math.min(5, strany.size())));

        // Určenie prezidenta (kandidát s najvyšším počtom hlasov celkovo, zo strán v parlamente)
        prezident = Prezident.urciPrezidenta(parlament);

        // Určenie premiéra
        premier = Premier.urciPremiera(vitaznaStrana, prezident);
    }

    public void vypisVysledky(ArrayList<Strana> strany) {
        // Pred výpisom výsledkov pre každú stranu aktualizujeme počet hlasov
        for (Strana strana : strany) {
            strana.spocitajHlasy();
        }
    
        StringBuilder sb = new StringBuilder();
        sb.append("Do parlamentu sa dostali strany:\n");
        for (Strana strana : parlament) {
            sb.append(strana.getNazov()).append("\n");
        }
        sb.append("\n");
        sb.append("Strana s najvyssim poctom hlasov: ").append(vitaznaStrana.getNazov()).append(" - ")
                .append(vitaznaStrana.getPocetHlasov()).append(" hlasov\n");
        sb.append("Prezidentom sa ");
        if (prezident != null) {
            if (prezident.getPohlavie().equals("muz")) {
                sb.append("stal: ").append(prezident.getMeno()).append(" ").append(prezident.getPriezvisko())
                .append(" zo strany ").append(prezident.getStrana().getNazov()).append(", ktory ziskal ")
                .append(prezident.getPocetHlasov()).append(" hlasov.\n");
            } else {
                sb.append("stala: ").append(prezident.getMeno()).append(" ").append(prezident.getPriezvisko())
                .append(" zo strany ").append(prezident.getStrana().getNazov()).append(", ktora ziskala ")
                .append(prezident.getPocetHlasov()).append(" hlasov.\n");
            }
        } else {
            sb.append("Ziadny kandidát zo strany v parlamente sa nestal prezidentom.\n");
        }
        sb.append("Premierom sa ");
        if (premier != null) {
            if (premier.getPohlavie().equals("muz")) {
                sb.append("stal: ").append(premier.getMeno()).append(" ").append(premier.getPriezvisko())
                .append(" zo strany ").append(premier.getStrana().getNazov()).append(", ktory ziskal ")
                .append(premier.getPocetHlasov()).append(" hlasov.\n");
            } else {
                sb.append("stala: ").append(premier.getMeno()).append(" ").append(premier.getPriezvisko())
                .append(" zo strany ").append(premier.getStrana().getNazov()).append(", ktora ziskala ")
                .append(premier.getPocetHlasov()).append(" hlasov.\n");
            }
        } else {
            sb.append("Ziadny kandidat zo strany v parlamente sa nestal premierom.\n");
        }
    
        // Zobrazenie výsledkov pomocou JOptionPane
        JOptionPane.showMessageDialog(null, sb.toString(), "Vysledky volieb", JOptionPane.PLAIN_MESSAGE);
    }    
}
