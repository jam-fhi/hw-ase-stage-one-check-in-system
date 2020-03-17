package checkInGUI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author amymcfarland
 *
 */
public class CheckInInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param flightCode
	 * @param bookingCode
	 * @param passengerName
	 * @param excessfee
	 * @param baggagedimensions
	 */
	public CheckInInformation(String passengerFirstName, String passengerLastName) {

		this.setLayout(new GridLayout(1, 2));
		JLabel passengerFirstNameLbl = new JLabel();
		passengerFirstNameLbl.setText(passengerFirstName);

		JLabel passengerLastNameLbl = new JLabel();
		passengerLastNameLbl.setText(passengerLastName);

		this.add(passengerFirstNameLbl);
		this.add(passengerLastNameLbl);

		this.setVisible(true);
	}
}
