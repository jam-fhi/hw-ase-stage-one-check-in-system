package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;

public class SecurityQueueProducer extends QueueProducer {
	public SecurityQueueProducer(BookingCollection allBookings, BookingCollection passengerQueue) {
		super(allBookings, passengerQueue);
	}

	@Override
	public void run() {
		try {
			Booking aPassenger = allBookings.getPassengerNotSecurityCheckIn();
			passengerQueue.addBooking(aPassenger);
		} catch (Exception e) {
			log.addLog("Failed to get passenger for security queue " + e.getMessage());
		}
	}
}
