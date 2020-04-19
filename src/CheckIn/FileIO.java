package CheckIn;

/**
 * Import File IO packages.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Import data structure packages.
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * FileIO
 * Provides a wrapper to the file system
 * read and write methods such that it
 * will read and return an array list of
 * lines per specified file or write out
 * an array list as lines within a specified
 * file.
 * @author jamiehill
 *
 */
public class FileIO {

	/**
	 * readFile
	 * Takes a file name and attempts to read the
	 * contents of the text file into an array list
	 * that will be returned
	 * @param fileName
	 * @return ArrayList<String> each line of the read file
	 * @throws CheckInIOException
	 */
	public ArrayList<String> readFile(String fileName) throws CheckInIOException {
		/**
		 *  Create an array list of strings to store the file contents.
		 */
		ArrayList<String> fileContents = new ArrayList<String>();
		/**
		 *  Try and create a new file reader buffer to store the incoming data from the specified file.
		 */
		try (BufferedReader buffReader = new BufferedReader(new FileReader(fileName))) {
			/**
			 *  Temporary storage for the contents of one line.
			 */
		    String line;
		    /**
		     *  While there is line content in the buffer, add it to the temporary line storage.
		     */
		    while ((line = buffReader.readLine()) != null) {
		    	/**
		    	 *  Add the line to the file contents array list.
		    	 */
		    	fileContents.add(line);
		    }
		    /**
		     *  Close the buffer.
		     */
			buffReader.close();
		} catch(FileNotFoundException e) {
			/**
			 *  Catch a file not found exception and throw a custom exception with message.
			 */
			throw new CheckInIOException("The file " + fileName + " was not found");
		} catch(IOException e) {
			/**
			 *  Catch an input/output exception and throw a custom exception with message.
			 */
			throw new CheckInIOException("There was a problem reading the file " + fileName);
		}
		return fileContents;
	}

	/**
	 * writeFile
	 * Takes an array list of strings and a file
	 * name parameter and attempts to write each
	 * element in the array list as a line within
	 * the specified file
	 * @param fileName
	 * @param fileContents
	 * @throws CheckInIOException
	 */
	public void writeFile(String fileName, ArrayList<String> fileContents) throws CheckInIOException {
		/**
		 *  Check there is content to write.
		 */
		if(fileContents.size() > 0) {
			/**
			 *  Get a file handler for the file we want to write to.
			 */
	        File file = new File(fileName);
	        /**
	         *  Create a file writer object, initialised to null.
	         */
	        FileWriter writer = null;
	        try {
	        	/**
	        	 *  Load a file write handler.
	        	 */
	            writer = new FileWriter(file);
	            /**
	             *  Create an iterator to process the file contents.
	             */
	            Iterator<String> fileContentsIt = fileContents.iterator();
	            while(fileContentsIt.hasNext()) {
	            	/**
	            	 *  Write each line provided to the file via the file write handler.
	            	 */
	            	writer.write(fileContentsIt.next() + "\n");
	            }
	        } catch(IOException e) {
	        	/**
	        	 *  Catch an input / output exception and throw a custom exception with error message.
	        	 */
	        	throw new CheckInIOException("There was a problem writing the file " + fileName);
	        } finally {
	        	try {
	        		/**
	        		 *  Try to close the file writer.
	        		 */
	        		writer.close();
	        	} catch(NullPointerException e) {
	        		/**
	        		 *  Catch a null pointer exception, but don't do anything as
	        		 *  at this point the error could be that nothing was opened.
	        		 */
	        		System.out.println("Nothing to close. Shhh...");
	        	} catch(IOException e) {
	        		/**
	        		 *  Catch an Input/output exception, but don't do anything as
	        		 *  at this point the error could be that nothing was opened.
	        		 */
	        		System.out.println("Nothing to close. Shhh...");
	        	}
	        }
		} else {
			/**
			 *  Throw an exception if there is no content to write.
			 */
			throw new CheckInIOException("Empty file contents detected");
		}
	}
}
