import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class Lab3SongTest {

	@Test
	void testGetters() {
		String title = "The Sound of Muzak";
		String artist = "Porcupine Tree";
		int[] time = {59, 4};
		Song song = new Song(title, artist, time);
		assertEquals(title, song.getTitle());
		assertEquals(artist, song.getArtist());
		assertArrayEquals(time, song.getTime());
		
		title = "Her Majesty";
		artist = "The Beatles";
		time = new int[] {23};
		song = new Song(title, artist, time);
		assertEquals(title, song.getTitle());
		assertEquals(artist, song.getArtist());
		assertArrayEquals(time, song.getTime());
	}
	
	@Test
	void testImmutability() {
		String title = "Ten Years Gone";
		String artist = "Led Zeppelin";
		int seconds = 55;
		int minutes = 6;
		int[] time = {seconds, minutes};
		Song song = new Song(title, artist, time);
		
		time[0] = -12345;
		time[1] = -54321;
		assertEquals(seconds, song.getTime()[0]);
		assertEquals(minutes, song.getTime()[1]);
		
		int[] returnedTime = song.getTime();
		returnedTime[0] = -12345;
		returnedTime[1] = -54321;
		assertEquals(seconds, song.getTime()[0]);
		assertEquals(minutes, song.getTime()[1]);
	}
}
