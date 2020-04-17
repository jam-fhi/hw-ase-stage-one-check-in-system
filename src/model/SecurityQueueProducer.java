package model;

import java.util.concurrent.ThreadLocalRandom;

import CheckIn.Booking;
import CheckIn.BookingCollection;

public class SecurityQueueProducer extends QueueProducer {
	public SecurityQueueProducer(BookingCollection allBookings, BookingCollection passengerQueue) {
		super(allBookings, passengerQueue);
	}

	@Override
	public void run() {
		try {
			int addPassengerCount = ThreadLocalRandom.current().nextInt(0, 3);
			int count = 0;
			while (count < addPassengerCount) {
				Booking aPassenger = allBookings.getPassengerNotSecurityCheckIn();
				passengerQueue.addBooking(aPassenger);
				count++;
			}

		} catch (Exception e) {
			log.addLog("Failed to get passenger for security queue " + e.getMessage(), "log");
		}
	}
}
