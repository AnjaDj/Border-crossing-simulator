public class RadnikProdaje extends Zaposleni{
	
	public RadnikProdaje(String ime, String prezime, int godineRada, int cijenaRada){
		super(ime,prezime,godineRada,cijenaRada);
	}
	
	@Override
	public String getMessage(){
		return "Ponuda za prodaju ";
	}
	@Override
	public long sleepTime(){
		return 3000;
	}
}