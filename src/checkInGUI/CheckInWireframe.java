package checkInGUI;
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

	public void update(CheckIn checkinmodel) {
		
		/**
		 * On update remove any existing components on this JFrame
		 */
		int compCounter = 0;
		while(compCounter < this.getComponentCount()) {
			this.remove(compCounter);
			compCounter++;
		}
		
		/**
		 * Iterate through the flights collection
		 * Add a new flight panel for each flight
		 * in our system
		 */
		
		Iterator<Flight> flightIt = checkinmodel.getFlightCollection().getFlightCollection().iterator();
		while(flightIt.hasNext()) {
			Flight aFlight = flightIt.next();
			FlightInformation aFlightPanel = new FlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport());
			this.add(aFlightPanel);
		}
		
		
	}
	
	//if fake time is greater than departure time then hide the flights
			//there is a 
			//actionlistener is updating the model and fed into the checkin timer task
			//checkin desks that have a close button 

	
}