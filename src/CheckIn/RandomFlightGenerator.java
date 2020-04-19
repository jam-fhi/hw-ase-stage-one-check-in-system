package CheckIn;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomFlightGenerator {

	private static String[] flightCodes = { "BA", "EZ", "SM", "BD", "CJ", "BE" };
	private static String[] destinationAirport = { "Barcelona", "New York", "London", "Edinburgh", "Glasgow", "Oslo", "Kuala Lumpur", "Beijing", "Tokoyo", "Sydney" };
	private static String[] carrier = { "British Airways", "Easy Jet", "Aberdeen Airways", "BMI", "Citiflyer Express", "FlyBe" };

	private static int getRandomOperatorIndex() {
		return ThreadLocalRandom.current().nextInt(0, flightCodes.length - 1);
	}
	
	private static String getFlightCode(int operator) {
		return flightCodes[operator] + "-" + getNumericCode();
	}

	public static String getNumericCode() {
		String numericCode = String.valueOf(ThreadLocalRandom.current().nextInt(1, 999));
		while(numericCode.length() < 3) {
			numericCode = "0" + numericCode;
		}
		return numericCode;
	}

	private static String getCarrier(int operator) {
		return carrier[operator];
	}
	
	private static String getDestination() {
		int destination = ThreadLocalRandom.current().nextInt(0, destinationAirport.length-1);
		return destinationAirport[destination];
	}
	
	private static int getMaximumPassengers() {
		return ThreadLocalRandom.current().nextInt(1, 300);
	}
	
	private static double getMaxBaggageVolumeOrWeightOrCharge() {
		return (ThreadLocalRandom.current().nextInt(1, 999) * ThreadLocalRandom.current().nextDouble());
	}
	
	private static Date getDepartureDate() {
		SimulationTimeSingleton simTime = SimulationTimeSingleton.getInstance();
		long depatureTime = simTime.getCurrentSimTime().getTime() + (ThreadLocalRandom.current().nextInt(7, 15) * 3600000);
		Calendar departureDate = Calendar.getInstance();
		departureDate.setTimeInMillis(depatureTime);
		return departureDate.getTime();
	}
	
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
