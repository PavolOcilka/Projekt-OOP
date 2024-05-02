package volebne;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import generovanie.Hlasy;
import model.Kandidat;
import model.Strana;

public class Simulacia {

    private Hlasy hlasyGenerator;
    private Vysledky vysledky;

    public Simulacia() {
        this.hlasyGenerator = new Hlasy();
        this.vysledky = new Vysledky();
    }

    public void spustitProgram(ArrayList<Strana> strany) {
        vynulujHlasy(strany);
        generujHlasy(strany);
        zoraditStrany(strany);
        zapisVysledkyDoSuboru(strany);
        urciAVypisVysledky(strany);
    }

    private void vynulujHlasy(ArrayList<Strana> strany) {
        for (Strana strana : strany) {
            strana.vynulujHlasy();
            for (Kandidat kandidat : strana.getKandidati()) {
                kandidat.vynulujHlasy();
            }
        }
    }

    private void generujHlasy(ArrayList<Strana> strany) {
        hlasyGenerator.generujHlasy(strany);
    }

    private void zoraditStrany(ArrayList<Strana> strany) {
        // Porovnávač na zoradenie strán podľa počtu hlasov
        Comparator<Strana> comparator = Comparator.comparingInt(Strana::getPocetHlasov);
        // Zoradenie strán zostupne
        strany.sort(comparator.reversed());
    }

    private void zapisVysledkyDoSuboru(ArrayList<Strana> strany) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Vysledky.txt"))) {
            for (Strana strana : strany) {
                writer.write(strana.getNazov() + " (" + strana.getPocetHlasov() + " hlasov)\n");
                ArrayList<Kandidat> kandidati = strana.getKandidati();
                // Zoradenie kandidátov v rámci strany podľa počtu hlasov zostupne
                kandidati.sort(Comparator.comparingInt(Kandidat::getPocetHlasov).reversed());
                for (Kandidat kandidat : kandidati) {
                    writer.write("  " + kandidat.getMeno() + " " + kandidat.getPriezvisko() +
                            " - " + kandidat.getPocetHlasov() + " hlasov\n");
                }
                writer.write("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    

    private void urciAVypisVysledky(ArrayList<Strana> strany) {
        vysledky.urciVysledky(strany);
        vysledky.vypisVysledky(strany);
    }
}
