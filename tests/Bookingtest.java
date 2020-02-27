import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CheckIn.Booking;
import CheckIn.BookingException;
public class Bookingtest {
	
	private Booking thebooking ;
// initialise the booking before each method
	@Before 
	public void setUp() throws BookingException {  
		thebooking = new Booking("AA000-000", "yesfname", "yeslname", "yesfcode"); 
		// default valid booking for most methods
	}
	// check if a booking can be correctly retrieved
	@Test
	public void testgetBookingCode() {
		String expected1 = "AA000-000";
		String message1 = "Failed to retrieve ";
		String actual1 = thebooking.getBookingCode();
		assertEquals(message1, expected1, actual1);
		// test if an invalid booking cide will correctly throw a booking exception
		try {
			new Booking("AA0", "yesfname", "yeslname", "yesfcode");
			fail("Invalid code - should throw exception");
		} catch(BookingException e) {
				 
		}			
	}
	// test if the correct passenger is retrieved
	@Test
	public void testgetGuest() {
		assertEquals(thebooking.getPassenger().getFirstName(), "yesfname");
		assertNotEquals(thebooking.getPassenger().getLastName() , "notguy");
	}
	// test if the correct flight code is retrieved
	@Test
	public void getFlightCodeTest() {
		assertEquals(thebooking.getFlightCode(), "yesfcode");
		assertNotEquals(thebooking.getFlightCode(),  "abcdeefghf");
	}		
}