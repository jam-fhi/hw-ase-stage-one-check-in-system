package model;

import java.util.LinkedList;
import java.util.List;

import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.FlightCollection;
import observer.Observer;
/**
 * 
 * CheckIn 
 * the model class 
 * @author amymcfarland
 */
public class CheckIn {
	
	private BookingCollection bookingCollection;
	private FlightCollection flightCollection;
	
	public CheckIn(String flightfile, String bookingfile) throws CheckInIOException, BookingException {
		this.bookingCollection = new BookingCollection(bookingfile);
		this.flightCollection = new FlightCollection(flightfile);
	}

	public BookingCollection getBookingCollection() {
		return bookingCollection;
	}
	
	public FlightCollection getFlightCollection()  {
		return flightCollection;
		
	}
	
	// OBSERVER PATTERN
		// SUBJECT must be able to register, remove and notify observers
		// list to hold any observers
		private List<Observer> registeredObservers = new LinkedList<Observer>();

		// methods to register, remove and notify observers
		public void registerObserver(Observer obs) {
			registeredObservers.add(obs);
		}

		public void removeObserver(Observer obs) {
			registeredObservers.remove(obs);
		}

		public void notifyObservers() {
			for (Observer obs : registeredObservers)
				obs.update();
		}
}
