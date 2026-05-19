import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Testt {

    int spawnX;
    int spawnY;
    long seed;
    int difficulty;

    Spielercharakter spielercharakter;
    Monster monster;

    JFrame frame;
    JLabel hpLabel;
    JTextArea kampfLog;

    public Testt(int spawnX, int spawnY, long seed, Monster monster) {

        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.seed = seed;
        this.monster = monster;

        difficulty = Integer.parseInt(
                JOptionPane.showInputDialog(
                        null,
                        "Wähle Schwierigkeitsgrad\n\n" +
                                "1 = Einfach\n" +
                                "2 = Normal\n" +
                                "3 = Schwer\n" +
                                "4 = Hardcore",
                        "Minecraft",
                        JOptionPane.QUESTION_MESSAGE
                )
        );

        spielercharakter = new Steve();
    }

    public static void main(String[] args) {

        MusikPlayer.spieleMusik();

        Monster zombie = new Zombie();

        Testt welt = new Testt(0, 0, 12345L, zombie);

        welt.guiStarten();
    }

    public void guiStarten() {

        frame = new JFrame("Minecraft");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        hpLabel = new JLabel(
                "❤ HP: " + spielercharakter.hp + "/" + spielercharakter.maxHp
        );

        hpLabel.setBounds(20, 20, 300, 30);

        JButton kampfBtn = new JButton("⚔ Weiterkämpfen");
        kampfBtn.setBounds(20, 70, 200, 40);

        JButton shopBtn = new JButton("🛒 Shop");
        shopBtn.setBounds(20, 130, 200, 40);

        JButton heilBtn = new JButton(
                "🧪 Heiltrank (" +
                        spielercharakter.inventar.getOrDefault(
                                "Heiltrank",
                                0
                        ) + ")"
        );

        heilBtn.setBounds(20, 190, 200, 40);

        kampfLog = new JTextArea();

        kampfLog.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(kampfLog);

        scrollPane.setBounds(250, 20, 320, 400);

        kampfBtn.addActionListener(e -> {

            MonsterSpawner();

            kampf();

            hpLabel.setText(
                    "❤ HP: "
                            + spielercharakter.hp
                            + "/"
                            + spielercharakter.maxHp
            );
        });

        heilBtn.addActionListener(e -> {

            int anzahl =
                    spielercharakter.inventar.getOrDefault(
                            "Heiltrank",
                            0
                    );

            if (anzahl > 0) {

                spielercharakter.hp += 50;

                if (spielercharakter.hp > spielercharakter.maxHp) {
                    spielercharakter.hp =
                            spielercharakter.maxHp;
                }

                spielercharakter.inventar.put(
                        "Heiltrank",
                        anzahl - 1
                );

                kampfLog.append(
                        "🧪 Heiltrank benutzt!\n"
                );

                hpLabel.setText(
                        "❤ HP: "
                                + spielercharakter.hp
                                + "/"
                                + spielercharakter.maxHp
                );

                heilBtn.setText(
                        "🧪 Heiltrank (" +
                                spielercharakter.inventar.getOrDefault(
                                        "Heiltrank",
                                        0
                                ) + ")"
                );

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Keine Heiltränke mehr!"
                );
            }
        });

        shopBtn.addActionListener(e -> openShop());

        frame.add(hpLabel);
        frame.add(kampfBtn);
        frame.add(shopBtn);
        frame.add(heilBtn);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    public void kampf() {

        kampfLog.append(
                "\n👾 Ein "
                        + monster.typ
                        + " erscheint!\n"
        );

        while (spielercharakter.hp > 0
                && monster.hp > 0) {

            monster.hp -=
                    spielercharakter.getWaffe().dam;

            kampfLog.append(
                    "⚔ Du machst "
                            + spielercharakter.getWaffe().dam
                            + " Schaden!\n"
            );

            if (monster.hp <= 0) {

                kampfLog.append(
                        "🏆 Monster besiegt!\n"
                );

                erfahrung();

                break;
            }

            int damage =
                    monster.getWaffe().dam * difficulty;

            damage =
                    damage
                            * (100 - spielercharakter.armor)
                            / 100;

            spielercharakter.hp -= damage;

            kampfLog.append(
                    "💥 Monster macht "
                            + damage
                            + " Schaden!\n"
            );

            if (spielercharakter.hp <= 0) {

                kampfLog.append(
                        "☠ Du bist gestorben!\n"
                );

                JOptionPane.showMessageDialog(
                        frame,
                        "GAME OVER"
                );

                System.exit(0);
            }
        }
    }

    public void erfahrung() {

        if (difficulty == 1)
            spielercharakter.erfahrung +=
                    monster.erfahrung;

        else if (difficulty == 2)
            spielercharakter.erfahrung +=
                    monster.erfahrung
                            + monster.erfahrung / 4;

        else if (difficulty == 3)
            spielercharakter.erfahrung +=
                    monster.erfahrung
                            + monster.erfahrung / 2;

        else
            spielercharakter.erfahrung +=
                    monster.erfahrung * 2;

        kampfLog.append(
                "⭐ Erfahrung: "
                        + spielercharakter.erfahrung
                        + "\n"
        );
    }

    public void openShop() {

        String[] optionen = {
                "Holzschwert",
                "Steinschwert",
                "Heiltrank"
        };

        int auswahl =
                JOptionPane.showOptionDialog(
                        frame,
                        "Willkommen im Shop!",
                        "Shop",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        optionen,
                        optionen[0]
                );

        if (auswahl == 0) {

            spielercharakter.waffe =
                    new Holzschwert();

            kampfLog.append(
                    "🪵 Holzschwert gekauft!\n"
            );

        } else if (auswahl == 1) {

            spielercharakter.waffe =
                    new Steinschwert();

            kampfLog.append(
                    "🪨 Steinschwert gekauft!\n"
            );

        } else if (auswahl == 2) {

            int alte =
                    spielercharakter.inventar.getOrDefault(
                            "Heiltrank",
                            0
                    );

            spielercharakter.inventar.put(
                    "Heiltrank",
                    alte + 1
            );

            kampfLog.append(
                    "🧪 Heiltrank gekauft!\n"
            );
        }
    }

    public void MonsterSpawner() {

        List<Monster> monsterList =
                new ArrayList<>();

        monsterList.add(new Zombie());
        monsterList.add(new Ertrunkener());
        monsterList.add(new Creeper());

        int random =
                (int) (Math.random()
                        * monsterList.size());

        monster =
                monsterList.get(random);
    }

    public Spielercharakter getSpielercharakter() {
        return spielercharakter;
    }

    public Monster getMonster() {
        return monster;
    }
}
