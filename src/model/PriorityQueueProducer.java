package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;

public class PriorityQueueProducer extends QueueProducer {
	public PriorityQueueProducer(BookingCollection allBookings, BookingCollection passengerQueue) {
		super(allBookings, passengerQueue);
	}

	@Override
	public void run() {
		try {
			Booking aPassenger = allBookings.getPassengerNotCheckedIn(true);
			passengerQueue.addBooking(aPassenger);
			allBookings.removeBooking(aPassenger.getBookingCode());
		} catch (Exception e) {
			log.addLog("Failed to get passenger for priority queue " + e.getMessage(), "log");
		}
	}
}
