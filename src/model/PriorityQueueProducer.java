package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;

public class PriorityQueueProducer extends QueueProducer {
	public PriorityQueueProducer(BookingCollection allBookings) {
		super(allBookings);
	}

	@Override
	public void run() {
		try {
			allBookings.getPassengerNotCheckedIn(true);
		} catch (Exception e) {
			log.addLog("Failed to get passenger for priority queue " + e.getMessage(), "log");
		}
	}
}
