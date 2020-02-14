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
		assertEquals(resultFlightCode, validFlightCode);
	}


	@Test
	public void testDestinationAirport() {
		String resultDestinationAirport = myFlight.getDestinationAirport();
		assertEquals(resultDestinationAirport, validDestinationAirport);
	}

	@Test
	public void testCarrier() {
		String resultCarrier = myFlight.getCarrier();
		assertEquals(resultCarrier, validCarrier);
	}

	@Test
	public void testMaximumPassengers() {
		int resultMaximumPassengers = myFlight.getMaximumPassengers();
		assertEquals(resultMaximumPassengers, validMaximumPassengers);
	}

	@Test
	public void testMaximumBaggageWeight() {
		double resultMaximumBaggageWeight = myFlight.getMaximumBaggageWeight();
		assertEquals(resultMaximumBaggageWeight, validMaximumBaggageWeight, deltaPrecisionLoss);
	}

	@Test
	public void testMaximumBaggageVolume() {
		double resultMaximumBaggageVolume = myFlight.getMaximumBaggageVolume();
		assertEquals(resultMaximumBaggageVolume, validMaximumBaggageVolume, deltaPrecisionLoss);
	}

	@Test
	public void testExcessCharge() {
		double resultExcessCharge = myFlight.getExcessCharge();
		assertEquals(resultExcessCharge, validExcessCharge, deltaPrecisionLoss);
	}

	@Test
	public void testAllowedBaggageWeightPerPassenger() {
		double resultAllowedBaggageWeightPerPassenger = myFlight.getAllowedBaggageWeightPerPassenger();
		assertEquals(resultAllowedBaggageWeightPerPassenger, validMaximumBaggageWeight / validMaximumPassengers,
				deltaPrecisionLoss);
	}

	@Test
	public void testAllowedBaggageVolumePerPassenger() {
		double resultAllowedBaggageVolumePerPassenger = myFlight.getAllowedBaggageVolumePerPassenger();
		assertEquals(resultAllowedBaggageVolumePerPassenger, validMaximumBaggageVolume / validMaximumPassengers,
				deltaPrecisionLoss);
	}

}

