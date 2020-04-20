package checkInModel;

/**
 * Import packages to manipulate the date/time.
 */
import java.util.Calendar;
import java.util.Date;

/**
 * Import packages for random numbers.
 */
import java.util.concurrent.ThreadLocalRandom;

public class RandomFlightGenerator {

	/**
	 * Data collection to generate random flight information from.
	 */
	private static String[] flightCodes = { "BA", "EZ", "SM", "BD", "CJ", "BE" };
	private static String[] destinationAirport = { "Barcelona", "New York", "London", "Edinburgh", "Glasgow", "Oslo", "Kuala Lumpur", "Beijing", "Tokoyo", "Sydney" };
	private static String[] carrier = { "British Airways", "Easy Jet", "Aberdeen Airways", "BMI", "Citiflyer Express", "FlyBe" };

	/**
	 * getRandomOperatorIndex
	 * Picks a random index between 0 and the number of flight codes.
	 * @return int
	 */
	private static int getRandomOperatorIndex() {
		return ThreadLocalRandom.current().nextInt(0, flightCodes.length - 1);
	}
	
	/**
	 * getFlightCode
	 * Returns the element at the provided index of the flight code array.
	 * @param operator
	 * @return String
	 */
	private static String getFlightCode(int operator) {
		return flightCodes[operator] + "-" + getNumericCode();
	}

	/**
	 * getNumericCode
	 * Picks a random number between 1 and 999 to use as the
	 * flight or booking id number. Pads the value with 0s to
	 * make a 3 digit string to return.
	 * @return String
	 */
	public static String getNumericCode() {
		String numericCode = String.valueOf(ThreadLocalRandom.current().nextInt(1, 999));
		while(numericCode.length() < 3) {
			numericCode = "0" + numericCode;
		}
		return numericCode;
	}

	/**
	 * getCarrier
	 * Gets the element within the carrier array at the provided index.
	 * @param operator
	 * @return String
	 */
	private static String getCarrier(int operator) {
		return carrier[operator];
	}
	
	/**
	 * getDestination
	 * Picks a number between 0 and the size of the destination airport
	 * array then returns the element at that index.
	 * @return String
	 */
	private static String getDestination() {
		int destination = ThreadLocalRandom.current().nextInt(0, destinationAirport.length-1);
		return destinationAirport[destination];
	}
	
	/**
	 * getMaximumPassengers
	 * Picks a number between 1 and 300 to be used as the
	 * maximum number of passengers on a flight.
	 * @return int
	 */
	private static int getMaximumPassengers() {
		return ThreadLocalRandom.current().nextInt(1, 300);
	}
	
	/**
	 * getMaxBaggageVolumeOrWeightOrCharge
	 * Returns a double to be used for the max baggage volume/weight
	 * or as the excess baggage charge on a flight.
	 * @return double
	 */
	private static double getMaxBaggageVolumeOrWeightOrCharge() {
		return (ThreadLocalRandom.current().nextInt(1, 999) * ThreadLocalRandom.current().nextDouble());
	}
	
	/**
	 * getDepartureDate
	 * Picks a random number of hours between 7 and 15
	 * and adds that number of milliseconds onto the 
	 * current simulation time to use as the flight 
	 * departure date. 
	 * @return Date
	 */
	private static Date getDepartureDate() {
		SimulationTimeSingleton simTime = SimulationTimeSingleton.getInstance();
		long depatureTime = simTime.getCurrentSimTime().getTime() + (ThreadLocalRandom.current().nextInt(7, 15) * SimulationTimeSingleton.hourInMs);
		Calendar departureDate = Calendar.getInstance();
		departureDate.setTimeInMillis(depatureTime);
		return departureDate.getTime();
	}
	
	/**
	 * getRandomFlight
	 * Populates all properties of a flight object
	 * with a random selection of data.
	 * @return Flight
	 */
	@SuppressWarnings("deprecation")
	public static Flight getRandomFlight() {
		int operator = getRandomOperatorIndex();
		String flightCode = getFlightCode(operator);
		String carrier = getCarrier(operator);
		String destination = getDestination();
		int maximumPassengers = getMaximumPassengers();
		double maximumBaggageWeight = getMaxBaggageVolumeOrWeightOrCharge();
		double maximumBaggageVolume = getMaxBaggageVolumeOrWeightOrCharge();
		double excessCharge = getMaxBaggageVolumeOrWeightOrCharge();
		Date departureDate = getDepartureDate();
		String departureTimeStr = departureDate.getHours() + ":" + departureDate.getMinutes();
		String departureDateStr = (departureDate.getYear() + 1900) + "-" + departureDate.getMonth() + "-" + departureDate.getDate();
		return new Flight(flightCode, destination, carrier, maximumPassengers, maximumBaggageWeight, maximumBaggageVolume, excessCharge, departureTimeStr, departureDateStr);
	}
}
