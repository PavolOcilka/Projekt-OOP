package volebne;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import generovanie.Hlasy;

import model.Kandidat;

import model.Strana;

public class Simulacia {

    public void spustitProgram(ArrayList<Strana> strany) {
        zoraditStrany(strany);
        generujHlasy(strany);
        zapisVysledkyDoSuboru(strany);
        urciAVypisVysledky(strany);
    }

    private void zoraditStrany(ArrayList<Strana> strany) {
        // Porovnávač na zoradenie strán podľa počtu hlasov
        Comparator<Strana> comparator = Comparator.comparingInt(Strana::getPocetHlasov);
        // Zoradenie strán zostupne
        strany.sort(comparator.reversed());
    }

    private void generujHlasy(ArrayList<Strana> strany) {
        Hlasy hlasyGenerator = new Hlasy();
        hlasyGenerator.generujHlasy(strany);
    }

    private void zapisVysledkyDoSuboru(ArrayList<Strana> strany) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Vysledky.txt"))) {
            // Zoradenie strán podľa počtu hlasov zostupne
            zoraditStrany(strany);
    
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
        Vysledky vysledky = new Vysledky();
        vysledky.urciVysledky(strany);
        vysledky.vypisVysledky(strany);
    }
}
