import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Spielercharakter {
    String name;
    int hp;
    Waffe waffe;
    int erfahrung;
    Endsiedlung endsiedlung;
    int maxHp;
    Helm helm;
    Brustplatte brustplatte;
    Hose hose;
    Schuhe schuhe;
    int armor;
    int stufe;
    Map<String, Integer> inventar;
    int gold;
    int[] koordinaten = {0, 0}; //x, y koordinaten


    public Spielercharakter(String name, int hp, int maxHp, Waffe waffe, int erfahrung, Helm helm, Brustplatte brustplatte, Hose hose, Schuhe schuhe) {
        this.name = name;
        this.hp = hp;
        this.waffe = waffe;
        this.erfahrung = erfahrung;
        this.maxHp = maxHp;
        this.helm = helm;
        this.brustplatte = brustplatte;
        this.hose = hose;
        this.schuhe = schuhe;
        this.armor = helm.schutz + brustplatte.schutz + hose.schutz + schuhe.schutz;
        this.stufe = 0;
        this.inventar = new HashMap<>();
        this.gold = 0;
    }

    public void setEndsiedlung(Endsiedlung endsiedlung) {
        this.endsiedlung = endsiedlung;
    }

    public static Waffe setStandardWaffe() {
        return new Faust();
    }

    public Waffe getWaffe() {
        return waffe;
    }

    public Endsiedlung getEndsiedlung() {
        return endsiedlung;
    }
}
