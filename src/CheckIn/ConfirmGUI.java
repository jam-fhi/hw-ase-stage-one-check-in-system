package CheckIn;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConfirmGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Booking confirmBooking;
	private Flight confirmFlight;
	private BookingCollection allBookings;
	private FlightCollection allFlights;

	JTextField length, height, width, weight;
	JButton calculateExcessCharge, close;
	JLabel result;
		
	public ConfirmGUI(Booking aBooking, Flight aFlight, BookingCollection bookings, FlightCollection flights) {

		confirmBooking = aBooking;
		confirmFlight = aFlight;
		allBookings = bookings;
		allFlights = flights;
		
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
		searchPanel.setLayout(new GridLayout(5, 2));
		searchPanel.add(new JLabel("Enter Bag Height: "));
		searchPanel.add(height);
		
		searchPanel.add(new JLabel("Enter Bag Width : "));
		searchPanel.add(width);
		
		searchPanel.add(new JLabel("Enter Bag Length : "));
		searchPanel.add(length);
		
		searchPanel.add(new JLabel("Enter Bag Weight : "));
		searchPanel.add(weight);
		
		calculateExcessCharge = new JButton("Check In");

		// Set up the area where the results will be displayed.
		// excessBaggageFee = new JTextField(25);
		// excessBaggageFee.setEditable(false);
		
		result = new JLabel("");
		searchPanel.add(result);
		
		searchPanel.add(calculateExcessCharge);
		// searchPanel.add(excessBaggageFee);
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
				this.confirmBooking.getPassenger().getBaggage().setExcessCharge(allowedBaggageWeight, excessCharge);
				double charge = this.confirmBooking.getPassenger().getBaggage().getExcessCharge();
				System.out.println(charge);
				if(charge >  0) {
					JOptionPane.showMessageDialog(null, "Your excess baggage fee is: " + charge);
				}
				try {
					CheckInGUI checkInGUI = new CheckInGUI(allBookings, allFlights);
				    checkInGUI.setVisible(true);
				    checkInGUI.setLocationRelativeTo(null);
				} catch (BookingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CheckInIOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.dispose();
			}
		}
	}	

	private boolean isBagWidthValid() {

		if(width.getText().trim().compareTo("") == 0) {
			displayMessage("Invalid entry. Please input a number");
			return false;			
		}
		
		int widthValue = Integer.parseInt(width.getText().trim());
		
		if (widthValue != (int)widthValue) {
			displayMessage("Invalid entry. Please input a number");
			return false;
		}
		
		if (widthValue < 0 || widthValue > 100) {
			displayMessage("Please enter a valid width value");
			return false;
		}

		if (widthValue == 0 || widthValue == 0) {
			displayMessage("Please enter a value");
			return false;
		}
		return true;
	}

	private boolean isBagLengthValid() {

		if(length.getText().trim().compareTo("") == 0) {
			displayMessage("Invalid entry. Please input a number");
			return false;			
		}
		
		int lengthValue = Integer.parseInt(length.getText().trim());
		
		if (lengthValue < 0 || lengthValue > 100) {
			displayMessage("Please enter a valid length value");
			return false;
		}
	
		if (lengthValue == 0 || lengthValue == 0) {
			displayMessage("Please enter a value");
			return false;
		}

		if (lengthValue != (int)lengthValue) {
			displayMessage("Invalid entry. Please input a number");
			return false;
		}
			
		return true;
	}

	private boolean isBagHeightValid() {
		if(height.getText().trim().compareTo("") == 0) {
			displayMessage("Invalid entry. Please input a number");
			return false;			
		}
		
		int heightValue = Integer.parseInt(height.getText().trim());
		
		if (heightValue < 0 || heightValue > 100) {
			displayMessage("Please enter a valid height value");
			return false;
		}
		
		if (heightValue == 0 || heightValue == 0) {
			displayMessage("Please enter a value");
			return false;
		}
			
		if (heightValue != (int)heightValue) {
			displayMessage("Invalid entry. Please input a number");
			return false;
		}	
		return true;
	}

	private boolean isBagWeightValid() {
		if(weight.getText().trim().compareTo("") == 0) {
			displayMessage("Invalid entry. Please input a number");
			return false;			
		}
		
		int weightValue = Integer.parseInt(weight.getText().trim());
		
		if (weightValue < 0 || weightValue > 1000) {
			displayMessage("Please enter a valid weight value");
			return false;
		}

		if (weightValue == 0 || weightValue == 0) {
			displayMessage("Please enter a value");
			return false;
		}

		if (weightValue != (double)weightValue) {
			displayMessage("Invalid entry. Please input a number");
			return false;
		}
		return true;
	}	
	
	private void displayMessage(String message) {
		result.setText(message);
		result.updateUI();
	}
}
