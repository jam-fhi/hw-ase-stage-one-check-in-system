package CheckIn;

public class Booking {
	// initialise local variables
	private String bookingCode;
	private Passenger passenger;
	private String flightCode;
	// the constructor checks if the booking code format is correct and then creates a booking and passenger  with the supplied variables
	public Booking(String bookingCode,String firstName, String lastName ,String flightCode) throws BookingException{
		isBookingCodeValid(bookingCode);
		this.bookingCode = bookingCode;
		passenger = new Passenger(firstName,lastName);
		this.flightCode = flightCode;
	}

	private void isBookingCodeValid(String code) throws BookingException {
		// check if booking code fits format current  format is a string of 9 characters
		if (code.length() < 9) {
			throw new BookingException("Invalid Booking Code"); 
		}
	}
// methods to retrieve the bookings variables 
	public String getBookingCode() {
		return bookingCode;
	}

	public String getFlightCode() {
		return flightCode;
		
	}
	// this should be called to retrieve information about the passenger and their bags and excess baggage
	public Passenger getPassenger() {
		return passenger;
	}
}