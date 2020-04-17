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
//import CheckIn.ThreadNewPassenger;
import CheckIn.FakeTime;
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
	private BookingCollection securityQueue;
	private BookingCollection economyQueue;
	private BookingCollection businessQueue;
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

		if (simTime.getCurrentTime().getTime() > flightCheckInClosed.getTime()) {
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
		// log.addLog("Current simulation time is: " + simDateTime);
		this.updateView();
	}
	
	private void resetCheckInSimulation() {
		try {
			flightCollection = new FlightCollection();
			bookingCollection = new BookingCollection("All bookings");
			securityQueue = new BookingCollection("Security queue");
			economyQueue = new BookingCollection("Economy queue");
			businessQueue = new BookingCollection("Business queue");
			checkInDeskCollection = new CheckInDeskCollection(flightCollection, economyQueue, businessQueue);
			this.updateView();
		} catch (CheckInIOException | BookingException e) {
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
		while(this.getSimulationRunning()) {
			log.addLog("Main control loop", "checkin");
			this.setSimulationDateTime(simTime.getCurrentTime().toGMTString());
			new Thread(flightCollection).start();
			new Thread(new RandomBookingGenerator(flightCollection, bookingCollection)).start();
			new Thread(new SecurityQueueProducer(bookingCollection, securityQueue)).start();
			new Thread(new CheckInQueueProducer(securityQueue, economyQueue)).start();
			new Thread(new PriorityQueueProducer(securityQueue, businessQueue)).start();
			new Thread(checkInDeskCollection).start();
			
			try {
				Thread.sleep(FakeTime.getSpeedDelay(simTime.getSpeed()));
			} catch (InterruptedException e) {
				log.addLog("Thread sleep interrupted.", "log");
			}
			simTime.setCurrentTime(FakeTime.getCurrentTime());
		}
	}
	
	public ArrayList<Booking> getSecurityQueue() {
		try {
			return securityQueue.getAllBookings();
		} catch(NullPointerException e) {
			return new ArrayList<Booking>();
		}
	}

	public ArrayList<Booking> getCheckInQueue() {
		try {
			return economyQueue.getAllBookings();
		} catch(NullPointerException e) {
			return new ArrayList<Booking>();
		}
	}
	
	public ArrayList<Booking> getPriorityQueue() {
		try {
			return businessQueue.getAllBookings();
		} catch(NullPointerException e) {
			return new ArrayList<Booking>();
		}
	}
}
