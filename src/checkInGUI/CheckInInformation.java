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
	public CheckInInformation(String flightCode, String bookingCode, String passengerName, String bagWeight, String excessFee, String index, boolean closeStatus, boolean checkInStatus) {

		this.setLayout(new GridLayout(7, 1));
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		String statusText = "Open";
		
		close = new JButton();
		close.setName(index);
		this.add(close);
		if(closeStatus == false) {
			statusText = "Close";
		this.add(new JLabel(flightCode));
		this.add(new JLabel(bookingCode));
		this.add(new JLabel(passengerName));
		this.add(new JLabel(bagWeight));
		this.add(new JLabel(String.valueOf(checkInStatus)));
		this.add(new JLabel(excessFee));
		}
		close.setText(statusText);
		this.setVisible(true);
		this.setName("checkindesk");
		
	}

	public void setCloseButtonAction (ActionListener e) {
		close.addActionListener(e);
	}
	
	
}
