package checkInGUI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

class FlightInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public FlightInformation(String flightCode, String destination) {
		
		this.setLayout(new GridLayout(2, 1));
		JLabel flightCodeLbl = new JLabel();
		flightCodeLbl.setText(flightCode);
		

		JLabel destinationLbl = new JLabel();
		destinationLbl.setText(destination);
		
		this.add(flightCodeLbl);
		this.add(destinationLbl);

		this.setVisible(true);
	}
}