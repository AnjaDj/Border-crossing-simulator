public class MaloljetnaOsoba extends Osoba implements java.io.Serializable{
	
	public OdraslaOsoba roditelj;
	
	public MaloljetnaOsoba(String ime, String prezime, String jmbg, OdraslaOsoba roditelj){
		super(ime, prezime, jmbg);
		this.roditelj = roditelj;
	}
	
	@Override
	public boolean podnosenjeZahtjevaZaPasos(){
		
		if (roditelj != null && !roditelj.imaKaznu && roditelj.novac >= tipUsluge.getCijenaUsluge()){
			super.podnosenjeZahtjevaZaPasos();
			return true;
		}else{
			System.out.println("Maloljetna osoba " + this + " je pokusala da podnese zahtjev za pasos bez prisustva roditelja!");
			return false;
		}
	}
	
	@Override
	public String toString(){
		return super.toString() + " " + roditelj;
	}
	
	@Override
	public int getVrijemeTrajanja(){
		return 3;
	}
	
	@Override
	public void setNovac(int novac){
		roditelj.setNovac(novac);
	}
	
}