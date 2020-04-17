package CheckIn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * FlightCollection
 * Creating a TreeSet using FlightCollection.
 * @author NadiaAbulhawa
 */
public class FlightCollection implements Runnable {
	
	/**
	 * Treeset to hold flight records.
	 */
	private FlightComparator flightComp = new FlightComparator();
	private TreeSet<Flight> flightCollection = new TreeSet<Flight>(flightComp);
	public Flight nextFlight = null;
	private LoggingSingleton log;
	private boolean inUse = false;
	
	/**
	 * FlightCollection
	 * Constructor to create the flight collection onject.
	 * @param fileName
	 * @throws CheckInIOException
	 */
	public FlightCollection() throws CheckInIOException {
		log = LoggingSingleton.getInstance();
	}

	/**
	 * loadFlights
	 * Creating a method to load flights from the flight.csv file the method does
	 * this by iterating through the list of flights and uses the parseCSVToStringArray
	 * to split each line into parts. A flight object is created and added to the list.
	 * @param fileName using the flight.csv file
	 * @throws CheckInIOException
	 */
	public void loadFlights(String fileName) throws CheckInIOException {
		CSVProcessor csvProc = new CSVProcessor ();
		ArrayList<String[]> rawFlights = csvProc.parseCSVToStringArray(fileName);
		Iterator<String[]> flightIt = rawFlights.iterator();
		while(flightIt.hasNext()) {
			String[] aFlight = flightIt.next();
			String flightCode = aFlight[2];
			String destinationAirport = aFlight[0];
			String carrier = aFlight[1];
			int maximumPassengers = Integer.parseInt(aFlight[3]);
			double maximumBaggageWeight = Double.parseDouble(aFlight[4]);
			double maximumBaggageVolume = Double.parseDouble(aFlight[5]);
			double excessCharge = Double.parseDouble(aFlight[6]);
			String addDepartureTime = aFlight[7];
			String addDepartureDate = aFlight[8];
			Flight addFlight = new Flight(flightCode, destinationAirport, carrier, maximumPassengers, maximumBaggageWeight, maximumBaggageVolume, excessCharge, addDepartureTime, addDepartureDate);
			flightCollection.add(addFlight);
			nextFlight = addFlight;
		}
	}

	public void addFlight(Flight aFlight) {
		flightCollection.add(aFlight);
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
	public Flight findFlight(String flightCode) throws FlightException {
		Iterator<Flight> iterator = flightCollection.iterator();
		while(iterator.hasNext()) {
			Flight aFlight = iterator.next();
			if (aFlight.getFlightCode().compareTo(flightCode) == 0) {
				return aFlight;
			}
		}
		throw new FlightException("Flight not found");
	}
	
	/**
	 * getFlightCollection
	 * Method to return list of flights.
	 * @return flightCollection returning flightCollection
	 */
	public synchronized TreeSet<Flight> getFlightCollection() {
		takeInUse();
		return flightCollection;
	}

	private void takeInUse() {
		while (inUse) {
			try {
				log.addLog("Wait for flights to be free", "log");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inUse = true;
	}

	@Override
	public synchronized void run() {
		takeInUse();
		Iterator<Flight> iterator = flightCollection.iterator();
		int activeFlights = 0;
		while(iterator.hasNext()) {
			Flight aFlight = iterator.next();
			if (aFlight.getFlightStatus().compareTo("waiting") == 0) {
				activeFlights++;
			}
		}
		if(activeFlights < 4) {
			Flight newFlight = RandomFlightGenerator.getRandomFlight();
			log.addLog("Added new flight " + newFlight.getFlightCode() + " departs at " + newFlight.getDepartureDate().toGMTString(), "log");
			flightCollection.add(newFlight);
		}
		setInUse();
	}
	
	public synchronized void setInUse() {
		inUse = false;
		notifyAll();
	}
}
