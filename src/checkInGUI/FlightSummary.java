package checkInGUI;

import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import CheckIn.Flight;
import CheckIn.FlightCollection;

public class FlightSummary extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel flightTotal = new JLabel();
	private int flightCount = 0;

	public FlightSummary(FlightCollection allFlights) {

		this.setLayout(new GridLayout(allFlights.getFlightCollection().size() + 1, 1));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		flightTotal = new JLabel("There are " + allFlights.getFlightCollection().size() + " flights.");
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
			addFlightPanel(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus());
		}
	}
	
	private void addFlightPanel(String flightCode, String destination, String status) {
		FlightInformation aFlightPanel = new FlightInformation(flightCode, destination, status);
		aFlightPanel.setName(flightCode);
		aFlightPanel.setSize(500, 150);
		aFlightPanel.setVisible(true);
		this.add(aFlightPanel);
	}
	
	private FlightInformation getFlightPanel(String name) {
		if(this.getComponents().length > 1) {
			int panelCount = 1;
			while(panelCount < this.getComponents().length) {
				if(this.getComponent(panelCount).getName().compareTo(name) == 0) {
					return (FlightInformation)this.getComponent(panelCount);
				}
				panelCount++;
			}
		}
		return null;
	}

	public void updateSummary(FlightCollection allFlights) {
		if((allFlights.getFlightCollection().size() + 1) > flightCount) {
			flightCount = allFlights.getFlightCollection().size() + 1;
			this.setLayout(new GridLayout(flightCount, 1));
		} else {
			// Assume less flights means the simulation has been reset.
			this.removeAll();
			this.revalidate();
			this.repaint();
			this.add(flightTotal);
		}
		
		flightTotal.setText("There are " + allFlights.getFlightCollection().size() + " flights.");

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
			FlightInformation flightDisplay = getFlightPanel(aFlight.getFlightCode());
			if(flightDisplay != null) {
				flightDisplay.updateFlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus());
			} else {
				addFlightPanel(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus());
			}
		}
	}
}
