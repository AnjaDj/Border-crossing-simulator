import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class Song{

	String artistName, songName, producerName, date;
	int duration;
		
	public Song(String artistName, String songName, String producerName, String date, int duration){
		this.producerName = producerName;
		this.artistName = artistName;
		this.songName = songName;
		this.duration = duration;
		this.date = date;
	}	
	
	@Override
	public String toString(){
		return artistName + " " + songName + " " + producerName + " " + date + " " + duration;
	}
	
	public int getYear(){
		return Integer.valueOf( (date.split("-"))[2] );
	}
	public String getArtistName(){
		return artistName;
	}
	public int getDuration(){
		return duration;
	}
	
}