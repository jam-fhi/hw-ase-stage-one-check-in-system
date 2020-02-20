
	
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import CheckIn.Booking;
import CheckIn.Passenger;
public class BookingCollectionTest {



		/*
		 * Tests method to return date in format dd/mm/yyyy
		 * numbers < 10 need a leading 0 for day and month
		 */
	
		@Test
		public void loadbookings()
		{ try {
			 BookingCollection TestCollection = new BookingCollection();
			 TestCollection.loadBookings("bookings.csv");
			 
		}catch(Exception e) {fail("exception thrown");}
		}
		
		@Test
		public void getbooking() throws FileNotFoundException, IOException, BookingException {
			BookingCollection TestCollection = new BookingCollection();
			TestCollection.loadBookings("bookings.csv");
			Booking test1 = TestCollection.getBooking("BA123", "Hill");
			assertTrue(test1.getGuest().getFirstName() == "Jamie");
			assertTrue(test1.getFlightCode() == "BA123-121");
		}
		@Test
		public void testunloaded() {
			try {BookingCollection TestCollection = new BookingCollection();
			TestCollection.getBooking("BA123", "Hill");
			fail("not loaded exception not thrown");
			}catch(Exception e) {}
		}
		/*
		 * tests that 2 dates are equal. 
		 * Test all components diff, 2 same, all same
		 */
		@Test
		public void testfilenotfound() {
			try {
			BookingCollection TestCollection = new BookingCollection();
			TestCollection.loadBookings("thisisafakefilename");
			fail("file was found unexpectadly");
			}catch(Exception e) {}
		}
		
		@Test
		public void  testinvalidbookingcode() throws FileNotFoundException, IOException, BookingException {
			BookingCollection TestCollection = new BookingCollection();
			TestCollection.loadBookings("bookings.csv");
			try {
			Booking test1 = TestCollection.getBooking("BA1236567568_,.;%$$", "Hill");
			fail("Booking Exception not thrown");
			}catch(BookingException e) {}
		}
		

		/**
		 * tests that exception is thrown for invalid dates
		 * @throws BookingException 
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 *
		 */
		@Test(expected = IllegalArgumentException.class)
		public  void wronglastnametest() throws FileNotFoundException, IOException, BookingException {
			BookingCollection TestCollection = new BookingCollection();
			TestCollection.loadBookings("bookings.csv");
			try {
			Booking test1 = TestCollection.getBooking("BA123", "Hillock");
			fail("Booking Exception not thrown");
			}catch(BookingException e) {}
		}

	}


