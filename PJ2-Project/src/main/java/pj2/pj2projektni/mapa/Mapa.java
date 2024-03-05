package pj2.pj2projektni.mapa;

public class Mapa {

    public static final Polje[] STAZA;
    public static final int VELICINA_STAZE = 50;

    static {
        STAZA = new Polje[VELICINA_STAZE];
        for(int i = 0; i < VELICINA_STAZE; i++)
            STAZA[i] = new Polje();
    }

}
