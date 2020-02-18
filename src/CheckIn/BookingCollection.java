
package CheckIn;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
public class BookingCollection {
	private HashMap<String, Booking> Bookings;
	private Booking giveit;
	private Boolean loaded = false;
	private String  newbooking[]  = new String[5];
	private Booking bookingtobeadded;
	private Flight theflight; // replace flight test and flight collection calls with appropiate functions and names
	
	public BookingCollection() {
		Bookings = new HashMap<String, Booking>();
	}
	private void loadBookings(String filename) throws FileNotFoundException, IOException, BookingException {
		CSVProcessor csv = new CSVProcessor();
		ArrayList<String[]> fileContents = csv.parseCSVToStringArray(filename);
		Iterator<String[]> fileLinesIt = fileContents.iterator();
		while(fileLinesIt.hasNext()) {
			String[] newbooking = fileLinesIt.next();
			bookingtobeadded = new Booking(
				(String)newbooking[3], // bookingcode
				newbooking[1], // guestfname
				newbooking[2], // guestlname
				newbooking[0]); // flightcode
			Bookings.put(newbooking[3], bookingtobeadded);
		}
		loaded = true;
	}
	public Booking getBooking(String Bookingid, String lastname) throws BookingException {
		// needs to throw an exception when booking hasnt been loaded yet and when the booking code doesnt exist
		if(!loaded) {
			throw new BookingException();
		}
		try {
			giveit = Bookings.get(Bookingid); 
			}catch(Exception e){
				throw new BookingException(); // catch nullpointer exception
			}
	
			if (giveit.getGuest().getLastName() == lastname) {// to fix with passenger update
			return giveit;
			}else {
				throw new BookingException();
			}
			 //  Block of code to try
		
		
	}
}

