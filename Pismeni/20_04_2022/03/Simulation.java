import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Simulation{
	
	public static void main(String[] args){
		
		List<Song> songs = new ArrayList<>();
		try{
			List<String> contentOfFile = Files.readAllLines(Paths.get("songs.txt"));
			
			for(Iterator<String> it = contentOfFile.iterator(); it.hasNext(); ){
				
				String line = it.next();
				String[] parts = line.split("###");
				
				songs.add( new Song(parts[0], parts[1], parts[2], parts[3], Integer.valueOf(parts[4])) );
			}
				
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("a. Kreirati listu pjesama grupisanih po godini objavljivanja");
		Map<Integer, List<Song>> groupedByYear = songs.stream()
													  .collect(Collectors.groupingBy(Song::getYear));
		groupedByYear.forEach((year, list) -> {
												System.out.println(year);
												list.forEach(System.out::println);
												System.out.println();
		});
		
		System.out.println("b. Kreirati listu pjesama grupisanih po zadatim izvodjacima");
		Map<String, List<Song>> groupedByArtist = songs.stream()
													  .collect(Collectors.groupingBy(Song::getArtistName));
		groupedByArtist.forEach((artist, list) -> {
												System.out.println(artist);
												list.forEach(System.out::println);
												System.out.println();
		});
		
		System.out.println("c. Kreirati listu pjesama cija je duzina trajanja veca od 5min");
		List<Song> filtredByDuration = songs.stream()
													  .filter(song -> song.getDuration() > 300)
													  .toList();
		filtredByDuration.forEach(System.out::println);
												
		
		System.out.print("e. Izracunati prosjecnu duzinu pjesama objavljenih 80ih godina : ");
		Double averageDuration = songs.stream()
													  .filter(song -> song.getYear() >= 1980 &&  song.getYear() < 1990)
													  .mapToInt(Song::getDuration)
													  .average()
													  .getAsDouble();
		System.out.println(averageDuration);
		
		
	}
}