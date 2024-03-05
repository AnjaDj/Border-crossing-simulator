import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class AnalizaFoldera{
	
	public static String PUTANJA_ULAZNOG_DIREKTORIJUMA;
	
	public static void main(String[] args) throws Exception{
		
		long startTime = System.nanoTime();
		
		for(int i = 0; i < args.length; i++)
			if ("-d".equals(args[i])) PUTANJA_ULAZNOG_DIREKTORIJUMA = args[i+1]; 
		
		
		try (Stream<Path> cjelokupanSadrzajStream = Files.walk(Paths.get(PUTANJA_ULAZNOG_DIREKTORIJUMA)) ){
			
			List<Path> listaPutanjaDoSvihFoldera = 
													cjelokupanSadrzajStream.filter(path -> {
																							File file = path.toFile();
																							if (file.isDirectory()) return true;
																							else return false;
																							})
																			.collect(Collectors.toList());			

			Map<Path,List<File>> mapa = new HashMap<>();
			
				for(Path path : listaPutanjaDoSvihFoldera){
					
					List<File> temp = new ArrayList<>();
					System.out.println(path.toFile().getAbsolutePath());
					
					File[] sadrzajTekucegFoldera = (path.toFile()).listFiles();
					for(File f : sadrzajTekucegFoldera)
						if (!f.isDirectory()) { System.out.println("	"+f.getName()); temp.add(f);}
						
					System.out.println();
					
					mapa.put(path,temp);
				}						

				mapa.forEach((p,list) -> {
											System.out.print(p.toFile().getAbsolutePath()+" = ");
											System.out.println(list.stream().count());
					
				});
			
		}
	

		System.out.println("Vrijeme trajanja programa = "+(System.nanoTime() - startTime));	
	}
	
}