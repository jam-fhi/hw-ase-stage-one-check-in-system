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
	private Thread deskOne = new Thread();
	private Thread deskTwo = new Thread();
	private Thread deskThree = new Thread();
	private Thread deskFour = new Thread();
	
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
		while(allFlightsIt.hasNext()) {
			Flight aFlight = allFlightsIt.next();
			String status = aFlight.getFlightStatus();
			log.addLog("Processing flight " + aFlight.getFlightCode() + " which is " + status, "checkin");
			if(status.compareTo("delay") == 0) {
				delayFlights++;
			}
			if(status.compareTo("ready") == 0) {
				int freeThread = getFreeDesk();
				if(freeThread > 0) {
					aFlight.setHasCheckInDesk();
					addDesk(new Thread(new CheckInDesk(aFlight, allBookings, freeThread)), freeThread);
					log.addLog("Opened Check In Desk for flight " + aFlight.getFlightCode(), "checkin1");
				} else {
					log.addLog("Added delay to flight " + aFlight.getFlightCode(), "checkin1");
					aFlight.addDelay();
				}
			}
		}
	
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
		allFlights.setInUse();
	}

	private synchronized int getFreeDesk() {
		takeInUse();

		int free = -1;
		
		if(deskOne.isAlive() == false) {
			free = 1;
		}
	
		if(deskTwo.isAlive() == false) {
			free = 2;
		}
		
		if(deskThree.isAlive() == false) {
			free = 3;
		}
		
		if(deskFour.isAlive() == false) {
			free = 4;
		}
	
		freeInUse();
		return free;
	}

	private synchronized void addDesk(Thread newDesk, int slot) {
		takeInUse();
		
		if(slot == 1) {
			deskOne = newDesk;
			deskOne.start();
		}
		
		if(slot == 2) {
			deskTwo = newDesk;
			deskTwo.start();
		}
		
		if(slot == 3) {
			deskThree = newDesk;
			deskThree.start();
		}
		
		if(slot == 4) {
			deskFour = newDesk;
			deskFour.start();
		}
		
		freeInUse();
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
