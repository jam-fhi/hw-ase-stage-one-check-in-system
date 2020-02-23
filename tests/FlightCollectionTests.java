import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import CheckIn.CheckInIOException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightException;

public class FlightCollectionTests {

	private String validFlightsCSV = "flights.csv";
	private String invalidFlightsCSV = "noFlights.csv";
	private String validFlightCode = "BA123";
	private String invalidFlightCode = "PQ154";
	private int validFlightSize = 4;
	private String errorIOMessage = "The file noFlights.csv was not found";
	private String errorFindMessage = "Flight not found";
	private FlightCollection validFlightCollection;
	
	@Before
	public void beforeEach() throws CheckInIOException {
		validFlightCollection = new FlightCollection(validFlightsCSV);
	}
	
	@Test
	public void testSuccessfullyLoadsFlights() throws CheckInIOException, FlightException {
		TreeSet<Flight> allFlights = validFlightCollection.getFlightCollection();
		assertEquals(validFlightSize, allFlights.size());
	}

	@Test
	public void testUnsuccessfullyLoadsFlights() {
		try {
			new FlightCollection(invalidFlightsCSV);
			fail("Didn't throw");
		} catch (CheckInIOException myException) {
			assertEquals(errorIOMessage, myException.getMessage());
		}
	}
	
	@Test
	public void testUnsuccessfullyFindFlights() {
		try {
			validFlightCollection.findFlight(invalidFlightCode);
		} catch(FlightException e) {
			assertEquals(errorFindMessage, e.getMessage());
		}
	}
	
	@Test
	public void testSuccessfullyFindFlights() throws FlightException {
		Flight aFlight = validFlightCollection.findFlight(validFlightCode);
		assertEquals(validFlightCode, aFlight.getFlightCode());
	}

}