public class Steinschwert extends Waffe{
    public Steinschwert(){
        super("Steinschwert",20,"Stein", 400, 400);
    }
             public void repair() {
       haltb += 50;
       if (haltb > maxHaltb) {
          haltb = maxHaltb;
       } // end of if
    }
}
