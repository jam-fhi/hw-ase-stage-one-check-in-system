package checkInModel;

import java.text.DecimalFormat;

/**
 * CheckInDesk
 * Runs in a thread to check in passengers to a
 * specified flight, simulating a check in desk.
 * @author jamiehill
 */
public class CheckInDesk implements Runnable {
	
	/**
	 * Logging Singleton Instance
	 */
	private LoggingSingleton log;
	
	/**
	 * Simulation Time Singleton Instance
	 */
	private SimulationTimeSingleton simTime = null;
	
	/**
	 * Round to 2 decimal places.
	 */
	private static DecimalFormat decPlace = new DecimalFormat("#.##");

	/**
	 * All the bookings in our system, to find passengers to check in.
	 */
	private volatile BookingCollection allBookings;
	
	/**
	 * The flight we are checking passengers into.
	 */
	private Flight boardingFlight;
	
	/**
	 * Identifying number of this desk.
	 */
	private int deskNumber;
	
	/**
	 * The thread that this check in desk runs within.
	 */
	private Thread myDesk;
	
	/**
	 * Values to display on the UI
	 */
	private String currentBookingCode;
	private String currentPassengerName;
	private String currentBagWeight = "0.0";
	private String currentExcessFee = "0.00";
	
	/**
	 * CheckInDesk
	 * Constructor, stores data we need to check passengers in
	 * and starts the thread running that processes passengers.
	 * @param boardingFlight
	 * @param allBookings
	 * @param deskNumber
	 */
	public CheckInDesk(Flight boardingFlight, BookingCollection allBookings, int deskNumber) {
		/**
		 * Get our singleton instances.
		 */
		log = LoggingSingleton.getInstance();
		simTime = SimulationTimeSingleton.getInstance();
		/**
		 * Store the bookings, flight and desk number.
		 */
		this.boardingFlight = boardingFlight;
		this.allBookings = allBookings;
		this.deskNumber = deskNumber;
		/**
		 * Create and start worker thread.
		 */
		myDesk = new Thread(this);
		myDesk.start();
	}

	/**
	 * getFlightCode
	 * Returns the code of the flight currently
	 * being processed.
	 * @return String
	 */
	public String getFlightCode() {
		return boardingFlight.getFlightCode();
	}
	
	/**
	 * getFlightDestination
	 * Returns the destination airport of the
	 * flight we are currently processing.
	 * @return String
	 */
	public String getFlightDestination() {
		return boardingFlight.getDestinationAirport();
	}

	/**
	 * getThreadState
	 * Returns the state of the thread
	 * that is doing the check in process.
	 * @return Thread.State
	 */
	public Thread.State getThreadState() {
		return myDesk.getState();
	}

	/**
	 * getBookingCode
	 * Returns the booking code of the
	 * passenger that is currently 
	 * being processed.
	 * @return String
	 */
	public String getBookingCode() {
		return currentBookingCode;
	}

	/**
	 * getPassengerName
	 * Returns the name of the passenger
	 * who is currently being checked in.
	 * @return String
	 */
	public String getPassengerName() {
		return currentPassengerName;
	}

	/**
	 * getBaggageWeight
	 * Returns the weight of the passenger's
	 * bag, who is currently being checked in.
	 * @return String
	 */
	public String getBaggageWeight() {
		return currentBagWeight;
	}

	/**
	 * getExcessFee
	 * Returns the excess fee the passenger
	 * has been charged.
	 * @return String
	 */
	public String getExcessFee() {
		return currentExcessFee;
	}

	/**
	 * getCheckInDeskNumber
	 * Returns the id number of this
	 * check in desk.
	 * @return int
	 */
	public int getCheckInDeskNumber() {
		return deskNumber;
	}

	/**
	 * run
	 * Runs a thread to process passengers
	 * for check in to the flight specified
	 * within this class.
	 */
	@Override
	public void run() {
		/**
		 * While the flight status is boarding, find passengers to check in.
		 */
		log.addLog("Check In Desk " + deskNumber + " for flight " + boardingFlight.getFlightCode() + " has opened.", "CheckInDesk" + deskNumber);
		while(boardingFlight.getFlightStatus().compareTo("boarding") == 0 && simTime.isSimRunning()) {
			log.addLog("Processing passengers on Desk " + deskNumber, "checkin");
			
			/**
			 * Get any business class passengers first.
			 */
			Booking nextPassenger = allBookings.getNextBooking(boardingFlight.getFlightCode(), "Business");
			if(nextPassenger == null) {

				/**
				 * If there are no business class pasengers, get an economy class passenger.
				 */
				nextPassenger = allBookings.getNextBooking(boardingFlight.getFlightCode(), "Economy");
			}
			
			/**
			 * If there is a passenger, proceed with check in.
			 */
			if(nextPassenger != null) {
				/**
				 * Store booking code and passenger name, for UI display.
				 */
				currentBookingCode = nextPassenger.getBookingCode();
				currentPassengerName = nextPassenger.getPassenger().getFirstName() + " " + nextPassenger.getPassenger().getLastName();
				/**
				 * Generate a bag for the passenger to check in. 
				 */
				double weight = boardingFlight.getAllowedBaggageWeightPerPassenger();
				double volume = boardingFlight.getAllowedBaggageVolumePerPassenger();
				Bag baggage = RandomBagGenerator.getRandomBag(weight, (int)volume);
				/**
				 * Store the weight of the current passengers bag.
				 */
				currentBagWeight = decPlace.format(baggage.getWeight());
				/**
				 * Perform check in operation.
				 */
				nextPassenger.getPassenger().addBaggage(baggage);
				nextPassenger.getPassenger().setCheckIn();
				/**
				 * Calculate the excess charge.
				 */
				double allowedBaggageWeight = boardingFlight.getAllowedBaggageWeightPerPassenger();
				double excessCharge = boardingFlight.getExcessCharge();
				nextPassenger.getPassenger().getBaggage().setExcessCharge(allowedBaggageWeight, excessCharge);
				double charge = nextPassenger.getPassenger().getBaggage().getExcessCharge();
				/**
				 * Store the excess charge for UI display.
				 */
				currentExcessFee = decPlace.format(charge);
				log.addLog("Processing passenger " + nextPassenger.getPassenger().getFirstName() + " " + nextPassenger.getPassenger().getLastName() + " on booking " + nextPassenger.getBookingCode() + " who has a excess charge of £" + charge, "CheckInDesk" + deskNumber);
			}

			try {
				Thread.sleep(SimulationTimeSingleton.getSpeedDelay(simTime.getSpeed()));
			} catch (InterruptedException e) {
				log.addLog("Thread sleep interrupted.", "CheckInDesk" + deskNumber);
			}
		}
		log.addLog("Check In Desk " + deskNumber + " for flight " + boardingFlight.getFlightCode() + " has closed.", "CheckInDesk" + deskNumber);
		new ReportGenerator(allBookings, boardingFlight);
	}
}
