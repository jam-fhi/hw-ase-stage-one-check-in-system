package model;

import java.util.ArrayList;
import java.util.Iterator;

import CheckIn.BookingCollection;
import CheckIn.CheckInDeskCountSingleton;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.LoggingSingleton;

public class CheckInDeskCollection implements Runnable {

	private CheckInDeskCountSingleton deskCount;
	private FlightCollection allFlights;
	private BookingCollection allBookings;
	private LoggingSingleton log;
	private boolean inUse;
	private volatile ArrayList<Thread> checkInDesks = new ArrayList<Thread>();
	private int totalCheckInDesks = 4;
	
	public CheckInDeskCollection(FlightCollection allFlights, BookingCollection allBookings) {
		log = LoggingSingleton.getInstance();
		deskCount = CheckInDeskCountSingleton.getInstance();
		this.allFlights = allFlights;
		this.allBookings = allBookings;
	}

	@Override
	public synchronized void run() {
 		Iterator<Flight> allFlightsIt = allFlights.getFlightCollection().iterator();
 		allFlights.setInUse();
 		int delayFlights = 0;
 		log.addLog("Processing " + allFlights.getFlightCollection().size() + " flights for check in", "checkin");
		takeInUse();
 		while(allFlightsIt.hasNext()) {
			Flight aFlight = allFlightsIt.next();
			String status = aFlight.getFlightStatus();
			log.addLog("Processing flight " + aFlight.getFlightCode() + " which is " + status, "checkin");
			if(status.compareTo("delay") == 0) {
				delayFlights++;
			}
			if(status.compareTo("ready") == 0) {
				int freeThread = getFreeDesk();
				if(freeThread > -1) {
					aFlight.setHasCheckInDesk();
					addDesk(new Thread(new CheckInDesk(aFlight, allBookings, freeThread)));
					log.addLog("Opened Check In Desk for flight " + aFlight.getFlightCode() + " at " + freeThread, "checkin13");
				} else {
					log.addLog("Added delay to flight " + aFlight.getFlightCode(), "checkin1");
					aFlight.addDelay();
				}
			}
		}
 		freeInUse();
 		
		/*int freeThread = getFreeDesk();
		log.addLog("There are " + delayFlights + " delayed and desks is " + freeThread, "checkin1");
		if(delayFlights > 0 && freeThread > 0) {
			Iterator<Flight> delayFlightsIt = allFlights.getFlightCollection().iterator();
			while(delayFlightsIt.hasNext()) {
				Flight aFlight = delayFlightsIt.next();
				String status = aFlight.getFlightStatus();
				log.addLog("Processing flight " + aFlight.getFlightCode() + " which is " + status, "checkin1");
				if(freeThread > 0 && status.compareTo("delay") == 0) {
					aFlight.setHasCheckInDesk();
					aFlight.setDelayedToBoarding();
					addDesk(new Thread(new CheckInDesk(aFlight, allBookings, freeThread)), freeThread);
					log.addLog("Added desk " + freeThread, "checkin1");
					log.addLog("Opened Check In Desk for flight " + aFlight.getFlightCode(), "checkin1");
				}
				
			}
		}*/
		removeDepartedCheckInDesk();
		allFlights.setInUse();
	}

	private synchronized int getFreeDesk() {
		//takeInUse();

		int free = -1;
		if(checkInDesks.size() < totalCheckInDesks) {
			free = checkInDesks.size();
		}
	
		//freeInUse();
		return free;
	}
	
	private synchronized void removeDepartedCheckInDesk() {
		takeInUse();
		
		int count = 0;
		while(count < checkInDesks.size()) {
			Thread aDesk = checkInDesks.get(count);
			if(aDesk.getState() == Thread.State.TERMINATED) {
				checkInDesks.remove(count);
			}
			count++;
		}
	
		freeInUse();
	}

	private synchronized void addDesk(Thread newDesk) {
		//takeInUse();
		
		if(checkInDesks.size() < totalCheckInDesks) {
			checkInDesks.add(newDesk);
			checkInDesks.get(checkInDesks.size() - 1).start();
		}
		
		//freeInUse();
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
}
