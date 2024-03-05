public class FizickoLice extends Osoba implements java.io.Serializable{
	
	private String jmbg;
	private Vozilo vozilo = null;
	
	public FizickoLice(String prezime, String ime, String jmbg){
		super(prezime,ime);
		this.jmbg = jmbg;
	}
	
	public void setVozilaZaRegistraciju(Vozilo vozilo){this.vozilo = vozilo;}
	
	public String getID(){return jmbg;}
	
	@Override
	public int getNovacZaNaplatu(){
		return vozilo.getTipRegistracije().getCijenaUslugeRegistacije() + vozilo.getTaksa();
	}
}