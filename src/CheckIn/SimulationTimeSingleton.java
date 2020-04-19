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
public class SimulationTimeSingleton {

	private Date startTime = new Date();
	private Date currentTime = new Date();
	private int simSpeed = 1;
	private boolean simRunning = false;
	/**
	 * Constant delay value in milliseconds,
	 * setting this higher will make the 
	 * simulation slower or lower to run faster.
	 */
	private static int simDelay = 200;
	private static SimulationTimeSingleton simTimeInstance = null;
		
	private SimulationTimeSingleton() {
		startTime = new Date();
		currentTime = new Date();
	}
	
	public static SimulationTimeSingleton getInstance() {
		/**
		 * If no instance of the simulation time class
		 * exists, create one.
		 */
		if(simTimeInstance == null) {
			simTimeInstance = new SimulationTimeSingleton();
		}
		/**
		 * Always return the same instance of the logging class.
		 */
		return simTimeInstance;
	}
	
	public Date getCurrentSimTime() {
		return currentTime;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public int getSpeed() {
		return simSpeed;
	}

	public void setSpeed(int speed) {
		simSpeed = speed;
	}

	public void setCurrentSimTime(Date current) {
		currentTime = current;
	}

	public void setStartSimulation() {
		startTime = new Date();
		currentTime = new Date();
	}
	
	public boolean isSimRunning() {
		return simRunning;
	}
	
	public void toggleSimRunning() {
		simRunning = !simRunning;
	}

	/**
	 * getSpeedDelay
	 * Returns a number of milliseconds that the
	 * simulation should use as a delay for things
	 * such as Thread.Sleep
	 * @param speed
	 * @return int
	 */
	public static int getSpeedDelay(int speed) {
		return SimulationTimeSingleton.simDelay / speed;
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
		SimulationTimeSingleton simTime = getInstance();
		Date actualSystemTime = new Date(); 
		long speedDelay = SimulationTimeSingleton.getSpeedDelay(simTime.getSpeed());
		long deltaTimeMS = actualSystemTime.getTime() - simTime.getStartTime().getTime();
		long deltaTimeFake = deltaTimeMS + (SimulationTimeSingleton.simDelay - speedDelay);
	
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
		currentFakeTime.setTimeInMillis(simTime.getCurrentSimTime().getTime() + deltaTimeFake);
		return currentFakeTime.getTime();
	}
}
