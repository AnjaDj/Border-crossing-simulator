public class RadnikNabavke extends Zaposleni{
	
	public RadnikNabavke(String ime, String prezime, int godineRada, int cijenaRada){
		super(ime,prezime,godineRada,cijenaRada);
	}
	
	@Override
	public String getMessage(){
		return "Upit za nabavku ";
	}
	@Override
	public long sleepTime(){
		return 3000;
	}
}