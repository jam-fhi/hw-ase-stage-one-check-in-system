package theactualone;

public class Booking {
	
	private Boolean checkedin = false;
	private String bookingcode;
	private Passenger passenger;
	private String flightcode;
	private Double baggagevolume;
	private Double baggageWeight;
	private Double ExcessVolume;
	private Double ExcessWeight;
	
	public Booking(String bcode,Passenger guest ,String fcode, Double excessV, Double excessW){
		checkedin = false;
		if (bcode.length() != 5) {
			throw new IllegalArgumentException(bcode + " illegal booking code");
		}// check if booking code fits format current assumed format is a string of 6 characters
		bookingcode = bcode;
		passenger = guest;
		flightcode = fcode;
		ExcessVolume = excessV;
		ExcessWeight = excessW;
		baggagevolume = 0.0 ;
		baggageWeight = 0.0;
	}
	public double getexceessbaggagevolume() {
		if(ExcessVolume > baggagevolume) {
			return ExcessVolume - baggagevolume;
		}else { return 0; }
	}
	public double GetexcessBaggageWeight() {
		if(ExcessWeight> baggageWeight) {
			return ExcessVolume - baggagevolume;
		}else { return 0; }
	}
	public void Setbaggagedetails(Double Volume, Double Weight) {
		baggagevolume = Volume;
		baggageWeight = Weight;
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
		checkedin = true;
	}
}
