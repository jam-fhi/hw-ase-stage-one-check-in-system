package model;

import java.util.Iterator;

import CheckIn.BookingCollection;
import CheckIn.CheckInDeskCountSingleton;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.LoggingSingleton;

public class CheckInDeskCollection implements Runnable {

	private CheckInDeskCountSingleton deskCount;
	private FlightCollection allFlights;
	private BookingCollection economyQueue;
	private BookingCollection businessQueue;
	private LoggingSingleton log;
	
	public CheckInDeskCollection(FlightCollection allFlights, BookingCollection economyQueue, BookingCollection businessQueue) {
		log = LoggingSingleton.getInstance();
		deskCount = CheckInDeskCountSingleton.getInstance();
		this.allFlights = allFlights;
		this.economyQueue = economyQueue;
		this.businessQueue = businessQueue;
	}

	@Override
	public synchronized void run() {
 		Iterator<Flight> allFlightsIt = allFlights.getFlightCollection().iterator();
 		allFlights.setInUse();
 		log.addLog("Processing " + allFlights.getFlightCollection().size() + " flights for check in", "checkin");
		while(allFlightsIt.hasNext()) {
			Flight aFlight = allFlightsIt.next();
			String status = aFlight.getFlightStatus();
			log.addLog("Processing flight " + aFlight.getFlightCode() + " which is " + status, "checkin");
			if(status.compareTo("ready") == 0) {
				if(deskCount.canAddNewDesk()) {
					deskCount.incActiveDesks();
					aFlight.setHasCheckInDesk();
					new Thread(new CheckInDesk(aFlight, economyQueue, businessQueue, deskCount.getDeskCount())).start();
					log.addLog("Opened Check In Desk for flight " + aFlight.getFlightCode(), "checkin");
				} else {
					log.addLog("Added delay to flight " + aFlight.getFlightCode(), "checkin");
					aFlight.addDelay();
				}
			}
		}
		allFlights.setInUse();
	}
}
