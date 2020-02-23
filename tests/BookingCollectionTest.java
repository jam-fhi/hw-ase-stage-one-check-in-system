
	
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;

public class BookingCollectionTest {



		/*
		 * Tests method to return date in format dd/mm/yyyy
		 * numbers < 10 need a leading 0 for day and month
		 */
	
		@Test
		public void testloadbookings()
		{ try {
			 new BookingCollection("bookings.csv");
			 
		}catch(Exception e) {fail("exception thrown");}
		}
		
		@Test
		public void testgetbooking() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
			BookingCollection TestCollection = new BookingCollection("bookings.csv");

			Booking test1 = TestCollection.getBooking("BA123-121", "Hill");
			assertEquals(test1.getPassenger().getFirstName() , "Jamie");
			assertEquals(test1.getFlightCode(), "BA123" );
		}
		@Test
		public void testfilenotfound() {
			try {
				new BookingCollection("fakefilename");
				fail("file was found unexpectedly");
			} catch(Exception e) {
				assertEquals(e.getMessage() ,"The file fakefilename was not found");
			}
		}
		
		@Test
		public void  testinvalidbookingcode() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
			BookingCollection TestCollection = new BookingCollection("bookings.csv");
			try {
			TestCollection.getBooking("BA1236567568_,.;%$$", "Hill");
			fail("Booking Exception not thrown");
			}catch(BookingException e) {}
		}
		

		/**
		 * tests that exception is thrown for invalid dates
		 * @throws BookingException 
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 * @throws CheckInIOException 
		 *
		 */
		@Test(expected = BookingException.class)
		public  void testwronglastname() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
			BookingCollection TestCollection = new BookingCollection("bookings.csv");
		
			TestCollection.getBooking("BA123-121", "Hillock");
			fail("Booking Exception not thrown");
	
		}
		@Test
		public void testgetbookingbyflightcode() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
			BookingCollection TestCollection = new BookingCollection("bookings.csv");

			ArrayList<Booking> testarray = TestCollection.getBookingByFlightCode("BA123");
			Booking test1 = testarray.get(1);
			assertEquals( "Jamie" , test1.getPassenger().getFirstName() );
			assertEquals(test1.getFlightCode(), "BA123" );
		}
		@Test
		public void testgetbookingbyflightcodeinvalid() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
			BookingCollection TestCollection = new BookingCollection("bookings.csv");
			ArrayList<Booking> testarray = TestCollection.getBookingByFlightCode("BA1235454654654");
			assertEquals(testarray.size() , 0);
			
		}
	}


