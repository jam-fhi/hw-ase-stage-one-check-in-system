package checkInView;

/**
 * Import UI packages to display Check In Information
 */
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Import our Check In Desk class
 */
import checkInModel.CheckInDesk;

/**
 * CheckInInformation
 * Displays information on a single check in desk.
 * @author amymcfarland
 */
public class CheckInInformation extends JPanel {

	/**
	 * Required by Java for compatibility.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Check In Desk information components.
	 */
	private JLabel flightCode = new JLabel();
	private JLabel bookingCode = new JLabel();
	private JLabel passengerName = new JLabel();
	private JLabel bagWeight = new JLabel();
	private JLabel excessFee = new JLabel();
	
	/**
	 * Check In Desk ID number.
	 */
	private int myId = 0;
	
	/**
	 * CheckInInformation
	 * Creating the layout using GridLayout with 6 rows and 1 column.
	 * @param CheckInDesk
	 */
	public CheckInInformation(CheckInDesk aDesk) {

		/**
		 * Set up the Check In Information layout.
		 */
		this.setLayout(new GridLayout(6, 1));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/**
		 * Add the Check In Information display components.
		 */
		this.add(flightCode);
		this.add(bookingCode);
		this.add(passengerName);
		this.add(bagWeight);
		this.add(excessFee);
		this.setVisible(true);
		
		/** 
		 * Set the name of the component so
		 * the CheckInDeskSummary component 
		 * can use this as a filter to update
		 * check in desks.
		 */
		this.setName("checkindesk");
		
		/**
		 * Store the ID number.
		 */
		myId = aDesk.getCheckInDeskNumber();
		
		/**
		 * Update the information display.
		 */
		updateCheckInDesk(aDesk);
	}

	/**
	 * getDeskInfoNumber
	 * Returns the Id number of this check in desk.
	 * @return
	 */
	public int getDeskInfoNumber() {
		return myId;
	}

	/**
	 * updateCheckInDesk
	 * Updates the display of information on a check in desk.
	 * @param aDesk
	 */
	public void updateCheckInDesk(CheckInDesk aDesk) {
		/**
		 * Update the display of check in desk information.
		 */
		flightCode.setText(aDesk.getFlightCode());
		bookingCode.setText(aDesk.getBookingCode());
		passengerName.setText(aDesk.getPassengerName());
		bagWeight.setText(aDesk.getBaggageWeight());
		excessFee.setText(aDesk.getExcessFee());
	}
}
