public class OdraslaOsoba extends Osoba implements java.io.Serializable{
	
	public boolean imaKaznu = false;
	public int novac = 250;
	
	public OdraslaOsoba(String ime, String prezime, String jmbg){
		super(ime, prezime, jmbg);
	}
	
	@Override
	public void setNovac(int novac){
		this.novac = novac;
	}
	
	@Override
	public boolean podnosenjeZahtjevaZaPasos(){
		if (imaKaznu || novac < tipUsluge.getCijenaUsluge())
			return false;
		else
			super.podnosenjeZahtjevaZaPasos();
			return true;
	}
}