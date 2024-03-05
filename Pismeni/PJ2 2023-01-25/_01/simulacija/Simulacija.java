package simulacija;
import liga.*;
import igraci.*;

public class Simulacija{
	
	public static void main(String[] args){
		
		Liga[] lige = new Liga[8];
		for(int i = 0; i < 8; i++)
			lige[i] = new Liga();
		
		for(int i = 0; i < 8; i++)
			lige[i].printLiga();


	}
}