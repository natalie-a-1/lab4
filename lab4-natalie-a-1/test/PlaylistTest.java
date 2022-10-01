import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class PlaylistTest {

	@Test
	void testConstructorFile() throws IOException {
		String info0 = "Fake Plastic Trees; Radiohead; 04:52";
		String info1 = "Subterranean Homesick Alien; Radiohead; 04:27";
		String info2 = "2 + 2 = 5; Radiohead; 03:21";
		
		Playlist playlist = new Playlist("playlists/radiohead.txt");
		assertEquals(info0, playlist.getSong(0).toString());
		assertEquals(info1, playlist.getSong(1).toString());
		assertEquals(info2, playlist.getSong(2).toString());
		
		info0 = "One Big Holiday; My Morning Jacket; 05:21";
		info1 = "Steam Engine; My Morning Jacket; 07:26";
		info2 = "Tropics (Erase Traces); My Morning Jacket; 05:10";
		
		playlist = new Playlist("playlists/my-morning-jacket.txt");
		assertEquals(info0, playlist.getSong(0).toString());
		assertEquals(info1, playlist.getSong(1).toString());
		assertEquals(info2, playlist.getSong(2).toString());
	}
	
	@Test
	void testAddSongsFile() throws IOException {
		String info0 = "A Spoonful Weighs a Ton; The Flaming Lips; 03:32";
		String info1 = "What Is the Light?; The Flaming Lips; 04:05";
		String info2 = "Fight Test; The Flaming Lips; 04:14";
		String info3 = "Yoshimi Battles the Pink Robots, Pt. 1; "
				+ "The Flaming Lips; 04:45";
		String info4 = "Do You Realize??; The Flaming Lips; 03:32";
		String info5 = "The Yeah Yeah Yeah Song (With All Your Power); "
				+ "The Flaming Lips; 04:51";
		
		Playlist playlist = new Playlist();
		playlist.addSongs("playlists/the-flaming-lips-soft-bulletin.txt");
		assertEquals(info0, playlist.getSong(0).toString());
		assertEquals(info1, playlist.getSong(1).toString());
		
		playlist.addSong(new Song(info2));
		assertEquals(info0, playlist.getSong(0).toString());
		assertEquals(info1, playlist.getSong(1).toString());
		assertEquals(info2, playlist.getSong(2).toString());
		
		playlist.addSongs("playlists/the-flaming-lips-yoshimi.txt");
		assertEquals(info0, playlist.getSong(0).toString());
		assertEquals(info1, playlist.getSong(1).toString());
		assertEquals(info2, playlist.getSong(2).toString());
		assertEquals(info3, playlist.getSong(3).toString());
		assertEquals(info4, playlist.getSong(4).toString());
		
		playlist.addSong(new Song(info5));
		assertEquals(info0, playlist.getSong(0).toString());
		assertEquals(info1, playlist.getSong(1).toString());
		assertEquals(info2, playlist.getSong(2).toString());
		assertEquals(info3, playlist.getSong(3).toString());
		assertEquals(info4, playlist.getSong(4).toString());
		assertEquals(info5, playlist.getSong(5).toString());
	}
	
	@Test
	void testToString() {
		String info0 = "Dreams; Fleetwood Mac; 04:14";
		String info1 = "The Chain; Fleetwood Mac; 04:28";
		String info2 = "Silver Springs; Fleetwood Mac; 04:29";
		
		Playlist playlist = new Playlist();
		assertEquals("", playlist.toString());
		
		playlist.addSong(new Song(info0));
		String songStrings = info0;
		assertEquals(songStrings, playlist.toString());
		
		playlist.addSong(new Song(info1));
		songStrings = info0 + System.lineSeparator() + info1;
		assertEquals(songStrings, playlist.toString());
		
		playlist.addSong(new Song(info2));
		songStrings = info0 + System.lineSeparator() + info1 
				+ System.lineSeparator() + info2;
		assertEquals(songStrings, playlist.toString());
	}
	
	@Test
	void testSaveSongs() throws IOException {
		String info0 = "You're All I Need to Get By; "
				+ "Marvin Gaye & Tammi Terrell; 02:38";
		String info1 = "What's Going On; Marvin Gaye; 03:53";
		String info2 = "Trouble Man; Marvin Gaye; 03:49";
		String filename = "playlists/marvin-gaye.txt";
		
		Playlist playlist = new Playlist();
		playlist.addSong(new Song(info0));
		playlist.saveSongs(filename);
		String[] expectedLines = {info0};
		assertTrue(checkFile(expectedLines, filename));
		
		playlist.addSong(new Song(info1));
		playlist.saveSongs(filename);
		expectedLines = new String[] {info0, info1};
		assertTrue(checkFile(expectedLines, filename));
		
		playlist.addSong(new Song(info2));
		playlist.saveSongs(filename);
		expectedLines = new String[] {info0, info1, info2};
		assertTrue(checkFile(expectedLines, filename));
	}
	
	@Test
	void testGetTotalTime() {
		String info0 = "Her Majesty; The Beatles; 23";
		String info1 = "Johnny B. Goode; Chuck Berry; 02:41";
		String info2 = "A Farewell to Kings; Rush; 05:51";
		String info3 = "Can't You Hear Me Knocking; The Rolling Stones; 07:15";
		String info4 = "Stairway to Heaven; Led Zeppelin; 08:02";
		String info5 = "Close to the Edge; Yes; 18:42";
		String info6 = "Supper's Ready; Genesis; 23:06";
		String info7 = "Symphony No. 9; Ludwig van Beethoven; 01:09:00";
		
		Playlist playlist = new Playlist();
		assertArrayEquals(new int[] {0}, playlist.getTotalTime());
		
		playlist.addSong(new Song(info0));
		assertArrayEquals(new int[] {23}, playlist.getTotalTime());
		
		playlist.addSong(new Song(info1));
		assertArrayEquals(new int[] {4, 3}, playlist.getTotalTime());
		
		playlist.addSong(new Song(info2));
		assertArrayEquals(new int[] {55, 8}, playlist.getTotalTime());
		
		playlist.addSong(new Song(info3));
		assertArrayEquals(new int[] {10, 16}, playlist.getTotalTime());
		
		playlist.addSong(new Song(info4));
		assertArrayEquals(new int[] {12, 24}, playlist.getTotalTime());
		
		playlist.addSong(new Song(info5));
		assertArrayEquals(new int[] {54, 42}, playlist.getTotalTime());
		
		playlist.addSong(new Song(info6));
		assertArrayEquals(new int[] {0, 6, 1}, playlist.getTotalTime());
		
		playlist.addSong(new Song(info7));
		assertArrayEquals(new int[] {0, 15, 2}, playlist.getTotalTime());
	}

	// Define a helper method that checks the lines of a text file.
	private static boolean checkFile(String[] expectedLines, String filename) 
			throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		// Return false if any of the lines don't match.
		for (String expected : expectedLines) {
			String actual = reader.readLine();
			if (actual == null || !expected.equals(actual)) {
				reader.close();
				return false;
			}
		}
		
		reader.close();
		return true;
	}
}
