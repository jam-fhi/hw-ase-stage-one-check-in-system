package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.LoggingSingleton;

public class QueueProducer implements Runnable {

	
	private BookingCollection allBookings;
	private BookingCollection passengerQueue;
	private LoggingSingleton log;
	
	public QueueProducer(BookingCollection allBookings, BookingCollection passengerQueue) {
		this.allBookings = allBookings;
		this.passengerQueue = passengerQueue;
		log = LoggingSingleton.getInstance();
	
	}
	
	@Override
	public void run() {
			try {
				Booking aPassenger = allBookings.getPassengerNotSecurityCheckIn();
			passengerQueue.addBooking(aPassenger);
			} catch (Exception e) {
			log.addLog("Failed to get passengers for queue" + e.getMessage());
			}
	}

}
