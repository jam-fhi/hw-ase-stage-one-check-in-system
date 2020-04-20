package checkInView;

/**
 * Import packages to display UI components
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Import packages to manipulate data structures.
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Import our Check In Desk class
 */
import checkInModel.CheckInDesk;

/**
 * CheckInDeskSummary
 * Parent component for individual check in information
 * display, which can add/remove and update check in desks.
 */
public class CheckInDeskSummary extends JPanel {

	/**
	 * Required by Java for compatibility.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Panel to hold check in desks.
	 */
	private JPanel desks = new JPanel();

	/**
	 * CheckInDeskSummary
	 * Constructor to create container that holds individual check in information
	 * JPanels.
	 * 
	 * @param allDesks to iterate through the ArrayList of CheckInDesks
	 */
	public CheckInDeskSummary(ArrayList<CheckInDesk> allDesks) {
		/**
		 * Set up panel layout.
		 */
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/**
		 * Add panel title label and desks container.
		 */
		this.add(new JLabel("Check In Desks"), BorderLayout.NORTH);
		this.add(desks, BorderLayout.SOUTH);
		
		/**
		 * Update the check in desk information display.
		 */
		updateCheckInDesks(allDesks);
	}

	/**
	 * updateCheckInDesks
	 * Updates the information displayed on check in desks
	 * and either adds or removes check in desks depending
	 * on wither or not they exist in the ArrayList or not.
	 * @param allDesks
	 */
	public void updateCheckInDesks(ArrayList<CheckInDesk> allDesks) {
		/**
		 * Update layout for any additional checking desks in columns.
		 */
		desks.setLayout(new GridLayout(1, allDesks.size() + 1));
		/**
		 * Remove any desks that are displayed but no longer in the allDesks collection.
		 */
		removeUnusedDesks(allDesks);
		/**
		 * Loop through all the desks and add any that do not yet
		 * exist on the view or update ones that do exist.
		 */
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

	/**
	 * removeUnusedDesks
	 * Removes any desks on the view that no 
	 * longer exist in the allDesks ArrayList.
	 * @param allDesks
	 */
	private void removeUnusedDesks(ArrayList<CheckInDesk> allDesks) {
		/**
		 * Loop through all desk components on the view.
		 */
		if(desks.getComponents().length > 0) {
			int panelCount = 0;
			while(panelCount < desks.getComponents().length) {
				/**
				 * Find that view component in the allDesks ArrayList
				 */
				Iterator<CheckInDesk> desksIt = allDesks.iterator();
				boolean found = false;
				while(desksIt.hasNext()) {
					CheckInDesk aDesk = desksIt.next();
					if(aDesk.getCheckInDeskNumber() == ((CheckInInformation)desks.getComponent(panelCount)).getDeskInfoNumber()) {
						found = true;
					}
				}
				/**
				 * If not found, remove the component from the view.
				 */
				if(found == false) {
					desks.remove(panelCount);
				}
			panelCount++;
			}
		}
	}
}
