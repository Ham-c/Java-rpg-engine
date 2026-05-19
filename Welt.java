import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class Welt {
    int spawnX;
    int spawnY;
    long seed;
    int difficulty;
    Spielercharakter spielercharakter;
    Monster monster;

    public Welt(int spawnX, int spawnY, long seed, Monster monster) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
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

        // Spielercharakter wird von Welt erstellt
        this.spielercharakter = new Steve();
        this.monster = monster;
    }

    public static void main(String[] args) {
        MusikPlayer.spieleMusik();
        Monster zombie = new Zombie();
        Welt welt = new Welt(0, 0, 12345L, zombie);
        JOptionPane.showMessageDialog(null, "Welt erstellt mit: " + welt.spielercharakter.name);
        System.out.println(welt.spielercharakter.armor);
        while (welt.spielercharakter.hp > 0) {
            welt.neuKampf();

            if (welt.spielercharakter.hp > 0) {
                welt.menü();
            }
        }

    }

    public void erfahrung() {
        if (difficulty == 1)
            spielercharakter.erfahrung += monster.erfahrung;
        else if (difficulty == 2) {
            spielercharakter.erfahrung += monster.erfahrung + monster.erfahrung / 4;
        } else if (difficulty == 3) {
            spielercharakter.erfahrung += monster.erfahrung + monster.erfahrung / 2;
        } else {
            spielercharakter.erfahrung += monster.erfahrung * 2;
        }
        stufeSpieler();
    }

    public void menü() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Was möchtest du tun?");
        System.out.println("1 - Weiterkämpfen");
        System.out.println("2 - Shop");
        if (spielercharakter.inventar.containsKey("Heiltrank") && spielercharakter.inventar.get("Heiltrank") > 0) {
            System.out.println("3 - Essen, du hast noch: " + spielercharakter.inventar.get("Heiltrank") + " Heilgetränke. (heilt 50 HP)");
        }
        int wahl = scanner.nextInt();
        if (wahl == 1) {
            MonsterSpawner();
            System.out.println("Ab zum nächsten kampf!");
        } else if (wahl == 2) {
            openShop();
        } else if (wahl == 3) {
            if (spielercharakter.inventar.get("Heiltrank") > 0) {
                spielercharakter.hp += 50;
                spielercharakter.inventar.put("Heiltrank", spielercharakter.inventar.get("Heiltrank") - 1);
                if (spielercharakter.hp > spielercharakter.maxHp) {
                    spielercharakter.hp = spielercharakter.maxHp;
                    MonsterSpawner();
                }
                MonsterSpawner();
            }

            else{
                System.out.println("Nicht genug Heilgetränke!");
                menü();
            }
        }
    }

    public void openShop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Du kannst kaufen:");
        System.out.println("1 - Dreizack. Schaden = " + new Trident().dam + " Haltbarkeit = " + new Trident().haltb + " Preis = 50Gold");
        System.out.println("2 - Heiltränke (heilt 50 HP). dein HP = " + spielercharakter.hp + " Preis = 5Gold");
        if (spielercharakter.waffe instanceof Faust) {
            System.out.println("3 - Holzschwert kaufen. Schaden = " + new Holzschwert().dam + "Haltbarkeit = " + new Holzschwert().haltb + "Preis = 10Gold");
        } else {
            System.out.println("3 - Schwert auf Stein verbessern. Schaden = " + new Steinschwert().dam + "Haltbarkeit = " + new Steinschwert().haltb + "Preis = 25Gold");
        }
