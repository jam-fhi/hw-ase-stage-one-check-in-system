package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.Passenger;
import CheckIn.fakeTime;
/**
 * 
 * CheckIn 
 * the model class 
 * @author amymcfarland
 */
public class CheckIn {

	private BookingCollection bookingCollection;
	private FlightCollection flightCollection;

	public boolean isCheckInClosed(Flight aFlight) {

		Date flightCheckInClosed = aFlight.checkInClosingTime();
		Date currentTime = fakeTime.getCurrentTime();

		if (currentTime.getTime() > flightCheckInClosed.getTime()) {

			return true;
		}

		return false;
	}

	public void doCheckIn(String bookingCode, Passenger passenger) throws Exception {

		try {

			Booking aBooking = bookingCollection.getBooking(bookingCode, passenger.getLastName());
			Flight aFlight = flightCollection.findFlight(aBooking.getFlightCode());
			if (isCheckInClosed(aFlight) == false) {

				aBooking.getPassenger().setCheckIn();
				double allowedBaggageWeight = aFlight.getAllowedBaggageWeightPerPassenger();
				double excessCharge = aFlight.getExcessCharge();
				aBooking.getPassenger().getBaggage().setExcessCharge(allowedBaggageWeight, excessCharge);
				double charge = aBooking.getPassenger().getBaggage().getExcessCharge();
				System.out.println(charge);
			}

			else {
				throw new Exception("The check in desk is closed.");
			}

		}

		catch (BookingException e) {

			throw e;
		}

	}

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
		for (Observer obs : registeredObservers) {
			obs.update();
		}
	}
}