public class Tnt extends Waffe{
    public Tnt(){
        super("Tnt",50, "schießpulver", 1, 1);
    }
    
             public void repair() {
       haltb += 50;
       if (haltb > maxHaltb) {
          haltb = maxHaltb;
       } // end of if
    }
}