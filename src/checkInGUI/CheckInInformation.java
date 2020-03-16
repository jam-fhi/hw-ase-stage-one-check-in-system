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
	public CheckInInformation(String passengerFirstName, String passengerLastName, double baggageweight, double excessfee) {
		
		this.setLayout(new GridLayout(1, 2));
		JLabel passengerFirstNameLbl = new JLabel();
		passengerFirstNameLbl.setText(passengerFirstName);
		
		JLabel passengerLastNameLbl = new JLabel();
		passengerLastNameLbl.setText(passengerLastName);

		
		JLabel bagbaggageWeightLbl = new JLabel();
		String baggagefee = String.valueOf(baggageweight);
		bagbaggageWeightLbl.setText(baggagefee);
		
		JLabel excessfeeLbl = new JLabel();
		String excessfee1 = String.valueOf(excessfee);
		excessfeeLbl.setText(excessfee1);
		
		
		
		this.add(passengerFirstNameLbl);
		this.add(passengerLastNameLbl);
		this.add(bagbaggageWeightLbl);
		this.add(excessfeeLbl);
		this.setVisible(true);
	}
	
}
