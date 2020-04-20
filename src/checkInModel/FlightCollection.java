package checkInModel;

/**
 * Import packages to manage data structures.
 */
import java.util.Iterator;
import java.util.TreeSet;

/**
 * FlightCollection
 * Creating a TreeSet using FlightCollection.
 * @author NadiaAbulhawa
 */
public class FlightCollection {
	
	/**
	 * Treeset to hold flight records.
	 */
	private FlightComparator flightComp = new FlightComparator();
	private TreeSet<Flight> flightCollection = new TreeSet<Flight>(flightComp);
	
	/**
	 * Logging singleton instance.
	 */
	private LoggingSingleton log;
	
	/**
	 * Thread blocking boolean, for data manipulation across threads.
	 */
	private boolean inUse = false;
	
	/**
	 * FlightCollection
	 * Constructor to create the flight collection onject.
	 * @param fileName
	 */
	public FlightCollection() {
		log = LoggingSingleton.getInstance();
	}

	/**
	 * findFlight
	 * Method to find a flight by iterating through the FlightCollection and
	 * comparing flight codes of two flights. If the comparator equals 0 then the
	 * flight has been found and returned.
	 * @param flightCode
	 * @return aFlight returning a flight
	 * @throws FlightException
	 */
	public synchronized Flight findFlight(String flightCode) throws FlightException {
		/**
		 * Get exclusive access to the flight collection, so
		 * that no other thread modifies it during this operation.
		 */
		takeInUse();
		Iterator<Flight> iterator = flightCollection.iterator();
		while(iterator.hasNext()) {
			Flight aFlight = iterator.next();
			if (aFlight.getFlightCode().compareTo(flightCode) == 0) {
				/**
				 * Release access to the flight collection.
				 */
				freeInUse();
				return aFlight;
			}
		}
		/**
		 * Release access to the flight collection.
		 */
		freeInUse();
		throw new FlightException("Flight not found");
	}
	
	/**
	 * getFlightCollection
	 * Method to return list of flights.
	 * @return flightCollection returning flightCollection
	 */
	public TreeSet<Flight> getFlightCollection() {
		return flightCollection;
	}

	/**
	 * generateFlights
	 * This will generate random flight data to add to
	 * the flight collection so the simulation can run.
	 */
	@SuppressWarnings("deprecation")
	public synchronized void generateFlights() {
		/**
		 * Get exclusive access to the flight collection, so
		 * that no other thread modifies it during this operation.
		 */
		takeInUse();
		/**
		 * Loop through flights in our system and count how many are waiting.
		 */
		Iterator<Flight> iterator = flightCollection.iterator();
		int waitingFlights = 0;
		while(iterator.hasNext()) {
			Flight aFlight = iterator.next();
			if (aFlight.getFlightStatus().compareTo("waiting") == 0) {
				waitingFlights++;
			}
		}
		/**
		 * When there are less than 4 waiting flights, add a new random flight.
		 */
		if(waitingFlights < 4) {
			Flight newFlight = RandomFlightGenerator.getRandomFlight();
			log.addLog("Added new flight " + newFlight.getFlightCode() + " departs at " + newFlight.getDepartureDate().toGMTString(), "FlightCollection");
			flightCollection.add(newFlight);
		}
		/**
		 * Release access to the flight collection.
		 */
		freeInUse();
	}
	
	/**
	 * takeInUse
	 * Waits for the inUse flag to be false and then
	 * sets it to true so other lines can be executed
	 * in a thread safe way.
	 */
	private synchronized void takeInUse() {
		while (inUse) {
			try {
				log.addLog("Wait for flights to be free", "FlightCollection");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inUse = true;
	}

	/**
	 * freeInUse
	 * Sets the inUse flag to false and notifies all other threads.
	 */
	public synchronized void freeInUse() {
		inUse = false;
		notifyAll();
	}
}
