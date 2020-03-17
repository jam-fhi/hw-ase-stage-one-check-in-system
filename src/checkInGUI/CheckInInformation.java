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
	public CheckInInformation(String flightCode, String bookingCode, String passengerName, String bagWeight, String excessFee) {

		this.setLayout(new GridLayout(5, 1));
		
		this.add(new JLabel(flightCode));
		this.add(new JLabel(bookingCode));
		this.add(new JLabel(passengerName));
		this.add(new JLabel(bagWeight));
		this.add(new JLabel(excessFee));

		this.setVisible(true);
	}
}
