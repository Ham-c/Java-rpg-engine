public abstract class Waffe {
    String name;
    int dam;
    String material;
    int haltb;
    int maxHaltb;

    public Waffe(String name, int dam, String material, int haltb, int maxHaltb) {
        this.name = name;
        this.dam = dam;
        this.material = material;
        this.haltb = haltb;
    }
    
    public abstract void repair(); //{
//       haltb += 50;
//       if (haltb > maxHaltb) {
//          haltb = maxHaltb;
//       } // end of if
//    }
}
