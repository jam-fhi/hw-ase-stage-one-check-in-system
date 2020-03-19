package checkInGUI;

/**
 *Importing all the GUI classes
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightException;
import CheckIn.LoggingSingleton;
import CheckIn.ReportGenerator;

/**
 * Simple GUI for Checkin application
 * @author amymcfarland
 */
public class CheckInGUI extends JFrame implements ActionListener, WindowListener {
	
	/**
	 * The flight and booking collection classes to be searched
	 */
	private static final long serialVersionUID = 1L;
	private BookingCollection bookingCollection;
	private FlightCollection flightCollection;
	private ConfirmGUI confirmGUI;
	
	/**
	 * Creating GUI components to be included in GUI panels
	 */
	JTextField lastName, bookingCode, searchField;
	JLabel result;
	JButton checkin, close;
	
	/**
	 * Create the frame with panels.
	 * @param bookings the list of bookings
	 * @param flights the list of flights
	 */
	public CheckInGUI(BookingCollection bookings, FlightCollection flights) throws BookingException, CheckInIOException {
		this.bookingCollection = bookings;
		this.flightCollection = flights;
		// set up window title
		setTitle("Check In ");
		// ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setupCenterPanel();
		
		addWindowListener(this);

		// pack and set visible
		pack();
		setVisible(true);
	}

	/**
	 * Creating the center panel with a two JTextFields and a JButton
	 */
	private void setupCenterPanel() {
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(3, 2));
		searchPanel.add(new JLabel("Enter Last Name: "));
		lastName = new JTextField(15);
		searchPanel.add(lastName);
		searchPanel.add(new JLabel("Enter Booking Reference : "));
		bookingCode = new JTextField(15);
		searchPanel.add(bookingCode);
		result = new JLabel("");
		searchPanel.add(result);
		checkin = new JButton("Check In");
		searchPanel.add(checkin);
		checkin.addActionListener(this);
		this.add(searchPanel, BorderLayout.CENTER);
	}

	/**
	 * Creating a method that closes the window and creates the report.
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		saveReport(this);
		// log event
		LoggingSingleton logger = LoggingSingleton.getInstance();
		logger.addLog("Report generated and window closed");
		try {
			logger.writelog("Logs.txt");
		} catch (CheckInIOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Creating a method which is used when the check in button is clicked. Once the 
	 * button is clicked the method finds the booking and flight. If the booking the found it
	 * then opens the confirmGUI. 
	 * @param e used when a button is clicked 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if (e.getSource() == checkin) {
			// get search text and search booking list
			// setting result text if found
			String lastNameSearch = lastName.getText().trim();
			String bookingCodeSearch = bookingCode.getText().trim();

			try {
				if (lastNameSearch.length() > 0 && bookingCodeSearch.length() > 0) {

					Booking booking = bookingCollection.getBooking(bookingCodeSearch, lastNameSearch);
					Flight flight = flightCollection.findFlight(booking.getFlightCode());
					this.setVisible(false);
					confirmGUI = new ConfirmGUI(booking, flight, bookingCollection, flightCollection);
					confirmGUI.setVisible(true);
					confirmGUI.setLocationRelativeTo(null);
				} else {
					displayMessage("Invalid entry. Please input a valid input");

				}
				if (lastNameSearch.isEmpty() || bookingCodeSearch.isEmpty()) {
					displayMessage("Please input all information required");
				}

			} catch (BookingException ex) {
				displayMessage(ex.getMessage());
			} catch (FlightException ex) {
				displayMessage(ex.getMessage());
			}
		}

	}
		
	/**
	 * Creating a method which displays messages in the GUI.
	 * @param message adding in a messages to be able to display it on the GUI.
	 */
	
	private void displayMessage(String message) {
		result.setText(message);
		result.updateUI();
	}

	/**
	 * Creating a method which generates the report to a csv file with a CheckInIOException
	 * @param app 
	 */
	private void saveReport(JFrame app) {
		try {
			new ReportGenerator(bookingCollection, flightCollection, "FlightReport.csv");
		} catch (CheckInIOException e1) {
			// TODO Auto-generated catch block
			// It's on exit of the application, nothing we can really do at this point.
			e1.printStackTrace();
		} finally {
			app.dispose();
		}
	}

	/**
	 * Creating method studs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Creating method studs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Creating method studs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Creating method studs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Creating method studs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Creating method studs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
