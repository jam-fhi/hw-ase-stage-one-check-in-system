package checkInModel;

/**
 * Import data structure packages.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * BookingCollection
 * Stores and manages a collection of bookings.
 * @author christophermuir
 *
 */
public class BookingCollection {
	
	/**
	 *  Initialise the hash map.
	 */
	private volatile HashMap<String, Booking> allBookings = new HashMap<String, Booking>();
	
	/**
	 * Logging Singleton holds our event logs.
	 */
	private LoggingSingleton log;
	
	/**
	 * Thread blocking boolean, for data manipulation across threads.
	 */
	private volatile boolean inUse;

	/**
	 * BookingCollection
	 * Creates the booking collection object, gets the log singleton instance.
	 */
	public BookingCollection() { 
		log = LoggingSingleton.getInstance();
	}

	/**
	 * getBookingsByFlightCode
	 * Gets all bookings for a given flight code.
	 * @param flightCode
	 * @return ArrayList<Booking>
	 */
	public synchronized ArrayList<Booking> getBookingsByFlightCode(String flightCode) {
		/**
		 * Get exclusive access to the booking hash map, so
		 * that no other thread modifies it during this operation.
		 */
		takeInUse();

		/**
		 *  Can be called to retrieve all bookings with a certain flight code.
		 */
		ArrayList<Booking> flightBookings = new ArrayList<Booking>();
		for(Map.Entry<String, Booking> aBooking: allBookings.entrySet()) {
			/**
			 * If the flight codes match.
			 */
			if(aBooking.getValue().getFlightCode().compareTo(flightCode) == 0) {
				flightBookings.add(aBooking.getValue());
			}
		}
		
		/**
		 * Release access to the booking hash map.
		 */
		freeInUse();
		return flightBookings;
	}
	
	/**
	 * 
	 * progressBookingPassedSecurity
	 * Will find the first booking that is in the security
	 * queue and progress them to the queue they should be in.
	 */
	public synchronized void progressBookingPassedSecurity(boolean bookingClass) {
		/**
		 * Get exclusive access to the booking hash map, so
		 * that no other thread modifies it during this operation.
		 */
		takeInUse();
		for(Map.Entry<String, Booking> aBooking: allBookings.entrySet()) {
			if(aBooking.getValue().isBusinessClass() == bookingClass && aBooking.getValue().getPassenger().getInQueue().compareTo("Security") == 0) {
				/**
				 * Set the name of the queue that the
				 * passenger for this booking will join.
				 */
				String queue = bookingClass == true ? "Business" : "Economy";
				log.addLog("Progressed Passenger to " + queue + " Queue", "BookingCollection");
				aBooking.getValue().getPassenger().setInQueue(queue);
				break;
			}
		}
		/**
		 * Release access to the booking hash map.
		 */
		freeInUse();
	}

	/**
	 * addBatchBookings
	 * Adds a batch of bookings from an array list.
	 * @param newBookings
	 */
	public synchronized void addBatchBookings(ArrayList<Booking> newBookings) {
		/**
		 * Get exclusive access to the booking hash map, so
		 * that no other thread modifies it during this operation.
		 */
		takeInUse();
		Iterator<Booking> newBookingsIt = newBookings.iterator();
		while(newBookingsIt.hasNext()) {
			/**
			 * Iterate through all provided bookings in the array list parameter
			 * and add them, one by one, to the booking hash map.
			 */
			Booking aBooking = newBookingsIt.next();
			log.addLog("Saved booking " + aBooking.getBookingCode(), "BookingCollection");
			allBookings.put(aBooking.getBookingCode(), aBooking);
		}
		log.addLog("Added " + newBookings.size() + " bookings", "BookingCollection");
		/**
		 * Release access to the booking hash map.
		 */
		freeInUse();
	}
	
	/**
	 * progressBookingToSecurity
	 * Finds the first booking not in the security queue and
	 * adds them to that queue.
	 */
	public synchronized void progressBookingToSecurity() {
		/**
		 * Get exclusive access to the booking hash map, so
		 * that no other thread modifies it during this operation.
		 */
		takeInUse();
		for(Map.Entry<String, Booking> aBooking: allBookings.entrySet()) {
			if(aBooking.getValue().getPassenger().getInQueue().compareTo("") == 0) {
				aBooking.getValue().getPassenger().setInQueue("Security");
				log.addLog("Added passenger " + aBooking.getValue().getPassenger().getFirstName() + " " + aBooking.getValue().getPassenger().getLastName() + " to security queue", "BookingCollection");
				/**
				 * We don't want to add all our bookings to the
				 * security queue in one go, so break out of the
				 * loop and end after one.
				 */
				break;
			}
		}
		/**
		 * Release access to the booking hash map.
		 */
		freeInUse();
	}

	/**
	 * getBookingsInQueue
	 * Takes a string queue name and finds all
	 * the bookings that have a passenger in 
	 * that queue. Returns an ArrayList of booking 
	 * objects.
	 * @param queueName
	 * @return ArrayList Bookings
	 */
	public synchronized ArrayList<Booking> getBookingsInQueue(String queueName) {
		/**
		 * Get exclusive access to the booking hash map, so
		 * that no other thread modifies it during this operation.
		 */
		takeInUse();
		ArrayList<Booking> queue = new ArrayList<Booking>(); 
		for(Map.Entry<String, Booking> aBooking: allBookings.entrySet()) {
			/**
			 * Loop through all entries within the hash map and
			 * if it has a passenger in the queue matching the
			 * provided name, add it to the queue array list for
			 * return.
			 */
			if(aBooking.getValue().getPassenger().getInQueue().compareTo(queueName) == 0) {
				queue.add(aBooking.getValue());
			}
		}
		/**
		 * Release access to the booking hash map.
		 */
		freeInUse();
		return queue;
	}

	/**
	 * getNextBooking
	 * Returns the first booking that has a passenger in
	 * the specified queue for the specified flight code.
	 * @param flightCode
	 * @param queueName
	 * @return Booking object
	 */
	public synchronized Booking getNextBooking(String flightCode, String queueName) {
		ArrayList<Booking> allBookings = getBookingsByFlightCode(flightCode);
		Iterator<Booking> allBookingsIt = allBookings.iterator();
		while(allBookingsIt.hasNext()) {
			Booking aBooking = allBookingsIt.next();
			if(aBooking.getPassenger().getInQueue().compareTo(queueName) == 0) {
				/**
				 * Loop through the bookings that matched the
				 * specified flight code and if that passenger
				 * matches the specified queue, move them to
				 * the check in queue.
				 */
				aBooking.getPassenger().setInQueue("checkin");
				log.addLog("Passenger " + aBooking.getPassenger().getFirstName() + " " + aBooking.getPassenger().getLastName() + " has moved to Check In.", "BookingCollection");
				/**
				 * Release access to the booking hash map.
				 */
				freeInUse();
				return aBooking;
			}
		}
		return null;
	}
	
	/**
	 * takeInUse
	 * Waits for the inUse flag to be false and then
	 * sets it to true so other lines can be executed
	 * in a thread safe way.
	 */
	private synchronized void takeInUse() {
		while (inUse) {
			try {
				log.addLog("Wait for bookings to be free", "BookingCollection");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inUse = true;
	}
	
	/**
	 * freeInUse
	 * Sets the inUse flag to false and notifies all other threads.
	 */
	public synchronized void freeInUse() {
		inUse = false;
		notifyAll();
	}
}

