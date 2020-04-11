package CheckIn;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomFlightGenerator {

	private static String[] flightCodes = { "BA", "EZ", "SM", "BD", "CJ", "BE" };
	private static String[] destinationAirport = { "Barcelona", "New York", "London", "Edinburgh", "Glasgow", "Oslo", "Kuala Lumpur", "Beijing", "Tokoyo", "Sydney" };
	private static String[] carrier = { "British Airways", "Easy Jet", "Aberdeen Airways", "BMI", "Citiflyer Express", "FlyBe" };

	private static int getRandomOperatorIndex() {
		return ThreadLocalRandom.current().nextInt(0, flightCodes.length);
	}
	
	private static String getFlightCode(int operator) {
		String flightNumber = String.valueOf(ThreadLocalRandom.current().nextInt(0, 999));
		while(flightNumber.length() < 3) {
			flightNumber = "0" + flightNumber;
		}
		return flightCodes[operator] + "-" + flightNumber;
	}

	private static String getCarrier(int operator) {
		return carrier[operator];
	}
	
	private static String getDestination() {
		int destination = ThreadLocalRandom.current().nextInt(0, destinationAirport.length);
		return destinationAirport[destination];
	}
	
	private static int getMaximumPassengers() {
		return ThreadLocalRandom.current().nextInt(0, 999);
	}
	
	private static double getMaxBaggageVolumeOrWeightOrCharge() {
		return (ThreadLocalRandom.current().nextInt(0, 999) * ThreadLocalRandom.current().nextDouble());
	}
	
	private static Date getDepartureDate() {
		SimulationTimeSingleton simTime = SimulationTimeSingleton.getInstance();
		long depatureTime = simTime.getCurrentTime().getTime() + (ThreadLocalRandom.current().nextInt(3, 10) * 360000);
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
