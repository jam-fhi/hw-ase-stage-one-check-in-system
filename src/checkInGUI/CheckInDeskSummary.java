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

public class CheckInDeskSummary extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel desks = new JPanel();
	
	
	public CheckInDeskSummary(ArrayList<CheckInDesk> allDesks) {
		
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Check In Desks"), BorderLayout.NORTH);
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		desks.setLayout(new GridLayout(1, allDesks.size() + 1));
		
		Iterator<CheckInDesk> checkInDeskIt = allDesks.iterator();
		int index = 0;
		while(checkInDeskIt.hasNext()) {
			CheckInDesk aDesk = checkInDeskIt.next();
			desks.add(new CheckInInformation(aDesk.getFlightCode(), aDesk.getBookingCode(), aDesk.getPassengerName(), String.valueOf(aDesk.getBaggageWeight()), aDesk.getExcessFee(), String.valueOf(index), aDesk.isClosestatus(), aDesk.getCheckInStatus()));
			index ++;	
		}
		this.add(desks, BorderLayout.SOUTH);
	}

	public void setDeskStatusActionListener (ActionListener e) {
		int compCounter = 0;
		while(compCounter < desks.getComponentCount()) {
			if(desks.getComponent(compCounter).getName().compareTo("checkindesk") == 0) {
				((CheckInInformation)desks.getComponent(compCounter)).setCloseButtonAction(e);
			}
			compCounter++;
		}
	}
}
