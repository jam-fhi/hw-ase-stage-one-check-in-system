package CheckIn;
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
	
	/**
	 * constructor
	 * @param double illegalbagchance
	 * @param CheckinDeck checkin
	 */
	ThreadNewPassenger(double illegal, CheckinDesk checkin){
		illegalbagchance = illegal;
		mydesk = checkin;
	}
	
	/**
	 * run method find appropriate passenger 
	 */
	
	public void run() {
		newpassenger = mydesk.getBookingCollection().getUncheckedInPassenger();
		Flight flight = mydesk.getFlightCollection().FindFlight(mydesk.getBookingCollection().getBookingCode(newpassenger));
		newpassenger.addBaggage(RandomBagGenerator.getRandomBag(flight.getAllowedBaggageWeightPerPassenger(), flight.getAllowedBaggageVolumePerPassenger(), illegalbagchance));
		mydesk.addPassengertoQueue(newpassenger);
	}
	
	private void getPassengerForCheckIn() {
		while(passengerReady) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		passengerReady = false;
		notifyAll(); // so wake up the producer
		// Needs to be integrated
		mydesk.doCheckedIn(passenger);
	}
}
