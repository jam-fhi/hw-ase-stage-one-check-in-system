import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import CheckIn.Bag;
import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightException;
import CheckIn.Passenger;
import CheckIn.ThreadNewPassenger;
import model.CheckIn;


public class CheckInTests {
	
	private BookingCollection booking;
	private FlightCollection flight;
	private String  validbookingfile = "bookings.csv";
	private String validflightfile = "flights.csv";
	private CheckIn myCheckin = null;
	private String validFlightCode = "BA123";
	private String validBookingCode = "BA123-121";
	private String validLastName = "Hill";
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
	private String checkInClosedException = "The check in desk is closed.";
	
	@Before
	public void beforeEach() throws CheckInIOException, BookingException {
		ThreadNewPassenger so = new ThreadNewPassenger();
		myCheckin = new CheckIn(validflightfile, validbookingfile, so);
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
	public void testDoCheckInSuccess() throws BookingException, FlightException {
		Booking aBooking = booking.getBooking(validBookingCode, validLastName);
		Passenger aPassenger = aBooking.getPassenger();
		Bag baggage = new Bag(10, 10, 10, 1000.0);
		myCheckin.doCheckIn(validBookingCode, aPassenger, baggage);
		boolean isCheckedIn = myCheckin.getBookingCollection().getBooking(validBookingCode, validLastName).getPassenger().isCheckIn();
		assertEquals(true, isCheckedIn);
	}
	
	private void waitForMilliseconds(long miliseconds) {
		Date startDate = new Date();
		Date currentDate = new Date();
		while(currentDate.getTime() - startDate.getTime() < miliseconds) {
			currentDate = new Date();
		}
	}
	
	@Test
	public void testDoCheckInFail() throws BookingException, FlightException {
		Booking aBooking = booking.getBooking(validBookingCode, validLastName);
		Passenger aPassenger = aBooking.getPassenger();
		Bag baggage = new Bag(10, 10, 10, 1000.0);
		waitForMilliseconds(1000);
		try {
			myCheckin.doCheckIn(validBookingCode, aPassenger, baggage);
			fail("Check In did not throw an error");
		} catch(BookingException e) {
			assertEquals(checkInClosedException, e.getMessage());
		}
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

