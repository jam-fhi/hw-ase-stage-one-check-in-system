package CheckIn;
/** 
 * imports
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * BookingCollection
 * Stores and manages a collection of bookings
 * @author christophermuir
 *
 */
public class BookingCollection {
	/**
	 *  initialise hash map
	 */
	private HashMap<String, Booking> Bookings = new HashMap<String, Booking>(); 
	
	/**
	 *  the constructor calls load bookings to fill the hash map
	 * @param fileName
	 * @throws CheckInIOException
	 * @throws BookingException
	 */
	public BookingCollection(String fileName) throws CheckInIOException, BookingException { 
		loadBookings(fileName);
	}

	/**
	 * loadBookings
	 * Reads the booking csv file and loads the data into booking objects which are put into a collection.
	 * @param fileName
	 * @throws BookingException
	 * @throws CheckInIOException
	 */
	public void loadBookings(String fileName) throws BookingException, CheckInIOException {
		CSVProcessor csv = new CSVProcessor();
		/**
		 *  calls csv proccessor to convert to string array
		 */
		ArrayList<String[]> fileContents = csv.parseCSVToStringArray(fileName);
		/**
		 * initialise a new iterator for this file
		 */
		Iterator<String[]> fileLinesIt = fileContents.iterator(); 
		/**
		 *  run until the file has been completly ran through
		 */
		while(fileLinesIt.hasNext()) { 
			/**
			 *  set to next line of file
			 */
			String[] loadBooking = fileLinesIt.next(); 
			Booking loadBookingObj = new Booking(
				loadBooking[3].trim(), 	// booking code
				loadBooking[1].trim(), 	// guest first name
				loadBooking[2].trim(), 	// guest last name
				loadBooking[0].trim()); // flight code
			 /**
			  *  add to hash map with booking code as the key
			  */
			Bookings.put(loadBooking[3], loadBookingObj);
		}
	}

	/**
	 * getBooking
	 * Returns the booking object matching the id and last name
	 * @param Bookingid
	 * @param lastName
	 * @return Booking
	 * @throws BookingException
	 */
	public Booking getBooking(String Bookingid, String lastName) throws BookingException {
		/**
		 *  throws the booking exception back to the caller when booking hasn't been loaded yet and when the booking code doesn't exist
		 */
		if(Bookings.isEmpty()) {
			throw new BookingException("There are no bookings loaded");
		}

		Booking foundBooking = null;
		
		
		foundBooking = Bookings.get(Bookingid);
		if(foundBooking == null) {
			throw new BookingException("Booking not found");
		}
		/**
		 *  additionally throws the booking when the last name is erronous 
		 */
		if (foundBooking.getPassenger().doesLastNameMatch(lastName)) {
			return foundBooking;
		} else {
			throw new BookingException("Passenger for '" + lastName + "' does not match booking");
		}
	}
	
	/**
	 * getBookingByFlightCode
	 * @param flightCode
	 * @return ArrayList<Booking>
	 */
	public ArrayList<Booking> getBookingByFlightCode(String flightCode) {
		/**
		 *  can be called to retrieve all bookings with a certain flight code
		 */
		ArrayList<Booking> flightBookings = new ArrayList<Booking>();
		for(Map.Entry<String, Booking> aBooking: Bookings.entrySet()) {
			/**
			 * if the flight codes match 
			 */
			if(aBooking.getValue().getFlightCode().compareTo(flightCode) == 0) {
				flightBookings.add(aBooking.getValue());
			}
		}
		return flightBookings;
	}
	
	/**
	 * 
	 * Will find and return the first passenger not checked in
	 * @return Passenger
	 * @throws Exception
	 */
	public Passenger getPassengerNotCheckedIn() throws Exception {
		for(Map.Entry<String, Booking> aBooking: Bookings.entrySet()) {
			/**
			 * TODO: What if another thread is using this passenger? 
			 */
			if(aBooking.getValue().getPassenger().isCheckIn() == false) {
				return aBooking.getValue().getPassenger();
			}
		}
		throw new Exception("No passengers found who are not checked in");
	}
}