import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import checkInGUI.ConfirmGUI;

public class ConfirmGUITests {

	@Test
	public void testConfirmGUI() throws CheckInIOException, BookingException {
		BookingCollection bookings = new BookingCollection("bookings.csv");
		FlightCollection flights = new FlightCollection("flights.csv");
		Booking allbookings = new Booking("BA123-121", "Jamie", "Hill", "BA123");
		Flight allflights = new Flight("BA123", "Barcelona", "British Airways", 123, 123, 123,20);
		ConfirmGUI confirmGUI = new ConfirmGUI(allbookings, allflights,bookings, flights);
		assertNotNull(confirmGUI);
	}
}