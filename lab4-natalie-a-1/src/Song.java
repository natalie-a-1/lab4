import java.util.Arrays;
import java.util.StringJoiner;

public class Song {
	
	private String title;
	
	private String artist;
	
	private int[] duration;
	
	private final static String INFO_DELIMITER = "; ";
	
	private final static String TIME_DELIMITER = ":";
	
	public Song(String title, String artist, int[] time) {
		
		this.title = title;
		
		this.artist = artist;
		
		this.duration = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info) {
		
		//TODO:  Initialize a Song by parsing a String. Use split method.
		String[] arr = info.split(INFO_DELIMITER);
		
		title = arr[0];
		
		artist = arr[1];
		
		String[] temp = arr[2].split(TIME_DELIMITER);
		
		int[] numArr = new int[temp.length];
		
		for (int i = 0; i < temp.length ; i++) {
			
			numArr[temp.length-i-1] = Integer.parseInt(temp[i]);
		}
		duration = numArr;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public String getArtist() {
		
		return artist;
	}
	
	public int[] getTime() {
		
		return Arrays.copyOf(duration, duration.length);
	}
	//received help from Spencer on toString method
	@Override
	public String toString() {
		
		String temp;
		
		if (duration.length == 3) {
			
			temp = String.format("%02d", duration[2]) + TIME_DELIMITER + String.format("%02d", duration[1]) + TIME_DELIMITER + String.format("%02d", duration[0]);
			
		} else if (duration.length == 2) {
			
			temp = String.format("%02d", duration[1]) + TIME_DELIMITER + String.format("%02d", duration[0]);
			
		} else {
			
			temp = String.format("%02d", duration[0]);
		}
		
		String output = title + INFO_DELIMITER + artist + INFO_DELIMITER + temp;
		
		return output;
	}
}
