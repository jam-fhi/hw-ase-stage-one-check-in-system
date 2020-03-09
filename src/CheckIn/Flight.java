package CheckIn;

import java.util.Date;

/**
 * Flight Creating instance variables flightCode, destinationAirport, carrier,
 * maximumPassengers, maximumBaggageWeight, maximumBaggageVolume, and
 * excessCharge
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
		this.flightCode = flightCode;
		this.destinationAirport = destinationAirport;
		this.carrier = carrier;
		this.maximumPassengers = maximumPassengers;
		this.maximumBaggageWeight = maximumBaggageWeight;
		this.maximumBaggageVolume = maximumBaggageVolume;
		this.excessCharge = excessCharge;
		this.departureDate = new Date(departureDate + "T" + departureTime + ".000Z");

	}

	/**
	 * get method to return the flight code
	 * 
	 * @return flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * get method to return the destination airport
	 * 
	 * @return destinationAirport
	 */
	public String getDestinationAirport() {
		return destinationAirport;
	}

	/**
	 * get method to return the carrier
	 * 
	 * @return carrier
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * get method to return the maximum passengers
	 * 
	 * @return maximumPassengers
	 */
	public int getMaximumPassengers() {
		return maximumPassengers;
	}

	/**
	 * get method to return the maximum baggage weight
	 * 
	 * @return maximumBaggageWeight
	 */
	public double getMaximumBaggageWeight() {
		return maximumBaggageWeight;
	}

	/**
	 * get method to return the maximum baggage volume
	 * 
	 * @return maximumBaggageVolume
	 */
	public double getMaximumBaggageVolume() {
		return maximumBaggageVolume;
	}

	/**
	 * get method to return the excess charge
	 * 
	 * @return excessCharge
	 */
	public double getExcessCharge() {
		return excessCharge;
	}

	/**
	 * get method to return allowed baggage weight per passenger
	 * 
	 * @return maximumBaggageWeight / maximumPassengers returns allowed baggage
	 *         weight
	 */
	public double getAllowedBaggageWeightPerPassenger() {
		return maximumBaggageWeight / maximumPassengers;
	}

	/**
	 * get method to return allowed baggage volume per passenger
	 * 
	 * @return maximumBaggageVolume / maximumPassengers returns allowed baggage
	 *         volume
	 */
	public double getAllowedBaggageVolumePerPassenger() {
		return maximumBaggageVolume / maximumPassengers;
	}
	
	/**
	 * get method to return the departure time
	 * 
	 * @return departureTime
	 */
	public Date getDepartureDate() {
		return departureDate;
	}
	
	
	public Date checkInClosingTime() {
		long hourInMs = 60 * 60 * 100;
		long closingTime = departureDate.getTime() - hourInMs;
		return new Date(closingTime); 
				
	}
	
	

}
