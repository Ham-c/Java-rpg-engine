public class Trident extends Waffe {

public static int triDamage = 40;
public static int maxDurability = 250;

    public Trident() {
        super("Trident",triDamage, "Wasserstahl", 250, maxDurability);

    }
    
         public void repair() {
       haltb += 50;
       if (haltb > maxHaltb) {
          haltb = maxHaltb;
       } // end of if
    }
}
