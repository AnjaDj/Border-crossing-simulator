public class Agencija extends Thread{
	
	public static Nekretnina aktuelnaPonuda;
	public static final Object NOVA_PONUDA_NOTIFY = new Object();

	@Override
	public void run(){
		try{
			
			sleep(1000);
			
			while(Simulacija.KRAJ == false){
				
				for(Nekretnina nekretnina : FileReaderGenerator.PONUDA_NEKRETNINA){
					
					synchronized(FileReaderGenerator.PONUDA_NEKRETNINA_LOCK){
						aktuelnaPonuda = nekretnina;
					}
					
					synchronized(NOVA_PONUDA_NOTIFY){
						NOVA_PONUDA_NOTIFY.notify();
					}
				
					sleep(2000);
				}
				
			}
		
	}catch(Exception e){e.printStackTrace();}
	
	}
}