public class Zombie extends Monster {
    String form;

    public Zombie() {
        super("Zombie", 40, waffenGenerator(), 5);
        this.form = "Normal";
        this.gold = 5;
    }

    public static Waffe waffenGenerator() {
        int r = (int) (Math.random() * 100);
        if (r < 80) {
            return new Faust();
        } else if (r < 100) {
            return new Holzschwert();
        } else {
            return new Faust();
        }

    }
    public Boolean isSteveNear() {
     return true; 
    }
}
