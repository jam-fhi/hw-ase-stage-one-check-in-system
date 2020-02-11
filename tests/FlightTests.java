import static org.junit.Assert.assertEquals;

import org.junit.Test;

import CheckIn.Flight;

public class FlightTests {

	private String validFlightCode = "BA123";

	@Test
	public void testFlightObject() {

		Flight myFlight = new Flight(validFlightCode);
		String resultFlightCode = myFlight.getFlightCode();
		assertEquals(resultFlightCode, validFlightCode);
	}

}
