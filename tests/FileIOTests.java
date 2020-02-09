

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import CheckIn.FileIO;

public class FileIOTests {

	private String validAsciiFile = "bookings.csv";
	private String invalidAsciiFile = "noBookings.csv";
	private String testWriteFile = "test.csv";
	private String lineOne = "Line One";
	private String lineTwo = "Line Two";
	private String lineThree = "Line Three";
	private int contentTestSize = 3;
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
	
	@Test
	public void testWriteFileSuccess() throws IOException {
		FileIO FileWrite = new FileIO();
		ArrayList<String> fileContent = new ArrayList<String>();
		fileContent.add(lineOne);
		fileContent.add(lineTwo);
		fileContent.add(lineThree);
		FileWrite.writeFile(testWriteFile, fileContent);
		ArrayList<String> readContents = FileWrite.readFile(testWriteFile);
		assertEquals(contentTestSize, readContents.size());
		File file = new File(testWriteFile); 
		file.delete();
	}
	
	@Test(expected = IOException.class)
	public void testWriteFileNoContent() throws IOException {
		FileIO FileWrite = new FileIO();
		ArrayList<String> fileContent = new ArrayList<String>();
		FileWrite.writeFile(testWriteFile, fileContent);
	}

	// TODO
	// Test for IOException when overwriting a read-only file?
}
