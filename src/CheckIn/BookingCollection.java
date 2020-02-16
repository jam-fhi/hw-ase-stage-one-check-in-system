package theactualone;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
public class BookingCollection {
	private HashMap<String, Booking> Bookings;
	private Booking giveit;
	private Boolean loaded = false;
	private String  hmmmm[]  = new String[5];
	private Booking bookingtobeadded;
	private FlightPlaceholder theflight; // replace flight test and flight collection calls with appropiate functions and names
	
	
	
	private void loadBookings(String filename) throws InvalidBookingCode {
		 Bookings = new HashMap<String, Booking>();
		 ArrayList<String> fileContents = new ArrayList<String>();
			Iterator<String[]> fileLinesIt = fileLine.iterator();
			while(fileLinesIt.hasNext()) {
				String[] hmmmm = fileLinesIt.next();
		//csv loader here = csvloader(filename)
				
		 
			bookingtobeadded = new Booking(
					hmmmm[3], // bookingcode
					hmmmm[1], // guestfname
					hmmmm[2], // guestlname
					hmmmm[0], // flightcode
					theflight.getAllowedBaggageVolume(),theflight.getAllowedBaggageWeight());
			Bookings.put(hmmmm[3], bookingtobeadded);
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

