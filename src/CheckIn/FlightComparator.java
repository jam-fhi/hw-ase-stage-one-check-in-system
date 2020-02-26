package CheckIn;

import java.util.Comparator;

/**
 * Method used to compare the flight code of two flights
 * 
 * @author NadiaAbulhawa
 */
public class FlightComparator implements Comparator<Flight> {
	@Override
	public int compare(Flight aFlight, Flight bFlight) {
		return aFlight.getFlightCode().compareTo(bFlight.getFlightCode());
	}
}
