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
    
    public abstract void repair();


    public int getDamage(){
        return this.dam;
    }
    //{
//       haltb += 50;
//       if (haltb > maxHaltb) {
//          haltb = maxHaltb;
//       } // end of if
//    }
}
