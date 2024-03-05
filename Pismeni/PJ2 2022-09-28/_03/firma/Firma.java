package firma;
import java.util.*;

public class Firma{
	
	private List<Skladiste> skladistaUfirmi;
	
	public Firma(List<Skladiste> skladistaUfirmi){
		this.skladistaUfirmi = skladistaUfirmi;
	}
	public List<Skladiste> getSkladistaUfirmi(){return skladistaUfirmi;}
	
}