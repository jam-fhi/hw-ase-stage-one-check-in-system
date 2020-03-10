package CheckIn;

import java.util.Calendar;
import java.util.Date;

public class fakeTime {

	/**
	 *  When the system starts get the current system start time, from a new date object.
	 */
	private static Date systemTime = new Date();
	
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
		long deltaTimeHr = deltaTimeMS * 60 * 60 * 1000;
		
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
}
