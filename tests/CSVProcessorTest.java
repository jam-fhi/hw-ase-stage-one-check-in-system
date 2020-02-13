import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import CheckIn.CSVProcessor;

public class CSVProcessorTest {
	
	private String validCSVFile = "bookings.csv";
	private String invalidCSVFile = "noBookings.csv";
	private int expectedRows = 5;
	private int expectedCols = 5;
	
	@Test
	public void testReadCSV() throws FileNotFoundException, IOException {
		CSVProcessor csvProc = new CSVProcessor();
		ArrayList<String[]> csvResult = csvProc.parseCSVToStringArray(validCSVFile);
		assertEquals(csvResult.size(), expectedRows);
		String[] csvRow = csvResult.remove(0);
		assertEquals(csvRow.length, expectedCols);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testReadCSVFileNotFound() throws FileNotFoundException, IOException {
		CSVProcessor csvProc = new CSVProcessor();
		csvProc.parseCSVToStringArray(invalidCSVFile);
	}
	
}
