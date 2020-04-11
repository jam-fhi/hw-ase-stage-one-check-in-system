package CheckIn;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * FakeTime
 * Simulates hours passing for thread
 * based simulation of check in system.
 * @author jamiehill and Haikah Ghoghari
 *
 */
public class FakeTime {

	private static int simDelay = 2000;

	public static int getSpeedDelay(int speed) {
		return simDelay / speed;
	}

	/**
	 * getCurrentTime
	 * Returns a new date object that is a
	 * fake time that has elapsed within the
	 * check in system simulation.
	 * @return Date of fake current time
	 */
	public static Date getCurrentTime() {
		/**
		 * Calculate how many milliseconds have passed since the system started and the current time.
		 * Then convert the number of milliseconds into hours for the simulation.
		 * 
		 */
		SimulationTimeSingleton simTime = SimulationTimeSingleton.getInstance();
		Date actualSystemTime = new Date(); 
		long speedDelay = getSpeedDelay(simTime.getSpeed());
		long deltaTimeMS = actualSystemTime.getTime() - simTime.getStartTime().getTime();
		long deltaTimeFake = deltaTimeMS + (simDelay - speedDelay);

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
		currentFakeTime.setTimeInMillis(simTime.getCurrentTime().getTime() + deltaTimeFake);
		return currentFakeTime.getTime();
	}
}
