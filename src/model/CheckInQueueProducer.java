package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;

public class CheckInQueueProducer extends QueueProducer {
	public CheckInQueueProducer(BookingCollection allBookings, BookingCollection passengerQueue) {
		super(allBookings, passengerQueue);
	}

	@Override
	public void run() {
		try {
			Booking aPassenger = allBookings.getPassengerNotCheckedIn(false);
			passengerQueue.addBooking(aPassenger);
			allBookings.removeBooking(aPassenger.getBookingCode());
		} catch (Exception e) {
			log.addLog("Failed to get passenger for check in queue " + e.getMessage(), "log");
		}
	}
}
