import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import checkInGUI.ConfirmGUI;
import checkInModel.Booking;
import checkInModel.BookingCollection;
import checkInModel.BookingException;
import checkInModel.CheckInIOException;
import checkInModel.Flight;
import checkInModel.FlightCollection;

public class ConfirmGUITests {

	@Test
	public void testConfirmGUI() throws CheckInIOException, BookingException {
		BookingCollection bookings = new BookingCollection("bookings.csv");
		FlightCollection flights = new FlightCollection("flights.csv");
		Booking allbookings = new Booking("BA123-121", "Jamie", "Hill", "BA123");
		Flight allflights = new Flight("BA123", "Barcelona", "British Airways", 123, 123, 123,20, "14:00", "2020-03-01");
		ConfirmGUI confirmGUI = new ConfirmGUI(allbookings, allflights,bookings, flights);
		assertNotNull(confirmGUI);
	}
}