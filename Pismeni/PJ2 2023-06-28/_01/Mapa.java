public class Mapa{

	public static Polje[][] PLOCA;
	public static int VELICINA_X, VELICINA_Y;
	public static final Object LOCK_MAP_OBJECT = new Object();
	
	static{
		
		VELICINA_X = Simulacija.X;
		VELICINA_Y = Simulacija.Y;
		PLOCA = new Polje[VELICINA_X][VELICINA_Y];
		
		for(int i = 0; i < VELICINA_X; i++){
			for(int j = 0; j <VELICINA_Y; j++)
				PLOCA[i][j] = new Polje();
		}
	}

}