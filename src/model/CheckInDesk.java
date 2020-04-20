package model;

import java.util.Iterator;

import checkInModel.Bag;
import checkInModel.Booking;
import checkInModel.BookingCollection;
import checkInModel.Flight;
import checkInModel.LoggingSingleton;
import checkInModel.RandomBagGenerator;
import checkInModel.SimulationTimeSingleton;

public class CheckInDesk implements Runnable {
	private LoggingSingleton log;
	private SimulationTimeSingleton simTime = null;
	private volatile BookingCollection allBookings;
	private Flight boardingFlight;
	private int deskNumber;
	private Thread myDesk;
	private String currentBookingCode;
	private String currentPassengerName;
	private String currentBagWeight = "0.0";
	private String currentExcessFee = "0.00";
	private boolean closedStatus = false;
	
	public CheckInDesk(Flight boardingFlight, BookingCollection allBookings, int deskNumber) {
		log = LoggingSingleton.getInstance();
		simTime = SimulationTimeSingleton.getInstance();
		this.boardingFlight = boardingFlight;
		this.allBookings = allBookings;
		this.deskNumber = deskNumber;
		myDesk = new Thread(this);
		myDesk.start();
	}

	public String getFlightCode() {
		return boardingFlight.getFlightCode();
	}
	
	public String getFlightDestination() {
		return boardingFlight.getDestinationAirport();
	}

	public Thread.State getThreadState() {
		return myDesk.getState();
	}

	@Override
	public void run() {
		log.addLog("Check In Desk " + deskNumber + " for flight " + boardingFlight.getFlightCode() + " has opened.", "checkin");
		while(boardingFlight.getFlightStatus().compareTo("boarding") == 0 && simTime.isSimRunning()) {
			
			log.addLog("Processing passengers on Desk " + deskNumber, "checkin");
			Booking nextPassenger = allBookings.getNextBooking(boardingFlight.getFlightCode(), "Business");
			if(nextPassenger == null) {
				nextPassenger = allBookings.getNextBooking(boardingFlight.getFlightCode(), "Economy");
			}
			
			if(nextPassenger != null) {
				currentBookingCode = nextPassenger.getBookingCode();
				currentPassengerName = nextPassenger.getPassenger().getFirstName() + " " + nextPassenger.getPassenger().getLastName();
				double weight = boardingFlight.getAllowedBaggageWeightPerPassenger() < 1 ? 1 : boardingFlight.getAllowedBaggageWeightPerPassenger();
				int volume = boardingFlight.getAllowedBaggageVolumePerPassenger() < 1 ? 1 : (int)boardingFlight.getAllowedBaggageVolumePerPassenger();
				Bag baggage = RandomBagGenerator.getRandomBag(weight, volume);
				currentBagWeight = String.valueOf(weight);
				nextPassenger.getPassenger().addBaggage(baggage);
				nextPassenger.getPassenger().setCheckIn();
				double allowedBaggageWeight = boardingFlight.getAllowedBaggageWeightPerPassenger();
				double excessCharge = boardingFlight.getExcessCharge();
				nextPassenger.getPassenger().getBaggage().setExcessCharge(allowedBaggageWeight, excessCharge);
				double charge = nextPassenger.getPassenger().getBaggage().getExcessCharge();
				currentExcessFee = String.valueOf(charge);
				log.addLog("Processing passenger " + nextPassenger.getPassenger().getFirstName() + " " + nextPassenger.getPassenger().getLastName() + " on booking " + nextPassenger.getBookingCode() + " who has a excess charge of £" + charge, "checkin");
			}

			try {
				Thread.sleep(SimulationTimeSingleton.getSpeedDelay(simTime.getSpeed()));
			} catch (InterruptedException e) {
				log.addLog("Thread sleep interrupted.", "log");
			}
		}
		log.addLog("Check In Desk " + deskNumber + " for flight " + boardingFlight.getFlightCode() + " has closed.", "checkin13");
	}

	public String getBookingCode() {
		return currentBookingCode;
	}

	public String getPassengerName() {
		return currentPassengerName;
	}

	public String getBaggageWeight() {
		return currentBagWeight;
	}

	public String getExcessFee() {
		return currentExcessFee;
	}
	
	public boolean isClosed() {
		return closedStatus;
	}
	
	public int getCheckInDeskNumber() {
		return deskNumber;
	}
	
	/*public boolean getCheckInStatus() {
		return checkInStatus;
	}*/
	
	/*public void toggleclosestatus() {
		if(closestatus == true) {
			closestatus = false;
		}
		else {
			closestatus = true;
		}
		
	}*/
}
