package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;

public class CheckInQueueProducer extends QueueProducer {
	public CheckInQueueProducer(BookingCollection allBookings) {
		super(allBookings);
	}

	@Override
	public void run() {
		try {
			allBookings.getPassengerNotCheckedIn(false);
		} catch (Exception e) {
			log.addLog("Failed to get passenger for check in queue " + e.getMessage(), "log");
		}
	}
}
