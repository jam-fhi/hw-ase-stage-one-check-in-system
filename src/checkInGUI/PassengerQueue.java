package checkInGUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import CheckIn.Booking;

public class PassengerQueue extends JPanel {

	/**
	 * Default required for eclipse/JAVA compatibility
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Element that displays the passenger queue.
	 */
	PassengerSummary passengers = new PassengerSummary();
	
	/**
	 * PassengerQueue
	 * Constructor, takes a collection of passengers in
	 * a queue and displays them.
	 * @param allBookings
	 */
	public PassengerQueue(ArrayList<Booking> passengerQueue) {
		Iterator<Booking> queueIt = passengerQueue.iterator();
		while(queueIt.hasNext()) {
			Booking aBooking = queueIt.next();
			passengers.addPassengerList(aBooking.getBookingCode(), aBooking.getPassenger().getFirstName() + " " + aBooking.getPassenger().getLastName());		    
		}
		this.add(passengers, BorderLayout.NORTH);
		this.setVisible(true);
	}
}
