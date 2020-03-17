package checkInGUI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import model.CheckInDesk;

public class CheckInDeskSummary extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckInDeskSummary(ArrayList<CheckInDesk> allDesks) {
		this.setLayout(new GridLayout(1, allDesks.size()));
		Iterator<CheckInDesk> checkInDeskIt = allDesks.iterator();
		while(checkInDeskIt.hasNext()) {
			CheckInDesk aDesk = checkInDeskIt.next();
			this.add(new CheckInInformation(aDesk.getFlightCode(), aDesk.getBookingCode(), aDesk.getPassengerName(), aDesk.getBaggageWeight(), aDesk.getExcessFee()));
		}
	}
}
