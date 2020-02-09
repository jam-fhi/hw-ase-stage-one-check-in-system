

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import CheckIn.FileIO;

public class FileIOTests {

	private String validAsciiFile = "bookings.csv";
	private String invalidAsciiFile = "noBookings.csv";
	private int contentLineSize = 5;
	
	@Test
	public void testFileReadSuccess() throws IOException {
		FileIO FileRead = new FileIO();
		ArrayList<String> contents = FileRead.readFile(validAsciiFile);
		assertEquals(contentLineSize, contents.size());
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testFileReadFileNotFound() throws FileNotFoundException, IOException {
		FileIO FileRead = new FileIO();
		FileRead.readFile(invalidAsciiFile);
	}

}
