package CheckIn;

public class Flight {
	private String flightCode;
	private String destinationAirport;
	private String carrier;
	private int maximumPassengers;
	private double maximumBaggageWeight;
	private double maximumBaggageVolume;
	private double excessCharge;

	public Flight(String flightCode, String destinationAirport, String carrier, int maximumPassengers,
			double maximumBaggageWeight, double maximumBaggageVolume, double excessCharge) {

		this.flightCode = flightCode;
		this.destinationAirport = destinationAirport;
		this.carrier = carrier;
		this.maximumPassengers = maximumPassengers;
		this.maximumBaggageWeight = maximumBaggageWeight;
		this.maximumBaggageVolume = maximumBaggageVolume;
		this.excessCharge = excessCharge;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public String getCarrier() {
		return carrier;
	}

	public int getMaximumPassengers() {
		return maximumPassengers;
	}

	public double getMaximumBaggageWeight() {
		return maximumBaggageWeight;
	}

	public double getMaximumBaggageVolume() {
		return maximumBaggageVolume;
	}

	public double getExcessCharge() {
		return excessCharge;
	}

	public double getAllowedBaggageWeightPerPassenger() {
		return maximumBaggageWeight / maximumPassengers;
	}

	public double getAllowedBaggageVolumePerPassenger() {
		return maximumBaggageVolume / maximumPassengers;
	}

}
