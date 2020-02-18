package CheckIn;

import java.util.Comparator;

public class FlightComparator implements Comparator<Flight> {
	@Override
	public int compare(Flight aFlight, Flight bFlight) {
		return aFlight.getFlightCode().compareTo(bFlight.getFlightCode());
	}
}