import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import CheckIn.CSVProcessor;
import CheckIn.CheckInIOException;

public class CSVProcessorTest {
	
	private String validCSVFile = "bookings.csv";
	private String invalidCSVFile = "noBookings.csv";
	private int expectedRows = 5;
	private int expectedCols = 4;
	private String colOneA = "Col One A";
	private String colTwoA = "Col Two A";
	private String colThreeA = "Col Three A";
	private String colOneB = "Col One B";
	private String colTwoB = "Col Two B";
	private String colThreeB = "Col Three B";
	private ArrayList<String[]> outputCSV;
	private String[] oneLine = {colOneA, colTwoA, colThreeA};
	private String[] twoLine = {colOneB, colTwoB, colThreeB};
	private int outputExpectedRows = 2;
	private int outputExpectedCols = 3;
	private String outputCSVFileName = "test.csv";
	private ArrayList<String[]> emptyCSV = new ArrayList<String[]>();
	
	@Before
	public void beforeEach() {
		outputCSV = new ArrayList<String[]>();
		outputCSV.add(oneLine);
		outputCSV.add(twoLine);
	}
	
	@Test
	public void testReadCSV() throws CheckInIOException {
		CSVProcessor csvProc = new CSVProcessor();
		ArrayList<String[]> csvResult = csvProc.parseCSVToStringArray(validCSVFile);
		assertEquals(csvResult.size(), expectedRows);
		String[] csvRow = csvResult.remove(0);
		assertEquals(csvRow.length, expectedCols);
	}
	
	@Test(expected = CheckInIOException.class)
	public void testReadCSVFileNotFound() throws CheckInIOException {
		CSVProcessor csvProc = new CSVProcessor();
		csvProc.parseCSVToStringArray(invalidCSVFile);
	}
	
	@Test
	public void testWriteCSV() throws CheckInIOException {
		CSVProcessor csvProc = new CSVProcessor();
		csvProc.parseStringArrayToCSV(outputCSVFileName, outputCSV);
		ArrayList<String[]> readCSVTest = csvProc.parseCSVToStringArray(outputCSVFileName);
		assertEquals(readCSVTest.size(), outputExpectedRows);
		String[] oneLine = readCSVTest.remove(0);
		assertEquals(oneLine.length, outputExpectedCols);
		File file = new File(outputCSVFileName); 
		file.delete();
	}
	
	@Test
	public void testWriteBlankCSV() throws CheckInIOException {
		CSVProcessor csvProc = new CSVProcessor();
		ArrayList<String[]> blankLine = new ArrayList<String[]>();
		String[] lineOne = {"One", "Two", "Three"};
		String[] lineTwo = {}; 
		String[] lineThree = {"One", "Two", "Three"};
		blankLine.add(lineOne);
		blankLine.add(lineTwo);
		blankLine.add(lineThree);
		csvProc.parseStringArrayToCSV(outputCSVFileName, blankLine);
		ArrayList<String[]> readCSVTest = csvProc.parseCSVToStringArray(outputCSVFileName);
		assertEquals(readCSVTest.size(), outputExpectedRows);
		String[] oneLine = readCSVTest.remove(0);
		assertEquals(oneLine.length, outputExpectedCols);
		File file = new File(outputCSVFileName); 
		file.delete();
	}
	
	@Test(expected = CheckInIOException.class)
	public void testWriteCSVFileNoContent() throws CheckInIOException {
		CSVProcessor csvProc = new CSVProcessor();
		csvProc.parseStringArrayToCSV(outputCSVFileName, emptyCSV);
	}

	@Test(expected = CheckInIOException.class)
	public void testWriteFileReadOnlyFail() throws CheckInIOException {
		try {
			CSVProcessor csvProc = new CSVProcessor();
			csvProc.parseStringArrayToCSV(outputCSVFileName, outputCSV);
			File file = new File(outputCSVFileName); 
			file.setReadOnly();
			csvProc.parseStringArrayToCSV(outputCSVFileName, outputCSV);
			fail("The file is still writable");
		} finally {
			File file = new File(outputCSVFileName); 
			file.delete();
		}
	}
}
