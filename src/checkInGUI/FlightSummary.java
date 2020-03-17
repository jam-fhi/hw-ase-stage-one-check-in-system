package checkInGUI;

import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import CheckIn.Flight;
import CheckIn.FlightCollection;

public class FlightSummary extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlightSummary(FlightCollection allFlights) {

		this.setLayout(new GridLayout(allFlights.getFlightCollection().size() + 1, 1));

		JLabel flightTotal = new JLabel("There are " + allFlights.getFlightCollection().size() + " flights.");
		this.add(flightTotal);

		/**
		 * Iterate through the flights collection
		 * Add a new flight panel for each flight
		 * in our system
		 * Check if the flight status isn't equal departed then it will show the flight details. If it is departed 
		 * it wont show on the GUI. 
		 */

		Iterator<Flight> flightIt = allFlights.getFlightCollection().iterator();
		while(flightIt.hasNext()) {
			Flight aFlight = flightIt.next();
			if(aFlight.getFlightStatus().compareTo("departed") != 0) {
				FlightInformation aFlightPanel = new FlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus());
				aFlightPanel.setSize(500, 150);
				aFlightPanel.setVisible(true);
				this.add(aFlightPanel);
			}
		}
	}
}
