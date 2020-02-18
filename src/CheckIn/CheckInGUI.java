package CheckIn;

	//import all the GUI classes
	import java.awt.*;
	import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
	import javax.swing.*;
	import javax.swing.text.JTextComponent;

	/**
	 * Simple GUI for CompetitorList application
	 */
	public class CheckInGUI extends JFrame implements ActionListener {

		// The booking list to be searched.
		private BookingCollection bookingCollection;
		private FlightCollection flightCollection;
		private ConfirmGUI gui;

		/**
		 * Creating GUI components to be included in GUI panels
		 */
		JTextField lastname, bookingcode, searchField, result;
		JButton checkin, close;
		

		/**
		 * Create the frame with its panels.
		 * 
		 * @param list The booking and flight list to be searched.
		 * @throws BookingException 
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 */
	
		public CheckInGUI() throws FileNotFoundException, IOException, BookingException{

			
			this.bookingCollection = new BookingCollection();
			this.flightCollection = new FlightCollection ();
			
			bookingCollection.loadBookings("bookings.csv");
			flightCollection.loadFlights("flights.csv");
			
			
			
			// set up window title
			setTitle("Check In ");
			// ensure program ends when window closes
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			setupCenterPanel();

			// pack and set visible
			pack();
			setVisible(true);

		}

		private void setupCenterPanel() {
			JPanel searchPanel = new JPanel();
			searchPanel.setLayout(new GridLayout(3, 3));
			searchPanel.add(new JLabel("Enter Last Name: "));
			lastname = new JTextField(15);
			searchPanel.add(lastname);
			searchPanel.add(new JLabel("Enter Booking Reference : "));
			bookingcode = new JTextField(15);
			searchPanel.add(bookingcode);
			checkin = new JButton("Check In");

			searchPanel.add(checkin);
			//searchPanel.add(result);
			// specify action when button is pressed
			checkin.addActionListener(this);
			// Set up the area where the results will be displayed.

			// add south panel to the content pane
			this.add(searchPanel, BorderLayout.CENTER);

		}
		

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == checkin) {
				// get search text and search booking list
				// setting result text if found
				
				
				String lastNameSearch = lastname.getText().trim();
				String bookingCodeSearch = bookingcode.getText().trim();
				if (lastNameSearch.length() > 0 && bookingCodeSearch.length() > 0) {
					Booking booking = bookingCollection.getBooking(bookingCodeSearch, lastNameSearch);
					if (booking != null) {
						Flight flight = flightCollection.findFlight(flight.getFlightCode());
						//JOptionPane.showMessageDialog(null, "You are now checked in for your" + booking.getBookingCode() + "flight");
						//booking.setCheckedin();
						this.dispose();
						ConfirmGUI c = new ConfirmGUI(booking, flight);
						c.setVisible(true);
					}
			
						
					else {
						result.setText("not found");
				}
			}
		
		}
		
			else if (e.getSource() == close) {

			FileIO.readFile("src/flight.csv");
			FileIO.writeFile("outputreport.txt");
			
			this.dispose();
			
		}
		
		}
		
		
	}
		



	
	
