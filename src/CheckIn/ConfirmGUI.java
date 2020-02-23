package CheckIn;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfirmGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Booking confirmBooking;
	private Flight confirmFlight;
	private CheckInGUI checkInGUI;

	JTextField length, height, width, weight, excessBaggageFee;
	JButton calculateExcessCharge, close;
	JTextField result;
		
	public ConfirmGUI(Booking aBooking, Flight aFlight, CheckInGUI checkInForm) {

		confirmBooking = aBooking;
		confirmFlight = aFlight;
		this.checkInGUI = checkInForm;

		setTitle("Baggage Information ");
		// ensure program ends when window closes
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setupCenterPanel();

		// pack and set visible
		pack();
		setVisible(true);
	}

	private void setupCenterPanel() {
		height = new JTextField(15);
		width = new JTextField(15);
		length = new JTextField(15);
		weight = new JTextField(15);
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(3, 3));
		searchPanel.add(new JLabel("Enter Bag Height: "));
		searchPanel.add(height);
		
		searchPanel.add(new JLabel("Enter Bag Width : "));
		searchPanel.add(width);
		
		searchPanel.add(new JLabel("Enter Bag Length : "));
		searchPanel.add(length);
		
		searchPanel.add(new JLabel("Enter Bag Weight : "));
		searchPanel.add(weight);
		
		calculateExcessCharge = new JButton("Calculate baggage charge");

		// Set up the area where the results will be displayed.
		excessBaggageFee = new JTextField(25);
		excessBaggageFee.setEditable(false);
		
		result = new JTextField(25);
		result.setEditable(false);

		searchPanel.add(calculateExcessCharge);
		searchPanel.add(excessBaggageFee);
		// specify action when button is pressed
		calculateExcessCharge.addActionListener(this);
		// Set up the area where the results will be displayed.

		// add south panel to the content pane
		this.add(searchPanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == calculateExcessCharge) {
			// get search text and search booking list
			// setting result text if found
			if(isBagWidthValid() && isBagLengthValid() && isBagHeightValid() && isBagWeightValid()) {		
				double weightSearch = Integer.parseInt(weight.getText().trim());
				int widthSearch = Integer.parseInt(width.getText().trim());
				int heightSearch = Integer.parseInt(height.getText().trim());
				int lengthSearch = Integer.parseInt(length.getText().trim());
				Bag baggage = new Bag(widthSearch,lengthSearch,heightSearch, weightSearch);
				confirmBooking.getPassenger().addBaggage(baggage);
				confirmBooking.getPassenger().setCheckIn();
				double allowedBaggageWeight = confirmFlight.getAllowedBaggageWeightPerPassenger();
				double excessCharge = confirmFlight.getExcessCharge();
				double excessWeight = weightSearch - allowedBaggageWeight;
				if(excessWeight >  0) {
					this.confirmBooking.getPassenger().getBaggage().setExcessCharge(excessWeight, excessCharge);
					double charge = this.confirmBooking.getPassenger().getBaggage().getExcessCharge();
					result.setText("Your excess baggage fee is: " + charge);
				}
				this.dispose();
				checkInGUI.setVisible(true);
			}
		}
	}	

	private boolean isBagWidthValid() {
		int widthValue = Integer.parseInt(width.getText().trim());

		if (widthValue < 0 || widthValue > 30) {
			result.setText("Please enter a valid width value");
			return false;
		}

		if (widthValue ==0 || widthValue ==0) {
			result.setText("Please enter a value");
			return false;
		}

		if (widthValue != (int)widthValue) {
			result.setText("Invalid entry. Please input a number");
			return false;
		}
		return true;
	}

	private boolean isBagLengthValid() {
		int lengthValue = Integer.parseInt(length.getText().trim());

		if (lengthValue < 0 || lengthValue > 30) {
			result.setText("Please enter a valid length value");
			return false;
		}
	
		if (lengthValue ==0 || lengthValue ==0) {
			result.setText("Please enter a value");
			return false;
		}

		if (lengthValue != (int)lengthValue) {
			result.setText("Invalid entry. Please input a number");
			return false;
		}
			
		return true;
	}

	private boolean isBagHeightValid() {
		int heightValue = Integer.parseInt(height.getText().trim());

		if (heightValue < 0 || heightValue > 30) {
			result.setText("Please enter a valid height value");
			return false;
		}
		
		if (heightValue ==0 || heightValue ==0) {
			result.setText("Please enter a value");
			return false;
		}
			
		if (heightValue != (int)heightValue) {
			result.setText("Invalid entry. Please input a number");
			return false;
		}	
		return true;
	}

	private boolean isBagWeightValid() {
		int weightValue = Integer.parseInt(weight.getText().trim());

		if (weightValue < 0 || weightValue > 30) {
			result.setText("Please enter a valid weight value");
			return false;
		}

		if (weightValue ==0 || weightValue ==0) {
			result.setText("Please enter a value");
			return false;
		}

		if (weightValue != (double)weightValue) {
			result.setText("Invalid entry. Please input a number");
			return false;
		}
		return true;
	}		
}