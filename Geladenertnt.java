public class Geladenertnt extends Tnt{
    public Geladenertnt(){
        this.dam = 100;
        this.name = "Geladenertnt";
    }
             public void repair() {
       haltb += 50;
       if (haltb > maxHaltb) {
          haltb = maxHaltb;
       } // end of if
    }
}