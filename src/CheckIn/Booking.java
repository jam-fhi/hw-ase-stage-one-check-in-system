package CheckIn;

public class Booking {
	
	private String bookingcode;
	private Passenger passenger;
	private String flightcode;
	private Bag bag;
	
	public Booking(String bcode,String fname, String lname ,String fcode) throws BookingException{
		if (bcode.length() != 5) {
			throw new BookingException();
		}// check if booking code fits format current assumed format is a string of 6 characters
		bookingcode = bcode;
		passenger = new Passenger(false , null ,fname,lname );
		flightcode = fcode;
	}
	public void addBaggage(int width, int length, int height, double weight) {
		bag = new Bag( width, length,  height,  weight);
		passenger.addBaggage(bag);
	}
	public String getBookingCode() {
		return bookingcode;
		
	}
	public Passenger getGuest() {
		return passenger;
		
	}
	public String getFlightCode() {
		return flightcode;
		
	}
	public void setCheckedin() {
		passenger.setCheckIn();
	}
}
