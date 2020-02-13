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
}
