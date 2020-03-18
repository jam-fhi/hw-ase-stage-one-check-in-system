package checkInGUI;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author amymcfarland
 *
 */
public class CheckInInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JButton close;
	int count = 0;
	
	/**
	 * @param flightCode
	 * @param bookingCode
	 * @param passengerName
	 * @param excessfee
	 * @param baggagedimensions
	 */
	public CheckInInformation(String flightCode, String bookingCode, String passengerName, String bagWeight, String excessFee, String index, boolean closeStatus) {

		this.setLayout(new GridLayout(6, 1));
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		String statusText = "Open";
		if(closeStatus == false) {
			statusText = "Close";
		}
		close = new JButton(statusText);
		close.setName(index);
		this.add(close);
		this.add(new JLabel(flightCode));
		this.add(new JLabel(bookingCode));
		this.add(new JLabel(passengerName));
		this.add(new JLabel(bagWeight));
		this.add(new JLabel(excessFee));
		this.setVisible(true);
		this.setName("checkindesk");
		count++;
	}

	public void setCloseButtonAction (ActionListener e) {
		close.addActionListener(e);
	}
}
