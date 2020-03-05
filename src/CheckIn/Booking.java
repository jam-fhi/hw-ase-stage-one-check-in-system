package CheckIn;

/**
 * 
 * Booking
 * Booking class to store booking record.
 * @author christophermuir
 *
 */
public class Booking {
	/**
	 *  initialise local variables
	 */
	private String bookingCode;
	private Passenger passenger;
	private String flightCode;
	/**
	 *  the constructor checks if the booking code format is correct and then creates a booking and passenger  with the supplied variables
	 * @param bookingCode
	 * @param firstName
	 * @param lastName
	 * @param flightCode
	 * @throws BookingException
	 */
	public Booking(String bookingCode,String firstName, String lastName ,String flightCode) throws BookingException{
		isBookingCodeValid(bookingCode);
		this.bookingCode = bookingCode;
		passenger = new Passenger(firstName,lastName);
		this.flightCode = flightCode;
	}

	/**
	 * isBookingCodeValid
	 * Checks if the booking code is valid and throws if not
	 * @param code
	 * @throws BookingException
	 */
	private void isBookingCodeValid(String code) throws BookingException {
		/**
		 * check if booking code fits format current format is a string of 8 characters
		 */
		if (code.length() < 8) {
			throw new BookingException("Invalid Booking Code"); 
		}
	}
	/**
	 *  methods to retrieve the bookings variables 
	 * @return
	 */
	public String getBookingCode() {
		return bookingCode;
	}

	/**
	 * getFlightCode
	 * Returns the flight code
	 * @return String
	 */
	public String getFlightCode() {
		return flightCode;
	}
	
	/**
	 *  this should be called to retrieve information about the passenger and their bags and excess baggage
	 * @return Passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}
}