package io;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Kandidat;
import model.Strana;

public class KandidatnaListina {

    public static ArrayList<Strana> nacitatKandidatnuListinu(String subor) {
        ArrayList<Strana> strany = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(subor))) {
            String riadok;
            Strana aktualnaStrana = null;

            while ((riadok = br.readLine()) != null) {
                // Ak je riadok prázdny, preskočime ho
                if (riadok.trim().isEmpty()) {
                    continue;
                }

                // Ak riadok končí dvojbodkou, ide o názov novej strany
                if (riadok.endsWith(":")) {
                    // Vytvoríme novú stranu s názvom bez dvojbodky
                    String nazovStrany = riadok.substring(0, riadok.length() - 1);
                    aktualnaStrana = new Strana(nazovStrany);
                    // Pridáme aktuálnu stranu do zoznamu
                    strany.add(aktualnaStrana);
                } else {
                    // Ak nie, ide o kandidáta, priradíme ho ku aktuálnej strane
                    if (aktualnaStrana != null) {
                        String[] udaje = riadok.trim().split(" ");
                        if (udaje.length >= 2) {
                            String meno = udaje[0];
                            String priezvisko = udaje[1];
                            Kandidat kandidat = new Kandidat(meno, priezvisko, aktualnaStrana);
                            aktualnaStrana.pridajKandidata(kandidat);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strany;
    }
}
