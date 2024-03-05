package songs;

public class Song{
	
	private String songName, artistName, producerName, dateRelease;
	private int duration;
	
	public Song(String artistName, String songName, String producerName, String dateRelease, int duration){
		this.artistName = artistName;
		this.songName = songName;
		this.producerName = producerName;
		this.dateRelease = dateRelease;
		this.duration = duration;
	}
	
	@Override
	public String toString(){
		return "Song ->"+songName+" "+artistName+" "+producerName+" "+dateRelease+" "+duration+"sec";
	}
	
	public int getYearRelease(){
		String[] parts = dateRelease.split("-");
		return Integer.valueOf(parts[2]);
	}
	public String getArtistName(){
		return artistName;
	}
	public int getDuration(){
		return duration;
	}
}