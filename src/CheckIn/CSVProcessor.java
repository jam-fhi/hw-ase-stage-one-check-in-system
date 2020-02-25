package CheckIn;

import java.util.ArrayList;
import java.util.Iterator;

public class CSVProcessor {

	private FileIO fileIO = new FileIO();
	
	public ArrayList<String[]> parseCSVToStringArray(String fileName) throws CheckInIOException {
		// Create array list of strings to store each line of the file.
		ArrayList<String> fileLines = fileIO.readFile(fileName);
		// Create array list of string arrays to store csv columns in the string array and each row of the file in the array list.
		ArrayList<String[]> fileLinesColumns = new ArrayList<String[]>();
		// Temporary storage for one line split into columns
		String[] lineColumns;
		// Get an iterator for the file input lines.
		Iterator<String> fileLinesIt = fileLines.iterator();
		// Loop through each line of the file
		while(fileLinesIt.hasNext()) {
			// Split a line on a comma into the temporary string array
			lineColumns = fileLinesIt.next().split(",");
			// Add the split string / columns to the array list for rows.
			fileLinesColumns.add(lineColumns);
		}
		// Return the array list of string arrays representing the csv file
		return fileLinesColumns;
	}
	
	public void parseStringArrayToCSV(String fileName, ArrayList<String[]> fileLine) throws CheckInIOException {
		// Create an array list of strings to store the output content
		ArrayList<String> fileContents = new ArrayList<String>();
		// Get an iterator to process the rows inputed via the fileLine parameter
		Iterator<String[]> fileLinesIt = fileLine.iterator();
		// Loop through each line of the output content
		while(fileLinesIt.hasNext()) {
			// Get the next line to process
			String[] aLine = fileLinesIt.next();
			// If there is content then
			if(aLine.length > 0) {
				// Create a temporary storage variable for one line from columns
				String lineContent = "";
				// columns index variable to loop
				int columns = 0;
				// Loop through all the columns, until all columns are processed
				while(columns < aLine.length) {
					// Add each column value to the temporary line storage
					lineContent += aLine[columns];
					// If its before the last column, then add a comma to the end of the line. Otherwise an empty string.
					lineContent += columns < (aLine.length - 1) ? "," : "";
					// Increment the column counter
					columns++;
				}
				// Add a string line to the file contents array list
				fileContents.add(lineContent);
			} else {
				// TODO: Should test for an empty string?
				System.out.println("Skipping empty CSV line");
			}
		}
		// Write the file contents to a file using the file name provided.
		fileIO.writeFile(fileName, fileContents);
	}
}
