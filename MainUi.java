import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUi extends JFrame {

    private Spielercharakter spieler;
    private JLabel koordinatenAnzeige;

    public MainUi(Spielercharakter spieler) {
        this.spieler = spieler;

        setTitle("RPG");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Koordinaten-Anzeige oben
        koordinatenAnzeige = new JLabel(getKoordText());
        koordinatenAnzeige.setHorizontalAlignment(SwingConstants.CENTER);
        add(koordinatenAnzeige, BorderLayout.NORTH);

        // MiniMap in der Mitte
        ImageIcon miniMapOriginal = new ImageIcon("miniMap.png");
        Image scaled = miniMapOriginal.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon miniMap = new ImageIcon(scaled);

        JLabel miniMapLabel = new JLabel(miniMap);
        miniMapLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Klick auf MiniMap öffnet große Karte
        miniMapLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                zeigeGrosseKarte();
            }
        });

        add(miniMapLabel, BorderLayout.CENTER);

        // Richtungsbuttons
        JButton nord = new JButton("↑ Nord");
        JButton sued = new JButton("↓ Süd");
        JButton west = new JButton("← West");
        JButton ost  = new JButton("→ Ost");

        nord.addActionListener(e -> bewegeChar("Nord"));
        sued.addActionListener(e -> bewegeChar("Süd"));
        west.addActionListener(e -> bewegeChar("West"));
        ost.addActionListener(e  -> bewegeChar("Ost"));

        // Steuerkreuz-Layout
        JPanel steuerkreuz = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        g.gridx = 1; g.gridy = 0; steuerkreuz.add(nord, g);
        g.gridx = 0; g.gridy = 1; steuerkreuz.add(west, g);
        g.gridx = 2; g.gridy = 1; steuerkreuz.add(ost,  g);
        g.gridx = 1; g.gridy = 2; steuerkreuz.add(sued, g);

        add(steuerkreuz, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void bewegeChar(String richtung) {
        switch (richtung) {
            case "Nord" -> spieler.koordinaten[1]++;
            case "Süd"  -> spieler.koordinaten[1]--;
            case "West" -> spieler.koordinaten[0]--;
            case "Ost"  -> spieler.koordinaten[0]++;
        }
        koordinatenAnzeige.setText(getKoordText());
    }

    private String getKoordText() {
        return "Position: X=" + spieler.koordinaten[0] + " Y=" + spieler.koordinaten[1];
    }

    private void zeigeGrosseKarte() {
        JFrame grosseKarte = new JFrame("Weltkarte");
        grosseKarte.setSize(1000, 1000);

        ImageIcon grosseIcon = new ImageIcon("miniMap.png");
        JLabel kartenLabel = new JLabel(grosseIcon);
        grosseKarte.add(kartenLabel);

        grosseKarte.setLocationRelativeTo(null);
        grosseKarte.setVisible(true);
    }
}