package CheckIn;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * fakeTime
 * Simulates hours passing for thread
 * based simulation of check in system.
 * @author jamiehill and Haikah Ghoghari
 *
 */
public class fakeTime {

	/**
	 *  When the system starts get the current system start time, from a new date object.
	 */
	private static Date systemTime = new Date();
	
	/**
	 * getCurrentTime
	 * Returns a new date object that is a
	 * fake time that has elapsed within the
	 * check in system simulation.
	 * @return Date of fake current time
	 */
	public static Date getCurrentTime() {
		/**
		 * When we call getCurrentTime, get the current system time, from a new date object.
		 */
		Date currentSystemTime = new Date();
		/**
		 * Calculate how many milliseconds have passed since the system started and the current time.
		 * Then convert the number of milliseconds into hours for the simulation.
		 */
		long deltaTimeMS = currentSystemTime.getTime() - systemTime.getTime();
		long deltaTimeHr = deltaTimeMS * 60 * 1000;
		
		/**
		 * We assume our simulation begins on the first of march.
		 */
		Calendar startDate = Calendar.getInstance();
		startDate.set(2020, 3, 1, 0, 0, 0);
		/**
		 * Get unix time stamp of the first of march 2020
		 */
		long firstMarch = startDate.getTime().getTime();
		
		/**
		 * Create a calendar to hold our fake simulation time.
		 */
		Calendar currentFakeTime = Calendar.getInstance();
		
		/**
		 * Add the number of milliseconds the simulation has
		 * been running for, which have been converted to hours,
		 * onto the first of march unix time stamp to create a
		 * fake date/time.
		 */
		currentFakeTime.setTimeInMillis(firstMarch + deltaTimeHr);
		return currentFakeTime.getTime();
	}
	
	/**
	 * waitForMilliseconds
	 * Takes a number of milliseconds to wait for
	 * by running a while loop that checks if the
	 * number of milliseconds specified have passed
	 * before exiting the loop.
	 * @param miliseconds
	 */
	private void waitForMilliseconds(long miliseconds) {
		Date startDate = new Date();
		Date currentDate = new Date();
		while(currentDate.getTime() - startDate.getTime() < miliseconds) {
			currentDate = new Date();
		}
	}
	
	/**
	 * delaySimulation
	 * Forces the simulation to wait for n milliseconds
	 * so that the speed it runs at can be controlled.
	 * @param simulationSpeed
	 */
	public void delaySimulation(int simulationSpeed) {
		/**
		 * Default wait is 25 ms
		 */
		long defaultWait = 25;
		/**
		 * Calculate wait time based on - simulationSpeed being slower
		 * and positive speeds being x times faster so have a lower
		 * wait in ms.
		 */
		long useWait = (simulationSpeed * simulationSpeed) - defaultWait;
		if(simulationSpeed < 0) {
			useWait = (simulationSpeed * simulationSpeed) + defaultWait;
		}
		waitForMilliseconds(useWait);
	}
}
