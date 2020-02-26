import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import CheckIn.CheckInIOException;
import CheckIn.FileIO;

/**
 * FileIOTests
 * Test suite of test cases that will
 * test that the read and write functionality
 * provided by the file io class does indeed
 * work as expected.
 * @author jamiehill
 *
 */
public class FileIOTests {

	/**
	 *  Test file names
	 */
	private String validAsciiFile = "bookings.csv";
	private String invalidAsciiFile = "noBookings.csv";
	private String testWriteFile = "test.csv";
	
	/**
	 *  Line contents
	 */
	private String lineOne = "Line One";
	private String lineTwo = "Line Two";
	private String lineThree = "Line Three";
	
	/**
	 *  Expected file line counts for generated and existing files
	 */
	private int contentTestSize = 3;
	private int contentLineSize = 11;
	
	/**
	 *  Test file content variables.
	 */
	private ArrayList<String> fileWriteContent = new ArrayList<String>();
	private ArrayList<String> fileWriteEmptyContent = new ArrayList<String>();

	/**
	 * beforeEach
	 * Sets up test data that is used within each test.
	 * This runs before each test runs.
	 */
	@Before
	public void beforeEach() {
		/**
		 *  Build test data of array list of string arrays that we can write out
		 */
		fileWriteContent.add(lineOne);
		fileWriteContent.add(lineTwo);
		fileWriteContent.add(lineThree);	
	}
	
	/**
	 * testFileReadSuccess
	 * Tests to see if a file can be read successfully.
	 * @throws CheckInIOException
	 */
	@Test
	public void testFileReadSuccess() throws CheckInIOException {
		// Create a new file input output object.
		FileIO FileRead = new FileIO();
		// Read the specified file.
		ArrayList<String> contents = FileRead.readFile(validAsciiFile);
		// Assert that the content size is as expected.
		assertEquals(contentLineSize, contents.size());
	}
	
	/**
	 * testFileReadFileNotFound
	 * Tests to see if the correct exception
	 * is thrown when a non-existent filename
	 * is read.
	 * @throws CheckInIOException
	 */
	@Test(expected = CheckInIOException.class)
	public void testFileReadFileNotFound() throws CheckInIOException {
		/**
		 *  Create a new file input output object
		 */
		FileIO FileRead = new FileIO();
		/**
		 *  Try to read a file that does not exist
		 * the expect = in the @Test catches the 
		 * exception to pass the test
		 */
		FileRead.readFile(invalidAsciiFile);
		/**
		 * Fail the test if we get past the read statement.
		 */
		fail("File was read successfully. You should delete the file.");
	}
	
	/**
	 * testWriteFileSuccess
	 * Tests to see if a file can be written
	 * successfully. This is done by writing
	 * a file and then reading it back in to
	 * assert the expected content size.
	 * @throws CheckInIOException
	 */
	@Test
	public void testWriteFileSuccess() throws CheckInIOException {
		/**
		 *  Create a new file input output object
		 */
		FileIO FileWrite = new FileIO();
		/**
		 *  Write out the test file contents to the test file name
		 */
		FileWrite.writeFile(testWriteFile, fileWriteContent);
		/**
		 *  Read the content that was just outputed into a ArrayList of strings
		 */
		ArrayList<String> readContents = FileWrite.readFile(testWriteFile);
		/**
		 *  Assert that the size of the read content matches the expected
		 *  size of the outputed test content.
		 */
		assertEquals(contentTestSize, readContents.size());
		/**
		 *  Open the file that was just used in the test above
		 */
		File file = new File(testWriteFile); 
		/**
		 *  Delete the test file for test clean up
		 */
		file.delete();
	}
	
	/**
	 * testWriteFileNoContent
	 * Tests to see if the correct exception
	 * is thrown when no content is provided
	 * to be written out.
	 * @throws CheckInIOException
	 */
	@Test(expected = CheckInIOException.class)
	public void testWriteFileNoContent() throws CheckInIOException {
		/**
		 *  Create a new file input output object
		 */
		FileIO FileWrite = new FileIO();
		/**
		 *  Write empty contents to the file
		 * the writeFile method will throw an exception
		 * which is caught by @Test expected and
		 * allows the test to pass.
		 */
		FileWrite.writeFile(testWriteFile, fileWriteEmptyContent);
		/**
		 * Fail the test if we get past the write file statement
		 */
		fail("Test failed as the empty content was successfully written");
	}

	/**
	 * testWriteFileReadOnlyFail
	 * Tests to see if the file io class will
	 * properly handle a read only file when
	 * attempting to write out content.
	 * @throws CheckInIOException
	 */
	@Test(expected = CheckInIOException.class)
	public void testWriteFileReadOnlyFail() throws CheckInIOException {
		try {
			/**
			 *  Try to create a new file input output object
			 */
			FileIO FileWrite = new FileIO();
			/**
			 *  Write test content to the test file name
			 */
			FileWrite.writeFile(testWriteFile, fileWriteContent);
			/**
			 *  Open the file that was outputed
			 */
			File file = new File(testWriteFile);
			/**
			 *  Set the file properties to be read only
			 */
			file.setReadOnly();
			/**
			 *  Write to the file for a second time
		 	 * We expect the check io exception to be
			 * throw and the @Test expected will catch
			 * it to pass the tests
			 */
			FileWrite.writeFile(testWriteFile, fileWriteContent);
			/**
			 *  If not, then out tests will fail.
			 */
			fail("The file is still writable");
		} finally {
			/**
			 *  Finally, open the test file we just wrote.
			 */
			File file = new File(testWriteFile); 
			/**
			 *  Delete it as part of test clean up
			 */
			file.delete();
		}
	}
}
