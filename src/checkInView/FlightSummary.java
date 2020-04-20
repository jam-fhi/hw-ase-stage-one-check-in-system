package checkInView;

import java.awt.BorderLayout;
/**
 * Import packages that are used to make our User Interface work.
 */
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Import packages to use data structures.
 */
import java.util.Iterator;

import checkInModel.BookingCollection;
import checkInModel.Flight;
import checkInModel.FlightCollection;
import checkInModel.FlightStatus;

/**
 * This class displays the flight summary.
 * 
 * @author jamiehill
 *
 */
public class FlightSummary extends JPanel {

	/**
	 * Required by Java for compatibility.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * JLabel for total number of flights.
	 */
	private JLabel flightTotal = new JLabel("There are 0 flights.");
	private JLabel flightHeader = new JLabel("Flight Code, Destination, Status, Bookings / Total Capacity");

	/**
	 * Flight info display panel
	 */
	private JPanel flightInfo = new JPanel();

	/**
	 * Count of how many flights are displayed. Initialises flight count to 0.
	 */
	private int flightCount = 0;

	/**
	 * Constructor 
	 * Creates the flight summary object which
	 * is a collection of flight information 
	 * custom JPanels.
	 * 
	 * @param allFlights
	 * @param allBookings
	 */
	public FlightSummary(FlightCollection allFlights, BookingCollection allBookings) {

		/**
		 * Set grid layout for 3 rows and 1 column
		 */
		this.setLayout(new BorderLayout());
		
		/**
		 * Pads the panel with a 10 pixel border.
		 */
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/**
		 * Adds flight total and header label to JPanel, as well as flight info panel.
		 */
		this.add(flightTotal, BorderLayout.NORTH);
		this.add(flightHeader, BorderLayout.CENTER);
		this.add(flightInfo, BorderLayout.SOUTH);

		/**
		 * Adds the initial flight information to the flight summary.
		 */
		updateSummary(allFlights, allBookings);
	}
	
	/**
	 * addFlightPanel 
	 * Adds a new custom flight information JPanel to this panel.
	 * 
	 * @param flightCode
	 * @param destination
	 * @param status
	 * @param bookings
	 * @param capacity
	 */
	private void addFlightPanel(String flightCode, String destination, FlightStatus status, int bookings, int capacity) {
		FlightInformation aFlightPanel = new FlightInformation(flightCode, destination, status, bookings, capacity);

		/**
		 * Set the name of the panel so we can find it later.
		 */
		aFlightPanel.setName(flightCode);

		/**
		 * Makes sure the flight summary is visible.
		 */
		aFlightPanel.setVisible(true);

		/**
		 * Increase flight count for every flight information panel added.
		 */
		flightCount++;

		/**
		 * Sets the text for the flight total.
		 */
		flightTotal.setText("There are " + flightCount + " flights.");

		/**
		 * Adds label to JPanel.
		 */
		flightInfo.add(aFlightPanel);
	}
	
	/**
	 * getFlightPanel 
	 * Finds a flight information JPanel 
	 * using the name set when added.
	 * 
	 * @param name
	 * @return FlightInformation object
	 */
	private FlightInformation getFlightPanel(String name) {

		/**
		 * Only look for flight information panels.
		 */
		if(flightInfo.getComponents().length > 0) {
			int panelCount = 0;
			while(panelCount < flightInfo.getComponents().length) {

				/**
				 * Return a flight information panel if a 
				 * component's name matches the name that
				 * is specified in the method parameter.
				 */
				if(flightInfo.getComponent(panelCount).getName().compareTo(name) == 0) {
					return (FlightInformation)flightInfo.getComponent(panelCount);
				}
				panelCount++;
			}
		}
		/**
		 * If nothing found, return null.
		 */
		return null;
	}

	/**
	 * updateSummary 
	 * Update the flight information for provided flights.
	 * 
	 * @param allFlights
	 * @param allBookings
	 */
	public void updateSummary(FlightCollection allFlights, BookingCollection allBookings) {
		
		if(allFlights.getFlightCollection().size() < flightCount) {
			/**
			 * Assume less flights means the simulation 
			 * has been reset. Remove everything and add 
			 * flight total again.
			 */
			flightInfo.removeAll();
			flightInfo.revalidate();
			flightInfo.repaint();
			flightCount = 0;
		}

		/**
		 * Iterate through the flights collection 
		 * Add a new flight panel for each flight
		 * in our system
		 */
		Iterator<Flight> flightIt = allFlights.getFlightCollection().iterator();
		while(flightIt.hasNext()) {
			Flight aFlight = flightIt.next();
			FlightInformation flightDisplay = getFlightPanel(aFlight.getFlightCode());
			if(flightDisplay != null) {

				/**
				 * If flight found and not departed, update the flight 
				 * information. Otherwise, remove the flight from the view.
				 */
				if(aFlight.getFlightStatus().compareTo(FlightStatus.DEPARTED) == 0) {
					flightCount--;
					flightInfo.remove(flightDisplay);
				} else {
					flightDisplay.updateFlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus(), allBookings.getBookingsByFlightCode(aFlight.getFlightCode()).size(), aFlight.getMaximumPassengers());
				}
			} else {
				/**
				 * Flight not found. If it's not departed, add it to the view.
				 */
				if(aFlight.getFlightStatus().compareTo(FlightStatus.DEPARTED) != 0) {
					addFlightPanel(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus(), allBookings.getBookingsByFlightCode(aFlight.getFlightCode()).size(), aFlight.getMaximumPassengers());
				}
			}
		}

		/**
		 * Update number of rows to display.
		 */
		flightInfo.setLayout(new GridLayout(flightCount, 1));
	}
}
