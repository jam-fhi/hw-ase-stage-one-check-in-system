
	
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

import checkInModel.Booking;
import checkInModel.BookingCollection;
import checkInModel.BookingException;
import checkInModel.CheckInIOException;

/**
 * BookingCollectionTest
 * Test suite for the booking collection
 * @author jamiehill
 *
 */
public class BookingCollectionTest {
	/**
	 * testloadbookings
	 *  test if a booking collection with a valid file can be initialised
	 */
	@Test
	public void testloadbookings() {
		try {
			new BookingCollection();
		} catch(Exception e) {
			fail("exception thrown");
		}
	}
	/**
	 *  test if a booking can be retrieved	
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws BookingException
	 * @throws CheckInIOException
	 */
	@Test
	public void testgetbooking() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
		BookingCollection TestCollection = new BookingCollection();
		ArrayList<Booking> TestData = new ArrayList<Booking>();
		TestData.add(new Booking("BA123-121", "Amy", "McFarland", "BA123"));
		TestCollection.addBatchBookings(TestData);
		Booking test1 = TestCollection.getNextBooking("BA123", "");
		assertEquals(test1.getPassenger().getFirstName() , "Amy");
		assertEquals(test1.getFlightCode(), "BA123" );
	}
	/**
	 *  test if the exception for an invalid file is thrown
	 */
	@Test
	public void testfilenotfound() {
		try {
			new BookingCollection();
			fail("file was found unexpectedly");
		} catch(Exception e) {
			assertEquals(e.getMessage() ,"The file fakefilename was not found");
		}
	}
	/**
	 *  test if the exception for an invalid booking code is thrown
	 * 
	 */
	@Test
	public void testinvalidbookingcode() {
		
		BookingCollection TestCollection = new BookingCollection();
		TestCollection.getNextBooking("BA1236567568_,.;%$$", "Hill");
		fail("Booking Exception not thrown");
	}	

	/**
	 * tests that exception is thrown for invalid names 
	 * @throws BookingException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws CheckInIOException 
	 *
	 */
	@Test(expected = BookingException.class)
	public  void testwronglastname() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
		BookingCollection TestCollection = new BookingCollection();
		TestCollection.getNextBooking("BA123-121", "Hillock");
		fail("Booking Exception not thrown");
	}
	/**
	 *  test if it can retrieve passengers by flight code
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws BookingException
	 * @throws CheckInIOException
	 */
	@Test
	public void testgetbookingbyflightcode() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
		BookingCollection TestCollection = new BookingCollection();
		ArrayList<Booking> testarray = TestCollection.getBookingsByFlightCode("BA123");
		Booking test1 = testarray.get(1);
		assertEquals( "Jamie" , test1.getPassenger().getFirstName() );
		assertEquals(test1.getFlightCode(), "BA123" );
	}
	
	/**
	 *  test an invalid flight code
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws BookingException
	 * @throws CheckInIOException
	 */
	@Test
	public void testgetbookingbyflightcodeinvalid() throws FileNotFoundException, IOException, BookingException, CheckInIOException {
		BookingCollection TestCollection = new BookingCollection();
		ArrayList<Booking> testarray = TestCollection.getBookingsByFlightCode("BA1235454654654");
		assertEquals(testarray.size() , 0);	
	}
	
	
	/**
	 * Tests that when there are no passengers
	 * not checked in that an exception is thrown.
	 * @throws IOException 
	 * @throws BookingException 
	 * @throws CheckInIOException 
	 */
	@Test
	public void testGetPassengerNotCheckedInFail() throws IOException, CheckInIOException, BookingException {
		File writeFile = new File("test.csv");
		FileWriter writer = new FileWriter(writeFile);
		writer.write("Flight Code,First Name,Last Name,Booking Code\nBA123,Jamie, Hill,BA123-121");
		writer.close();
		BookingCollection testCollection = new BookingCollection();
		testCollection.getNextBooking("BA123-121", "Hill").getPassenger().setCheckIn();
		try {
			testCollection.progressBookingPassedSecurity(true);
			fail("Passenger found who is not checked in");
		} catch(Exception e) {
			assertEquals(e.getMessage(), "No passengers found who are not checked in");
		} finally {
			writeFile.delete();
		}
			
	}
}