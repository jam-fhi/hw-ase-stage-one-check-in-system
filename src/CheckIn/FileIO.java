package CheckIn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
// import java.io.FileOutputStream;
import java.util.ArrayList;

public class FileIO {

	public ArrayList<String> readFile(String fileName) throws IOException, FileNotFoundException {
		ArrayList<String> fileContents = new ArrayList<String>();
		try (BufferedReader buffReader = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    while ((line = buffReader.readLine()) != null) {
		    	fileContents.add(line);
		    }
		}
		return fileContents;
	}
	
	// TODO
	// Check line is text characters only and not binary

}
