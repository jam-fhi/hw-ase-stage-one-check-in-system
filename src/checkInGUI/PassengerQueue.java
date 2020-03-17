package checkInGUI;

import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JPanel;
import CheckIn.Booking;
import CheckIn.BookingCollection;

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
	public PassengerQueue(BookingCollection allBookings) {
		for(Map.Entry<String, Booking> aBooking: allBookings.getBookingCollection().entrySet()) {
			passengers.addPassengerList(aBooking.getValue().getBookingCode(), aBooking.getValue().getPassenger().getFirstName() + " " + aBooking.getValue().getPassenger().getLastName());		    
		}
		
		this.add(passengers, BorderLayout.NORTH);
		this.setVisible(true);
	}
}
