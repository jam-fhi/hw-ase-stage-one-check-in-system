package checkInGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import CheckIn.Booking;

/**
 * 
 * Passenger Summary
 * Displays a list of passengers in the check in queue.
 * @author Amy McFarland
 *
 */
public class PassengerSummary extends JPanel{
	
	/**
	 * Default JAVA required for compatibility.
	 */
	private static final long serialVersionUID = 1L;

	private String queueName = "queue";

	/**
	 * List of passengers in the queue. Uses a default list model to
	 * store passenger information to display in the list component.
	 */
	private JList<String> passengerQueue = new JList<String>();
	private DefaultListModel<String> queueModel = new DefaultListModel<String>();
	
	/**
	 * Displays an information list of how many passengers are in the queue.
	 */
	private JLabel passengerDetails = new JLabel(queueName + " queue has 0 passengers.");
	
	/**
	 * PassengerSummary
	 * Constructor, sets up the list of passengers in the queue.
	 */
	public PassengerSummary(String name) {
		this.setLayout(new BorderLayout());
		passengerQueue = new JList<String>(queueModel);
		JScrollPane sp = new JScrollPane(passengerQueue);
		passengerQueue.setPreferredSize(new Dimension(200, 200));
		this.add(passengerDetails, BorderLayout.NORTH);
		this.add(sp, BorderLayout.SOUTH);
		this.setVisible(true);
		queueName = name;
	}
	
	/**
	 * addPassengerList
	 * Adds a passenger to the list of passengers in the queue,
	 * also updates the information label of how many passengers
	 * there are.
	 * @param bookingCode
	 * @param passengerName
	 */
	public void updatePassengerList(ArrayList<Booking> displayBookings) {
		addPassengers(displayBookings);
		removePassengers(displayBookings);
		passengerDetails
				.setText(queueName + " queue has " + queueModel.getSize() + " passengers waiting in the queue.");
	}

	private void addPassengers(ArrayList<Booking> displayBookings) {
		Iterator<Booking> displayBookingsIt = displayBookings.iterator();
		while (displayBookingsIt.hasNext()) {
			Booking aBooking = displayBookingsIt.next();
			String queueEntry = aBooking.getBookingCode() + " " + aBooking.getPassenger().getFirstName() + " "
					+ aBooking.getPassenger().getLastName();
			int count = 0;
			boolean found = false;
			while (count < queueModel.getSize() && found == false) {
				if (queueModel.get(count).compareTo(queueEntry) == 0) {
					found = true;
				}
				count++;
			}
			if (found == false) {
				queueModel.addElement(queueEntry);
			}

		}
	}

	private void removePassengers(ArrayList<Booking> displayBookings) {
		int count = 0;
		int found = -1;
		while (count < queueModel.getSize() && found == -1) {
			Iterator<Booking> displayBookingsIt = displayBookings.iterator();

			while (displayBookingsIt.hasNext()) {
				Booking aBooking = displayBookingsIt.next();
				String queueEntry = aBooking.getBookingCode() + " " + aBooking.getPassenger().getFirstName() + " "
						+ aBooking.getPassenger().getLastName();
				if (queueModel.get(count).compareTo(queueEntry) == 0) {
					found = count;
				}

			}

			if (found == -1) {
				queueModel.remove(count);
			}

			count++;
		}

	}

}
