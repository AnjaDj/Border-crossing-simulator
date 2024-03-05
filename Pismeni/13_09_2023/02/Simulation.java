import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Simulation{

	private static int MAXLine;
	private static boolean unique = false;
	private static String destinationFile = "";
	private static List<String> sourceFiles = new ArrayList<>();
	
	public static void main(String[] args){
		
		for(int i = 0; i < args.length; i++){
			
			if ("-dest".equals(args[i])) 
				destinationFile = args[i+1];
			
			if ("-limit".equals(args[i])) 
				MAXLine = Integer.valueOf(args[i+1]);
			
			if ("-unique".equals(args[i])) 
				unique = true;
			
			if ("-src".equals(args[i])){
				
				int j = i+1;
				while(j < args.length && !args[j].isEmpty() && !args[j].isBlank() && args[j].endsWith(".txt"))
				{
					sourceFiles.add(args[j]);
					j++;
				}
				
			}
			
		}
		
		if (destinationFile.isEmpty() || sourceFiles.size() < 1)
			return;
		
		if (!unique){
			for( Iterator<String> it = sourceFiles.iterator(); it.hasNext(); )
				try{
					List<String> contentOfSourceFile = Files.readAllLines(Paths.get(it.next()));
					Files.write(Paths.get(destinationFile), contentOfSourceFile, StandardOpenOption.APPEND);
				}catch(Exception e){
					e.printStackTrace();
				}
				return;
		}
		
		List<String> contentOfAllSourceFiles = new ArrayList<>();
		for( Iterator<String> it = sourceFiles.iterator(); it.hasNext(); )
				try{
					List<String> contentOfSourceFile = Files.readAllLines(Paths.get(it.next()));
					contentOfAllSourceFiles.addAll(contentOfSourceFile);
				}catch(Exception e){
					e.printStackTrace();
				}
				
		List<String> listWithoutDuplicates = new ArrayList<>(new HashSet<>(contentOfAllSourceFiles));		
		try{
			Files.write(Paths.get(destinationFile), listWithoutDuplicates, StandardOpenOption.APPEND);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}