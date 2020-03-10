import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CheckIn.PassengerWithBcode;
import CheckIn.BookingException;
import CheckIn.Passenger;

/**
 * 
 * TestPassengerWithBcode
 * Test suite for the PassengerWithBcode class
 * @author christophermuir
 *
 */
public class TestPassengerWithBcode {
	

	/**
	 *  check if a PassengerWithBcode can be created properly
	 */
	@Test
	public void testConstructor() {
		Passenger p = new Passenger("henry" , "henryson");
		String validbcode = "iamavalidbookingcode";
		try {
			PassengerWithBcode complete = new PassengerWithBcode(validbcode,p);
		} catch (BookingException e) {
			fail("some exception has been thrown");
		}
		String invalidbcode = "no";
		try {
			PassengerWithBcode complete = new PassengerWithBcode(invalidbcode,p);
			fail("an exception has not been thrown");
		} catch (BookingException e) {
		
		}
		
	}
	/**
	 *  test if the correct code is retrieved
	 * @throws BookingException 
	 */
	@Test
	public void testgetBookingcode() throws BookingException {
		Passenger p = new Passenger("henry" , "henryson");
		String validbcode = "iamavalidbookingcode";
		PassengerWithBcode complete = new PassengerWithBcode(validbcode,p);
		assertEquals(complete.getBookingCode(), "validbcode");
		assertNotEquals(complete.getBookingCode() , "notguy");
	}
	/**
	 *  test if the correct Passenger
	 * @throws BookingException 
	 */
	@Test
	public void getPassenger() throws BookingException {
		Passenger p = new Passenger("henry" , "henryson");
		String validbcode = "iamavalidbookingcode";
		PassengerWithBcode complete = new PassengerWithBcode(validbcode,p);
		assertEquals(complete.getPassenger(), p);
		assertNotEquals(complete.getPassenger() , null);
	}		
}