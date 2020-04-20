package checkInGUI;

/**
 * Import packages that are used to make our User Interface work.
 */
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class displays the flight information.
 * @author jamiehill
 */
class FlightInformation extends JPanel {
	
	/**
	 * Required by Java for compatibility.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * JLabels for flight code, flight status, 
	 * destination, and flight capacity.
	 */
	private JLabel flightCodeLbl = new JLabel();
	private JLabel flightStatus = new JLabel();
	private JLabel destinationLbl = new JLabel();
	private JLabel flightCapacity = new JLabel();
	
	/**
	 * Constructor 
	 * Creates the flight information object
	 * 
	 * @param flightCode  - the flight code to display
	 * @param destination - destination airport
	 * @param status      - current status of the flight
	 * @param bookings    - number of bookings
	 * @param capacity    - total flight passenger capacity
	 */
	public FlightInformation(String flightCode, String destination, String status, int bookings, int capacity) {
		/**
		 * Create grid layout with 4 columns and 1 row.
		 */
		this.setLayout(new GridLayout(1, 4));
		
		/**
		 * Add labels to JPanel.
		 */
		this.add(flightCodeLbl);
		this.add(destinationLbl);
		this.add(flightStatus);
		this.add(flightCapacity);

		/**
		 * Pass information to display.
		 */
		this.updateFlightInformation(flightCode, destination, status, bookings, capacity);

		/**
		 * Makes sure the information is visible.
		 */
		this.setVisible(true);
	}
	
	/**
	 * updateFlightInformation 
	 * Set flight information text on JLabels.
	 * 
	 * @param flightCode
	 * @param destination
	 * @param status
	 * @param bookings
	 * @param capacity
	 */
	public void updateFlightInformation(String flightCode, String destination, String status, int bookings, int capacity) {
		flightCodeLbl.setText(flightCode);
		flightStatus.setText(status);
		destinationLbl.setText(destination);
		flightCapacity.setText(bookings + " / " + capacity);
	}
}