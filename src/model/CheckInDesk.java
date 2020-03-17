package model;

public class CheckInDesk {

	private String flightCode;
	private String bookingCode;
	private String passengerName;
	private String baggageWeight;
	private String excessFee;
	
	public CheckInDesk(String flightCode, String bookingCode, String passengerName, String baggageWeight, String excessFee) {
		this.flightCode = flightCode;
		this.bookingCode = bookingCode;
		this.passengerName = passengerName;
		this.baggageWeight = baggageWeight;
		this.excessFee = excessFee;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public String getBaggageWeight() {
		return baggageWeight;
	}

	public String getExcessFee() {
		return excessFee;
	}
}
