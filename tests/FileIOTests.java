

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

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
	private ArrayList<String> fileWriteContent = new ArrayList<String>();
	private ArrayList<String> fileWriteEmptyContent = new ArrayList<String>();
	
	// TODO: Test for empty file
	// TODO: Test for binary file
	
	@Before
	public void beforeEach() {
		fileWriteContent.add(lineOne);
		fileWriteContent.add(lineTwo);
		fileWriteContent.add(lineThree);	
	}
	
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
		FileWrite.writeFile(testWriteFile, fileWriteContent);
		ArrayList<String> readContents = FileWrite.readFile(testWriteFile);
		assertEquals(contentTestSize, readContents.size());
		File file = new File(testWriteFile); 
		file.delete();
	}
	
	@Test(expected = IOException.class)
	public void testWriteFileNoContent() throws IOException {
		FileIO FileWrite = new FileIO();
		FileWrite.writeFile(testWriteFile, fileWriteEmptyContent);
	}

	@Test(expected = IOException.class)
	public void testWriteFileReadOnlyFail() throws IOException {
		try {
			FileIO FileWrite = new FileIO();
			FileWrite.writeFile(testWriteFile, fileWriteContent);
			File file = new File(testWriteFile); 
			file.setReadOnly();
			FileWrite.writeFile(testWriteFile, fileWriteContent);
			fail("The file is still writable");
		} finally {
			File file = new File(testWriteFile); 
			file.delete();
		}
	}
}
