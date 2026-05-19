public abstract class Monster {
    String typ;
    int hp;
    Waffe waffe;
    int erfahrung;
    int gold;

    public Monster(String typ, int hp, Waffe waffe, int erfahrung) {
        this.typ = typ;
        this.hp = hp;
        this.waffe = waffe;
        this.erfahrung = erfahrung;
        this.gold = gold;
    }

    public Waffe getWaffe() {
        return waffe;
    }
    
    public abstract Boolean isSteveNear();
}
