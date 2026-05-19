public class Holzschwert extends Waffe{
  public Holzschwert(){
    super("Holzschwert",15, "Holz", 200, 200);

  }
  public void repair() {
    haltb += 50;
    if (haltb > maxHaltb) {
      haltb = maxHaltb;
    } // end of if
  }
}
