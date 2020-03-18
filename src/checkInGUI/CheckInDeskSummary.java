package checkInGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CheckInDesk;

public class CheckInDeskSummary extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckInDeskSummary(ArrayList<CheckInDesk> allDesks) {
		
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Check In Desks"), BorderLayout.NORTH);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JPanel desks = new JPanel();
		desks.setLayout(new GridLayout(1, allDesks.size() + 1));
		
		Iterator<CheckInDesk> checkInDeskIt = allDesks.iterator();
		while(checkInDeskIt.hasNext()) {
			CheckInDesk aDesk = checkInDeskIt.next();
			desks.add(new CheckInInformation(aDesk.getFlightCode(), aDesk.getBookingCode(), aDesk.getPassengerName(), String.valueOf(aDesk.getBaggageWeight()), aDesk.getExcessFee()));
		}
		
		this.add(desks, BorderLayout.SOUTH);
	}
}
