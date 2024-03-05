import java.io.*;
import java.util.*;
import java.nio.file.*;

public class FileWatcher extends Thread{

	@Override
	public void run(){

		try(WatchService service = FileSystems.getDefault().newWatchService()){

			Path path = Paths.get("folder");
			Map<WatchKey,Path> keyMap = new HashMap<>();
				keyMap.put(path.register(service,
												StandardWatchEventKinds.ENTRY_CREATE,
												StandardWatchEventKinds.ENTRY_DELETE,
												StandardWatchEventKinds.ENTRY_MODIFY),path);

			WatchKey watchKey;
			Path eventDir;		
			
			do{
				watchKey = service.take();
				eventDir = keyMap.get(watchKey);

				for(WatchEvent<?> event : watchKey.pollEvents()){

					WatchEvent.Kind<?> kind = event.kind();
					Path file = (Path)event.context();

					System.out.println(eventDir+": "+kind+": "+file);

					if(kind.equals(StandardWatchEventKinds.ENTRY_MODIFY) && file.endsWith("kontrola.txt")){ System.out.println("1");
						// ----- OBRADA....
					}
					if(kind.equals(StandardWatchEventKinds.ENTRY_CREATE) && file.endsWith("kontrola.txt")){ System.out.println("2");
						// ----- OBRADA....
					}
					if(kind.equals(StandardWatchEventKinds.ENTRY_DELETE) && file.endsWith("kontrola.txt")){ System.out.println("3");
						// ----- OBRADA....
					}
				}

			}while(watchKey.reset());

		}catch(Exception e){}

	}
	
}