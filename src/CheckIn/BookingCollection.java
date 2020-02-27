package CheckIn;
//imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BookingCollection {
	private HashMap<String, Booking> Bookings = new HashMap<String, Booking>(); // initialise hash map
	
	public BookingCollection(String fileName) throws CheckInIOException, BookingException { // the constructor calls load bookings to fill the hash map
		loadBookings(fileName);
	}

	public void loadBookings(String fileName) throws BookingException, CheckInIOException {
		CSVProcessor csv = new CSVProcessor();
		ArrayList<String[]> fileContents = csv.parseCSVToStringArray(fileName);// calls csv proccessor to convert to string array
		Iterator<String[]> fileLinesIt = fileContents.iterator(); // initialise a new iterator for this file
		while(fileLinesIt.hasNext()) { // run until the file has been completly ran through
			String[] loadBooking = fileLinesIt.next(); // set to next line of file
			Booking loadBookingObj = new Booking(
				loadBooking[3].trim(), 	// booking code
				loadBooking[1].trim(), 	// guest first name
				loadBooking[2].trim(), 	// guest last name
				loadBooking[0].trim()); // flight code
			Bookings.put(loadBooking[3], loadBookingObj); // add to hash map with booking code as the key
		}
	}

	public Booking getBooking(String Bookingid, String lastName) throws BookingException {
		// throws the booking exception back to the caller when booking hasn't been loaded yet and when the booking code doesn't exist
		if(Bookings.isEmpty()) {
			throw new BookingException("There are no bookings loaded");
		}

		Booking foundBooking = null;
		
		
		foundBooking = Bookings.get(Bookingid);
		if(foundBooking == null) {
			throw new BookingException("Booking not found");
		}
		// additionally throws the booking when the last name is erronous 
		if (foundBooking.getPassenger().doesLastNameMatch(lastName)) {
			return foundBooking;
		} else {
			throw new BookingException("Passenger for '" + lastName + "' does not match booking");
		}
	}
	
	public ArrayList<Booking> getBookingByFlightCode(String flightCode) {// can be called to retrieve all bookings with a certain flight code
		ArrayList<Booking> flightBookings = new ArrayList<Booking>();
		for(Map.Entry<String, Booking> aBooking: Bookings.entrySet()) {
			if(aBooking.getValue().getFlightCode().compareTo(flightCode) == 0) { // if the flight codes match 
				flightBookings.add(aBooking.getValue());
			}
		}
		return flightBookings;
	}
}