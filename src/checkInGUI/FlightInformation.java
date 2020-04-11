package checkInGUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class FlightInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel flightCodeLbl = new JLabel();
	private JLabel flightStatus = new JLabel();
	private JLabel destinationLbl = new JLabel();
	
	public FlightInformation(String flightCode, String destination, String status) {
		
		this.setLayout(new GridLayout(1, 3));
		
		this.add(flightCodeLbl);
		this.add(destinationLbl);
		this.add(flightStatus);
		this.updateFlightInformation(flightCode, destination, status);
		this.setVisible(true);
	}
	
	public void updateFlightInformation(String flightCode, String destination, String status) {
		flightCodeLbl.setText(flightCode);
		flightStatus.setText(status);
		destinationLbl.setText(destination);
	}
}