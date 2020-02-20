
package CheckIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BookingCollection {
	private HashMap<String, Booking> Bookings = new HashMap<String, Booking>();
	
	public BookingCollection(String fileName) throws CheckInIOException, BookingException {
		loadBookings(fileName);
	}

	public void loadBookings(String fileName) throws BookingException, CheckInIOException {
		CSVProcessor csv = new CSVProcessor();
		ArrayList<String[]> fileContents = csv.parseCSVToStringArray(fileName);
		Iterator<String[]> fileLinesIt = fileContents.iterator();
		while(fileLinesIt.hasNext()) {
			String[] loadBooking = fileLinesIt.next();
			Booking loadBookingObj = new Booking(
				loadBooking[3].trim(), 	// booking code
				loadBooking[1].trim(), 	// guest first name
				loadBooking[2].trim(), 	// guest last name
				loadBooking[0].trim()); // flight code
			Bookings.put(loadBooking[3], loadBookingObj);
		}
	}

	public Booking getBooking(String Bookingid, String lastName) throws BookingException {
		// needs to throw an exception when booking hasn't been loaded yet and when the booking code doesn't exist
		if(Bookings.isEmpty()) {
			throw new BookingException("There are no bookings loaded");
		}

		Booking foundBooking = null;
		
		try {
			foundBooking = Bookings.get(Bookingid); 
		} catch (Exception e) {
			throw new BookingException("Booking not found");
		}
	
		if (foundBooking.getPassenger().doesLastNameMatch(lastName)) {
			return foundBooking;
		} else {
			throw new BookingException("Passenger for '" + lastName + "' does not match booking");
		}
	}
	
	public ArrayList<Booking> getBookingByFlightCode(String flightCode) {
		ArrayList<Booking> flightBookings = new ArrayList<Booking>();
		for(Map.Entry<String, Booking> aBooking: Bookings.entrySet()) {
			if(aBooking.getValue().getFlightCode().compareTo(flightCode) == 0) {
				flightBookings.add(aBooking.getValue());
			}
		}
		return flightBookings;
	}
}

