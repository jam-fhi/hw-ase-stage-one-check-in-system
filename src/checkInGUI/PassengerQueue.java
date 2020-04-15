package checkInGUI;

import java.awt.BorderLayout;
import java.util.ArrayList;

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
	PassengerSummary passengers;
	
	/**
	 * PassengerQueue
	 * Constructor, takes a collection of passengers in
	 * a queue and displays them.
	 * @param allBookings
	 */
	public PassengerQueue(ArrayList<Booking> passengerQueue, String queueName) {
		passengers = new PassengerSummary(queueName);
		updatePassengerQueue(passengerQueue);
		this.add(passengers, BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	public void updatePassengerQueue(ArrayList<Booking> passengerQueue) {
		passengers.updatePassengerList(passengerQueue);
		
	}
}

