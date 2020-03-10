import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.TreeSet;

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
		
	@Before
	public void beforeEach() throws CheckInIOException, BookingException {
		myCheckin = new CheckIn(validflightfile, validbookingfile);
		flight = new FlightCollection(validflightfile);
		booking = new BookingCollection(validbookingfile);
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

