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
	// private CheckIn mydesk;
	private double illegalbagchance;
	private boolean passengerReady = true;
	private ArrayList<Booking> list = new ArrayList<Booking>();
	
	/**
	 * constructor
	 * @param double illegalbagchance
	 * @param CheckinDeck checkin
	 */
	public ThreadNewPassenger(/*CheckIn checkin*/){
		this.illegalbagchance = 3.0;
		//this.mydesk = checkin;
	}
	
	/**
	 * put method 
	 * finds an appropriate Unchecked in passenger and add to the array list
	 * @throws Exception 
	 * @throws FlightException 
	 */
	
	public synchronized void put(Booking queuePassenger, Flight flight) throws FlightException, Exception {
		
		//Thread.sleep(1000);
		System.out.println(queuePassenger.getPassenger().getFirstName());
		passengerReady = false;
		queuePassenger.getPassenger().addBaggage(RandomBagGenerator.getRandomBag(flight.getAllowedBaggageWeightPerPassenger(), (int)flight.getAllowedBaggageVolumePerPassenger(), illegalbagchance));
		this.list.add(queuePassenger);
		// log event
		
		String name = queuePassenger.getPassenger().getFirstName() + " " + queuePassenger.getPassenger().getLastName();
		LoggingSingleton logger = LoggingSingleton.getInstance();
		logger.addLog(name + " added to Checkin queue.");
		
		
/*		while(!passengerReady) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		passengerReady = false;
		queuePassenger.getPassenger().addBaggage(RandomBagGenerator.getRandomBag(flight.getAllowedBaggageWeightPerPassenger(), (int)flight.getAllowedBaggageVolumePerPassenger(), illegalbagchance));
		this.list.add(queuePassenger);
		notifyAll();
*/		

	}

	/**
	 * get method 
	 * passes the current list to the CheckinDesk
	 * @throws BookingException 
	 * @throws FlightException 
	 */
	public synchronized Booking getPassengerForCheckIn() throws FlightException, BookingException {
		while(passengerReady || list.size() <= 0) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		passengerReady = true;
		notifyAll(); 
		return list.get(0);
		//mydesk.doCheckIn(list.get(0).getBookingCode(),list.get(0).getPassenger(),list.get(0).getPassenger().getBaggage());
		//list.clear();
		
	}
	
	/**
	 * get method 
	 * passes the current state of the list
	 */
	public ArrayList<Booking> getList() {
		return list;
	}
}
