package model;

import java.util.ArrayList;
import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
import java.util.Observable;

import CheckIn.Bag;
import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightException;
import CheckIn.LoggingSingleton;
import CheckIn.Passenger;
import CheckIn.RandomBookingGenerator;
import CheckIn.SimulationTimeSingleton;

/**
 * 
 * CheckIn 
 * the model class 
 * @author amymcfarland
 */
@SuppressWarnings("deprecation")
public class CheckIn extends Observable implements Runnable {

	private BookingCollection bookingCollection;
	private FlightCollection flightCollection;
	private String simulationDateTime = "";
	private SimulationTimeSingleton simTime = null;
	private LoggingSingleton log = null;
	private CheckInDeskCollection checkInDeskCollection;
	private Thread checkInDesksThread;
	
	public CheckIn() throws CheckInIOException, BookingException {
		log = LoggingSingleton.getInstance();
		resetCheckInSimulation();
		simTime = SimulationTimeSingleton.getInstance();
	}

	public boolean isCheckInClosed(Flight aFlight) {

		Date flightCheckInClosed = aFlight.checkInClosingTime();

		if (simTime.getCurrentSimTime().getTime() > flightCheckInClosed.getTime()) {
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
	
	public String getSimulationTime() {
		return String.valueOf(simTime.getSpeed());
	}

	public void setSimulationTime(String simulationTime) {
		simTime.setSpeed(Integer.parseInt(simulationTime.substring(0, simulationTime.length() - 1)));
		log.addLog("Simulation speed is " + simulationTime, "log");
		this.updateView();
	}

	public synchronized void toggleSimulationRunning() {
		simTime.toggleSimRunning();
		log.addLog("Simulation has " + (simTime.isSimRunning() ? "started" : "ended"), "log");
		this.updateView();
	}

	public boolean getSimulationRunning() {
		return simTime.isSimRunning();
	}

	public String getSimulationDateTime() {
		return simulationDateTime;
	}

	private void setSimulationDateTime(String simDateTime) {
		simulationDateTime = simDateTime;
		this.updateView();
	}
	
	private void resetCheckInSimulation() {
		try {
			flightCollection = new FlightCollection();
			bookingCollection = new BookingCollection();
			checkInDeskCollection = new CheckInDeskCollection(flightCollection, bookingCollection);
			this.updateView();
		} catch (CheckInIOException e) {
			log.addLog("Failed to reset flight collection", "log");
		}
	}

	private void setSimulationStartDateTime() {
		simTime.setStartSimulation();
		this.updateView();
	}

	private void updateView() {
		//update view display
		setChanged();
		notifyObservers();
    	clearChanged();
	}

	@Override
	public void run() {
		this.toggleSimulationRunning();
		this.setSimulationStartDateTime();
		this.resetCheckInSimulation();
		RandomBookingGenerator bookingGen = new RandomBookingGenerator(flightCollection, bookingCollection);
		while(this.getSimulationRunning()) {
			log.addLog("Main control loop", "checkin");
			this.setSimulationDateTime(simTime.getCurrentSimTime().toGMTString());
			flightCollection.generateFlights();
			bookingGen.generateBookings();
			try {
				bookingCollection.progressBookingToSecurity();
				bookingCollection.progressBookingPassedSecurity(false);
				bookingCollection.progressBookingPassedSecurity(true);
			} catch (Exception e1) {
				log.addLog(e1.getMessage(), "error");
			}
			new Thread(checkInDeskCollection).start();
			
			try {
				Thread.sleep(SimulationTimeSingleton.getSpeedDelay(simTime.getSpeed()));
			} catch (InterruptedException e) {
				log.addLog("Thread sleep interrupted.", "log");
			}
			simTime.setCurrentSimTime(SimulationTimeSingleton.getCurrentTime());
		}
	}
}
