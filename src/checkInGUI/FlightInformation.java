package checkInGUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class FlightInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public FlightInformation(String flightCode, String destination, String status) {
		
		this.setLayout(new GridLayout(1, 3));
		
		JLabel flightCodeLbl = new JLabel();
		flightCodeLbl.setText(flightCode);
		
		JLabel flightStatus = new JLabel();
		flightStatus.setText(status);

		JLabel destinationLbl = new JLabel();
		destinationLbl.setText(destination);
		
		this.add(flightCodeLbl);
		this.add(destinationLbl);
		this.add(flightStatus);

		this.setVisible(true);
	}
}