import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GrafickeRozhranie extends JFrame {
    private ArrayList<Strana> strany;
    private Simulacia simulacia;

    public GrafickeRozhranie(ArrayList<Strana> strany) {
        this.strany = strany;
        this.simulacia = new Simulacia();
        zoraditStrany(); // Zoradenie strán podľa počtu hlasov pri vytvorení inštancie
        initializeUI(); // Vytvorenie používateľského rozhrania
    }

    private void initializeUI() {
        setTitle("Graficke rozhranie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        JButton vygenerujButton = new JButton("Vygeneruj hlasy");
        vygenerujButton.addActionListener(e -> generujHlasy(strany));

        JButton spustitButton = new JButton("Spusti program");
        spustitButton.addActionListener(e -> simulacia.spustitProgram(strany));

        JButton zobrazitButton = new JButton("Zobrazit kandidatnu listinu");
        zobrazitButton.addActionListener(e -> zobrazitKandidatnuListinu(strany));

        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(vygenerujButton)
                .addComponent(spustitButton));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(zobrazitButton));
        vGroup.addComponent(vygenerujButton).addComponent(spustitButton).addComponent(zobrazitButton);

        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);

        add(panel);
        setVisible(true);
    }

    private void zoraditStrany() {
        // Porovnávač na zoradenie strán podľa počtu hlasov
        Comparator<Strana> comparator = Comparator.comparingInt(Strana::getPocetHlasov);
        // Zoradenie strán zostupne
        strany.sort(comparator.reversed());
    }

    private void generujHlasy(ArrayList<Strana> strany) {
        Hlasy hlasyGenerator = new Hlasy();
        hlasyGenerator.generujHlasy(strany);
    }

    private void zobrazitKandidatnuListinu(ArrayList<Strana> strany) {
        StringBuilder sb = new StringBuilder();

        // Načítanie kandidátnej listiny zo súboru
        ArrayList<Strana> aktualneStrany = KandidatnaListina.nacitatKandidatnuListinu("Kandidatna_listina.txt");

        for (Strana strana : aktualneStrany) {
            sb.append(strana.getNazov()).append(":\n"); // Názov strany s dvojbodkou
            for (Kandidat kandidat : strana.getKandidati()) {
                sb.append(kandidat.getMeno()).append(" ").append(kandidat.getPriezvisko()).append("\n");
            }
            sb.append("\n"); // Medzera iba medzi stranami
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 250)); // Nastavenie preferovanej veľkosti JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JOptionPane.showMessageDialog(this, scrollPane, "Kandidatna listina", JOptionPane.PLAIN_MESSAGE);
    }
}
