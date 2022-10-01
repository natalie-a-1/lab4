import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Lab3PlaylistTest {
	
	private static final String TITLE_1 = "Am I the Same Girl?";
	private static final String ARTIST_1 = "Barbara Acklin";
	private static final int[] TIME_1 = {56, 2};
	private static final Song SONG_1 = new Song(TITLE_1, ARTIST_1, TIME_1);
	
	private static final String TITLE_2 = "Kissing My Love";
	private static final String ARTIST_2 = "Bill Withers";
	private static final int[] TIME_2 = {49, 3};
	private static final Song SONG_2 = new Song(TITLE_2, ARTIST_2, TIME_2);
	
	private static final String TITLE_3 = "Feelin' Alright?";
	private static final String ARTIST_3 = "Joe Cocker";
	private static final int[] TIME_3 = {10, 4};
	private static final Song SONG_3 = new Song(TITLE_3, ARTIST_3, TIME_3);
	
	private static final String TITLE_4 = "Lean on Me";
	private static final String ARTIST_4 = "Bill Withers";
	private static final int[] TIME_4 = {17, 4};
	private static final Song SONG_4 = new Song(TITLE_4, ARTIST_4, TIME_4);
	
	private static final String TITLE_5 = "Little Wing";
	private static final String ARTIST_5 = "Stevie Ray Vaughan";
	private static final int[] TIME_5 = {49, 6};
	private static final Song SONG_5 = new Song(TITLE_5, ARTIST_5, TIME_5);
	
	@Test
	void testConstructor() {
		Playlist playlist = new Playlist();
		assertEquals(0, playlist.getNumSongs());
		assertArrayEquals(new Song[] {}, playlist.getSongs());
	}
	
	@Test
	void testAddSong1() {
		Playlist playlist = new Playlist();
		assertEquals(0, playlist.getNumSongs());
		assertNull(playlist.getSong(-1));
		assertNull(playlist.getSong(0));
		assertNull(playlist.getSong(1));
		
		assertFalse(playlist.addSong(null));
		assertEquals(0, playlist.getNumSongs());
		assertNull(playlist.getSong(0));
		
		assertTrue(playlist.addSong(SONG_1));
		assertEquals(1, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertNull(playlist.getSong(1));
		
		assertTrue(playlist.addSong(SONG_2));
		assertEquals(2, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertTrue(sameSong(SONG_2, playlist.getSong(1)));
		assertNull(playlist.getSong(2));
		
		assertTrue(playlist.addSong(SONG_3));
		assertEquals(3, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertTrue(sameSong(SONG_2, playlist.getSong(1)));
		assertTrue(sameSong(SONG_3, playlist.getSong(2)));
		assertNull(playlist.getSong(3));
		
		assertTrue(playlist.addSong(SONG_4));
		assertEquals(4, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertTrue(sameSong(SONG_2, playlist.getSong(1)));
		assertTrue(sameSong(SONG_3, playlist.getSong(2)));
		assertTrue(sameSong(SONG_4, playlist.getSong(3)));
		assertNull(playlist.getSong(4));
	}
	
	@Test
	void testAddSong2() {
		Playlist playlist = new Playlist();
		assertEquals(0, playlist.getNumSongs());
		
		assertTrue(playlist.addSong(0, SONG_1));
		assertEquals(1, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertNull(playlist.getSong(1));
		
		assertFalse(playlist.addSong(1, null));
		assertEquals(1, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertNull(playlist.getSong(1));
		
		assertFalse(playlist.addSong(-1, SONG_2));
		assertFalse(playlist.addSong(2, SONG_2));
		assertEquals(1, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertNull(playlist.getSong(1));
		
		assertTrue(playlist.addSong(0, SONG_2));
		assertEquals(2, playlist.getNumSongs());
		assertTrue(sameSong(SONG_2, playlist.getSong(0)));
		assertTrue(sameSong(SONG_1, playlist.getSong(1)));
		assertNull(playlist.getSong(2));
		
		assertTrue(playlist.addSong(1, SONG_3));
		assertEquals(3, playlist.getNumSongs());
		assertTrue(sameSong(SONG_2, playlist.getSong(0)));
		assertTrue(sameSong(SONG_3, playlist.getSong(1)));
		assertTrue(sameSong(SONG_1, playlist.getSong(2)));
		assertNull(playlist.getSong(3));
		
		assertFalse(playlist.addSong(4, SONG_4));
		assertEquals(3, playlist.getNumSongs());
		assertTrue(sameSong(SONG_2, playlist.getSong(0)));
		assertTrue(sameSong(SONG_3, playlist.getSong(1)));
		assertTrue(sameSong(SONG_1, playlist.getSong(2)));
		assertNull(playlist.getSong(3));
		
		assertTrue(playlist.addSong(3, SONG_4));
		assertEquals(4, playlist.getNumSongs());
		assertTrue(sameSong(SONG_2, playlist.getSong(0)));
		assertTrue(sameSong(SONG_3, playlist.getSong(1)));
		assertTrue(sameSong(SONG_1, playlist.getSong(2)));
		assertTrue(sameSong(SONG_4, playlist.getSong(3)));
		assertNull(playlist.getSong(4));
		
		assertTrue(playlist.addSong(2, SONG_5));
		assertEquals(5, playlist.getNumSongs());
		assertTrue(sameSong(SONG_2, playlist.getSong(0)));
		assertTrue(sameSong(SONG_3, playlist.getSong(1)));
		assertTrue(sameSong(SONG_5, playlist.getSong(2)));
		assertTrue(sameSong(SONG_1, playlist.getSong(3)));
		assertTrue(sameSong(SONG_4, playlist.getSong(4)));
		assertNull(playlist.getSong(5));
	}
	
	@Test
	void testAddSongs1() {
		Playlist playlist = new Playlist();
		
		Playlist other = null;
		assertEquals(0, playlist.addSongs(other));
		assertTrue(sameSongs(new Song[] {}, playlist.getSongs()));
		
		assertEquals(0, playlist.addSongs(playlist));
		assertTrue(sameSongs(new Song[] {}, playlist.getSongs()));
		
		playlist.addSong(SONG_1);
		assertTrue(sameSongs(new Song[] {SONG_1}, playlist.getSongs()));
		
		assertEquals(1, playlist.addSongs(playlist));
		assertTrue(sameSongs(new Song[] {SONG_1, SONG_1}, playlist.getSongs()));
		
		assertEquals(2, playlist.addSongs(playlist));
		assertTrue(sameSongs(new Song[] {SONG_1, SONG_1, SONG_1, SONG_1}, 
				playlist.getSongs()));
		
		assertEquals(4, playlist.addSongs(playlist));
		assertTrue(sameSongs(
				new Song[] {SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, 
						SONG_1, SONG_1}, 
				playlist.getSongs()));
		
		assertEquals(8, playlist.addSongs(playlist));
		assertTrue(sameSongs(
				new Song[] {SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, 
						SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, 
						SONG_1, SONG_1, SONG_1}, 
				playlist.getSongs()));
		
		assertEquals(0, playlist.addSongs(other));
		assertTrue(sameSongs(
				new Song[] {SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, 
						SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, SONG_1, 
						SONG_1, SONG_1, SONG_1}, 
				playlist.getSongs()));
	}
	
	@Test
	void testAddSongs2() {
		Playlist p1 = new Playlist();
		Playlist p2 = new Playlist();
		
		assertEquals(0, p1.addSongs(p2));
		assertEquals(0, p2.addSongs(p1));
		assertTrue(sameSongs(new Song[] {}, p1.getSongs()));
		assertTrue(sameSongs(new Song[] {}, p2.getSongs()));
		
		p1.addSong(SONG_1);
		p2.addSong(SONG_2);
		assertTrue(sameSongs(new Song[] {SONG_1}, p1.getSongs()));
		assertTrue(sameSongs(new Song[] {SONG_2}, p2.getSongs()));
		
		assertEquals(1, p1.addSongs(p2));
		assertEquals(2, p2.addSongs(p1));
		assertTrue(sameSongs(new Song[] {SONG_1, SONG_2}, p1.getSongs()));
		assertTrue(sameSongs(
				new Song[] {SONG_2, SONG_1, SONG_2}, 
				p2.getSongs()));
		
		assertEquals(3, p1.addSongs(p2));
		assertEquals(5, p2.addSongs(p1));
		assertTrue(sameSongs(
				new Song[] {SONG_1, SONG_2, SONG_2, SONG_1, SONG_2}, 
				p1.getSongs()));
		assertTrue(sameSongs(
				new Song[] {SONG_2, SONG_1, SONG_2, SONG_1, SONG_2, SONG_2, 
						SONG_1, SONG_2}, 
				p2.getSongs()));
		
		assertEquals(8, p1.addSongs(p2));
		assertEquals(13, p2.addSongs(p1));
		assertTrue(sameSongs(
				new Song[] {SONG_1, SONG_2, SONG_2, SONG_1, SONG_2, SONG_2, 
						SONG_1, SONG_2, SONG_1, SONG_2, SONG_2, SONG_1, 
						SONG_2}, 
				p1.getSongs()));
		assertTrue(sameSongs(
				new Song[] {SONG_2, SONG_1, SONG_2, SONG_1, SONG_2, SONG_2, 
						SONG_1, SONG_2, SONG_1, SONG_2, SONG_2, SONG_1, SONG_2, 
						SONG_2, SONG_1, SONG_2, SONG_1, SONG_2, SONG_2, SONG_1, 
						SONG_2}, 
				p2.getSongs()));
	}
	
	@Test
	void testRemoveSong1() {
		Playlist playlist = new Playlist();
		playlist.addSong(SONG_1);
		playlist.addSong(SONG_2);
		playlist.addSong(SONG_3);
		assertEquals(3, playlist.getNumSongs());
		
		assertTrue(sameSong(SONG_3, playlist.removeSong()));
		assertEquals(2, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertTrue(sameSong(SONG_2, playlist.getSong(1)));
		assertNull(playlist.getSong(2));
		
		assertTrue(sameSong(SONG_2, playlist.removeSong()));
		assertEquals(1, playlist.getNumSongs());
		assertTrue(sameSong(SONG_1, playlist.getSong(0)));
		assertNull(playlist.getSong(1));
		
		assertTrue(sameSong(SONG_1, playlist.removeSong()));
		assertEquals(0, playlist.getNumSongs());
		assertNull(playlist.getSong(0));
		
		assertNull(playlist.removeSong());
		assertEquals(0, playlist.getNumSongs());
	}
	
	@Test
	void testRemoveSong2() {
		Playlist playlist = new Playlist();
		playlist.addSong(SONG_1);
		playlist.addSong(SONG_2);
		playlist.addSong(SONG_3);
		playlist.addSong(SONG_4);
		playlist.addSong(SONG_5);
		
		assertTrue(sameSong(SONG_5, playlist.removeSong(4)));
		assertTrue(sameSongs(new Song[] {SONG_1, SONG_2, SONG_3, SONG_4}, 
				playlist.getSongs()));
		
		assertNull(playlist.removeSong(4));
		assertTrue(sameSongs(new Song[] {SONG_1, SONG_2, SONG_3, SONG_4}, 
				playlist.getSongs()));
		
		assertTrue(sameSong(SONG_1, playlist.removeSong(0)));
		assertTrue(sameSongs(new Song[] {SONG_2, SONG_3, SONG_4}, 
				playlist.getSongs()));
		
		assertTrue(sameSong(SONG_3, playlist.removeSong(1)));
		assertTrue(sameSongs(new Song[] {SONG_2, SONG_4}, playlist.getSongs()));
		
		assertTrue(sameSong(SONG_2, playlist.removeSong(0)));
		assertTrue(sameSongs(new Song[] {SONG_4}, playlist.getSongs()));
		
		assertTrue(sameSong(SONG_4, playlist.removeSong(0)));
		assertTrue(sameSongs(new Song[] {}, playlist.getSongs()));
		
		assertNull(playlist.removeSong(-1));
		assertTrue(sameSongs(new Song[] {}, playlist.getSongs()));
	}

	// Define a helper method that checks if two Songs are the same.
	private static boolean sameSong(Song song1, Song song2) {
		
		// Check that the titles are the same.
		String title1 = song1.getTitle();
		String title2 = song2.getTitle();
		if (!title1.equals(title2)) {
			return false;
		}
		
		// Check that the artists are the same.
		String artist1 = song1.getArtist();
		String artist2 = song2.getArtist();
		if (!artist1.equals(artist2)) {
			return false;
		}
		
		// Check that the times are the same (both length and elements).
		int[] time1 = song1.getTime();	
		int[] time2 = song2.getTime();
		if (time1.length != time2.length) {
			return false;
		}
		for (int idx = 0; idx < time1.length; ++idx) {
			if (time1[idx] != time2[idx]) {
				return false;
			}
		}
		
		// All the fields are the same, so song1 and song2 are the same Song.
		return true;
	}
	
	// Define a helper method that checks if two Song arrays are the same.
	private static boolean sameSongs(Song[] songs1, Song[] songs2) {
		
		// Check that the lengths are the same.
		if (songs1.length != songs2.length) {
			return false;
		}
		
		// Check that the corresponding elements are the same.
		for (int idx = 0; idx < songs1.length; ++idx) {
			if (!sameSong(songs1[idx], songs2[idx])) {
				return false;
			}
		}		
		return true;
	}
}
