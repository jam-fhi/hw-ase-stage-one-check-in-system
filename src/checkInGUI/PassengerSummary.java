package checkInGUI;

/**
 * Import UI display component packages
 */
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import checkInModel.Booking;

/**
 * Import data structure packages
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * Passenger Summary
 * Displays a list of passengers in the a check in queue.
 * @author Amy McFarland
 *
 */
public class PassengerSummary extends JPanel{
	
	/**
	 * Default JAVA required for compatibility.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of the queue to display.
	 */
	private String queueName = "queue";

	/**
	 * List of passengers in the queue. Uses a default list model to
	 * store passenger information to display in the list component.
	 */
	private JList<String> passengerQueue = new JList<String>();
	private JScrollPane queueScroll;
	private DefaultListModel<String> queueModel = new DefaultListModel<String>();
	
	/**
	 * Displays an information list of how many passengers are in the queue.
	 */
	private JLabel passengerDetails = new JLabel(queueName + " queue has 0 passengers.");
	
	/**
	 * PassengerSummary
	 * Constructor, sets up the list of passengers in the queue.
	 * @param name of the queue to display on the form.
	 */
	public PassengerSummary(String name) {
		/**
		 * Set up boarder layout for the JPanel.
		 */
		this.setLayout(new BorderLayout());
		
		/**
		 * Set queue name.
		 */
		queueName = name;

		/**
		 * Setup a JList with a scroll pane to display passengers.
		 */
		passengerQueue = new JList<String>(queueModel);
		queueScroll = new JScrollPane(passengerQueue);
		queueScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		/**
		 * Add queue title and queue scroll pane/list and make sure its visible.
		 */
		this.add(passengerDetails, BorderLayout.NORTH);
		this.add(queueScroll, BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	/**
	 * updatePassengerList
	 * Adds or remove passengers in the queue, also updates the 
	 * information label of how many passengers there are.
	 * @param bookingCode
	 * @param passengerName
	 */
	public void updatePassengerList(ArrayList<Booking> displayBookings) {
		addPassengers(displayBookings);
		removePassengers(displayBookings);
		passengerDetails.setText(queueName + " queue has " + queueModel.getSize() + " passengers waiting in the queue.");
	}

	/**
	 * addPassengers
	 * Find any passengers that are in the Booking array list,
	 * but not in the JList and add them to the JList.
	 * @param displayBookings all the bookings in the queue.
	 */
	private void addPassengers(ArrayList<Booking> displayBookings) {
		/**
		 * Create an iterator to loop through the bookings to be displayed.
		 */
		Iterator<Booking> displayBookingsIt = displayBookings.iterator();
		while (displayBookingsIt.hasNext()) {
			Booking aBooking = displayBookingsIt.next();
			/**
			 * Create the entry to display in the JList
			 */
			String queueEntry = aBooking.getBookingCode() + " " + aBooking.getPassenger().getFirstName() + " " + aBooking.getPassenger().getLastName();
			/**
			 * Loop through the entries in the queue model to find
			 * bookings that do not exist in the JList.
			 */
			int count = 0;
			boolean found = false;
			while (count < queueModel.getSize() && found == false) {
				if (queueModel.get(count).compareTo(queueEntry) == 0) {
					found = true;
				}
				count++;
			}
			if (found == false) {
				/**
				 * When a passenger does not exist in the list, add them.
				 */
				queueModel.addElement(queueEntry);
			}

		}
	}

	/**
	 * removePassengers
	 * Removes passengers from the queue when they
	 * are no longer in the Booking Array List.
	 * @param displayBookings
	 */
	private void removePassengers(ArrayList<Booking> displayBookings) {
		int count = 0;
		int found = -1;
		/**
		 * Loop through all passengers in the queue model, 
		 * or until the passenger is found.
		 */
		while (count < queueModel.getSize() && found == -1) {
			/**
			 * Iterate through the bookings object to find corresponding passengers.
			 */
			Iterator<Booking> displayBookingsIt = displayBookings.iterator();
			while (displayBookingsIt.hasNext()) {
				Booking aBooking = displayBookingsIt.next();
				/**
				 * Create the passenger queue entry from the booking object.
				 */
				String queueEntry = aBooking.getBookingCode() + " " + aBooking.getPassenger().getFirstName() + " " + aBooking.getPassenger().getLastName();
				if (queueModel.get(count).compareTo(queueEntry) == 0) {
					found = count;
				}
			}

			/**
			 * If the passenger in the queue model is
			 * not found in the booking array list,
			 * then remove the passenger from the queue model.
			 */
			if (found == -1) {
				queueModel.remove(count);
			}
			count++;
		}
	}
}
