import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import CheckIn.CheckInIOException;
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
	public void testFileReadSuccess() throws CheckInIOException {
		FileIO FileRead = new FileIO();
		ArrayList<String> contents = FileRead.readFile(validAsciiFile);
		assertEquals(contentLineSize, contents.size());
	}
	
	@Test(expected = CheckInIOException.class)
	public void testFileReadFileNotFound() throws CheckInIOException {
		FileIO FileRead = new FileIO();
		FileRead.readFile(invalidAsciiFile);
	}
	
	@Test
	public void testWriteFileSuccess() throws CheckInIOException {
		FileIO FileWrite = new FileIO();
		FileWrite.writeFile(testWriteFile, fileWriteContent);
		ArrayList<String> readContents = FileWrite.readFile(testWriteFile);
		assertEquals(contentTestSize, readContents.size());
		File file = new File(testWriteFile); 
		file.delete();
	}
	
	@Test(expected = CheckInIOException.class)
	public void testWriteFileNoContent() throws CheckInIOException {
		FileIO FileWrite = new FileIO();
		FileWrite.writeFile(testWriteFile, fileWriteEmptyContent);
	}

	@Test(expected = CheckInIOException.class)
	public void testWriteFileReadOnlyFail() throws CheckInIOException {
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
