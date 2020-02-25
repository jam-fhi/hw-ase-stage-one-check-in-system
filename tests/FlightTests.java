import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import CheckIn.Flight;

public class FlightTests {

	private String validFlightCode = "BA123";
	private String validDestinationAirport = "Barcelona";
	private String validCarrier = "British Airways";
	private int validMaximumPassengers = 30;
	private double validMaximumBaggageWeight = 130;
	private double validMaximumBaggageVolume = 1.89;
	private double validExcessCharge = 30;
	private Flight myFlight = null;
	private double deltaPrecisionLoss = 0.01;

	@Before
	public void beforeEach() {
		myFlight = new Flight(validFlightCode, validDestinationAirport, validCarrier, validMaximumPassengers,
		validMaximumBaggageWeight, validMaximumBaggageVolume, validExcessCharge);
	}

	@Test
	public void testFlightCode() {
		String resultFlightCode = myFlight.getFlightCode();
		assertEquals(validFlightCode, resultFlightCode);
	}


	@Test
	public void testDestinationAirport() {
		String resultDestinationAirport = myFlight.getDestinationAirport();
		assertEquals(validDestinationAirport, resultDestinationAirport);
	}

	@Test
	public void testCarrier() {
		String resultCarrier = myFlight.getCarrier();
		assertEquals(validCarrier, resultCarrier);
	}

	@Test
	public void testMaximumPassengers() {
		int resultMaximumPassengers = myFlight.getMaximumPassengers();
		assertEquals(validMaximumPassengers, resultMaximumPassengers);
	}

	@Test
	public void testMaximumBaggageWeight() {
		double resultMaximumBaggageWeight = myFlight.getMaximumBaggageWeight();
		assertEquals(validMaximumBaggageWeight, resultMaximumBaggageWeight, deltaPrecisionLoss);
	}

	@Test
	public void testMaximumBaggageVolume() {
		double resultMaximumBaggageVolume = myFlight.getMaximumBaggageVolume();
		assertEquals(validMaximumBaggageVolume, resultMaximumBaggageVolume, deltaPrecisionLoss);
	}

	@Test
	public void testExcessCharge() {
		double resultExcessCharge = myFlight.getExcessCharge();
		assertEquals(validExcessCharge, resultExcessCharge, deltaPrecisionLoss);
	}

	@Test
	public void testAllowedBaggageWeightPerPassenger() {
		double resultAllowedBaggageWeightPerPassenger = myFlight.getAllowedBaggageWeightPerPassenger();
		assertEquals(validMaximumBaggageWeight / validMaximumPassengers, resultAllowedBaggageWeightPerPassenger,
				deltaPrecisionLoss);
	}

	@Test
	public void testAllowedBaggageVolumePerPassenger() {
		double resultAllowedBaggageVolumePerPassenger = myFlight.getAllowedBaggageVolumePerPassenger();
		assertEquals(validMaximumBaggageVolume / validMaximumPassengers, resultAllowedBaggageVolumePerPassenger, deltaPrecisionLoss);
	}
}