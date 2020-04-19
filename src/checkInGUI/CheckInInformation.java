package checkInGUI;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CheckInDesk;

/**
 * @author amymcfarland
 *
 */
public class CheckInInformation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton close = new JButton();
	private JLabel flightCode = new JLabel();
	private JLabel bookingCode = new JLabel();;
	private JLabel passengerName = new JLabel();;
	private JLabel bagWeight = new JLabel();;
	private JLabel excessFee = new JLabel();;
	private int myId = 0;
	
	/**
	 * @param flightCode
	 * @param bookingCode
	 * @param passengerName
	 * @param excessfee
	 * @param baggagedimensions
	 */
	public CheckInInformation(CheckInDesk aDesk) {

		this.setLayout(new GridLayout(6, 1));
		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.add(flightCode);
		this.add(bookingCode);
		this.add(passengerName);
		this.add(bagWeight);
		this.add(excessFee);
		this.add(close);
		this.setVisible(true);
		this.setName("checkindesk");
		myId = aDesk.getCheckInDeskNumber();
		updateCheckInDesk(aDesk);
	}

	public int getDeskInfoNumber() {
		return myId;
	}

	public void updateCheckInDesk(CheckInDesk aDesk) {
		flightCode.setText(aDesk.getFlightCode());
		bookingCode.setText(aDesk.getBookingCode());
		passengerName.setText(aDesk.getPassengerName());
		bagWeight.setText(aDesk.getBaggageWeight());
		excessFee.setText(aDesk.getExcessFee());
		String statusText = aDesk.isClosed() ? "Open" : "Close";
		close.setText(statusText);
	}

	public void setCloseButtonAction (ActionListener e) {
		close.addActionListener(e);
	}
}
