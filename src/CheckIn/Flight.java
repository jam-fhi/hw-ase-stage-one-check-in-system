package CheckIn;

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
	private String flightCode;
	private String destinationAirport;
	private String carrier;
	private int maximumPassengers;
	private double maximumBaggageWeight;
	private double maximumBaggageVolume;
	private double excessCharge;
	private Date departureDate;
	private boolean isDeparted = false;
	private LoggingSingleton log;
	private boolean hasCheckInDesk = false;
	private long delayFlight = 0;
	
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
	public Flight(String flightCode, String destinationAirport, String carrier, int maximumPassengers,
			double maximumBaggageWeight, double maximumBaggageVolume, double excessCharge,String departureTime, String departureDate) {
		log = LoggingSingleton.getInstance();
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
	
	public boolean getHasCheckInDesk() {
		return hasCheckInDesk;
	}
	
	public void setHasCheckInDesk() {
		hasCheckInDesk = true;
	}

	/**
	 * checkInClosingTime
	 * Returns the flight departure time minus one hour
	 * as this is when a check in desk will close.
	 * @return
	 */
	public Date checkInClosingTime() {
		long hourInMs = 60 * 60 * 1000;
		long closingTime = departureDate.getTime() - hourInMs;
		return new Date(closingTime);
	}
	
	public void addDelay() {
		long hourInMs = 3600000;
		delayFlight += hourInMs;
	}
	 
	public String getFlightStatus() {
		if(isDeparted == false) {
			long hourInMs = 3600000;
			long sixHrInMs = hourInMs * 6;
			long departureTime = getDepartureDate().getTime() + delayFlight;
			long boardingStarts = departureTime - sixHrInMs;
			long checkinClosed = departureTime - hourInMs;
			if (FakeTime.getCurrentTime().getTime() > departureTime) {
				log.addLog("Flight " + getFlightCode() + " has departed", "log");
				isDeparted = true;
				return "departed";
			} else {
			
				if (FakeTime.getCurrentTime().getTime() > checkinClosed) {
					log.addLog("Flight " + getFlightCode() + " has boarding closed", "log");
					return "closed";
				} else {
					if(FakeTime.getCurrentTime().getTime() > boardingStarts) {
						
						if(hasCheckInDesk == false) {
							log.addLog("Flight " + getFlightCode() + " is ready for boarding", "log");
							return "ready";
						} else {
							log.addLog("Flight " + getFlightCode() + " is boarding", "log");
							return "boarding";
						}
					
					
					} else {
						if(delayFlight == 0) {
							log.addLog("Flight " + getFlightCode() + " is waiting for check in to open", "log");
							return "waiting";
						} else {
							log.addLog("Flight " + getFlightCode() + " is delayed by " + (delayFlight / hourInMs) + " hours", "log");
							return "delay";
						}
					}
				}
			}
		} else {
			return "departed";
		}
	}
	
}
