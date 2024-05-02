package strategy;

import java.util.ArrayList;
import java.util.Random;
import model.Strana;

public class PredvolebnaKampan implements Kampan {
    private Random random;

    public PredvolebnaKampan() {
        this.random = new Random();
    }

    @Override
    public void spustitKampan(ArrayList<Strana> strany) {
        for (Strana strana : strany) {
            if (strana.getPocetKandidatov() >= 7 && strana.getPocetKandidatov() <= 9) {
                if (random.nextDouble() < 0.4) {
                    for (int i = 0; i < 600; i++) {
                        strana.pridajHlas();
                    }
                }
            } else if (strana.getPocetKandidatov() >= 4 && strana.getPocetKandidatov() <= 6) {
                if (random.nextDouble() < 0.4) {
                    for (int i = 0; i < 900; i++) {
                        strana.pridajHlas();
                    }
                }
            }
            strana.spocitajHlasy(); // Aktualizácia celkového počtu hlasov
        }
    }
}
