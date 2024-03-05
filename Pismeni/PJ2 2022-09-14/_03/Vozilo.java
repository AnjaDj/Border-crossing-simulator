
public class Vozilo{
	
	private int godinaProizvodnje, snaga, brojSjedista;
	private Boja boja;
	private Tip tip;
	
	
	public Vozilo(Tip tip, int snaga, int godinaProizvodnje, int brojSjedista, Boja boja){
		this.tip = tip;
		this.boja = boja;
		this.snaga = snaga;
		this.brojSjedista = brojSjedista;
		this.godinaProizvodnje = godinaProizvodnje;
	}
	public int  getGodinaProizvodnje(){return godinaProizvodnje;}
	public int  getBrojSjedista(){return brojSjedista;}
	public int  getSnaga(){return snaga;}
	public Boja getBoja(){return boja;}
	public Tip  getTip() {return tip;}
	
	@Override
	public boolean equals(Object obj){
		
		Vozilo v = (Vozilo)obj;
		
		if ( (v.getTip() == tip) && (v.getBoja() == boja) && (v.getGodinaProizvodnje() == godinaProizvodnje) && 
		     (v.getSnaga() == snaga) && (v.getBrojSjedista() == brojSjedista) ) return true;
			 
		return false;
	}
	@Override
	public String toString(){
		return tip+" "+snaga+" "+godinaProizvodnje+" "+brojSjedista+" "+boja;
	}
	
}