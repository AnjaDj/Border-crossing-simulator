public class QuoteReader extends Thread{
	
	private QuoteStorage quoteStorage;
	private int brojIspisa = 0;
	
	public QuoteReader(QuoteStorage quoteStorage){
		this.quoteStorage = quoteStorage;
	}
	
	@Override
	public void run(){
		
		while(!DailyQuotes.END){
		
				synchronized(quoteStorage){
					System.out.println("Ispis_"+ ++brojIspisa+":");
					for( String citat : quoteStorage.skladisteCitata )
						System.out.println(citat);
					
				}
				try{
					sleep(2000);
				}catch(Exception e){}
		
		}
		System.out.println("QUOTE READER END");
	}
	
}