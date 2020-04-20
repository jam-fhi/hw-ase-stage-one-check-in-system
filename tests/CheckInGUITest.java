import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import checkInModel.BookingCollection;
import checkInModel.BookingException;
import checkInModel.CheckInIOException;
import checkInModel.FlightCollection;
import checkInView.CheckInGUI;

public class CheckInGUITest {

	@Test
	public void testCheckInGUI() throws CheckInIOException, BookingException {
		BookingCollection bookings = new BookingCollection("bookings.csv");
		FlightCollection flights = new FlightCollection("flights.csv");
		CheckInGUI checkInGUI = new CheckInGUI(bookings, flights);
		assertNotNull(checkInGUI);
	}
}
