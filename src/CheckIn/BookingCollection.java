package CheckIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * BookingCollection
 * Stores and manages a collection of bookings.
 * @author christophermuir
 *
 */
public class BookingCollection {
	
	/**
	 *  Initialise the hash map.
	 */
	private HashMap<String, Booking> Bookings = new HashMap<String, Booking>(); 
	
	/**
	 * BookingCollection
	 * The constructor calls load bookings to fill the hash map.
	 * @param fileName
	 * @throws CheckInIOException
	 * @throws BookingException
	 */
	public BookingCollection(String fileName) throws CheckInIOException, BookingException { 
		loadBookings(fileName);
	}

	/**
	 * loadBookings
	 * Reads the booking CSV file and loads the data into booking objects which are put into a collection.
	 * @param fileName
	 * @throws BookingException
	 * @throws CheckInIOException
	 */
	public void loadBookings(String fileName) throws BookingException, CheckInIOException {
		CSVProcessor csv = new CSVProcessor();
		/**
		 *  Calls CSV processor to convert to string array.
		 */
		ArrayList<String[]> fileContents = csv.parseCSVToStringArray(fileName);
		/**
		 * Initialise a new iterator for this file.
		 */
		Iterator<String[]> fileLinesIt = fileContents.iterator(); 
		/**
		 *  Run until the file has been completely ran through.
		 */
		while(fileLinesIt.hasNext()) { 
			/**
			 *  Set to next line of file.
			 */
			String[] loadBooking = fileLinesIt.next(); 
			Booking loadBookingObj = new Booking(
				loadBooking[3].trim(), 	// Booking code.
				loadBooking[1].trim(), 	// Guest first name.
				loadBooking[2].trim(), 	// Guest last name.
				loadBooking[0].trim()); // Flight code.
			 /**
			  *  Add to hash map with booking code as the key.
			  */
			Bookings.put(loadBooking[3], loadBookingObj);
		}
	}

	/**
	 * getBooking
	 * Returns the booking object matching the id and last name.
	 * @param Bookingid
	 * @param lastName
	 * @return Booking
	 * @throws BookingException
	 */
	public Booking getBooking(String Bookingid, String lastName) throws BookingException {
		/**
		 *  Throws the booking exception back to the caller when booking hasn't been loaded yet and when the booking code doesn't exist.
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
		 *  Additionally throws the booking when the last name is erroneous. 
		 */
		if (foundBooking.getPassenger().doesLastNameMatch(lastName)) {
			return foundBooking;
		} else {
			throw new BookingException("Passenger for '" + lastName + "' does not match booking");
		}
	}
	
	/**
	 * getBookingByFlightCode
	 * Gets all bookings for a given flight code.
	 * @param flightCode
	 * @return ArrayList<Booking>
	 */
	public ArrayList<Booking> getBookingByFlightCode(String flightCode) {
		/**
		 *  Can be called to retrieve all bookings with a certain flight code.
		 */
		ArrayList<Booking> flightBookings = new ArrayList<Booking>();
		for(Map.Entry<String, Booking> aBooking: Bookings.entrySet()) {
			/**
			 * If the flight codes match.
			 */
			if(aBooking.getValue().getFlightCode().compareTo(flightCode) == 0) {
				flightBookings.add(aBooking.getValue());
			}
		}
		return flightBookings;
	}
	
	/**
	 * 
	 * getPassengerNotCheckedIn
	 * Will find and return the first passenger not checked in.
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
	/**
	 * 
	 * Will find and return appropiate booking code for the passenger
	 * @return String
	 * @throws Exception
	 */
	public String getBookingCode(Passenger p) throws Exception {
		for(Map.Entry<String, Booking> aBooking: Bookings.entrySet()) {
			if(aBooking.getValue().getPassenger() == p) {
				return aBooking.getValue().getBookingCode();
			}
		}
		throw new Exception("No such Passenger in file");
	
		
		
	}
}
