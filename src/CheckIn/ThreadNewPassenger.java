package CheckIn;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import model.CheckIn;
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
	private CheckIn mydesk;
	private double illegalbagchance;
	private boolean passengerReady;
	private ArrayList<PassengerWithBcode> list = new ArrayList<PassengerWithBcode>();
	
	/**
	 * constructor
	 * @param double illegalbagchance
	 * @param CheckinDeck checkin
	 */
	public ThreadNewPassenger(double illegal, CheckIn checkin){
		this.illegalbagchance = illegal;
		this.mydesk = checkin;
	}
	
	/**
	 * put method 
	 * finds an appropriate Unchecked in passenger and add to the array list
	 * @throws Exception 
	 * @throws FlightException 
	 */
	
	public synchronized void put() throws FlightException, Exception {
		while(!passengerReady) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		passengerReady = true;
		PassengerWithBcode queuePassenger = mydesk.getBookingCollection().getPassengerNotCheckedIn();
		Flight flight = mydesk.getFlightCollection().findFlight(queuePassenger.getBookingCode());
		queuePassenger.getPassenger().addBaggage(RandomBagGenerator.getRandomBag(flight.getAllowedBaggageWeightPerPassenger(), (int)flight.getAllowedBaggageVolumePerPassenger(), illegalbagchance));
		
		this.list.add(queuePassenger);
		notifyAll();
		
		// log event
		Date currentTime = fakeTime.getCurrentTime();
		String name = queuePassenger.getPassenger().getFirstName() + " " + queuePassenger.getPassenger().getLastName();
		LoggingSingleton logger = LoggingSingleton.getInstance();
		logger.addLog(fakeTime.getCurrentTime().getTime(), name + " added to Checkin queue.");
	}

	/**
	 * get method 
	 * passes the current list to the CheckinDesk
	 * @throws BookingException 
	 * @throws FlightException 
	 */
	public synchronized void getPassengerForCheckIn() throws FlightException, BookingException {
		while(passengerReady) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		passengerReady = false;
		notifyAll(); 
		mydesk.doCheckIn(list.get(0).getBookingCode(),list.get(0).getPassenger(),list.get(0).getPassenger().getBaggage());
		list.clear();
		
	}
	
	/**
	 * get method 
	 * passes the current state of the list
	 */
	public ArrayList<PassengerWithBcode> getList() {
		return list;
	}
}
