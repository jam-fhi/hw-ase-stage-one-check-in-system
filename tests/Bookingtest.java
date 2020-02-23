
	
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CheckIn.Booking;
import CheckIn.BookingException;
public class Bookingtest {
	
	private Booking thebooking ;

	@Before 
	public void setUp() throws BookingException {  
		thebooking = new Booking("AA000", "yesfname", "yeslname", "yesfcode"); 
		// default valid booking for most methods
	}
	
	@Test
	public void testgetBookingCode() {
		String expected1 = "AA000";
		String message1 = "Failed to retrieve ";
		String actual1 = thebooking.getBookingCode();
		assertEquals(message1, expected1, actual1);
		try {
			new Booking("AA0", "yesfname", "yeslname", "yesfcode");
			fail("Invalid code - should throw exception");
		} catch(BookingException e) {
				 
		}			
	}
	
	@Test
	public void testgetGuest() {
		assertEquals(thebooking.getPassenger().getFirstName(), "yesfname");
		assertNotEquals(thebooking.getPassenger().getLastName() , "notguy");
	}
	
	@Test
	public void getFlightCodeTest() {
		assertEquals(thebooking.getFlightCode(), "yesfcode");
		assertNotEquals(thebooking.getFlightCode(),  "abcdeefghf");
	}		
}
