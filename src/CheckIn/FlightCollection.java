package CheckIn;

import java.util.ArrayList;
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
	FlightComparator flightComp = new FlightComparator();
	TreeSet<Flight> flightCollection = new TreeSet<Flight>(flightComp);
	public Flight nextFlight = null;

	/**
	 * FlightCollection
	 * Constructor to create the flight collection onject.
	 * @param fileName
	 * @throws CheckInIOException
	 */
	public FlightCollection(String fileName) throws CheckInIOException {
		loadFlights(fileName);
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
	public TreeSet<Flight> getFlightCollection() {
		return flightCollection;
	}
}
