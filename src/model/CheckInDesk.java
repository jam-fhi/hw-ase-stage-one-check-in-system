package model;

import java.util.Iterator;

import CheckIn.Bag;
import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.CheckInDeskCountSingleton;
import CheckIn.FakeTime;
//import CheckIn.Booking;
//import CheckIn.BookingCollection;
//import CheckIn.BookingException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.LoggingSingleton;
import CheckIn.RandomBagGenerator;
//import CheckIn.FlightException;
//import CheckIn.ThreadNewPassenger;
import CheckIn.SimulationTimeSingleton;

public class CheckInDesk implements Runnable {

	/*private String flightCode;
	private String bookingCode;
	private String passengerName;
	private double baggageWeight;
	private boolean checkInStatus = false;
	private String excessFee;
	private boolean closestatus = false;
	private ThreadNewPassenger so;
	private BookingCollection allBookings;
	private CheckIn model;*/
	private LoggingSingleton log;
	private CheckInDeskCountSingleton deskCount;
	private SimulationTimeSingleton simTime = null;
	private BookingCollection allBookings;
	private Flight boardingFlight;
	private int deskNumber;
	
	public CheckInDesk(Flight boardingFlight, BookingCollection allBookings, int deskNumber) {
		log = LoggingSingleton.getInstance();
		deskCount = CheckInDeskCountSingleton.getInstance();
		simTime = SimulationTimeSingleton.getInstance();
		this.boardingFlight = boardingFlight;
		this.allBookings = allBookings;
		this.deskNumber = deskNumber;
	}

	public String getFlightCode() {
		return boardingFlight.getFlightCode();
	}
	
	public String getFlightDestination() {
		return boardingFlight.getDestinationAirport();
	}

	@Override
	public void run() {
		log.addLog("Check In Desk " + deskNumber + " for flight " + boardingFlight.getFlightCode() + " has opened.", "checkin");
		while(boardingFlight.getFlightStatus().compareTo("boarding") == 0 && simTime.isSimRunning()) {
			
			log.addLog("Processing passengers on Desk " + deskNumber, "checkin");
			Booking nextPassenger = allBookings.getNextPassenger(boardingFlight.getFlightCode(), "Business");
			if(nextPassenger == null) {
				nextPassenger = allBookings.getNextPassenger(boardingFlight.getFlightCode(), "Economy");
			}
			
			if(nextPassenger != null) {
				
				Bag baggage = RandomBagGenerator.getRandomBag(boardingFlight.getAllowedBaggageWeightPerPassenger(), boardingFlight.getAllowedBaggageVolumePerPassenger(), 23);
				nextPassenger.getPassenger().addBaggage(baggage);
				nextPassenger.getPassenger().setCheckIn();
				double allowedBaggageWeight = boardingFlight.getAllowedBaggageWeightPerPassenger();
				double excessCharge = boardingFlight.getExcessCharge();
				nextPassenger.getPassenger().getBaggage().setExcessCharge(allowedBaggageWeight, excessCharge);
				double charge = nextPassenger.getPassenger().getBaggage().getExcessCharge();
				
				log.addLog("Processing passenger " + nextPassenger.getPassenger().getFirstName() + " " + nextPassenger.getPassenger().getLastName() + " on booking " + nextPassenger.getBookingCode() + " who has a excess charge of £" + charge, "checkin");
			}

			try {
				Thread.sleep(FakeTime.getSpeedDelay(simTime.getSpeed()));
			} catch (InterruptedException e) {
				log.addLog("Thread sleep interrupted.", "log");
			}
		}
		deskCount.decActiveDesks();
		log.addLog("Check In Desk " + deskNumber + " for flight " + boardingFlight.getFlightCode() + " has closed.", "checkin");
	}

	/*public String getBookingCode() {
		return boardingFlight.get;
	}*/

	/*public String getPassengerName() {
		return passengerName;
	}*/

	/*public double getBaggageWeight() {
		return baggageWeight;
	}*/

	/*public String getExcessFee() {
		return excessFee;
	}*/
	
	/*public boolean isClosestatus() {
		return closestatus;
	}*/
	
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
/*
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(so.hasPassengerInQueue()) {
			try {
				Booking aBooking = so.getPassengerForCheckIn();
				try {
					this.flightCode = aBooking.getFlightCode();
					this.bookingCode = aBooking.getBookingCode();
					this.passengerName = aBooking.getPassenger().getFirstName() + " " + aBooking.getPassenger().getLastName();
					this.baggageWeight = aBooking.getPassenger().getBaggage().getWeight();
					this.checkInStatus = aBooking.getPassenger().isCheckIn();
					model.notifyObservers();
					Thread.sleep(3000);
					this.model.doCheckIn(this.bookingCode, aBooking.getPassenger(), aBooking.getPassenger().getBaggage());
					this.excessFee = String.valueOf(aBooking.getPassenger().getBaggage().getExcessCharge()); // aPassenger.getPassenger().getBaggage().getExcessCharge();
					this.checkInStatus = aBooking.getPassenger().isCheckIn();
					model.notifyObservers();
					Thread.sleep(3000);
					// System.out.println("GOT: " + this.passengerName);
				} catch (FlightException | BookingException e) {
					System.out.println(aBooking.getPassenger().getFirstName() + " " + aBooking.getPassenger().getLastName() + " has missed flight " + aBooking.getFlightCode() + " because " + e.getMessage());
					// e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch(FlightException | BookingException e) {
				e.printStackTrace();
			}
		}
	}*/
}
