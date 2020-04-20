package checkInModel;

/**
 * Import packages for data structure manipulation.
 */
import java.util.ArrayList;

/**
 * Import packages for observer pattern.
 */
import java.util.Observable;

/**
 * 
 * CheckIn 
 * Holds the flight collection, booking collection and runs
 * the check in simulation in data.
 * @author amymcfarland
 */
@SuppressWarnings("deprecation")
public class CheckIn extends Observable implements Runnable {

	/**
	 * Booking and Flight data collections.
	 */
	private volatile BookingCollection bookingCollection;
	private volatile FlightCollection flightCollection;

	/**
	 * String representation of the simulations current time. For UI display.
	 */
	private volatile String simulationDateTime = "";
	
	/**
	 * Simulation Time and Logging Singleton.
	 */
	private volatile SimulationTimeSingleton simTime = null;
	private volatile LoggingSingleton log = null;
	
	/**
	 * Check In Desk collection.
	 */
	private volatile CheckInDeskCollection checkInDeskCollection;
	
	/**
	 * CheckIn
	 * Constructor, creates the check in simulation model object.
	 */
	public CheckIn() {
		log = LoggingSingleton.getInstance();
		simTime = SimulationTimeSingleton.getInstance();
		resetCheckInSimulation();
	}

	/**
	 * getBookingCollection
	 * Returns the booking collection for view display.
	 * @return BookingCollection
	 */
	public BookingCollection getBookingCollection() {
		return bookingCollection;
	}

	/**
	 * getFlightCollection
	 * Return the flight collection for view display.
	 * @return FlightCollection
	 */
	public FlightCollection getFlightCollection()  {
		return flightCollection;

	}

	/**
	 * getCheckInDesks
	 * Return the check in desk collection for view display.
	 * @return ArrayList CheckInDesk
	 */
	public ArrayList<CheckInDesk> getCheckInDesks() {
		return checkInDeskCollection.getCheckInDesks();
	}

	/**
	 * getSimulationSpeed
	 * Returns the speed that the simulation is.
	 * running at.
	 * @return String
	 */
	public String getSimulationSpeed() {
		return String.valueOf(simTime.getSpeed());
	}

	/**
	 * setSimulationSpeed
	 * Sets the speed that the simulation is running at.
	 * @param simulationTime
	 */
	public void setSimulationSpeed(String simulationTime) {
		simTime.setSpeed(Integer.parseInt(simulationTime.substring(0, simulationTime.length() - 1)));
		log.addLog("Simulation speed is " + simulationTime, "log");
		this.updateView();
	}

	/**
	 * toggleSimulationRunning
	 * Changes the simulation between a stopped / started state.
	 */
	public synchronized void toggleSimulationRunning() {
		simTime.toggleSimRunning();
		log.addLog("Simulation has " + (simTime.isSimRunning() ? "started" : "ended"), "log");
		this.updateView();
	}

	/**
	 * getSimulationRunning
	 * Gets true/false if the simulation is running.
	 * @return boolean
	 */
	public boolean getSimulationRunning() {
		return simTime.isSimRunning();
	}

	/**
	 * getSimulationDateTime
	 * Returns a string formatted date for view display
	 * of the current time within the simulation.
	 * @return String
	 */
	public String getSimulationDateTime() {
		return simulationDateTime;
	}

	/**
	 * setSimulationDateTime
	 * Sets the string formatted date for the current
	 * date/time within the simulation.
	 * @param simDateTime
	 */
	private void setSimulationDateTime(String simDateTime) {
		simulationDateTime = simDateTime;
		this.updateView();
	}
	
	/**
	 * getTotalDesks
	 * Returns the total number of check in desks
	 * the system is using.
	 * @return int
	 */
	public int getTotalDesks() {
		return checkInDeskCollection.getTotalDesks();
	}

	/**
	 * resetCheckInSimulation
	 * Resets the flight, booking and check in desk 
	 * collections so that the simualation begings from
	 * a blank slate.
	 */
	private void resetCheckInSimulation() {
		flightCollection = new FlightCollection();
		bookingCollection = new BookingCollection();
		checkInDeskCollection = new CheckInDeskCollection(flightCollection, bookingCollection);
		this.updateView();
	}

	/**
	 * updateView
	 * Wraps the method calls for the observer
	 * pattern view updates into one method
	 * for easy access through out the check in
	 * model.
	 */
	private void updateView() {
		//update view display
		setChanged();
		notifyObservers();
    	clearChanged();
	}

	/**
	 * run
	 * Runs the check in simulation, in effect this is the main
	 * control loop for the whole simulation.
	 */
	@Override
	public void run() {
		/**
		 * When called the simulation running will be
		 * false, toggle this to true.
		 */
		this.toggleSimulationRunning();
		/**
		 * Reset the booking, flight and check in desk collection
		 * before beginning the simulation. This is done before,
		 * rather than after, as it allows the end state of a
		 * simulation to persist on the view for user review.
		 */
		this.resetCheckInSimulation();
		/**
		 * Create random booking generator and set the 
		 * start date/time of the simulation.
		 */
		RandomBookingGenerator bookingGen = new RandomBookingGenerator(flightCollection, bookingCollection);
		simTime.setStartSimulation();
		this.setSimulationDateTime(simTime.getCurrentSimTime().toGMTString());
		this.updateView();
		while(this.getSimulationRunning()) {
			log.addLog("Main control loop", "CheckIn");
			/**
			 * Generate a random flight.
			 */
			flightCollection.generateFlights();
			/**
			 * Generate bookings for flight without any. 
			 */
			bookingGen.generateBookings();
			/**
			 * Progress bookings from not being in any
			 * queue to entering the security queue and 
			 * then going from security to either business
			 * or economy queues.
			 */
			bookingCollection.progressBookingToSecurity();
			bookingCollection.progressBookingPassedSecurity(false);
			bookingCollection.progressBookingPassedSecurity(true);

			/**
			 * Run the check in desk collection, which will
			 * create open check in desks for flights that
			 * are ready to board passengers or delay flights
			 * when all desks are currently in use.
			 */
			new Thread(checkInDeskCollection).start();
			
			/**
			 * Sleep the simulation for a given number of milliseconds
			 * based on the speed that the simulation is running at.
			 */
			try {
				Thread.sleep(SimulationTimeSingleton.getSpeedDelay(simTime.getSpeed()));
			} catch (InterruptedException e) {
				log.addLog("Thread sleep interrupted.", "log");
			}
			
			/**
			 * Update the current time of the simulation for the next iteration.
			 */
			simTime.setCurrentSimTime(SimulationTimeSingleton.getCurrentTime());
			this.setSimulationDateTime(simTime.getCurrentSimTime().toGMTString());
		}
	}
}
