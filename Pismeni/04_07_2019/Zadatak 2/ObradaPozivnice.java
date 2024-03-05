import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;


public class ObradaPozivnice extends Thread {
	
	public File file;
	
	public ObradaPozivnice(File file) {
		this.file = file;
	}
	
	public void run() {
		try {
		
		WatchService watcher = FileSystems.getDefault().newWatchService();
		Path dir = file.toPath();
		dir.register(watcher, ENTRY_CREATE);
		
			while(true) {
				
				try {
					WatchKey key = watcher.take();
				
					for(WatchEvent<?> event : key.pollEvents()) {
						WatchEvent.Kind<?> kind = event.kind();
						WatchEvent<Path> ev = (WatchEvent<Path>) event;
						Path path = ev.context();
						
						//System.out.println("Uvacen novi fajl: " + path.toString());
						
						String[] split = path.toString().split("@");
						String provajder = split[1];
						
						File direktorijumProvajdera = new File(provajder.substring(0,provajder.length() - 4));
						if(!direktorijumProvajdera.exists()) {
							direktorijumProvajdera.mkdirs();
						}
						
						String imePartnera = path.toString().split("#")[0];
						Files.copy(new File(Main.DOKUMENT_POZIVNICE + File.separator + path.toString()).toPath(), new File(direktorijumProvajdera.toPath() + File.separator + imePartnera + ".txt").toPath());
					}
					
					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				
					} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}