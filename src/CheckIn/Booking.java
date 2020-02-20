package CheckIn;

public class Booking {
	
	private String bookingCode;
	private Passenger passenger;
	private String flightCode;
	
	public Booking(String bookingCode,String firstName, String lastName ,String flightCode) throws BookingException{
		isBookingCodeValid(bookingCode);
		this.bookingCode = bookingCode;
		passenger = new Passenger(firstName,lastName);
		this.flightCode = flightCode;
	}

	private void isBookingCodeValid(String code) throws BookingException {
		// check if booking code fits format current assumed format is a string of 6 characters
		if (code.length() < 5) {
			throw new BookingException("Invalid Booking Code");
		}
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public String getFlightCode() {
		return flightCode;
		
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
}