//        System.out.println("Rüstung kaufen");
        int wahl = scanner.nextInt();
        if (wahl == 1) {
            if (spielercharakter.gold >= 50){
            spielercharakter.waffe = new Trident();
            System.out.println("Dreizack gekauft"); }
            else {
                System.out.println("Nicht genug Gold");
                openShop();
            }
        } else if (wahl == 2) {
            System.out.println("Wie viele Tränke möchtest du?");
            int anzahl = scanner.nextInt();
            if (spielercharakter.gold >= anzahl*5){
            spielercharakter.inventar.put("Heiltrank", spielercharakter.inventar.get("Heiltrank") + anzahl); }
            else {System.out.println("Nicht genug Gold");
            openShop();}
        } else {
            if (spielercharakter.gold >= 25){
            spielercharakter.waffe = new Steinschwert();}
            else {System.out.println("Nicht genug Gold");
            openShop();}
        }
        System.out.println("Ab zum nächsten kampf!");
        MonsterSpawner();
    } // ende von Shop

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

                System.out.println("Level Up! Neue Stufe: " + i);
            }
        }
    }

    public Object neuKampf() {
        Scanner scanner = new Scanner(System.in);
        boolean geflohen = false;
        int heilungRunden = 0;
        int creepAufladung = 0; // zählt wie weit der Creeper aufgeladen ist

        if (this.monster instanceof Creeper) {
            Creeper creep = (Creeper) this.monster;
            int explo = monster.getWaffe().dam + (difficulty * difficulty) * creep.explosionsdauer;
            System.out.println("Kampf hat begonnen! Es kämpfen " + spielercharakter.name + " gegen " + monster.typ);
            System.out.println("Dein HP: " + spielercharakter.hp + " | Dein Schaden: " + spielercharakter.getWaffe().dam);
            System.out.println("Monster HP: " + monster.hp + " | Explosionsschaden: " + explo);
        } else {
            System.out.println("Kampf hat begonnen! Es kämpfen " + spielercharakter.name + " gegen " + monster.typ);
            System.out.println("Dein HP: " + spielercharakter.hp + " | Dein Schaden: " + spielercharakter.getWaffe().dam);
            System.out.println("Monster HP: " + monster.hp + " | Monster Schaden: " + monster.getWaffe().dam * difficulty);
        }

        while (spielercharakter.hp > 0 && monster.hp > 0) {

            // Heilung über 2 Runden verarbeiten
            if (heilungRunden > 0) {
                spielercharakter.hp += 25;
                if (spielercharakter.hp > spielercharakter.maxHp) spielercharakter.hp = spielercharakter.maxHp;
                heilungRunden--;
                System.out.println("Heilung wirkt! +25 HP. Neues HP: " + spielercharakter.hp + " (noch " + heilungRunden + " Runden)");
            }

            // Kampfmenü
            System.out.println("\n--- Dein Zug ---");
            System.out.println("HP: " + spielercharakter.hp + " | Monster HP: " + monster.hp);
            if (this.monster instanceof Creeper) {
                Creeper creeper = (Creeper) this.monster;
                System.out.println("Creeper Aufladung: " + creepAufladung + " / " + creeper.explosionsdauer);
            }
            System.out.println("1 - Angreifen");
            if (spielercharakter.inventar.containsKey("Heiltrank") && spielercharakter.inventar.get("Heiltrank") > 0) {
                System.out.println("2 - Heiltrank benutzen (" + spielercharakter.inventar.get("Heiltrank") + " übrig, heilt über 2 Runden)");
            }
            System.out.println("3 - Fliehen");

            int wahl = scanner.nextInt();

            // --- Spieleraktion ---
            if (wahl == 1) {
                monster.hp -= spielercharakter.getWaffe().dam;
                spielercharakter.waffe.haltb -= 1;
                System.out.println("Du hast zugeschlagen! Schaden: " + spielercharakter.getWaffe().dam + " | Monster HP: " + monster.hp);
                if (monster.hp <= 0) {
                    System.out.println("Spieler hat gewonnen!");
                    erfahrung();
                    goldEinkommen();
                    break;
                }

            } else if (wahl == 2) {
                if (spielercharakter.inventar.containsKey("Heiltrank") && spielercharakter.inventar.get("Heiltrank") > 0) {
                    spielercharakter.inventar.put("Heiltrank", spielercharakter.inventar.get("Heiltrank") - 1);
                    heilungRunden = 2;
                    System.out.println("Heiltrank benutzt! Heilung wirkt über 2 Runden (+25 HP pro Runde).");
                } else {
                    System.out.println("Kein Heiltrank vorhanden! Zug verloren.");
                }

            } else if (wahl == 3) {
                System.out.println("Du versuchst zu fliehen...");
                double fluchChance = 0.5; // deine Formel hier
                if (Math.random() < fluchChance) {
                    System.out.println("Du bist erfolgreich geflohen!");
                    geflohen = true;
                    break;
                } else {
                    System.out.println("Flucht fehlgeschlagen!");
                }
            }

            // --- Monster Aktion
            if (monster.hp > 0 && spielercharakter.hp > 0) {
                if (this.monster instanceof Creeper) {
                    Creeper creeper = (Creeper) this.monster;
                    creepAufladung++;
                    System.out.println("Creeper lädt sich auf! (" + creepAufladung + " / " + creeper.explosionsdauer + ")");

                    if (creepAufladung >= creeper.explosionsdauer) {
                        // Creeper explodiert
                        int explosionsSchaden = monster.getWaffe().dam + (difficulty * difficulty) * creeper.explosionsdauer;
                        int reduzierterExplosionsSchaden = explosionsSchaden * (100 - spielercharakter.armor) / 100;
                        spielercharakter.hp -= reduzierterExplosionsSchaden;
                        System.out.println("Creeper ist explodiert! Explosionsschaden: " + reduzierterExplosionsSchaden);
                        System.out.println("Dein HP: " + spielercharakter.hp);
                        if (spielercharakter.hp <= 0) System.out.println("Monster hat gewonnen.");
                        break;
                    }

                } else {
                    int basisDamage = monster.getWaffe().dam * difficulty;
                    int reduzierterSchaden = basisDamage * (100 - spielercharakter.armor) / 100;
                    spielercharakter.hp -= reduzierterSchaden;
                    System.out.println("Monster hat zugeschlagen! Schaden: " + reduzierterSchaden + " | Dein HP: " + spielercharakter.hp);
                    if (spielercharakter.hp <= 0) {
                        System.out.println("Monster hat gewonnen.");
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

    } // Ende der Methode

    public void goldEinkommen(){
        spielercharakter.gold += this.monster.gold;
        System.out.println("Neues Gold: "+ spielercharakter.gold);
    }
}