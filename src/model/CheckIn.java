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

	private BookingCollection bookingCollection = new BookingCollection();
	private BookingCollection securityQueue = new BookingCollection();
	private BookingCollection checkInQueue = new BookingCollection();
	private FlightCollection flightCollection = new FlightCollection();
	private boolean simulationRunning = false;
	private String simulationDateTime = "";
	private SimulationTimeSingleton simTime = null;
	private LoggingSingleton log = null;

	private ArrayList<CheckInDesk> checkInDesks = new ArrayList<CheckInDesk>();
	//private ThreadNewPassenger aQueue;
	
	public CheckIn() throws CheckInIOException, BookingException {
		simTime = SimulationTimeSingleton.getInstance();
		log = LoggingSingleton.getInstance();
		/*aQueue = aPassengerQueue;
		
		this.bookingCollection = new BookingCollection(bookingfile);
		this.flightCollection = new FlightCollection(flightfile);
		
		CheckInDesk aDesk1 = new CheckInDesk(aQueue, this.bookingCollection, this);
		CheckInDesk aDesk2 = new CheckInDesk(aQueue, this.bookingCollection, this);
		CheckInDesk aDesk3 = new CheckInDesk(aQueue, this.bookingCollection, this);
		CheckInDesk aDesk4 = new CheckInDesk(aQueue, this.bookingCollection, this);*/
		
		/*Thread producerThread = new Thread(new QueueProducer(aQueue, bookingCollection, flightCollection, this));
		producerThread.start();*/
		
		// create a consumer thread and start it
		/*Thread consumerThread1 = new Thread(aDesk1);
		consumerThread1.start();
		
		Thread consumerThread2 = new Thread(aDesk2);
		consumerThread2.start();
		
		Thread consumerThread3 = new Thread(aDesk3);
		consumerThread3.start();
		
		Thread consumerThread4 = new Thread(aDesk4);
		consumerThread4.start();
		
		checkInDesks.add(aDesk4);
		checkInDesks.add(aDesk3);
		checkInDesks.add(aDesk2);
		checkInDesks.add(aDesk1);*/
	}

	public boolean isCheckInClosed(Flight aFlight) {

		Date flightCheckInClosed = aFlight.checkInClosingTime();
		//simulationCurrentDateTime = FakeTime.getCurrentTime(simulationStartDateTime, simulationCurrentDateTime, Integer.parseInt(simulationTime));

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

	public ArrayList<CheckInDesk> getCheckInDesk() {
		return checkInDesks;
	}
	
	/*public ArrayList<Booking> getPassengerQueue() {
		return aQueue.getList();
	}*/

 	// OBSERVER PATTERN
	// SUBJECT must be able to register, remove and notify observers
	// list to hold any observers
	/*private List<observer.Observer> registeredObservers = new LinkedList<observer.Observer>();

	public void notifyObservers() {
		for (observer.Observer obs : registeredObservers) {
			obs.update(this);
		}
	}*/

	/*@Override
	public void registerObserver(observer.Observer obs) {
		registeredObservers.add(obs);
		
	}

	@Override
	public void removeObserver(observer.Observer obs) {
		registeredObservers.remove(obs);
	}*/
	
	public String getSimulationTime() {
		return String.valueOf(simTime.getSpeed());
	}

	public void setSimulationTime(String simulationTime) {
		simTime.setSpeed(Integer.parseInt(simulationTime.substring(0, simulationTime.length() - 1)));
		log.addLog("Simulation speed is " + simulationTime);
		this.updateView();
	}
	
	public CheckInDesk getCheckInDesk(int index) {
		return checkInDesks.get(index);
		
	}

	public synchronized void toggleSimulationRunning() {
		this.simulationRunning = !this.simulationRunning;
		log.addLog("Simulation has " + (simulationRunning ? "started" : "ended"));
		this.updateView();
	}

	public boolean getSimulationRunning() {
		return simulationRunning;
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
			bookingCollection = new BookingCollection();
			this.updateView();
		} catch (CheckInIOException | BookingException e) {
			log.addLog("Failed to reset flight collection");
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
			this.setSimulationDateTime(simTime.getCurrentTime().toGMTString());
			new Thread(flightCollection).run();
			new Thread(new RandomBookingGenerator(flightCollection, bookingCollection)).run();
			new Thread(new SecurityQueueProducer(bookingCollection, securityQueue)).run();
			new Thread(new SecurityQueueProducer(bookingCollection, securityQueue)).run();
			new Thread(new CheckInQueueProducer(securityQueue, checkInQueue)).run();
			log.addLog("There are " + checkInQueue.getBookingCollection().size() + " passengers in the check in queue");
			try {
				Thread.sleep(FakeTime.getSpeedDelay(simTime.getSpeed()));
			} catch (InterruptedException e) {
				log.addLog("Thread sleep interrupted.");
			}
			simTime.setCurrentTime(FakeTime.getCurrentTime());
		}
	}
	
	public ArrayList<Booking> getSecurityQueue() {
		return securityQueue.getAllBookings();
		
	}
}
