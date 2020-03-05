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
	private CheckinDesk mydesk;
	private Flight flight;
	private double illegalbagchance;
	
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
}
