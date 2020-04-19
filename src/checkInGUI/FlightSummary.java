package checkInGUI;

/**
 * Import packages that are used to make our User Interface work.
 */
import java.awt.GridLayout;
/**
 * Import packages to use data structures.
 */
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Import our packages to use our model data.
 */
import CheckIn.BookingCollection;
import CheckIn.Flight;
import CheckIn.FlightCollection;

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
	private JLabel flightTotal = new JLabel();

	/**
	 * Count of how many flights are displayed. Initialises flight count to 0.
	 */
	private int flightCount = 0;

	/**
	 * Constructor Creates the flight summary object which is a collection of flight
	 * information custom JPanels.
	 * 
	 * @param allFlights
	 * @param allBookings
	 */
	public FlightSummary(FlightCollection allFlights, BookingCollection allBookings) {

		/**
		 * Create grid layout with 1 column and 1 more row than the flight count.
		 */
		this.setLayout(new GridLayout(flightCount + 1, 1));

		/**
		 * Pads the panel with a 10 pixel border.
		 */
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/**
		 * Adds flight total label to JPanel.
		 */
		this.add(flightTotal);

		/**
		 * Adds the initial flight information to the flight summary.
		 */
		updateSummary(allFlights, allBookings);
	}
	
	/**
	 * addFlightPanel Adds a new custom flight information JPanel to this panel.
	 * 
	 * @param flightCode
	 * @param destination
	 * @param status
	 * @param bookings
	 * @param capacity
	 */
	private void addFlightPanel(String flightCode, String destination, String status, int bookings, int capacity) {
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
		this.add(aFlightPanel);
	}
	
	/**
	 * getFlightPanel Finds a flight information JPanel using the name set when
	 * added.
	 * 
	 * @param name
	 * @return FlightInformation object
	 */
	private FlightInformation getFlightPanel(String name) {

		/**
		 * Only look for flight information panels if there's more than one component,
		 * as flight total will always be there.
		 */
		if(this.getComponents().length > 1) {
			/**
			 * Loop from 1 to the total number of components.
			 */
			int panelCount = 1;
			while(panelCount < this.getComponents().length) {

				/**
				 * Return a flight information panel if a component's name matches the name that
				 * is specified in the method parameter.
				 */
				if(this.getComponent(panelCount).getName().compareTo(name) == 0) {
					return (FlightInformation)this.getComponent(panelCount);
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
	 * updateSummary Update the flight information for provided flights.
	 * 
	 * @param allFlights
	 * @param allBookings
	 */
	public void updateSummary(FlightCollection allFlights, BookingCollection allBookings) {
		
		if((allFlights.getFlightCollection().size() + 1) < flightCount) {
			/**
			 * Assume less flights means the simulation has been reset. Remove everything
			 * and add flight total again.
			 */
			this.removeAll();
			this.revalidate();
			this.repaint();
			this.add(flightTotal);
			flightCount = 0;
		}

		/**
		 * Iterate through the flights collection Add a new flight panel for each flight
		 * in our system
		 */
		Iterator<Flight> flightIt = allFlights.getFlightCollection().iterator();
		while(flightIt.hasNext()) {
			Flight aFlight = flightIt.next();
			FlightInformation flightDisplay = getFlightPanel(aFlight.getFlightCode());
			if(flightDisplay != null) {

				/**
				 * If flight found and not departed, update the flight information. Otherwise,
				 * remove the flight from the view.
				 */
				if(aFlight.getFlightStatus().compareTo("departed") == 0) {
					flightCount--;
					this.remove(flightDisplay);
				} else {
					flightDisplay.updateFlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus(), allBookings.getBookingsByFlightCode(aFlight.getFlightCode()).size(), aFlight.getMaximumPassengers());
				}
			} else {
				/**
				 * Flight not found. If it's not departed, add it to the view.
				 */
				if(aFlight.getFlightStatus().compareTo("departed") != 0) {
					addFlightPanel(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus(), allBookings.getBookingsByFlightCode(aFlight.getFlightCode()).size(), aFlight.getMaximumPassengers());
				}
			}
		}

		/**
		 * Update number of rows to display.
		 */
		this.setLayout(new GridLayout(flightCount + 1, 1));
	}
}
