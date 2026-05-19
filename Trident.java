public class Trident extends Waffe {

    public Trident() {
        super("Trident",40, "Wasserstahl", 250, 250);

    }
    
         public void repair() {
       haltb += 50;
       if (haltb > maxHaltb) {
          haltb = maxHaltb;
       } // end of if
    }
}
