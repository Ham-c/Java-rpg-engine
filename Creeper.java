public class Creeper extends Monster{
   int explosionsdauer;
   int radius;
   
   public Creeper(){
        super("Creeper", 100, geladener(), 10);
        this.explosionsdauer = 2;
        this.radius = 5;
        this.gold = 10;
     }
     
         public static Waffe geladener() {
        int r = (int) (Math.random() * 100);
        if (r < 80) {
            return new Tnt();
        } else {
            return new Geladenertnt();
        } 

    } 
    public Boolean isSteveNear() {
     return true; 
    }  
     
  }