public abstract class Kupac extends Thread{
	
	private Random random = new Random();
	
	public double stanjeNaRacunu = 0;
	public boolean izabraoKorpu = false;
	public boolean izabraoKolica = false;
	
	public List<Artikal> izabraniArtikli = new ArrayList<>();
	
	@Override
	public void run(){
		
		while(izabraniArtikli.size() < 20){
			
			synchronized(Prodavnica.LOCK_KOLEKCIJA_ARTIKALA){
				int index = random.nextInt(Prodavnica.kolekcijaArtikala.size());
				izabraniArtikli.add( Prodavnica.kolekcijaArtikala.remove(index) );
			}
			try {
				sleep(100);
			}catch(Exception e){}
			
		}
		
		System.out.println(this+" je zavrsio sa odabirom artikala, odlazi do kase.");
		Kasa kasa = getKasa();
		
			synchronized(kasa.KASA_NOTIFY){
				kasa.redKupaca.offer(this);
				kasa.KASA_NOTIFY.notify();
			}
			synchronized(this){
				try{
					wait();
				}catch (InterruptedException e) {}
			}
		if (stanjeNaRacunu < 0) { System.out.println(this+ "nije imao dovoljno novca za transakciju. Kupovina neuspjesna");	return;}
		else { System.out.println(this+" je uspjesno zavrsio sa kupovinom."); return;	}
		
	}
	
	public boolean imaPrioritet(){return false;}
	public boolean procesibilanOdStraneSvihKasa(){return false;}
	public double  getVrijednostKorpe() {
		
		double sum = 0;
		for(Artikal artikal : izabraniArtikli)
			sum += artikal.getCijenaArtikla();
		
		return sum;
	}
	public Kasa getKasa(){
		
		if (procesibilanOdStraneSvihKasa()) 
			return Prodavnica.kolekcijaKasa.stream()
								.min(Comparator.comparing(kasa -> kasa.redKupaca.size()))
								.get();
		else
			return Prodavnica.kolekcijaKasa.stream()
								.filter(kasa -> kasa.mozeProcesiratiFizickaLica)
								.min(Comparator.comparing(kasa -> kasa.redKupaca.size()))
								.get();
							
	}
}