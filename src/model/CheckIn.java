package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import CheckIn.Bag;
import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightException;
import CheckIn.Passenger;
import CheckIn.ThreadNewPassenger;
import CheckIn.fakeTime;
import observer.Subject;
/**
 * 
 * CheckIn 
 * the model class 
 * @author amymcfarland
 */
public class CheckIn implements Subject {

	private BookingCollection bookingCollection;
	private FlightCollection flightCollection;
	private int SimulationTime = 1;
	private ArrayList<CheckInDesk> checkInDesks = new ArrayList<CheckInDesk>();
	private ThreadNewPassenger aQueue;
	
	public CheckIn(String flightfile, String bookingfile, ThreadNewPassenger aPassengerQueue) throws CheckInIOException, BookingException {
		
		aQueue = aPassengerQueue;
		
		this.bookingCollection = new BookingCollection(bookingfile);
		this.flightCollection = new FlightCollection(flightfile);
		
		CheckInDesk aDesk1 = new CheckInDesk(aQueue, this.bookingCollection);
		CheckInDesk aDesk2 = new CheckInDesk(aQueue, this.bookingCollection);
		CheckInDesk aDesk3 = new CheckInDesk(aQueue, this.bookingCollection);
		CheckInDesk aDesk4 = new CheckInDesk(aQueue, this.bookingCollection);
		
		Thread producerThread = new Thread(new QueueProducer(aQueue, bookingCollection, flightCollection));
		producerThread.start();
		
		// create a consumer thread and start it
		Thread consumerThread1 = new Thread(aDesk1);
		consumerThread1.start();
		
		/*Thread consumerThread2 = new Thread(aDesk2);
		consumerThread1.start();
		
		Thread consumerThread3 = new Thread(aDesk3);
		consumerThread1.start();
		
		Thread consumerThread4 = new Thread(aDesk4);
		consumerThread1.start();*/
		
		checkInDesks.add(aDesk4);
		checkInDesks.add(aDesk3);
		checkInDesks.add(aDesk2);
		checkInDesks.add(aDesk1);
	}

	public boolean isCheckInClosed(Flight aFlight) {

		Date flightCheckInClosed = aFlight.checkInClosingTime();
		Date currentTime = fakeTime.getCurrentTime();

		if (currentTime.getTime() > flightCheckInClosed.getTime()) {

			return true;
		}

		return false;
	}

	public void doCheckIn(String bookingCode, Passenger passenger, Bag baggage) throws FlightException, BookingException {
		Booking aBooking = bookingCollection.getBooking(bookingCode, passenger.getLastName());
		Flight aFlight = flightCollection.findFlight(aBooking.getFlightCode());
		if (isCheckInClosed(aFlight) == false) {
			bookingCollection.getBooking(bookingCode, passenger.getLastName()).getPassenger().setCheckIn();
			bookingCollection.getBooking(bookingCode,  passenger.getLastName()).getPassenger().addBaggage(baggage);
			double allowedBaggageWeight = aFlight.getAllowedBaggageWeightPerPassenger();
			double excessCharge = aFlight.getExcessCharge();
			bookingCollection.getBooking(bookingCode, passenger.getLastName()).getPassenger().getBaggage().setExcessCharge(allowedBaggageWeight, excessCharge);
		} else {
			throw new BookingException("The check in desk is closed.");
		}
	}

	public BookingCollection getBookingCollection() {
		return bookingCollection;
	}

	public FlightCollection getFlightCollection()  {
		return flightCollection;

	}

	public ArrayList<CheckInDesk> getCheckInDesk() {
		return checkInDesks;
	}
	
	public ArrayList<Booking> getPassengerQueue() {
		return aQueue.getList();
	}

 	// OBSERVER PATTERN
	// SUBJECT must be able to register, remove and notify observers
	// list to hold any observers
	private List<observer.Observer> registeredObservers = new LinkedList<observer.Observer>();

	public void notifyObservers() {
		for (observer.Observer obs : registeredObservers) {
			obs.update(this);
		}
	}

	@Override
	public void registerObserver(observer.Observer obs) {
		registeredObservers.add(obs);
		
	}

	@Override
	public void removeObserver(observer.Observer obs) {
		registeredObservers.remove(obs);
	}
}
