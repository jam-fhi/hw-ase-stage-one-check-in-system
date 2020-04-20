package checkInView;

/**
 * Import packages that are used to make our User Interface work.
 */
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * Import packages to use data structures.
 */
import java.util.ArrayList;

/**
 * Import our packages to use our model data.
 */
import checkInModel.Booking;

/**
 * This class displays a passenger queue. 
 * 
 * @author amymcfarland, jamiehill
 *
 */
public class PassengerQueue extends JPanel {

	/**
	 * Required by Java for compatibility.
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
		
		/**
		 * Creates a new passenger summary,
		 * taking in the parameter queueName.
		 */
		passengers = new PassengerSummary(queueName);
		
		/**
		 * Adds a new passenger to the passenger queue.
		 */
		updatePassengerQueue(passengerQueue);
		
		/**
		 * Adds the passenger custom JPanel to this JPanel.
		 */
		this.add(passengers, BorderLayout.NORTH);
		
		/**
		 * Makes sure the information is visible.
		 */
		this.setVisible(true);
	}
	
	/**
	 * updatePassengerQueue
	 * Passes an updated queue into the passenger 
	 * custom JPanel.
	 * 
	 * @param passengerQueue
	 */
	public void updatePassengerQueue(ArrayList<Booking> passengerQueue) {
		passengers.updatePassengerList(passengerQueue);		
	}
}