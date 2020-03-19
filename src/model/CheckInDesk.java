package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.FlightException;
import CheckIn.ThreadNewPassenger;

public class CheckInDesk implements Runnable {

	private String flightCode;
	private String bookingCode;
	private String passengerName;
	private double baggageWeight;
	private String excessFee;
	private boolean closestatus = false;
	private ThreadNewPassenger so;
	private BookingCollection allBookings;
	
	public CheckInDesk(String flightCode, String bookingCode, String passengerName, double baggageWeight, String excessFee) {
		this.flightCode = flightCode;
		this.bookingCode = bookingCode;
		this.passengerName = passengerName;
		this.baggageWeight = baggageWeight;
		this.excessFee = excessFee;
	}

	public CheckInDesk(ThreadNewPassenger so, BookingCollection allBookings) {
		this.so = so;
		this.allBookings = allBookings;
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

	public double getBaggageWeight() {
		return baggageWeight;
	}

	public String getExcessFee() {
		return excessFee;
	}
	
	public boolean isClosestatus() {
		return closestatus;
	}
	
	public void toggleclosestatus() {
		if(closestatus == true) {
			closestatus = false;
		}
		else {
			closestatus = true;
		}
		
	}

	@Override
	public void run() {
		try {
			Booking aPassenger = so.getPassengerForCheckIn();
			Booking aBooking = allBookings.getBooking(aPassenger.getBookingCode(), aPassenger.getPassenger().getLastName());
			this.flightCode = aBooking.getFlightCode();
			this.bookingCode = aBooking.getBookingCode();
			this.passengerName = aPassenger.getPassenger().getFirstName() + " " + aPassenger.getPassenger().getLastName();
			this.baggageWeight = aPassenger.getPassenger().getBaggage().getWeight();
			this.excessFee = "12"; // aPassenger.getPassenger().getBaggage().getExcessCharge();
			System.out.println("GOT: " + this.passengerName);
		} catch (FlightException | BookingException e) {
			e.printStackTrace();
		}
	}
}
