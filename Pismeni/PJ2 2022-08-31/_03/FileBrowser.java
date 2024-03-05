import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.util.stream.*;

public class FileBrowser{
	
	private static void write(File file){
		
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("rezultati.txt"),true)))){
			
			if (file.isDirectory()) pw.println(file.getAbsolutePath());
			else pw.println(file.getName());
			
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private static void ioAlgoritam(File[] files, File searchFor, boolean write){
		
		for(int i = 0; i < files.length; i++){		
			
			if ( files[i].getName().equals(searchFor.getName()) ) { 
					
					if (write == true)  write(files[i]); 
						
				return; 	
			}
					
			if (files[i].isDirectory()) ioAlgoritam(files[i].listFiles(),searchFor,write);	
			
		}	
	}
	
	private static void nioAlgoritam(File pocetnifile, File searchFor, boolean write) throws IOException{
		
		Files.walkFileTree(pocetnifile.toPath(), new SimpleFileVisitor<Path>() {
			
														 @Override
														 public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
															 
															 if (  path.toFile().getName().equals(searchFor.getName()) ) {
																 
																 if (write == false) 
																	 return FileVisitResult.TERMINATE; 
																 else{	
																	 write(path.toFile()); 
																	 return FileVisitResult.TERMINATE; 
																 }
																 
															 }
															 
															 return FileVisitResult.CONTINUE;
													}});
							
	}
	
	public static void main(String[] args)throws IOException{
		
		String putanjaPocetnogFoldera = "", putanjaSearchFor = ""; 
		for(int i = 0; i < args.length; i++){
			if ("-p".equals(args[i])) putanjaPocetnogFoldera = args[i+1];
			if ("-s".equals(args[i])) putanjaSearchFor = args[i+1];
		}
			
		File file = new File(putanjaPocetnogFoldera);	
		File searchFor = new File(putanjaSearchFor);
		
		if(file.exists() && file.isDirectory()){
			
			File[] sadrzajPocetnogDirektorijuma = file.listFiles();
			
			long vrijemeIzvrsavanjaIOalgoritma = System.currentTimeMillis();
				ioAlgoritam(sadrzajPocetnogDirektorijuma,searchFor,false);
			vrijemeIzvrsavanjaIOalgoritma = System.currentTimeMillis() - vrijemeIzvrsavanjaIOalgoritma;
			
			long vrijemeIzvrsavanjaNIOalgoritma = System.currentTimeMillis();
				nioAlgoritam(file,searchFor,false);
			vrijemeIzvrsavanjaNIOalgoritma = System.currentTimeMillis() - vrijemeIzvrsavanjaNIOalgoritma;
			
			if (vrijemeIzvrsavanjaIOalgoritma < vrijemeIzvrsavanjaNIOalgoritma) { 
				
				System.out.println("IO algoritam je bio brzi");
				ioAlgoritam(sadrzajPocetnogDirektorijuma,searchFor,true); 
			}
			
			if (vrijemeIzvrsavanjaIOalgoritma >= vrijemeIzvrsavanjaNIOalgoritma)  { 
				
				System.out.println("NIO algoritam je bio brzi");
				nioAlgoritam(file,searchFor,true);  
			}
			
		}else
			System.out.println("Putanja pocetnog foldera NIJE VALIDNA!");
		
	}	
	
}