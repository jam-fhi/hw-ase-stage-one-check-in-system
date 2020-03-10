import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightException;
import model.CheckIn;


public class CheckInTests {
	
	private BookingCollection booking;
	private Booking bookings;
	private FlightCollection flight;
	private int validFlightSize = 4;
	private String  validbookingfile = "bookings.csv";
	private String validflightfile = "flights.csv";
	private CheckIn myCheckin = null;
	private String validFlightCode = "BA123";

	private String validDestinationAirport = "Barcelona";
	private String validCarrier = "EasyJet";
	private int validMaximumPassengers = 40;
	private double validMaximumBaggageWeight = 20;
	private double validMaximumBaggageVolume = 1000;
	private double validExcessCharge = 7;
	private String validDepartureTime = "00:30";
	private String validOpenDepartureTime = "18:45";
	private String validDepartureDate = "2020-03-01";
	private String validOpenDepartureDate = "2020-03-03";

	@Before
	public void beforeEach() throws CheckInIOException, BookingException {
		myCheckin = new CheckIn(validflightfile, validbookingfile);
		flight = new FlightCollection(validflightfile);
		booking = new BookingCollection(validbookingfile);
	}
	
	@Test
	public void testCheckInClosed() {
		Flight testFlight = new Flight(validFlightCode, validDestinationAirport, validCarrier, validMaximumPassengers,
				validMaximumBaggageWeight, validMaximumBaggageVolume, validExcessCharge, validDepartureTime,
				validDepartureDate);
	boolean isCheckInClosed = myCheckin.isCheckInClosed(testFlight);
		assertEquals(true, isCheckInClosed);
	}

	@Test
	public void testCheckInOpen() {
		Flight testFlight = new Flight(validFlightCode, validDestinationAirport, validCarrier, validMaximumPassengers,
				validMaximumBaggageWeight, validMaximumBaggageVolume, validExcessCharge, validOpenDepartureTime,
				validOpenDepartureDate);
	
	boolean isCheckInClosed = myCheckin.isCheckInClosed(testFlight);
		assertEquals(false, isCheckInClosed);
	}

	@Test
	public void testBookingCollection() throws BookingException, CheckInIOException {
		BookingCollection resultBookingCollection = myCheckin.getBookingCollection();
		//Booking resultBookingCollection = myCheckin.getBookingCollection().getBooking(bookingcode, lastnamez);
		assertNotEquals(booking, resultBookingCollection);
	}
	@Test
	public void testFlightCollection() throws FlightException {
		FlightCollection resultFlightCollection = myCheckin.getFlightCollection();
		assertNotEquals(flight, resultFlightCollection);
	}
	
	
}

