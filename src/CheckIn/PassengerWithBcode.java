package CheckIn; 
/** 
 * PassengerWithBcode is a class for passing the Passenger with booking code to the checkin 
 * desk without passing an entire booking object. 
 * @author ChristopherSamMuir 
 */ 
public class PassengerWithBcode { 
	/** 
	 *  initialise local variables 
	 */ 
	private Passenger passenger; 
	private String bcode; 
	 
	/** 
	 * PassengerWithBcode creating constructor 
	 *  
	 * @param bcode 
	 * @param p 
	 * @throws BookingException  
	 *  
	 */ 
	public PassengerWithBcode(String bcode, Passenger p) throws BookingException { 
		this.passenger = p; 
		if (bcode.length() < 8) { 
			throw new BookingException("Invalid Booking Code");  
		}else { 
		this.bcode = bcode; 
		} 
	} 
	/** 
	 * getBookingCode 
	 * Returns the Booking code 
	 * @return String 
	 */ 
	public String getBookingCode() { 
		return bcode; 
	} 
	/** 
	 * getPassenger 
	 * Returns the Passenger 
	 * @return String 
	 */ 
	public Passenger getPassenger() { 
		return passenger; 
	} 
} 