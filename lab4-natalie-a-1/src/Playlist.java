import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class Playlist {

	private ArrayList<Song> songs;

	private Song[] songs2;

	private static final int EXPAND_THRESHOLD = 4;

	private int expandBy = 2;

	private int expandFrequency = 0;

	public Playlist() {

		songs = new ArrayList<Song>();

	}
	public Playlist(String filename) throws IOException {

		this();

		addSongs(filename);

	}

	public int getNumSongs() {

		return songs.size();
	}

	public Song getSong(int index) {

		if (index < 0 || index >= getNumSongs()) {

			return null;

		}
		return songs.get(index);
	}

	public Song[] getSongs() {

		return songs.toArray(new Song[0]);

	}

	public boolean addSong(Song song) {

		return addSong(songs.size(), song);

	}

	public boolean addSong(int index, Song song) {

		if (song == null || index < 0 || index > songs.size()) {

			return false;

		} else {

			songs.add(index, song);

		}

		return true;
	}

	public int addSongs(Playlist playlist) {

		if (playlist == null || playlist.getNumSongs() == 0) {

			return 0;

		} else {

			int count = playlist.getNumSongs();

			for (int i = 0; i < count; i++)

				this.addSong(playlist.getSong(i));

			return count;

		}
	}

	// addSongs received help from Abdullah Salous
	public int addSongs(String filename) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filename));

		int counter = 0;

		while (br.readLine() != null) {

			counter++;
		}

		br.close();

		BufferedReader brn = new BufferedReader(new FileReader(filename));

		for (int i = 0; i < counter; i++) {

			Song song = new Song(brn.readLine());

			songs.add(song);

		}
		brn.close();

		return counter;

	}

	public void expand() {

		if (expandFrequency >= EXPAND_THRESHOLD) {

			expandBy *= 2;
		}

		expandFrequency++;

		songs2 = Arrays.copyOf(songs2, songs2.length + expandBy);
	}

	public Song removeSong() {

		return removeSong(songs.size() - 1);
	}

	public Song removeSong(int index) {

		if (index >= songs.size() || index < 0 || songs.get(index) == null) {

			return null;

		} else {

			Song rid = songs.get(index);

			songs.remove(index);

			return rid;
		}
	}

	// Received help from Tanner Benbrook
	
	public void saveSongs(String filename) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		bw.write(toString());
		
		bw.close();
	}

	// Received help from tanner
	public int[] getTotalTime() {
		
		int[] totalTime = {0, 0, 0};

		for (Song song : songs) {

			int[] tempTime = song.getTime();

			for (int i = 0; i < tempTime.length; i++) {

				totalTime[i] += tempTime[i];
			}
		}

		if (totalTime[0] > 59) {

			totalTime[1] += (totalTime[0] / 60);

			totalTime[0] = (totalTime[0] % 60);
		}

		if (totalTime[1] > 59) {

			totalTime[2] += (totalTime[1] / 60);

			totalTime[1] = (totalTime[1] % 60);
		}

		if (totalTime[2] == 0 && totalTime[1] == 0) {

			totalTime = new int[] {totalTime[0]};

		} else if (totalTime[2] == 0) {

			totalTime = new int[] {totalTime[0], totalTime[1]};
		}

		return totalTime;
	}

	//
	@Override
	public String toString() {

		String output = "";


		if (songs != null) {

			// For loop Received help from Abdullah Salous

			for (int i = 0; i < getNumSongs(); i++) {

				if (i == getNumSongs() - 1) {

					output = output + songs.get(i).toString();

				} else {

					output = output + songs.get(i).toString() + System.lineSeparator();
				}
			}
		}
		return output;
	}

}
