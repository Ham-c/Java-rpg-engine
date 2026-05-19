import java.util.Random;

public class Ertrunkener extends Zombie {

    public Ertrunkener() {
        this.typ = "Ertrunkener";
        this.erfahrung = 7;
        this.hp = 50;
        this.waffe = waffenGenerator();
        this.gold = 7;
    }

    public static Waffe waffenGenerator() {
        int r = (int) (Math.random() * 100);
        if (r < 50) {
            return new Faust();
        } else if (r < 80) {
            return new Trident();
        } else {
            return new Faust();
        }

    }
    public Boolean isSteveNear() {
     return true; 
    }
}
