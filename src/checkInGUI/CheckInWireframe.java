package checkInGUI;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.*;

import CheckIn.Flight;
import observer.Observer;
import model.CheckIn;

/**
 * 
 * This class sets up the main GUI, which contains the Views.
 * @author amymcfarland
 */
public class CheckInWireframe extends JFrame implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckInWireframe() {
		this.setSize(640, 320);
		this.setTitle("Check In Simulator");
	    pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}

	public void update(CheckIn checkinmodel) {
		
		/**
		 * On update remove any existing components on this JFrame
		 */
		int compCounter = 1;
		while(compCounter < this.getComponentCount()) {
			if(this.getComponent(compCounter) != null) {
				if(this.getComponent(compCounter).getName().compareTo("flightSummary") == 0) {
					this.remove(compCounter);
				}
			}
			compCounter++;
		}
				
		JPanel flightSummary = new JPanel();
		flightSummary.setName("flightSummary");

		JLabel flightTotal = new JLabel();
		flightTotal.setText("There are " + checkinmodel.getFlightCollection().getFlightCollection().size() + " flights.");
		flightSummary.add(flightTotal);
		
		/**
		 * Iterate through the flights collection
		 * Add a new flight panel for each flight
		 * in our system
		 */
		Iterator<Flight> flightIt = checkinmodel.getFlightCollection().getFlightCollection().iterator();
		while(flightIt.hasNext()) {
			Flight aFlight = flightIt.next();
			FlightInformation aFlightPanel = new FlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport());
			aFlightPanel.setSize(500, 150);
			aFlightPanel.setVisible(true);
			flightSummary.add(aFlightPanel);
		}
		
		flightSummary.setLayout(new GridLayout(checkinmodel.getFlightCollection().getFlightCollection().size() + 1, 1));
		this.add(flightSummary);
		pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
	//if fake time is greater than departure time then hide the flights
			//there is a 
			//actionlistener is updating the model and fed into the checkin timer task
			//checkin desks that have a close button 

	
}