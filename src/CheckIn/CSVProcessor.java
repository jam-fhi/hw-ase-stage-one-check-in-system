package CheckIn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CSVProcessor {

	private FileIO fileIO = new FileIO();
	
	public ArrayList<String[]> parseCSVToStringArray(String fileName) throws FileNotFoundException, IOException {
		ArrayList<String> fileLines = fileIO.readFile(fileName);
		ArrayList<String[]> fileLinesColumns = new ArrayList<String[]>();
		String[] lineColumns;
		Iterator<String> fileLinesIt = fileLines.iterator();
		while(fileLinesIt.hasNext()) {
			lineColumns = fileLinesIt.next().split(",");
			fileLinesColumns.add(lineColumns);
		}
		return fileLinesColumns;
	}
	
	public void parseStringArrayToCSV(String fileName, ArrayList<String[]> fileLine) throws IOException {
		ArrayList<String> fileContents = new ArrayList<String>();
		Iterator<String[]> fileLinesIt = fileLine.iterator();
		while(fileLinesIt.hasNext()) {
			String[] aLine = fileLinesIt.next();
			if(aLine.length > 0) {
				String lineContent = "";
				int columns = 0;
				while(columns < aLine.length) {
					lineContent += aLine[columns];
					lineContent += columns < (aLine.length - 1) ? "," : "";
					columns++;
				}
				fileContents.add(lineContent);
			} else {
				// TODO: Should test for an empty string?
				System.out.println("Skipping empty CSV line");
			}
		}
		fileIO.writeFile(fileName, fileContents);
	}
}
