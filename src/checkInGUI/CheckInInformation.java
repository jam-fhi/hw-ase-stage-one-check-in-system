package checkInGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author amymcfarland
 *
 */
public class CheckInInformation extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	JButton close;
	
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
		close = new JButton();
		
		if(closeStatus == true) {
			close.setText("Open");;
		}else {
			close.setText("Close");
		}
		
		close.setName(index);
		this.add(close);
		this.add(new JLabel(flightCode));
		this.add(new JLabel(bookingCode));
		this.add(new JLabel(passengerName));
		this.add(new JLabel(bagWeight));
		this.add(new JLabel(excessFee));
		this.setVisible(true);
		this.setName("checkindesk");
		
	}


	public void setCloseButtonAction (ActionListener e) {
		close.addActionListener(e);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
