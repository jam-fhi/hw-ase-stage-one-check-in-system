package CheckIn;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class CheckInGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private BookingCollection bookingCollection;
	private FlightCollection flightCollection;
	private ConfirmGUI confirmGUI;

	JTextField lastName, bookingCode, searchField, result;
	JButton checkin, close;
	
	public CheckInGUI() throws BookingException, CheckInIOException {
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
		} catch(CheckInIOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
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
			try {
				new ReportGenerator(bookingCollection, flightCollection, "FlightReport.csv");
			} catch (CheckInIOException e1) {
				// TODO Auto-generated catch block
				// It's on exit of the application, nothing we can really do at this point.
				e1.printStackTrace();
			}
			this.dispose();
		}
	}	
}
