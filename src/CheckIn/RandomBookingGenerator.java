package CheckIn;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBookingGenerator {

	private static LoggingSingleton log;
	private static String[] passengerFirstNames = { "John", "Sally", "Haikah", "Nadia", "Sam", "Amy", "Jamie", "Kira", "Isambard", "Tyler" };
	private static String[] passengerLastNames = { "McFarland", "Abulhawa", "Ghoghari", "Muir", "Hill", "Smith", "Johnson", "Brunel", "Kirk", "Riker" };
	
	public static ArrayList<Booking> getRandomBookings(String flightCode, int amount) {
		log = LoggingSingleton.getInstance();
		int count = 0;
		ArrayList<Booking> randomBookings = new ArrayList<Booking>();
		while(count < amount) {
			String firstName = pickRandomFromList(passengerFirstNames);
			String lastName = pickRandomFromList(passengerLastNames);
			String bookingCode = getBookingCode(flightCode);
			try {
				Booking randomBooking = new Booking(bookingCode, firstName, lastName, flightCode);
				randomBookings.add(randomBooking);
			} catch (BookingException e) {
				log.addLog("There was an error creating the random booking " + e.getMessage());
			}
		}
		return randomBookings;
	}
	
	private static String getBookingCode(String flightCode) {
		String bookingNumber = String.valueOf(ThreadLocalRandom.current().nextInt(0, 999));
		while(bookingNumber.length() < 3) {
			bookingNumber = "0" + bookingNumber;
		}
		return flightCode + "-" + bookingNumber;
	}
	
	private static String pickRandomFromList(String[] passengerNames) {
		int index = ThreadLocalRandom.current().nextInt(0, passengerNames.length);
		return passengerNames[index];
	}
}
