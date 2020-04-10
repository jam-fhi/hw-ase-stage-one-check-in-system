package CheckIn;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPassengerGenerator {

	private static String[] passengerFirstNames = { "John", "Sally", "Haikah", "Nadia", "Sam", "Amy", "Jamie", "Kira", "Isambard", "Tyler" };
	private static String[] passengerLastNames = { "McFarland", "Abulhawa", "Ghoghari", "Muir", "Hill", "Smith", "Johnson", "Brunel", "Kirk", "Riker" };
	
	public static Passenger createRandomPassenger() {
		String firstName = pickRandomFromList(passengerFirstNames);
		String lastName = pickRandomFromList(passengerLastNames);
		return new Passenger(firstName, lastName);
	}
	
	private static String pickRandomFromList(String[] passengerFirstNames) {
		int index = ThreadLocalRandom.current().nextInt(0, passengerFirstNames.length);
		return passengerFirstNames[index];
	}
}
