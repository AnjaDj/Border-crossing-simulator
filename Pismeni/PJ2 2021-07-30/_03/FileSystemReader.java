import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.nio.file.*;

public class FileSystemReader extends Thread{
	
	private String DIR;
	private String zadatiPojam;
	private QuoteStorage quoteStorage;
	
	public FileSystemReader(QuoteStorage quoteStorage, String DIR, String zadatiPojam){
		this.quoteStorage = quoteStorage;
		this.zadatiPojam = zadatiPojam;
		this.DIR = DIR;
		this.setDaemon(true);
	}

	@Override
	public void run(){
		
		try{
			File[] sadrzajFoldera = (new File(DIR)).listFiles();
				for(File file : sadrzajFoldera){
					procesiranjeDatoteke(file);
				}
		}catch(Exception e){}		
		
		try(WatchService service = FileSystems.getDefault().newWatchService()){
			
			Map<WatchKey,Path> keyMap = new HashMap<>();
			Path path = Paths.get(DIR);
			keyMap.put(path.register(service,StandardWatchEventKinds.ENTRY_CREATE),path);
			
			WatchKey watchKey;
			Path eventDir;
			do{
				watchKey = service.take();
				eventDir = keyMap.get(watchKey);
				
				for(WatchEvent<?> event : watchKey.pollEvents()){
					WatchEvent.Kind<?> kind = event.kind();
					Path file = (Path)event.context();
					
					System.out.println(eventDir+": "+kind+": "+file);
					if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
						procesiranjeDatoteke(file.toFile());
						DailyQuotes.BROJ_NOVIH_FAJLOVA++;
					}
				}
			}while(watchKey.reset());
		
		}catch(Exception e){}
	}
	
	private void procesiranjeDatoteke(File file){
		
		try{ 
			List<String> linijeIzDatoteke = Files.readAllLines(file.toPath());
			List<String> linijeIzDatotekeSaZadatimPojmom = linijeIzDatoteke.stream()
																	.filter(string -> string.contains(zadatiPojam))
																	.collect(Collectors.toList());
			if (!linijeIzDatotekeSaZadatimPojmom.isEmpty())
				synchronized(quoteStorage){
					quoteStorage.skladisteCitata.addAll(linijeIzDatotekeSaZadatimPojmom);
				}
		}catch(Exception e){}
	}


}