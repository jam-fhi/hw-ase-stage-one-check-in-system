package checkInGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class FlightInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public FlightInformation(String flightCode, String destination, String status) {
		
		this.setLayout(new BorderLayout());
		JLabel flightCodeLbl = new JLabel();
		flightCodeLbl.setText(flightCode);
		JLabel flightstatus = new JLabel();
		flightCodeLbl.setText(status);

		JLabel destinationLbl = new JLabel();
		destinationLbl.setText(destination);
		
		this.add(flightCodeLbl);
		this.add(destinationLbl);
		this.add(flightstatus);

		this.setVisible(true);
	}
}