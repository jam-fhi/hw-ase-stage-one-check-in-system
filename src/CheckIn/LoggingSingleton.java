package CheckIn;

import java.util.ArrayList;
import java.util.Date;

/**
 * LoggingSingleton
 * Maintains a single instance of the logging class
 * so that there is system wide log collection
 * without there ever being any confusion over what
 * instance of the logging class is being used.
 * @author jamiehill
 *
 */
public class LoggingSingleton {

	/**
	 * The single instance of the logging class to use.
	 */
	private static LoggingSingleton loggingsingleton_instance = null;
	/**
	 * Log messages Array List.
	 */
	private ArrayList<String> logMessages = new ArrayList<String>();
	
	/**
	 * Constructor. Initialises the log message Array List.
	 * This is private so that it can only ever be used
	 * from within this class, thus preventing any other
	 * code block from creating a logging class outside
	 * of the static getInstance method.
	 */
	private LoggingSingleton() {
		logMessages = new ArrayList<String>();
	}
	
	/**
	 * getInstance
	 * Returns or creates the single instance
	 * of the logging class.
	 * @return
	 */
	public static LoggingSingleton getInstance() {
		/**
		 * If no instance of the logging class
		 * exists, create one.
		 */
		if(loggingsingleton_instance == null) {
			loggingsingleton_instance = new LoggingSingleton();
		}
		/**
		 * Always return the same instance of the logging class.
		 */
		return loggingsingleton_instance;
	}
	
	/**
	 * addLog
	 * Initial add log method, it takes a string
	 * log message and adds it to the log messages
	 * Array List.
	 */
	public void addLog(String message) {
		Date currentTime = fakeTime.getCurrentTime();
		logMessages.add(currentTime.getTime() + ": " + message);
	}

	public void writelog(String filename) throws CheckInIOException {
		if(logMessages.size() > 0) {
			FileIO fileIO = new FileIO();
			fileIO.writeFile(filename, logMessages);
		}
		
	}
}
