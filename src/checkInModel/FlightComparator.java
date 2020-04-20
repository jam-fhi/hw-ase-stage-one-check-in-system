package checkInModel;

/**
 * Import Comparator package
 */
import java.util.Comparator;

/**
 * FlightComparator
 * Class used to compare the flight code of two flights.
 * 
 * @author NadiaAbulhawa
 */
public class FlightComparator implements Comparator<Flight> {
	/**
	 * compare
	 * Will compare two flights to determine if they
	 * are equal.
	 * return int 0 is equal
	 */
	@Override
	public int compare(Flight aFlight, Flight bFlight) {
		return aFlight.getFlightCode().compareTo(bFlight.getFlightCode());
	}
}
