package CheckIn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FileIO {

	public ArrayList<String> readFile(String fileName) throws CheckInIOException {
		ArrayList<String> fileContents = new ArrayList<String>();
		try (BufferedReader buffReader = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    while ((line = buffReader.readLine()) != null) {
		    	fileContents.add(line);
		    }
			buffReader.close();
		} catch(FileNotFoundException e) {
			throw new CheckInIOException("The file " + fileName + " was not found");
		} catch(IOException e) {
			throw new CheckInIOException("There was a problem reading the file " + fileName);
		} 
		return fileContents;
	}
	
	// TODO: Check line is text characters only and not binary

	public void writeFile(String fileName, ArrayList<String> fileContents) throws CheckInIOException {
		if(fileContents.size() > 0) {
	        File file = new File(fileName);
	        FileWriter writer = null;
	        try {
	            writer = new FileWriter(file);
	            Iterator<String> fileContentsIt = fileContents.iterator();
	            while(fileContentsIt.hasNext()) {
	            	writer.write(fileContentsIt.next() + "\n");
	            }
	        } catch(IOException e) {
	        	throw new CheckInIOException("There was a problem writing the file " + fileName);
	        } finally {
	        	try {
	        		writer.close();
	        	} catch(NullPointerException e) {
	        		System.out.println("Nothing to close. Shhh...");
	        	} catch(IOException e) {
	        		System.out.println("Nothing to close. Shhh...");
	        	}
	        }
		} else {
			throw new CheckInIOException("Empty file contents detected");
		}
	}
}
