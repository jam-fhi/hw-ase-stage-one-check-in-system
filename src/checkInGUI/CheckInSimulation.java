package checkInGUI;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;

import CheckIn.Booking;
import CheckIn.Flight;
import CheckIn.fakeTime;
import observer.Observer;
import model.CheckIn;
import checkInGUI.PassengerSummary;

/**
 * 
 * This class sets up the main GUI, which contains the Views.
 * @author amymcfarland
 */
public class CheckInSimulation extends JFrame implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CheckInDeskSummary checkInDeskSummary;

	public CheckInSimulation() {
		this.setSize(100, 300);
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
		
		/**
		 * Add the passenger queue to the view.
		 */
		PassengerQueue passengerQueue = new PassengerQueue(checkinmodel.getBookingCollection());
		this.add(passengerQueue, BorderLayout.WEST);
		
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		
		/**
		 * Simulation controls
		 */
		SimControl simControl = new SimControl();
		rightSide.add(simControl, BorderLayout.NORTH);
		
		/**
		 * Add checkin desks
		 */
		checkInDeskSummary = new CheckInDeskSummary(checkinmodel.getCheckInDesk());
		rightSide.add(checkInDeskSummary, BorderLayout.CENTER);
		
		/**
		 * Add flights to the flight display.
		 */
		FlightSummary flightSummaryList = new FlightSummary(checkinmodel.getFlightCollection());
		rightSide.add(flightSummaryList, BorderLayout.SOUTH);
		
		this.add(rightSide, BorderLayout.EAST);
		
		pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	   

	} 
	
		public void setCheckInDeskAction(ActionListener e) {
			checkInDeskSummary.setDeskStatusActionListener(e);
			
	}
}	
