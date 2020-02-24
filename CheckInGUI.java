package CheckIn;

//import all the GUI classes
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class CheckInGUI extends JFrame implements ActionListener {

	/**
	 * The booking and flight collections to be searched.
	 */
	private BookingCollection bookingCollection;
	private FlightCollection flightCollection;
	private ConfirmGUI confirmGUI;

	/**
	 * Creating GUI components to be included in GUI panels
	 */
	JTextField lastName, bookingCode, searchField, result;
	JButton checkin, close;
	
	/**
	 * Create the frame with panels.
	 */
	public CheckInGUI() throws BookingException, CheckInIOException {
		try {
			this.bookingCollection = new BookingCollection("bookings.csv");
			this.flightCollection = new FlightCollection("flights.csv");

			// set up window title
			setTitle("Check In ");
			// ensure program ends when window closes
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			setupCenterPanel();
			setupNorthPanel();

			// pack and set visible
			pack();
			setVisible(true);
		
		}catch(BookingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch(CheckInIOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		/**
		 * Creating the center panel with two JLables and a JButton and
		 * adding an event to the checkin button once clicked
		 */
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
	
	/**
	 * Creating the north panel with a close JButton and
	 * adding an event to the button once clicked
	 */
	private void setupNorthPanel() {
		JPanel northPanel = new JPanel();
		close = new JButton("Close");
		close.addActionListener(this);
		northPanel.add(close);
		this.add(northPanel, BorderLayout.NORTH);
	}
	
	/**
	 * Creating a method which is used when the checkin or close button is clicked. If the checkin 
	 * button is clicked the method will search through the booking and flight collections to find the specific booking. 
	 * If the booking is found it will open the confirmGUI frame.
	 * If the close button is clicked it will generate a flight report.
	 */
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
				ReportGenerator reportGen = new ReportGenerator(bookingCollection, flightCollection, "FlightReport.csv");
			} catch (CheckInIOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}		
	}	
}

