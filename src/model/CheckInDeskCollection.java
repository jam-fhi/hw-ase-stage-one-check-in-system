package model;

import java.util.ArrayList;
import java.util.Iterator;

import checkInModel.BookingCollection;
import checkInModel.CheckInDesk;
import checkInModel.Flight;
import checkInModel.FlightCollection;
import checkInModel.LoggingSingleton;

public class CheckInDeskCollection implements Runnable {

	private volatile FlightCollection allFlights;
	private volatile BookingCollection allBookings;
	private LoggingSingleton log;
	private volatile boolean inUse;
	private volatile ArrayList<CheckInDesk> checkInDesks = new ArrayList<CheckInDesk>();
	private int totalCheckInDesks = 5;
	
	public CheckInDeskCollection(FlightCollection allFlights, BookingCollection allBookings) {
		log = LoggingSingleton.getInstance();
		this.allFlights = allFlights;
		this.allBookings = allBookings;
	}

	@Override
	public synchronized void run() {
 		Iterator<Flight> allFlightsIt = allFlights.getFlightCollection().iterator();
 		log.addLog("Processing " + allFlights.getFlightCollection().size() + " flights for check in", "checkin");
		takeInUse();
 		while(allFlightsIt.hasNext()) {
			Flight aFlight = allFlightsIt.next();
			String status = aFlight.getFlightStatus();
			log.addLog("Processing flight " + aFlight.getFlightCode() + " which is " + status, "checkin");
			if(status.compareTo("ready") == 0) {
				int freeThread = getFreeDesk();
				if(freeThread > -1) {
					aFlight.setHasCheckInDesk();
					addDesk(new CheckInDesk(aFlight, allBookings, freeThread));
					log.addLog("Opened Check In Desk for flight " + aFlight.getFlightCode() + " at " + freeThread, "checkin13");
				} else {
					log.addLog("Added delay to flight " + aFlight.getFlightCode(), "checkin1");
					aFlight.addDelay();
				}
			}
		}
 		freeInUse();
		removeDepartedCheckInDesk();
	}

	private synchronized int getFreeDesk() {
		int free = -1;
		if(checkInDesks.size() < totalCheckInDesks) {
			free = checkInDesks.size();
		}
		return free;
	}
	
	private synchronized void removeDepartedCheckInDesk() {
		takeInUse();
		
		int count = 0;
		while(count < checkInDesks.size()) {
			CheckInDesk aDesk = checkInDesks.get(count);
			if(aDesk.getThreadState() == Thread.State.TERMINATED) {
				checkInDesks.remove(count);
			}
			count++;
		}
	
		freeInUse();
	}

	private synchronized void addDesk(CheckInDesk newDesk) {
		if(checkInDesks.size() < totalCheckInDesks) {
			checkInDesks.add(newDesk);
		}
	}
	
	private synchronized void takeInUse() {
		while (inUse) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inUse = true;
	}
	
	public synchronized void freeInUse() {
		inUse = false;
		notifyAll();
	}
	
	public ArrayList<CheckInDesk> getCheckInDesks() {
		return checkInDesks;
	}
}
