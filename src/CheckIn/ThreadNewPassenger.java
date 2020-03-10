package CheckIn;

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
	private Passenger newpassenger;
	private CheckIn mydesk;
	private Flight flight;
	private double illegalbagchance;
	private boolean passengerReady;
	private PassengerWithBcode AddInfo;
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
		newpassenger = mydesk.getBookingCollection().getPassengerNotCheckedIn();
		Flight flight = mydesk.getFlightCollection().findFlight(mydesk.getBookingCollection().getBookingCode(newpassenger));
		newpassenger.addBaggage(RandomBagGenerator.getRandomBag(flight.getAllowedBaggageWeightPerPassenger(), flight.getAllowedBaggageVolumePerPassenger(), illegalbagchance));
		AddInfo = new PassengerWithBcode((mydesk.getBookingCollection().getBookingCode(newpassenger)), newpassenger);
		
		this.list.add(AddInfo);
		notifyAll();
	}

	/**
	 * get method 
	 * passes the current list to the CheckinDesk
	 */
	public synchronized void getPassengerForCheckIn() {
		while(passengerReady) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		passengerReady = false;
		notifyAll(); 
		mydesk.AddtoQueue(list);
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
