package model;

import CheckIn.BookingCollection;
import CheckIn.LoggingSingleton;

public abstract class QueueProducer implements Runnable {

	
	protected BookingCollection allBookings;
	protected BookingCollection passengerQueue;
	protected LoggingSingleton log;
	
	public QueueProducer(BookingCollection allBookings) {
		this.allBookings = allBookings;
		log = LoggingSingleton.getInstance();
	
	}

}
