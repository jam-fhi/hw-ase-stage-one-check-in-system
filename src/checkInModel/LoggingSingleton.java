package checkInModel;

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
	private volatile static LoggingSingleton loggingsingleton_instance = null;
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
	 * @param logType
	 */
	@SuppressWarnings("deprecation")
	public void addLog(String message, String logType) {
		Date currentTime = SimulationTimeSingleton.getCurrentTime();
		/**
		 * Create log entry.
		 */
		String logEntry = currentTime.toGMTString() + ": [" + logType + "] " + message;
		/**
		 * Add log entry to console and data structure.
		 */
		System.out.println(logEntry);
		logMessages.add(logEntry);
	}

	/**
	 * writeLog
	 * Saves log data structure to file.
	 * @param fileName
	 * @throws CheckInIOException
	 */
	public void writelog(String fileName) throws CheckInIOException {
		if(logMessages.size() > 0) {
			FileIO fileIO = new FileIO();
			fileIO.writeFile(fileName, logMessages);
		}	
	}
}
