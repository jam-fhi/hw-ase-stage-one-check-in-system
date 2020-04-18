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
	 * Initialise local variables for booking.
	 * Booking code is <FlightCode>-<BookingNumber> e.g. BA-123-123
	 * Passenger object holds passenger name, bag, etc
	 * Flight Code that this booking is for.
	 * businessClass boolean marks if this is a business class flight
	 */
	private String bookingCode;
	private Passenger passenger;
	private String flightCode;
	private boolean businessClass = false;

	/**
	 * Booking
	 * The constructor checks if the booking code format is correct 
	 * and then creates a booking and passenger with the supplied variables.
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
	 * Checks if the booking code is valid and throws if not.
	 * @param code
	 * @throws BookingException
	 */
	private void isBookingCodeValid(String code) throws BookingException {
		/**
		 * Check if booking code fits format current format is a string of 8 characters.
		 */
		if (code.length() < 8) {
			throw new BookingException("Invalid Booking Code"); 
		}
	}

	/**
	 * getBookingBode
	 * Methods to retrieve the bookings variables.
	 * @return
	 */
	public String getBookingCode() {
		return bookingCode;
	}

	/**
	 * getFlightCode
	 * Returns the flight code.
	 * @return String
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * getPassenger
	 * This should be called to retrieve information about the passenger and their bags and excess baggage.
	 * @return Passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}
	
	/**
	 * isBusinessClass
	 * Returns true if this booking is business class.
	 * @return boolean
	 */
	public boolean isBusinessClass() {
		return businessClass;
	}

	/**
	 * setBusinessClass
	 * Sets true to mark this booking as business class.
	 * No parameters as default means it is not business
	 * class and we don't downgrade passengers. We're nice
	 * that way ;)
	 */
	public void setBusinessClass() {
		this.businessClass = true;
	}
}
