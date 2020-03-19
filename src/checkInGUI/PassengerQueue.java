package checkInGUI;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
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
	public PassengerQueue(HashMap<String,Booking> passengerQueue) {
		for(Map.Entry<String, Booking> aBooking: passengerQueue.entrySet()) {
			Booking addBooking = aBooking.getValue();
			passengers.addPassengerList(addBooking.getBookingCode(), addBooking.getPassenger().getFirstName() + " " + addBooking.getPassenger().getLastName());
		}
		this.add(passengers, BorderLayout.NORTH);
		this.setVisible(true);
	}
}
