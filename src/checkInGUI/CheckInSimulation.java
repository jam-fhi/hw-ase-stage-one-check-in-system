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
	
	private JButton nextButton = new JButton();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckInSimulation() {
		this.setSize(100, 300);
		this.setTitle("Check In Simulator");
	    pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}

	public void addNextButtonActionListener(ActionListener nextAction) {
		nextButton.addActionListener(nextAction);
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
		this.add(passengerQueue, BorderLayout.NORTH);
		
		
		JLabel flightTotal = new JLabel();
		flightTotal.setText("There are " + checkinmodel.getFlightCollection().getFlightCollection().size() + " flights.");
		flightSummary.add(flightTotal);
		
		
		PassengerSummary passengerSummary = new PassengerSummary();
		passengerSummary.setName("Passenger");
		
		JPanel checkinSummary = new JPanel();
		checkinSummary.setName("Check In Desk 1");

		
		JLabel checkinDetails = new JLabel();
		checkinSummary.add(checkinDetails);
		
		
		JPanel checkinSummary1 = new JPanel();
		checkinSummary1.setName("Check In Desk 2");

		
		JLabel checkinDetails1 = new JLabel();
		checkinSummary1.add(checkinDetails1);
		
		
	
		/**
		 * Iterate through the flights collection
		 * Add a new flight panel for each flight
		 * in our system
		 * Check if the flight status isn't equal departed then it will show the flight details. If it is departed 
		 * it wont show on the GUI. 
		 */
		
		Iterator<Flight> flightIt = checkinmodel.getFlightCollection().getFlightCollection().iterator();
		while(flightIt.hasNext()) {
			Flight aFlight = flightIt.next();
			if(aFlight.getFlightStatus().compareTo("departed") != 0) {
				
			FlightInformation aFlightPanel = new FlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus());
			aFlightPanel.setSize(500, 150);
			aFlightPanel.setVisible(true);
			flightSummary.add(aFlightPanel);
			}
		}
		

		Flight aFlight = checkinmodel.getFlightCollection().nextFlight;
		
		FlightInformation aFlightPanel = new FlightInformation(aFlight.getFlightCode(), aFlight.getDestinationAirport(), aFlight.getFlightStatus());
		
		aFlightPanel.setSize(500, 150);
		aFlightPanel.setVisible(true);
		flightSummary.add(aFlightPanel);
		
		flightSummary.setLayout(new GridLayout(1, 3));
		this.add(flightSummary);
		pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	    
	    
	   

		// passengerSummary.displayList();
		
		for(Map.Entry<String, Booking> aBooking1: checkinmodel.getBookingCollection().getBookingCollection().entrySet()) {
			  
			CheckInInformation aCheckInPanel = new CheckInInformation(aBooking1.getValue().getPassenger().getFirstName(), aBooking1.getValue().getPassenger().getLastName());
	
			
			//aPassengerPanel.setSize(15, 10);
			aCheckInPanel.setVisible(true);
			checkinSummary.add(aCheckInPanel);
			
			this.add(checkinSummary,BorderLayout.EAST);
			pack();
			this.setVisible(true);
		    this.setLocationRelativeTo(null);
		    
		    aCheckInPanel.setVisible(true);
			checkinSummary1.add(aCheckInPanel);
			
			this.add(checkinSummary1,BorderLayout.EAST);
			pack();
			this.setVisible(true);
		    this.setLocationRelativeTo(null);
		    
		    
		   
		    
		}
		
		/*Booking aBooking = checkinmodel.getBookingCollection().;
		
		PassengerInformation aPassengerPanel = new PassengerInformation(aBooking.getBookingCode(), aBooking.getPassenger().getFirstName(), aBooking.getPassenger().getLastName());
		aPassengerPanel.setSize(500, 150);
		aPassengerPanel.setVisible(true);
		passengerSummary.add(aPassengerPanel);
		
		passengerSummary.setLayout(new GridLayout(3, 1));
		this.add(passengerSummary);
		pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	    */
	
}
	

   
}	
	
	
	


	//actionlistener is updating the model and fed into the checkin timer task
			//checkin desks that have a close button 
	
	
