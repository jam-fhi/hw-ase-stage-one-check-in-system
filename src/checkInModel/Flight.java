package checkInModel;

/**
 * Import classes to manage data and time.
 */
import java.util.Date;
import java.util.Calendar;

/**
 * Flight
 * Flight Creating instance variables flightCode, destinationAirport, carrier,
 * maximumPassengers, maximumBaggageWeight, maximumBaggageVolume, and
 * excessCharge.
 * 
 * @author NadiaAbulhawa
 *
 */
public class Flight {
	/**
	 * Individual flight details
	 */
	private String flightCode;
	private String destinationAirport;
	private String carrier;
	private int maximumPassengers;
	private double maximumBaggageWeight;
	private double maximumBaggageVolume;
	private double excessCharge;
	private Date departureDate;
	
	/**
	 * Simulation conditions for each flight
	 */
	private boolean isDeparted = false;
	private boolean hasCheckInDesk = false;
	private long delayFlight = 0;
	
	/**
	 * Logging singleton to collect event logs
	 * while simulation progresses.
	 */
	private LoggingSingleton log;

	/**
	 * Simulation Time Singleton
	 */
	private SimulationTimeSingleton simTime;
	
	/**
	 * Flight Creating constructor
	 * 
	 * @param flightCode
	 * @param destinationAirport
	 * @param carrier
	 * @param maximumPassengers
	 * @param maximumBaggageWeight
	 * @param maximumBaggageVolume
	 * @param excessCharge
	 * 
	 */
	public Flight(String flightCode, String destinationAirport, String carrier, int maximumPassengers, double maximumBaggageWeight, double maximumBaggageVolume, double excessCharge,String departureTime, String departureDate) {
		/**
		 * Get the instance of our logging singleton.
		 */
		log = LoggingSingleton.getInstance();

		/**
		 * Get the instance of our simulation time.
		 */
		simTime = SimulationTimeSingleton.getInstance();
		
		/**
		 * Load flight details.
		 */
		this.flightCode = flightCode;
		this.destinationAirport = destinationAirport;
		this.carrier = carrier;
		this.maximumPassengers = maximumPassengers;
		this.maximumBaggageWeight = maximumBaggageWeight;
		this.maximumBaggageVolume = maximumBaggageVolume;
		this.excessCharge = excessCharge;
		Calendar departureCalendar = Calendar.getInstance();
		String [] dateValues = departureDate.split("-");
		String [] timeValues = departureTime.split(":");
		departureCalendar.set(Integer.parseInt(dateValues[0]), Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[2]), Integer.parseInt(timeValues[0]), Integer.parseInt(timeValues[1]), 0);
		this.departureDate = departureCalendar.getTime();

	}

	/**
	 * getFlightCode
	 * Get method to return the flight code
	 * @return flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * getDestinationAirport
	 * Get method to return the destination airport.
	 * @return destinationAirport
	 */
	public String getDestinationAirport() {
		return destinationAirport;
	}

	/**
	 * gtCarrier
	 * Get method to return the carrier.
	 * @return carrier
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * getMaximumPassengers
	 * Get method to return the maximum passengers.
	 * @return maximumPassengers
	 */
	public int getMaximumPassengers() {
		return maximumPassengers;
	}

	/**
	 * getMaximumBaggageWeight
	 * Get method to return the maximum baggage weight.
	 * @return maximumBaggageWeight
	 */
	public double getMaximumBaggageWeight() {
		return maximumBaggageWeight;
	}

	/**
	 * getMaximumBaggageVolume
	 * Get method to return the maximum baggage volume.
	 * @return maximumBaggageVolume
	 */
	public double getMaximumBaggageVolume() {
		return maximumBaggageVolume;
	}

	/**
	 * getExcessCharge
	 * Get method to return the excess charge.
	 * @return excessCharge
	 */
	public double getExcessCharge() {
		return excessCharge;
	}

	/**
	 * GetAllowedBaggageWeightPerPassenger
	 * Get method to return allowed baggage weight per passenger.
	 * @return maximumBaggageWeight / maximumPassengers returns allowed baggage weight
	 */
	public double getAllowedBaggageWeightPerPassenger() {
		return maximumBaggageWeight / maximumPassengers;
	}

	/**
	 * getAllowedBaggageVolumePerPassenger
	 * Get method to return allowed baggage volume per passenger
	 * @return maximumBaggageVolume / maximumPassengers returns allowed baggage volume
	 */
	public double getAllowedBaggageVolumePerPassenger() {
		return maximumBaggageVolume / maximumPassengers;
	}
	
	/**
	 * getDepartureDate
	 * Get method to return the departure time.
	 * @return departureTime
	 */
	public Date getDepartureDate() {
		return departureDate;
	}
	
	/**
	 * getHasCheckInDesk
	 * Returns true if the flight is being processed
	 * on a check in desk.
	 * @return boolean
	 */
	public boolean getHasCheckInDesk() {
		return hasCheckInDesk;
	}
	
	/**
	 * setHasCheckInDesk
	 * Sets true if the flight is being
	 * processed on a check in desk. We
	 * do not take a parameter here as
	 * flights only depart after check in
	 * and do not go back to waiting for
	 * boarding status.
	 */
	public void setHasCheckInDesk() {
		hasCheckInDesk = true;
	}

	/**
	 * checkInClosingTime
	 * Returns the flight departure time minus one hour
	 * as this is when a check in desk will close.
	 * @return Date
	 */
	public Date checkInClosingTime() {
		long closingTime = departureDate.getTime() - SimulationTimeSingleton.hourInMs;
		return new Date(closingTime);
	}
	
	/**
	 * addDelay
	 * Adds one hour in milliseconds to
	 * the delay value for the flight.
	 */
	public void addDelay() {
		delayFlight += SimulationTimeSingleton.hourInMs;
	}
	 
	/**
	 * getFlightStatus
	 * Returns a string representing the status of
	 * a flight based on a series of time calculations.
	 * When the departure time is > 6 hr in the future, the flight is waiting.
	 * When the departure time is <= 6hr in the future, the flight is ready.
	 * When the depature time is <= 6hr in the future and it has a check in desk, is it boarding.
	 * When the delay time value is set then the flight is delayed
	 * When the depature time + delay time is <= 1hr in the future boarding is closed.
	 * @return FlightStatus
	 */
	public FlightStatus getFlightStatus() {
		/**
		 * If the flight is departed, then we 
		 * do not need to repeat any calculations
		 * so we use a flag to return a default
		 * departed status.
		 */
		if(isDeparted == false) {
			/**
			 * Calculate the times, in milliseconds, that affect a flights status.
			 */
			long sixHrInMs = SimulationTimeSingleton.hourInMs * 6;
			/**
			 * Add the delay time in milliseconds to the depature time
			 * so that all status take into account any delay a flight may
			 * have encountered.
			 */
			long departureTime = getDepartureDate().getTime() + delayFlight;
			long boardingStarts = departureTime - sixHrInMs;
			long checkinClosed = departureTime - SimulationTimeSingleton.hourInMs;
			long currentSimTime = simTime.getCurrentSimTime().getTime();
			/**
			 * If the current simulation time is after the departure
			 * time, then set the departure status.
			 */
			if (currentSimTime > departureTime) {
				log.addLog("Flight " + getFlightCode() + " has departed", "Flight");
				isDeparted = true;
				return FlightStatus.DEPARTED;
			} else {
			
				/**
				 * If the current simulation time is after
				 * the check in closed time, set the closed status.
				 */
				if (currentSimTime > checkinClosed) {
					log.addLog("Flight " + getFlightCode() + " has boarding closed", "Flight");
					return FlightStatus.CLOSED;
				} else {
					
					/**
					 * If the current simulation time is after
					 * the boarding status time...
					 */
					if(currentSimTime > boardingStarts) {
						
						/**
						 * If the flight has a check in desk then its
						 *  status will be boarding, other wise it is
						 *  ready to begin boarding.
						 */
						if(hasCheckInDesk == false) {
							log.addLog("Flight " + getFlightCode() + " is ready for boarding", "Flight");
							return FlightStatus.READY;
						} else {
							log.addLog("Flight " + getFlightCode() + " is boarding", "Flight");
							return FlightStatus.BOARDING;
						}
					
					
					} else {
						
						/**
						 * If there is a delay value then the flight status
						 * will be delayed. The delay time is added to the departure
						 * time so all other status take into account any delay
						 * added in. If there is no delay then the flight is waiting
						 * for check in to open.
						 */
						if(delayFlight == 0) {
							log.addLog("Flight " + getFlightCode() + " is waiting for check in to open", "Flight");
							return FlightStatus.WAITING;
						} else {
							log.addLog("Flight " + getFlightCode() + " is delayed by " + (delayFlight / SimulationTimeSingleton.hourInMs) + " hours", "Flight");
							return FlightStatus.DELAY;
						}
					}
				}
			}
		} else {
			return FlightStatus.DEPARTED;
		}
	}
	
	/**
	 * setSTatusFromDelayToBoarding
	 * Calculate a new departure time as 5 hr from
	 * the current simulation time. Set the delay flight
	 * time to be the difference between the original 
	 * departure time and the new departure time.
	 */
	public void setStatusFromDelayedToBoarding() {
		long currentTime = simTime.getCurrentSimTime().getTime();
		long departureTime = getDepartureDate().getTime();		
		long newDepartureTime = currentTime + (SimulationTimeSingleton.hourInMs * 5);
		delayFlight = newDepartureTime - departureTime;
	}
}
