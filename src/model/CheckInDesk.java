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
	private boolean checkInStatus = false;
	private String excessFee;
	private boolean closestatus = false;
	private ThreadNewPassenger so;
	private BookingCollection allBookings;
	private CheckIn model;

	public CheckInDesk(ThreadNewPassenger so, BookingCollection allBookings, CheckIn model) {
		this.so = so;
		this.allBookings = allBookings;
		this.model = model;
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
	
	public boolean getCheckInStatus() {
		return checkInStatus;
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
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(so.hasPassengerInQueue()) {
			try {
				Booking aPassenger = so.getPassengerForCheckIn();
				Booking aBooking = allBookings.getBooking(aPassenger.getBookingCode(), aPassenger.getPassenger().getLastName());
				this.flightCode = aBooking.getFlightCode();
				this.bookingCode = aBooking.getBookingCode();
				this.passengerName = aPassenger.getPassenger().getFirstName() + " " + aPassenger.getPassenger().getLastName();
				this.baggageWeight = aPassenger.getPassenger().getBaggage().getWeight();
				this.checkInStatus = aPassenger.getPassenger().isCheckIn();
				model.notifyObservers();
				Thread.sleep(3000);
				this.model.doCheckIn(this.bookingCode, aPassenger.getPassenger(), aPassenger.getPassenger().getBaggage());
				this.excessFee = String.valueOf(aPassenger.getPassenger().getBaggage().getExcessCharge()); // aPassenger.getPassenger().getBaggage().getExcessCharge();
				this.checkInStatus = aPassenger.getPassenger().isCheckIn();
				model.notifyObservers();
				Thread.sleep(3000);
				// System.out.println("GOT: " + this.passengerName);
			} catch (FlightException | BookingException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
