import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import checkInModel.Booking;
import checkInModel.BookingException;
import checkInModel.Passenger;

/**
 * 
 * Bookingtest
 * Test suite for the booking class
 * @author christophermuir
 *
 */
public class Bookingtest {
	
	private Booking thebooking ;
	private String validBookingCode = "AA000-000";
	private String validFlightCode = "BA123";
	private boolean businessclass = false;
	
	/**
	 *  initialise the booking before each method
	 * @throws BookingException
	 */
	@Before 
	public void setUp() throws BookingException {  
		thebooking = new Booking("AA000-000", "yesfname", "yeslname", "BA123"); 
		/**
		 *  default valid booking for most methods
		 */
	}
	/**
	 *  check if a booking can be correctly retrieved
	 */
	@Test
	public void testgetBookingCode() {
		String expected1 = "AA000-000";
		String message1 = "Failed to retrieve ";
		String actual1 = thebooking.getBookingCode();
		assertEquals(message1, expected1, actual1);
		// test if an invalid booking code will correctly throw a booking exception
		try {
			new Booking("AA0", "yesfname", "yeslname", "BA123");
			fail("Invalid code - should throw exception");
		} catch(BookingException e) {
				 
		}			
	}
	
	@Test
	public void testBookingCode() {
		String resultBookingCode = thebooking.getBookingCode();
		assertEquals(validBookingCode, resultBookingCode);
	}
	
	@Test
	public void testFlightCode() {	
		String resultFlightCode = thebooking.getFlightCode();
		assertEquals(validFlightCode, resultFlightCode);
	}
	@Test
	public void testBusinessClass() {	
		boolean resultBusinessClass = thebooking.isBusinessClass();
		assertEquals(businessclass, resultBusinessClass);
	}
	/**
	 *  test if the correct passenger is retrieved
	 */
	@Test
	public void testgetGuest() {
		assertEquals(thebooking.getPassenger().getFirstName(), "yesfname");
		assertNotEquals(thebooking.getPassenger().getLastName() , "notguy");
	}
	/**
	 *  test if the correct flight code is retrieved
	 */
	@Test
	public void getFlightCodeTest() {
		assertEquals(thebooking.getFlightCode(), "BA123");
		assertNotEquals(thebooking.getFlightCode(),  "abcdeefghf");
	}		
}