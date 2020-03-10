import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.FlightCollection;
import checkInGUI.CheckInGUI;

public class CheckInGUITest {

	@Test
	public void testCheckInGUI() throws CheckInIOException, BookingException {
		BookingCollection bookings = new BookingCollection("bookings.csv");
		FlightCollection flights = new FlightCollection("flights.csv");
		CheckInGUI checkInGUI = new CheckInGUI(bookings, flights);
		assertNotNull(checkInGUI);
	}
}
