package checkInGUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

class FlightInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public FlightInformation(String flightCode, String destination) {
		
		JLabel flightCodeLbl = new JLabel();
		flightCodeLbl.setText(flightCode);
		

		JLabel destinationLbl = new JLabel();
		destinationLbl.setText(destination);
		
		this.add(flightCodeLbl);
		this.add(destinationLbl);
		
		setLayout(new BorderLayout());
		setVisible(true);
	}
}