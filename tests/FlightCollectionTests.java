import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import CheckIn.Flight;
import CheckIn.FlightCollection;

public class FlightCollectionTests {

	private String validFlightsCSV = "flights.csv";
	private String invalidFlightsCSV = "noFlights.csv";
	private String validFlightCode = "BA123";
	private FlightCollection flightCollection = null;

	@Before
	public void beforeEach() {

		flightCollection = new FlightCollection();
	}

	@Test
	public void testSuccessfullyLoadsFlights() throws FileNotFoundException, IOException, Exception {

		flightCollection.loadFlights(validFlightsCSV);
		Flight aFlight = flightCollection.findFlight(validFlightCode);
		assertEquals(aFlight.getFlightCode(), validFlightCode);
	}

}
