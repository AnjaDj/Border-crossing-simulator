import java.util.stream.*;
import java.util.*;
import java.io.*; 
import java.nio.file.*; 


public class StreamWork{
	
	public static void main(String[] args) throws IOException{
		
		Stream<String> stream1 = Files.lines((new File("rezultati.txt")).toPath());
			 
			Map<Object,List<String>> groupedByType = stream1.sorted( (s1,s2) -> s1.compareToIgnoreCase(s2) )	
													   .collect(Collectors.groupingBy( string -> { 
																						if (string.startsWith("C:")) return -1;
																						else return 1;
																					 }));
			groupedByType.forEach((o,l) -> {
				l.forEach(System.out::println);
			});
			
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		
		Stream<String> stream2 = Files.lines((new File("rezultati.txt")).toPath());
		
			stream2.filter( string -> {
									if (string.startsWith("C:")){
										
										int counter = 0;

										for(int i = 0; i < string.length(); i++)
											if (string.charAt(i) == File.separatorChar) counter++;
										
										if (counter == 4) return true;
										else return false;
									}
									return false;
								} )
					.forEach(System.out::println);
			
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			
		Stream<String> stream3 = Files.lines((new File("rezultati.txt")).toPath());	
		
			Map<Object,List<String>> groupedByExe = stream3.filter(string -> !string.contains(File.separator))
														   .collect(Collectors.groupingBy( string -> {
																										int i = string.lastIndexOf(".");
																										return string.substring(i+1);
														   } ));
			groupedByExe.forEach((o,l) -> {
				l.forEach(System.out::println);
			});
			
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			
		Stream<String> stream4 = Files.lines((new File("rezultati.txt")).toPath());
		
			long startsWithA = stream4.filter(string -> !string.contains(File.separator))
									 .filter(string -> (string.startsWith("A") || string.startsWith("a")))
									 .collect(Collectors.counting());
									 
			System.out.println(startsWithA);

		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			
		Stream<String> stream5 = Files.lines((new File("rezultati.txt")).toPath());
		
		Map<String,Long> m = stream5.filter(string -> !string.contains(File.separator))
						 .collect(Collectors.groupingBy( string -> {
																		int i = string.lastIndexOf(".");
																		return string.substring(0,i);
														   }, Collectors.counting()));
		groupedByType.forEach((s,i) -> {
				System.out.println(i+" "+s);
			});												   
														   
	}
}