public abstract class Kasa extends Thread{
	
	public final Object KASA_NOTIFY = new Object();
	public boolean mozeProcesiratiFizickaLica = false;
	public PriorityQueue<Kupac> redKupaca = new PriorityQueue<>(Comparator.comparing(kupac -> kupac.imaPrioritet()));
	
	@Override
	public void run(){
		
		while(true){
		
				if (redKupaca.isEmpty())
					synchronized(KASA_NOTIFY){
						try{
							KASA_NOTIFY.wait();
						}catch(Exception e){}
					}
				while(!redKupaca.isEmpty()){
					
					Kupac kupac = redKupaca.poll();
					try{
						sleep(1000);
					}catch(Exception e){}
					
					kupac.stanjeNaRacunu -= kupac.getVrijednostKorpe();
					synchronized(kupac){
						kupac.notify();
					}
					
				}		
		
		}
	}
	
}