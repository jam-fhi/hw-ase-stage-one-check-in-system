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
		this.add(desks, BorderLayout.SOUTH);
	}

	public void updateCheckInDesks(ArrayList<CheckInDesk> allDesks) {
		desks.setLayout(new GridLayout(1, allDesks.size() + 1));
		removeUnusedDesks(allDesks);
		Iterator<CheckInDesk> checkInDeskIt = allDesks.iterator();
		while(checkInDeskIt.hasNext()) {
			CheckInDesk aDesk = checkInDeskIt.next();
			CheckInInformation deskPanel = getCheckInDeskPanel(aDesk.getCheckInDeskNumber());
			if(deskPanel != null) {
				deskPanel.updateCheckInDesk(aDesk);
			} else {
				desks.add(new CheckInInformation(aDesk));
			}
		}
	}

	/**
	 * getCheckInDeskPanel 
	 * Finds a Check In Desk JPanel 
	 * using the get desk number method.
	 * 
	 * @param number
	 * @return CheckInInformation object
	 */
	private CheckInInformation getCheckInDeskPanel(int number) {

		if(desks.getComponents().length > 0) {
			int panelCount = 0;
			while(panelCount < desks.getComponents().length) {
				if(((CheckInInformation)desks.getComponent(panelCount)).getDeskInfoNumber() == number) {
					return (CheckInInformation)desks.getComponent(panelCount);
				}
				panelCount++;
			}
		}
		/**
		 * If nothing found, return null.
		 */
		return null;
	}

	private void removeUnusedDesks(ArrayList<CheckInDesk> allDesks) {
		if(desks.getComponents().length > 0) {
			int panelCount = 0;
			while(panelCount < desks.getComponents().length) {
				Iterator<CheckInDesk> desksIt = allDesks.iterator();
				boolean found = false;
				while(desksIt.hasNext()) {
					CheckInDesk aDesk = desksIt.next();
					if(aDesk.getCheckInDeskNumber() == ((CheckInInformation)desks.getComponent(panelCount)).getDeskInfoNumber()) {
						found = true;
					}
				}
				if(found == false) {
					desks.remove(panelCount);
				}
			panelCount++;
			}
		}
	}

	public void setDeskStatusActionListener (ActionListener e) {
		int compCounter = 0;
		while (compCounter < desks.getComponentCount()) {
			if (desks.getComponent(compCounter).getName().compareTo("checkindesk") == 0) {
				((CheckInInformation) desks.getComponent(compCounter)).setCloseButtonAction(e);
			}
			compCounter++;
		}
	}
}
