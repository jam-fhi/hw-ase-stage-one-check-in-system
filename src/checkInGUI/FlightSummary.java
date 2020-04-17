package checkInGUI;

import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CheckIn.BookingCollection;
import CheckIn.Flight;
import CheckIn.FlightCollection;

public class FlightSummary extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel flightTotal = new JLabel();
	private int flightCount = 0;

	public FlightSummary(FlightCollection allFlights, BookingCollection allBookings) {

		this.setLayout(new GridLayout(flightCount + 1, 1));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.add(flightTotal);

		updateSummary(allFlights, allBookings);
	}
	
	private void addFlightPanel(String flightCode, String destination, String status, int bookings, int capacity) {
		FlightInformation aFlightPanel = new FlightInformation(flightCode, destination, status, bookings, capacity);
		aFlightPanel.setName(flightCode);
		aFlightPanel.setVisible(true);
		flightCount++;
		flightTotal.setText("There are " + flightCount + " flights.");
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

	public void updateSummary(FlightCollection allFlights, BookingCollection allBookings) {
		
		if((allFlights.getFlightCollection().size() + 1) < flightCount) {
			// Assume less flights means the simulation has been reset.
			this.removeAll();
			this.revalidate();
			this.repaint();
			this.add(flightTotal);
			flightCount = 0;
		}

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
				flightDisplay.updateFlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus(), allBookings.getBookingByFlightCode(aFlight.getFlightCode()).size(), aFlight.getMaximumPassengers());
			} else {
				addFlightPanel(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus(), allBookings.getBookingByFlightCode(aFlight.getFlightCode()).size(), aFlight.getMaximumPassengers());
			}
		}
		
		this.setLayout(new GridLayout(flightCount + 1, 1));
	}
}
