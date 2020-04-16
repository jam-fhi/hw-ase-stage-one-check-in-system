package CheckIn;

import java.util.Date;

public class SimulationTimeSingleton {

	private Date startTime = new Date();
	private Date currentTime = new Date();
	private int simSpeed = 1;
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
	
	public Date getCurrentTime() {
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

	public void setCurrentTime(Date current) {
		currentTime = current;
	}

	public void setStartSimulation() {
		startTime = new Date();
		currentTime = new Date();
	}
}
