public class Faust extends Waffe{
  public Faust(){
    super("Faust",10, "Körper", Integer.MAX_VALUE, Integer.MAX_VALUE);
  }
  public void repair() {
    haltb += 50;
    if (haltb > maxHaltb) {
      haltb = maxHaltb;
    } // end of if
  }
}
