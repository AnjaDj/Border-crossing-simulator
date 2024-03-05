package firma;
import java.util.*;

public class Skladiste{
	
	private String adresa;
	private List<Rafa> rafeUskladistu;
	private TipSkladista tip;
	
	public Skladiste(TipSkladista tip, String adresa, List<Rafa> rafeUskladistu){
		this.tip = tip;
		this.adresa = adresa;
		this.rafeUskladistu = rafeUskladistu;
	}
	
	public String getAdresa(){return adresa;}
	public TipSkladista getTipSkladista(){return tip;}
	public List<Rafa> getRafeUskladistu(){return rafeUskladistu;}
	
	
	
}

