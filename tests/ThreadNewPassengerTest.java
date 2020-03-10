import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import model.CheckIn;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.FlightException;
import CheckIn.PassengerWithBcode;
import CheckIn.ThreadNewPassenger;

/**
 * 
 * RandomBagGeneratorTest
 * Test suite for the RandomBagGeneratorTest class
 * @author christophermuir
 *
 */
public class ThreadNewPassengerTest {

	/**
	 *  test if a new PassengerWithBcode will be added by the producer
	 * @throws Exception 
	 * @throws FlightException 
	 */
	@Test
	public void Testput() throws FlightException, Exception {
		CheckIn checked = new CheckIn("bookings.csv" , "flights.csv");
		ThreadNewPassenger bd = new ThreadNewPassenger(0.2, checked);
		bd.put();
		assertNotNull(bd.getList());
	}
	/**
	 *  test if  a filled list can be retrieved
	 * @throws Exception 
	 * @throws FlightException 
	 */
	@Test
	public void Testget() throws FlightException, Exception {
		CheckIn checked = new CheckIn("bookings.csv" , "flights.csv");
		ThreadNewPassenger bd = new ThreadNewPassenger(0.2, checked);
		
		bd.put();
		ArrayList<PassengerWithBcode> list = bd.getList();
		bd.getPassengerForCheckIn();
		assertNull(bd.getList());
		assertEquals(list , checked.getQueue());
		
	}
}