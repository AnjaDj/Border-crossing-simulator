package simulations;
import songs.Song;
import java.util.stream.*;
import java.io.*;
import java.util.*;

public class Simulation{
	
	public static List<Song> readFile(File file){
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
		
			List<Song> array = new ArrayList<>();
			String line = br.readLine();
			
			while(line != null){
				
				String[] parts = line.split("###");
				array.add(new Song(parts[0],parts[1],parts[2],parts[3],Integer.valueOf(parts[4])));
				line = br.readLine();
				
			}
			
			return array;
			
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void printList(List<Song> array){
		array.forEach(song -> System.out.println(song));
	}

	public static void main(String[] args){
		
		File file = new File("songs.txt");
		List<Song> songs = readFile(file);
		
		System.out.println("a)Kreirati listu pjesama grupisanih po godini objavljivanja");
		Map<Integer,List<Song>> byYear = songs.stream()
												.collect(Collectors.groupingBy(Song::getYearRelease));
												
								byYear.forEach((i,l)-> { 
															System.out.println("Year : "+i);
															l.forEach(song -> System.out.println(song));
															System.out.println();
															});
															
															
															
															
															
		System.out.println("b)Kreirati listu pjesama grupisanih po zadatim izvodjacima minimalno dva izvodjaca");	
		String artist1 = "Bon Iver", artist2 = "Zdravko Colic";
		Map<String, List<Song>> byArtist = songs.stream()
												.filter(song -> song.getArtistName().equals(artist1) || song.getArtistName().equals(artist2))
												.collect(
														Collectors.groupingBy(Song::getArtistName)
														);
														
								byArtist.forEach((s,l)-> { 
															System.out.println("Artist : "+s);
															l.forEach(song -> System.out.println(song));
															System.out.println();
															});						
														
														
														
														
														
														
		System.out.println("c)Kreirati listu pjesama cija je duzina trajanja veca od 100s");
		List<Song> filteredByDuration = songs.stream()
												.filter(song -> song.getDuration() > 100)
												.collect(Collectors.toList());
											
								filteredByDuration.forEach(System.out::println);	








		System.out.println("Kreirati listu pjesama objavljenih u 2. deceniji 2000-ih");				
		List<Song> filteredByYear = songs.stream()
												.filter(song -> song.getYearRelease() >= 2010 && song.getYearRelease() <= 2019) // Stream<Song>
												.collect(Collectors.toList());       //  R collect(Collector<T,A,R>)  Collector<T,A,List<T>> toList()
												
								filteredByYear.forEach(System.out::println);
								
		
		
		
		
		
		
		
		
		
		System.out.print("izracunati prosjecnu duzinu pjesama objavljenih 80ih godina = ");
		double averageDuration = songs.stream()
												.filter(song -> song.getYearRelease() >= 1980 && song.getYearRelease() <= 1989)
												.collect(Collectors.averagingInt(song -> song.getDuration())); //  R collect(Collector<T,A,R>)     
																					//  Collector<T,?,Double>  averagingInt(ToIntFunction<? super T> mapper)
		
								System.out.print(averageDuration);
	}
}