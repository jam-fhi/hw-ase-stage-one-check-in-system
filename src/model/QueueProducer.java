package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightException;
import CheckIn.ThreadNewPassenger;

public class QueueProducer implements Runnable {

	
	private BookingCollection allBookings;
	private BookingCollection securityQueue;
	
	public QueueProducer(BookingCollection allBookings, BookingCollection securityQueue) {
		this.allBookings = allBookings;
		this.securityQueue = securityQueue;
	
	}
	
	@Override
	public void run() {
			try {
				Booking aPassenger = allBookings.getPassengerNotSecurityCheckIn();
				securityQueue.addBooking(aPassenger);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
