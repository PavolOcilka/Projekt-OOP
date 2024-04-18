import javax.swing.*;
import java.util.ArrayList;
import io.KandidatnaListina;
import model.*;
import ui.*;

public class Main {
    public static void main(String[] args) {
        // Načítanie kandidátnej listiny ze súboru
        ArrayList<Strana> strany = KandidatnaListina.nacitatKandidatnuListinu("Kandidatna_listina.txt"); 

        // Vytvorenie inštancie triedy GrafickeRozhranie s načítanými dátami
        SwingUtilities.invokeLater(() -> {
            new GrafickeRozhranie(strany);
        });
    }
}