package CheckIn;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * CSVProcessor
 * Provides input and output functionality
 * with CSV file formats by the use of an
 * ArrayList to represent rows and a string
 * array to represent columns.
 * Depends on the FileIO class to provide
 * direct read and write file access.
 * @author jamiehill
 *
 */
public class CSVProcessor {

	/**
	 * FileIO object, providing read and write
	 * access to the file system.
	 */
	private FileIO fileIO = new FileIO();
	
	/**
	 * parseCSVToStringArray
	 * Reads in a file, line by line, then splits each line
	 * on a comma before returning an array list of string
	 * arrays, where the array list entry represents a line
	 * or row of a file and the string array contains the
	 * column data.
	 * @param fileName
	 * @return ArrayList<String[]> Contents of file lines split into columns
	 * @throws CheckInIOException
	 */
	public ArrayList<String[]> parseCSVToStringArray(String fileName) throws CheckInIOException {
		/**
		 *  Create array list of strings to store each line of the file.
		 */
		ArrayList<String> fileLines = fileIO.readFile(fileName);
		/**
		 *  Create array list of string arrays to store csv columns in the string array and each row of the file in the array list.
		 */
		ArrayList<String[]> fileLinesColumns = new ArrayList<String[]>();
		/**
		 *  Temporary storage for one line split into columns
		 */
		String[] lineColumns;
		/**
		 *  Get an iterator for the file input lines.
		 */
		Iterator<String> fileLinesIt = fileLines.iterator();
		/**
		 *  Loop through each line of the file
		 */
		while(fileLinesIt.hasNext()) {
			/**
			 *  Split a line on a comma into the temporary string array
			 */
			lineColumns = fileLinesIt.next().split(",");
			/**
			 * Add the split string / columns to the array list for rows.
			 */
			fileLinesColumns.add(lineColumns);
		}
		/**
		 *  Return the array list of string arrays representing the CSV file
		 */
		return fileLinesColumns;
	}
	
	/**
	 * parseStringArrayToCSV
	 * Takes in a ArrayList of string arrays where the entry
	 * in the array list represents a row of data and the
	 * string array represents columns. This is joined with
	 * a comma to create a string line for output to the
	 * specified file name.
	 * @param fileName
	 * @param fileLine
	 * @throws CheckInIOException
	 */
	public void parseStringArrayToCSV(String fileName, ArrayList<String[]> fileLine) throws CheckInIOException {
		/**
		 *  Create an array list of strings to store the output content
		 */
		ArrayList<String> fileContents = new ArrayList<String>();
		/**
		 *  Get an iterator to process the rows inputed via the fileLine parameter
		 */
		Iterator<String[]> fileLinesIt = fileLine.iterator();
		/**
		 *  Loop through each line of the output content
		 */
		while(fileLinesIt.hasNext()) {
			/**
			 *  Get the next line to process
			 */
			String[] aLine = fileLinesIt.next();
			/**
			 *  If there is content then
			 */
			if(aLine.length > 0) {
				/**
				 *  Create a temporary storage variable for one line from columns
				 */
				String lineContent = "";
				/**
				 *  columns index variable to loop
				 */
				int columns = 0;
				/**
				 *  Loop through all the columns, until all columns are processed
				 */
				while(columns < aLine.length) {
					/**
					 *  Add each column value to the temporary line storage
					 */
					lineContent += aLine[columns];
					/**
					 *  If its before the last column, then add a comma to the end of the line. Otherwise an empty string.
					 */
					lineContent += columns < (aLine.length - 1) ? "," : "";
					/**
					 *  Increment the column counter
					 */
					columns++;
				}
				/**
				 *  Add a string line to the file contents array list
				 */
				fileContents.add(lineContent);
			} else {
				System.out.println("Skipping empty CSV line");
			}
		}
		/**
		 *  Write the file contents to a file using the file name provided.
		 */
		fileIO.writeFile(fileName, fileContents);
	}
}
