package CheckIn;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class CheckInGUI extends JFrame implements ActionListener {

	private BookingCollection bookingCollection;
	private FlightCollection flightCollection;
	private ConfirmGUI confirmGUI;

	JTextField lastName, bookingCode, searchField, result;
	JButton checkin, close;
	
	public CheckInGUI() {
		try {
			this.bookingCollection = new BookingCollection("bookings.csv");
			this.flightCollection = new FlightCollection("flights.csv");
			// set up window title
			setTitle("Check In ");
			// ensure program ends when window closes
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setupCenterPanel();

			// pack and set visible
			pack();
			setVisible(true);
		} catch(BookingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			this.dispose();
		} catch(CheckInIOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			this.dispose();
		}
	}

	private void setupCenterPanel() {
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(3, 3));
		searchPanel.add(new JLabel("Enter Last Name: "));
		lastName = new JTextField(15);
		searchPanel.add(lastName);
		searchPanel.add(new JLabel("Enter Booking Reference : "));
		bookingCode = new JTextField(15);
		searchPanel.add(bookingCode);
		checkin = new JButton("Check In");
		searchPanel.add(checkin);
		checkin.addActionListener(this);
		this.add(searchPanel, BorderLayout.CENTER);
	}
		
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkin) {
			// get search text and search booking list
			// setting result text if found
			String lastNameSearch = lastName.getText().trim();
			String bookingCodeSearch = bookingCode.getText().trim();
			if (lastNameSearch.length() > 0 && bookingCodeSearch.length() > 0) {
				try {
					Booking booking = bookingCollection.getBooking(bookingCodeSearch, lastNameSearch);
					Flight flight = flightCollection.findFlight(booking.getFlightCode());
					this.setVisible(false);
					confirmGUI = new ConfirmGUI(booking, flight, this);
					confirmGUI.setVisible(true);
				} catch (BookingException ex) {
					result.setText(ex.getMessage());
				} catch (FlightException ex) {
					result.setText(ex.getMessage());
				}
			}
		} else if (e.getSource() == close) {
			ReportGenerator reportGen = new ReportGenerator("FlightReport.csv");
			this.dispose();
		}		
	}	
}
