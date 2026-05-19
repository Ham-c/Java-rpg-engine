import javax.swing.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TestWelt {
    int spawnX;
    int spawnY;
    long seed;
    int difficulty;
    Spielercharakter spielercharakter;
    Monster monster;

    public TestWelt(int spawnX, int spawnY, long seed, Monster monster) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.seed = seed;
        this.seed = seed;
        this.difficulty = Integer.parseInt(
                JOptionPane.showInputDialog(
                        null,
                        "Choose the difficulty.\n\n" +
                                "1 = Easy\n" +
                                "2 = Normal\n" +
                                "3 = Hard\n" +
                                "4 = Hardcore\n\n" +
                                "Monsters deal more damage and drop more experience.",
                        "Minecraft Difficulty",
                        JOptionPane.QUESTION_MESSAGE
                )
        );

        this.spielercharakter = new Steve();
        this.monster = monster;
    }

    public static void main(String[] args) {
        MusikPlayer.spieleMusik();
        Monster zombie = new Zombie();
        TestWelt welt = new TestWelt(0, 0, 12345L, zombie);
        JOptionPane.showMessageDialog(null,
                "Welt erstellt mit: " + welt.spielercharakter.name +
                        "\nRüstung: " + welt.spielercharakter.armor);

        while (welt.spielercharakter.hp > 0) {
            welt.kampf();

            if (welt.spielercharakter.hp > 0) {
                welt.menü();
            }
        }
    }

    public Object kampf() {
        // Kampfinfo anzeigen
        if (this.monster instanceof Creeper) {
            Creeper creep = (Creeper) this.monster;
            int explo = monster.getWaffe().dam + (difficulty * difficulty) * creep.explosionsdauer;
            JOptionPane.showMessageDialog(null,
                    "⚔️ Kampf hat begonnen!\n" +
                            spielercharakter.name + " vs " + monster.typ + "\n\n" +
                            "Dein HP: " + spielercharakter.hp + "  |  Dein Schaden: " + spielercharakter.getWaffe().dam + "\n" +
                            "Monster HP: " + monster.hp + "  |  Monster Schaden: " + explo);
        } else {
            JOptionPane.showMessageDialog(null,
                    "⚔️ Kampf hat begonnen!\n" +
                            spielercharakter.name + " vs " + monster.typ + "\n\n" +
                            "Dein HP: " + spielercharakter.hp + "  |  Dein Schaden: " + spielercharakter.getWaffe().dam + "\n" +
                            "Monster HP: " + monster.hp + "  |  Monster Schaden: " + (monster.getWaffe().dam * difficulty));
        }

        while (spielercharakter.hp > 0 && monster.hp > 0) {

            if (difficulty >= 4) {
                if (this.monster instanceof Creeper) {
                    Creeper creeper = (Creeper) this.monster;
                    JOptionPane.showMessageDialog(null,
                            "🧨 Creeper explodiert bei einer Aufladung von: " + creeper.explosionsdauer);
                    boolean creepTot = false;
                    for (int i = 0; i < creeper.explosionsdauer; i++) {
                        monster.hp -= spielercharakter.waffe.dam;
                        spielercharakter.waffe.haltb -= 1;
                        JOptionPane.showMessageDialog(null,
                                "🗡️ Du hast zugeschlagen! Schaden: " + spielercharakter.getWaffe().dam +
                                        "\nMonster HP: " + monster.hp);
                        if (monster.hp <= 0) {
                            JOptionPane.showMessageDialog(null, "🏆 Du hast gewonnen!");
                            erfahrung();
                            creepTot = true;
                            break;
                        }
                        JOptionPane.showMessageDialog(null,
                                "⚡ Creeper hat sich um " + (i + 1) + " aufgeladen!!");
                    }
                    if (creepTot) break;

                    int explosionsSchaden = monster.getWaffe().dam + (difficulty * difficulty) * creeper.explosionsdauer;
                    int reduzierterExplosionsSchaden = explosionsSchaden * (100 - spielercharakter.armor) / 100;
                    spielercharakter.hp -= reduzierterExplosionsSchaden;
                    JOptionPane.showMessageDialog(null,
                            "💥 Creeper ist explodiert!\nExplosionsschaden: " + reduzierterExplosionsSchaden +
                                    "\nDein HP: " + spielercharakter.hp);
                    if (spielercharakter.hp <= 0) {
                        JOptionPane.showMessageDialog(null, "💀 Monster hat gewonnen.");
                    }
                    break;

                } else {
                    int basisDamage = monster.getWaffe().dam * difficulty;
                    int reduzierterSchaden = basisDamage * (100 - spielercharakter.armor) / 100;

                    spielercharakter.hp -= reduzierterSchaden;
                    JOptionPane.showMessageDialog(null,
                            "👾 Monster hat zugeschlagen! Schaden: " + reduzierterSchaden +
                                    "\nDein HP: " + spielercharakter.hp);
                    if (spielercharakter.hp <= 0) {
                        JOptionPane.showMessageDialog(null, "💀 Monster hat gewonnen.");
                        break;
                    }

                    monster.hp -= spielercharakter.getWaffe().dam;
                    spielercharakter.waffe.haltb -= 1;
                    JOptionPane.showMessageDialog(null,
                            "🗡️ Du hast zugeschlagen! Schaden: " + spielercharakter.getWaffe().dam +
                                    "\nMonster HP: " + monster.hp);
                    if (monster.hp <= 0) {
                        JOptionPane.showMessageDialog(null, "🏆 Du hast gewonnen!");
                        erfahrung();
                        break;
                    }
                }

            } else { // difficulty < 4
                if (this.monster instanceof Creeper) {
                    Creeper creeper = (Creeper) this.monster;
                    JOptionPane.showMessageDialog(null,
                            "🧨 Creeper explodiert bei einer Aufladung von: " + creeper.explosionsdauer);
                    boolean creepTot = false;
                    for (int i = 0; i < creeper.explosionsdauer; i++) {
                        monster.hp -= spielercharakter.waffe.dam;
                        spielercharakter.waffe.haltb -= 1;
                        JOptionPane.showMessageDialog(null,
                                "🗡️ Du hast zugeschlagen! Schaden: " + spielercharakter.getWaffe().dam +
                                        "\nMonster HP: " + monster.hp);
                        if (monster.hp <= 0) {
                            JOptionPane.showMessageDialog(null, "🏆 Du hast gewonnen!");
                            erfahrung();
                            creepTot = true;
                            break;
                        }
                        JOptionPane.showMessageDialog(null,
                                "⚡ Creeper hat sich um " + (i + 1) + " aufgeladen!!");
                    }
                    if (creepTot) break;

                    int explosionsSchaden = monster.getWaffe().dam + (difficulty * difficulty) * creeper.explosionsdauer;
                    int reduzierterExplosionsSchaden = explosionsSchaden * (100 - spielercharakter.armor) / 100;
                    spielercharakter.hp -= reduzierterExplosionsSchaden;
                    JOptionPane.showMessageDialog(null,
                            "💥 Creeper ist explodiert!\nExplosionsschaden: " + reduzierterExplosionsSchaden +
                                    "\nDein HP: " + spielercharakter.hp);
                    if (spielercharakter.hp <= 0) {
                        JOptionPane.showMessageDialog(null, "💀 Monster hat gewonnen.");
                    }
                    break;

                } else {
                    monster.hp -= spielercharakter.getWaffe().dam;
                    spielercharakter.waffe.haltb -= 1;
                    JOptionPane.showMessageDialog(null,
                            "🗡️ Du hast zugeschlagen! Schaden: " + spielercharakter.getWaffe().dam +
                                    "\nMonster HP: " + monster.hp);
                    if (monster.hp <= 0) {
                        JOptionPane.showMessageDialog(null, "🏆 Du hast gewonnen!");
                        erfahrung();
                        break;
                    }

                    int basisDamage = monster.getWaffe().dam * difficulty;
                    int reduzierterSchaden = basisDamage * (100 - spielercharakter.armor) / 100;

                    spielercharakter.hp -= reduzierterSchaden;
                    JOptionPane.showMessageDialog(null,
                            "👾 Monster hat zugeschlagen! Schaden: " + reduzierterSchaden +
                                    "\nDein HP: " + spielercharakter.hp);
                    if (spielercharakter.hp <= 0) {
                        JOptionPane.showMessageDialog(null, "💀 Monster hat gewonnen.");
                        break;
                    }
                }
            }
        } // ende von while

        if (spielercharakter.hp > 0) {
            return spielercharakter;
        } else {
            return monster;
        }
    }

    public void erfahrung() {
        if (difficulty == 1)
            spielercharakter.erfahrung += monster.erfahrung;
        else if (difficulty == 2)
            spielercharakter.erfahrung += monster.erfahrung + monster.erfahrung / 4;
        else if (difficulty == 3)
            spielercharakter.erfahrung += monster.erfahrung + monster.erfahrung / 2;
        else
            spielercharakter.erfahrung += monster.erfahrung * 2;
        stufeSpieler();
    }

    public void menü() {
        String[] optionen;
        boolean hatTränke = spielercharakter.inventar.containsKey("Heiltrank")
                && spielercharakter.inventar.get("Heiltrank") > 0;

        if (hatTränke) {
            optionen = new String[]{
                    "⚔️ Weiterkämpfen",
                    "🛒 Shop",
                    "🧪 Heiltrank trinken (" + spielercharakter.inventar.get("Heiltrank") + " übrig, heilt 50 HP)"
            };
        } else {
            optionen = new String[]{"⚔️ Weiterkämpfen", "🛒 Shop"};
        }

        int wahl = JOptionPane.showOptionDialog(null,
                "Was möchtest du tun?\n\nHP: " + spielercharakter.hp + " / " + spielercharakter.maxHp,
                "Aktionsmenü",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionen,
                optionen[0]);

        if (wahl == 0) {
            MonsterSpawner();
            JOptionPane.showMessageDialog(null, "Ab zum nächsten Kampf!");
        } else if (wahl == 1) {
            openShop();
        } else if (wahl == 2 && hatTränke) {
            spielercharakter.hp += 50;
            spielercharakter.inventar.put("Heiltrank", spielercharakter.inventar.get("Heiltrank") - 1);
            if (spielercharakter.hp > spielercharakter.maxHp) {
                spielercharakter.hp = spielercharakter.maxHp;
            }
            JOptionPane.showMessageDialog(null,
                    "🧪 Heiltrank getrunken! HP: " + spielercharakter.hp + " / " + spielercharakter.maxHp);
            MonsterSpawner();
        }
    }

    public void openShop() {
        String[] shopOptionen = {
                "🔱 Dreizack – Schaden: " + new Trident().dam + " | Halt: " + new Trident().haltb + " | 50 Gold",
                "🧪 Heiltrank – heilt 50 HP | 5 Gold",
                spielercharakter.waffe instanceof Faust
                        ? "🗡️ Holzschwert – Schaden: " + new Holzschwert().dam + " | Halt: " + new Holzschwert().haltb + " | 10 Gold"
                        : "⚔️ Steinschwert – Schaden: " + new Steinschwert().dam + " | Halt: " + new Steinschwert().haltb + " | 25 Gold"
        };

        int wahl = JOptionPane.showOptionDialog(null,
                "🛒 Shop\nDein Gold: " + spielercharakter.gold + "  |  HP: " + spielercharakter.hp,
                "Shop",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                shopOptionen,
                shopOptionen[0]);

        if (wahl == 0) {
            spielercharakter.waffe = new Trident();
            JOptionPane.showMessageDialog(null, "🔱 Dreizack gekauft!");
        } else if (wahl == 1) {
            String eingabe = JOptionPane.showInputDialog(null,
                    "Wie viele Heilgetränke möchtest du kaufen?",
                    "Heiltränke kaufen",
                    JOptionPane.QUESTION_MESSAGE);
            if (eingabe != null && !eingabe.isEmpty()) {
                int anzahl = Integer.parseInt(eingabe);
                spielercharakter.inventar.put("Heiltrank",
                        spielercharakter.inventar.get("Heiltrank") + anzahl);
                JOptionPane.showMessageDialog(null, "🧪 " + anzahl + " Heiltränke gekauft!");
            }
        } else if (wahl == 2) {
            spielercharakter.waffe = (spielercharakter.waffe instanceof Faust)
                    ? new Holzschwert()
                    : new Steinschwert();
            JOptionPane.showMessageDialog(null, "⚔️ Schwert gekauft!");
        }

        JOptionPane.showMessageDialog(null, "Ab zum nächsten Kampf!");
        MonsterSpawner();
    }

    public void MonsterSpawner() {
        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(new Zombie());
        monsterList.add(new Ertrunkener());
        monsterList.add(new Creeper());
        int random = (int) (Math.random() * monsterList.size());
        this.monster = monsterList.get(random);
    }

    public void stufeSpieler() {
        int maxStufe = 30;
        for (int i = spielercharakter.stufe + 1; i <= maxStufe; i++) {
            if (spielercharakter.erfahrung >= 20 * i) {
                spielercharakter.stufe = i;
                spielercharakter.maxHp += 10;
                JOptionPane.showMessageDialog(null,
                        "⬆️ Level Up! Neue Stufe: " + i +
                                "\nMax HP erhöht auf: " + spielercharakter.maxHp);
            }
        }
    }
}