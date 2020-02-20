
	
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import CheckIn.Booking;
import CheckIn.BookingException;
import CheckIn.Passenger;
public class Bookingtest {
		private Booking thebooking ;
	
		@Before 
		public void setUp() throws BookingException {  
			thebooking = new Booking("AA000", "yesfname", "yeslname", "yesfcode"); 
			// default valid booking for most methods
		}
		@Test
		public void testgetBookingCode()
		{
			
			 String expected1 = "AA000";
			 String message1 = "Failed to retrieve ";
		     String actual1 = thebooking.getBookingCode();
			 assertEquals(message1, expected1, actual1);
			 try {
			 Booking thelongbooking = new Booking("AA00000000", "yesfname", "yeslname", "yesfcode");
			 fail("Invalid code - should throw exception");
			 }catch(BookingException e){}
			 // attempt to construct an illegal booking code here
			
		}
		
		@Test
		public void testgetGuest() {
			assertTrue(thebooking.getPassenger().getFirstName() == "yesfname");
			assertFalse(thebooking.getPassenger().getLastName() == "notguy");
		}
		
	
		
		@Test
		public  void getFlightCodeTest() {
			 assertTrue(thebooking.getFlightCode() == "yesfcode");
			 assertFalse(thebooking.getFlightCode() == "abcdeefghf");
		}
		
	}


