import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
	private String invalidFlightCode = "PQ154";
	private String errorMessage = "Flight not found";
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

	@Test
	public void testUnsuccessfullyLoadsFlights() throws FileNotFoundException, IOException, Exception {

		try {

			flightCollection.loadFlights(validFlightsCSV);
			flightCollection.findFlight(invalidFlightCode);
			fail("didn't throw");

		}

		catch (Exception myException) {
			assertEquals(myException.getMessage(), errorMessage);
		}
	}

}