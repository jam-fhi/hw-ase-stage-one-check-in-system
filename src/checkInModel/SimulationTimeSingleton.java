package checkInModel;

/**
 * Import packages to maniplate dates
 */
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * SimulationTimeSingleton
 * Simulates hours passing for thread
 * based simulation of check in system.
 * @author jamiehill and Haikah Ghoghari
 *
 */
public class SimulationTimeSingleton {

	/**
	 * The time the simulation starts at
	 * and the current simulated time.
	 */
	private Date startTime = new Date();
	private Date currentTime = new Date();
	
	/**
	 * The speed the simulation is running at.
	 */
	private int simSpeed = 1;
	
	/**
	 * If the simulation is running.
	 */
	private boolean simRunning = false;
	
	/**
	 * Constant number of milliseconds in one hour.
	 * Static to use across the simulation.
	 */
	public static long hourInMs = 3600000;
	
	/**
	 * Constant delay value in milliseconds,
	 * setting this higher will make the 
	 * simulation slower or set it lower 
	 * to run faster.
	 */
	private static int simDelay = 200;
	
	/**
	 * Singleton pattern instance, we don't want 
	 * to get confused about different simulation
	 * times.
	 */
	private static SimulationTimeSingleton simTimeInstance = null;
		
	/**
	 * SimulationTimeSingleton
	 * Constructor, private for singleton pattern.
	 */
	private SimulationTimeSingleton() {
		startTime = new Date();
		currentTime = new Date();
	}
	
	/**
	 * getInstance
	 * Returns the instance of this class or
	 * creates a new one if it does not exist.
	 * Singleton Pattern.
	 * @return SimulationTimeSingleton
	 */
	public static SimulationTimeSingleton getInstance() {
		/**
		 * If no instance of the simulation time class
		 * exists, create one.
		 */
		if(simTimeInstance == null) {
			simTimeInstance = new SimulationTimeSingleton();
		}
		/**
		 * Always return the same instance of the SimulationTime class.
		 */
		return simTimeInstance;
	}
	
	/**
	 * getCurrentSimTime
	 * Returns the date object for the 
	 * current time of the simulation.
	 * @return Date
	 */
	public Date getCurrentSimTime() {
		return currentTime;
	}
	
	/**
	 * getStartTime
	 * Returns a Date object for the start
	 * of the simulation.
	 * @return Date
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * getSpeed
	 * Returns the speed the simulation 
	 * is running at.
	 * @return int
	 */
	public int getSpeed() {
		return simSpeed;
	}

	/**
	 * setSpeed
	 * Sets the speed the simulation is
	 * running at.
	 * @param speed
	 */
	public void setSpeed(int speed) {
		simSpeed = speed;
	}

	/**
	 * setCurrentSimTime
	 * Sets the current time of the simulation.
	 * @param current
	 */
	public void setCurrentSimTime(Date current) {
		currentTime = current;
	}

	/**
	 * setStartSimulation
	 * Resets the startTime and currentTime
	 * to start simulating the check in
	 * system all over again.
	 */
	public void setStartSimulation() {
		startTime = new Date();
		currentTime = new Date();
	}
	
	/**
	 * isSimRunning
	 * Returns true/false if the simulation
	 * is currently running.
	 * @return boolean
	 */
	public boolean isSimRunning() {
		return simRunning;
	}
	
	/**
	 * toggleSimRunning
	 * Turns the simulation on/off.
	 */
	public void toggleSimRunning() {
		/**
		 * To toggle, invert the current value.
		 */
		simRunning = !simRunning;
	}

	/**
	 * getSpeedDelay
	 * Returns a number of milliseconds that the
	 * simulation should use as a delay for things
	 * such as Thread.Sleep
	 * This is static as it does not need to be instanced.
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
	 * This is static as we may need to use the current
	 * simulation time across the entire simulation, getting
	 * instances to do this seems unnecessary.
	 * @return Date of simulated current time
	 */
	public static Date getCurrentTime() {
		/**
		 * Calculate how many milliseconds have passed since the system started and the current time.
		 * Then convert the number of milliseconds into hours for the simulation.
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
