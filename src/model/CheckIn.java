package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

	public CheckIn(String flightfile, String bookingfile) throws CheckInIOException, BookingException {
		this.bookingCollection = new BookingCollection(bookingfile);
		this.flightCollection = new FlightCollection(flightfile);
		
		CheckInDesk aDesk1 = new CheckInDesk("BA123", "BA123-123", "Passenger Name1", "123KG", "£12");
		CheckInDesk aDesk2 = new CheckInDesk("BA124", "BA123-124", "Passenger Name2", "124KG", "£13");
		CheckInDesk aDesk3 = new CheckInDesk("BA125", "BA123-125", "Passenger Name3", "125KG", "£14");
		CheckInDesk aDesk4 = new CheckInDesk("BA126", "BA123-126", "Passenger Name4", "126KG", "£15");
		checkInDesks.add(aDesk4);
		checkInDesks.add(aDesk3);
		checkInDesks.add(aDesk2);
		checkInDesks.add(aDesk1);
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
	
	public int getSimulationTime() {
		return SimulationTime;
	}

	public void setSimulationTime(int simulationTime) {
		SimulationTime = simulationTime;
	}
	
	public CheckInDesk getCheckInDesk(int index) {
		return checkInDesks.get(index);
		
	}
}
