package CheckIn;

import java.util.ArrayList;
// need to do add comments , fix the booking and passeneger requirement in check in to
// the new class 
// make prettier
/**
 * ThreadNewPassenger takes an uncheckinPassenger creates a random bag for them 
 * then adds them to the queue for the given checkindesk
 * @author ChristopherSamMuir
 */
public class ThreadNewPassenger extends Thread {
	/**
	 *  initialise local variables
	 */
	private double illegalbagchance;
	private ArrayList<Booking> list = new ArrayList<Booking>();
	
	/**
	 * constructor
	 * @param double illegalbagchance
	 * @param CheckinDeck checkin
	 */
	public ThreadNewPassenger(){
		this.illegalbagchance = 3.0;
	}
	
	/**
	 * put method 
	 * finds an appropriate Unchecked in passenger and add to the array list
	 * @throws Exception 
	 * @throws FlightException 
	 */
	
	public synchronized void put(Booking queuePassenger, Flight flight) throws FlightException, Exception {
		while(list.size() > 5) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		Thread.sleep(1000);
		queuePassenger.getPassenger().addBaggage(RandomBagGenerator.getRandomBag(flight.getAllowedBaggageWeightPerPassenger(), (int)flight.getAllowedBaggageVolumePerPassenger(), illegalbagchance));
		this.list.add(queuePassenger);		
		String name = queuePassenger.getPassenger().getFirstName() + " " + queuePassenger.getPassenger().getLastName();
		LoggingSingleton logger = LoggingSingleton.getInstance();
		logger.addLog(name + " added to Checkin queue.");
		notifyAll();
	}

	/**
	 * get method 
	 * passes the current list to the CheckinDesk
	 * @throws BookingException 
	 * @throws FlightException 
	 */
	public synchronized Booking getPassengerForCheckIn() throws FlightException, BookingException {
		while(list.size() <= 0) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		Booking nextCheckIn = list.remove(0);
		notifyAll();
		return nextCheckIn;
	}
	
	/**
	 * get method 
	 * passes the current state of the list
	 */
	public ArrayList<Booking> getList() {
		return list;
	}
	
	public boolean hasPassengerInQueue() {
		if(list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
