package checkInModel;

/**
 * Import data structure packages.
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Import random number packages.
 */
import java.util.concurrent.ThreadLocalRandom;

/**
 * RandomBookingGenerator
 * Generates random data to populate bookings.
 * @author jamiehill
 *
 */
public class RandomBookingGenerator {

	/**
	 * LoggingSingleton
	 */
	private LoggingSingleton log;
	
	/**
	 * Collection of first and last names to select a random of.
	 */
	private String[] passengerFirstNames = { "John", "Sally", "Haikah", "Nadia", "Sam", "Amy", "Jamie", "Kira", "Isambard", "Tyler" };
	private String[] passengerLastNames = { "McFarland", "Abulhawa", "Ghoghari", "Muir", "Hill", "Smith", "Johnson", "Brunel", "Kirk", "Riker" };
	
	/**
	 * Flight collection and booking collection to process and add bookings to.
	 */
	private FlightCollection flights;
	private volatile BookingCollection bookings;
	
	/**
	 * RandomBookingGenerator
	 * Constructor to create random booking generator object.
	 * @param flights
	 * @param bookings
	 */
	public RandomBookingGenerator(FlightCollection flights, BookingCollection bookings) {
		/**
		 * Get the logging instance.
		 */
		log = LoggingSingleton.getInstance();
		/**
		 * Store booking and flight collection files for later use.
		 */
		this.flights = flights;
		this.bookings = bookings;
	}

	/**
	 * getRandomBookings
	 * Generates a set number of bookings for a given flight code.
	 * @param flightCode
	 * @param amount
	 * @return ArrayList Booking
	 */
	private synchronized ArrayList<Booking> getRandomBookings(String flightCode, int amount) {
		int count = 0;
		ArrayList<Booking> randomBookings = new ArrayList<Booking>();
		/**
		 * Loop for the specified amount of times...
		 */
		while(count < amount) {
			/**
			 * Get a random first name, last name and booking number.
			 */
			String firstName = pickRandomFromList(passengerFirstNames);
			String lastName = pickRandomFromList(passengerLastNames);
			String bookingNumber = RandomFlightGenerator.getNumericCode();
			try {
				/**
				 * Create a booking object with random data.
				 */
				log.addLog("Added booking for " + firstName + " " + lastName + " on flight " + flightCode, "BookingGenerator");
				Booking newBooking = new Booking(flightCode + "-" + bookingNumber, firstName, lastName, flightCode);
				
				/**
				 * If the booking number is divisible by 5, make it business class.
				 */
				if (Integer.parseInt(bookingNumber) % 5 == 0) {
					newBooking.setBusinessClass();
					log.addLog("Added business class to booking " + newBooking.getBookingCode(), "BookingGenerator" );
				}
				
				/**
				 * Add the booking object to the collection of randomly generated bookings.
				 */
				randomBookings.add(newBooking);
			} catch (BookingException e) {
				log.addLog("There was an error creating the random booking " + e.getMessage(), "BookingGenerator");
			}
			count++;
		}
		return randomBookings;
	}
	
	/**
	 * pickRandomFromList
	 * Generates a random number between 0 and the size of the given list,
	 * then returns the array element at that index.
	 * @param passengerNames
	 * @return
	 */
	private String pickRandomFromList(String[] passengerNames) {
		int index = ThreadLocalRandom.current().nextInt(0, passengerNames.length - 1);
		return passengerNames[index];
	}

	/**
	 * generateBookings
	 * Generates a random number of random bookings for flights
	 * that do not have any bookings.
	 */
	public synchronized void generateBookings() {
		log.addLog("Creating bookings", "BookingGenerator");
		Iterator<Flight> flightsIt = flights.getFlightCollection().iterator();
		/**
		 * Loop through all flights.
		 */
		while(flightsIt.hasNext()) {
			Flight aFlight = flightsIt.next();
			/**
			 * Get the bookings on that flight already.
			 */
			ArrayList<Booking> allBookings = bookings.getBookingsByFlightCode(aFlight.getFlightCode());
			log.addLog("Flight " + aFlight.getFlightCode() + " has " + allBookings.size() + " bookings with a total capacity of " + aFlight.getMaximumPassengers(), "log");
			if(allBookings.size() <= 0) {
				/**
				 * When there are no bookings on a flight, pick a number between 
				 * 1/3rd passenger capacity and max passenger capacity and generate
				 * that number of bookings to add to the booking collection.
				 */
				int thirtyPercCapacity = (int) ((int) aFlight.getMaximumPassengers() * 0.3);
				int createBookings = ThreadLocalRandom.current().nextInt(thirtyPercCapacity, aFlight.getMaximumPassengers());
				log.addLog("Creating " + createBookings + " bookings for flight " + aFlight.getFlightCode(), "BookingGenerator");
				ArrayList<Booking> newBookings = getRandomBookings(aFlight.getFlightCode(), createBookings);
				bookings.addBatchBookings(newBookings);
			}
		}
	}
}
