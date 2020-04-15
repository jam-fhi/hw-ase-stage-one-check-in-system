package model;

import CheckIn.BookingCollection;
import CheckIn.LoggingSingleton;

public abstract class QueueProducer implements Runnable {

	
	protected BookingCollection allBookings;
	protected BookingCollection passengerQueue;
	protected LoggingSingleton log;
	
	public QueueProducer(BookingCollection allBookings, BookingCollection passengerQueue) {
		this.allBookings = allBookings;
		this.passengerQueue = passengerQueue;
		log = LoggingSingleton.getInstance();
	
	}

}
