package checkInGUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class FlightInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel flightCodeLbl = new JLabel();
	private JLabel flightStatus = new JLabel();
	private JLabel destinationLbl = new JLabel();
	private JLabel flightCapacity = new JLabel();
	
	public FlightInformation(String flightCode, String destination, String status, int bookings, int capacity) {
		
		this.setLayout(new GridLayout(1, 4));
		
		this.add(flightCodeLbl);
		this.add(destinationLbl);
		this.add(flightStatus);
		this.add(flightCapacity);
		this.updateFlightInformation(flightCode, destination, status, bookings, capacity);
		this.setVisible(true);
	}
	
	public void updateFlightInformation(String flightCode, String destination, String status, int bookings, int capacity) {
		flightCodeLbl.setText(flightCode);
		flightStatus.setText(status);
		destinationLbl.setText(destination);
		flightCapacity.setText(bookings + " / " + capacity);
	}
}