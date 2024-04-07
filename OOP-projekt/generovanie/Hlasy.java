package generovanie;
import java.util.ArrayList;
import java.util.Random;

import model.Kandidat;
import model.Strana;

public class Hlasy {
    private Random random;

    public Hlasy() {
        this.random = new Random();
    }

    public void generujHlasy(ArrayList<Strana> strany) {
        for (Strana strana : strany) {
            for (Kandidat kandidat : strana.getKandidati()) {
                int nahodnyPocetHlasov = random.nextInt(1001); // Generuj náhodný počet hlasov od 0 do 1000
                for (int i = 0; i < nahodnyPocetHlasov; i++) {
                    kandidat.pridajHlas();
                }
            }
            strana.spocitajHlasy(); // Spocitaj celkovy pocet hlasov pre stranu
        }
    }
}
