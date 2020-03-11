package CheckIn;

import java.util.ArrayList;

public class LoggingSingleton {

	private static LoggingSingleton loggingsingleton_instance = null;
	private ArrayList<String> logMessages = new ArrayList<String>();
	
	private LoggingSingleton() {
		logMessages = new ArrayList<String>();
	}
	
	public LoggingSingleton getInstance() {
		if(loggingsingleton_instance == null) {
			loggingsingleton_instance = new LoggingSingleton();
		}
		return loggingsingleton_instance;
	}
	
	public void addLog(String message) {
		logMessages.add(message);
	}
}
