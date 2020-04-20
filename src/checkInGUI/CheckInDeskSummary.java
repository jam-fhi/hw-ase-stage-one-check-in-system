package checkInGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CheckInDesk;

/**
 * 
 *
 */
public class CheckInDeskSummary extends JPanel {

	/**
	 * add new JPanel called desks
	 */
	private static final long serialVersionUID = 1L;
	private JPanel desks = new JPanel();

	/**
	 * adding in labels and positioning them using borderlayout using the iterator
	 * to iterate through the CheckInDesks and while there is a checkindesk, return
	 * the next checkindesk in the iteration and add new CheckInInformation add the
	 * desks to the south of the panel
	 * 
	 * @param allDesks to iterate through the ArrayList of CheckInDesks
	 */
	public CheckInDeskSummary(ArrayList<CheckInDesk> allDesks) {

		this.setLayout(new BorderLayout());
		this.add(new JLabel("Check In Desks"), BorderLayout.NORTH);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		desks.setLayout(new GridLayout(1, allDesks.size() + 1));

		Iterator<CheckInDesk> checkInDeskIt = allDesks.iterator();
		int index = 0;
		while (checkInDeskIt.hasNext()) {
			CheckInDesk aDesk = checkInDeskIt.next();
			desks.add(new CheckInInformation(aDesk.getFlightCode(), aDesk.getBookingCode(), aDesk.getPassengerName(),
					String.valueOf(aDesk.getBaggageWeight()), aDesk.getExcessFee(), String.valueOf(index),
					aDesk.isClosestatus(), aDesk.getCheckInStatus()));
			index++;
		}
		this.add(desks, BorderLayout.SOUTH);
	}

	/**
	 * Method that checks while there the number of desk components is more than 0
	 * and if the name of the component is equal to checkindesk it will equal 0 and
	 * set the button to close
	 * 
	 * @param e add ActionListener
	 */
	public void setDeskStatusActionListener(ActionListener e) {
		int compCounter = 0;
		while (compCounter < desks.getComponentCount()) {
			if (desks.getComponent(compCounter).getName().compareTo("checkindesk") == 0) {
				((CheckInInformation) desks.getComponent(compCounter)).setCloseButtonAction(e);
			}
			compCounter++;
		}
	}
}
