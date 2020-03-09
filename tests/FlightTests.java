import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

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
	private String validTime = "14:00";
	private String validTimeCheckInClose = "13:00";
	private String validDate =  "2020-03-01";
	private double deltaPrecisionLoss = 0.01;

	@Before
	public void beforeEach() {
		myFlight = new Flight(validFlightCode, validDestinationAirport, validCarrier, validMaximumPassengers,
		validMaximumBaggageWeight, validMaximumBaggageVolume, validExcessCharge,validTime, validDate);
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
	
	@Test
	public void testDepartureDate() {
		Date departureDate = myFlight.getDepartureDate();
		Calendar departureCalendar = Calendar.getInstance();
		departureCalendar.setTime(departureDate);
		int year = departureCalendar.get(Calendar.YEAR);
		int month = departureCalendar.get(Calendar.MONTH);
		int date = departureCalendar.get(Calendar.DATE);
		int hour = departureCalendar.get(Calendar.HOUR_OF_DAY);
		int minute = departureCalendar.get(Calendar.MINUTE);
		String monthStr = month < 10 ? "0" + String.valueOf(month) : String.valueOf(month);
		String dateStr = date < 10 ? "0" + String.valueOf(date) : String.valueOf(date);
		String hourStr = hour < 10 ? "0" + String.valueOf(hour) : String.valueOf(hour);
		String minStr = minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute);
		assertEquals(validDate, String.valueOf(year) + "-" + monthStr + "-" + dateStr);
		assertEquals(validTime, hourStr + ":" + minStr);
	}
	
	@Test
	public void testCheckInClosingTime() {
		Date departureDate = myFlight.checkInClosingTime();
		Calendar departureCalendar = Calendar.getInstance();
		departureCalendar.setTime(departureDate);
		int year = departureCalendar.get(Calendar.YEAR);
		int month = departureCalendar.get(Calendar.MONTH);
		int date = departureCalendar.get(Calendar.DATE);
		int hour = departureCalendar.get(Calendar.HOUR_OF_DAY);
		int minute = departureCalendar.get(Calendar.MINUTE);
		String monthStr = month < 10 ? "0" + String.valueOf(month) : String.valueOf(month);
		String dateStr = date < 10 ? "0" + String.valueOf(date) : String.valueOf(date);
		String hourStr = hour < 10 ? "0" + String.valueOf(hour) : String.valueOf(hour);
		String minStr = minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute);
		assertEquals(validDate, String.valueOf(year) + "-" + monthStr + "-" + dateStr);
		assertEquals(validTimeCheckInClose, hourStr + ":" + minStr);
		
	}
}