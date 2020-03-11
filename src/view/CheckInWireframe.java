package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import javax.swing.*;

import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightComparator;
import model.CheckIn;
import observer.Observer;

/**
 * 
 * This class sets up the main GUI, which contains the Views.
 * @author amymcfarland
 */
public class CheckInWireframe implements Observer {
	
	FlightComparator flightComp = new FlightComparator();
	
	TreeSet<Flight> flightCollection = new TreeSet<Flight>(flightComp);
	
	
	public CheckInWireframe() {
		
		flightCollection = new TreeSet<Flight>();
	}
	
	@Override
	public void update(CheckIn checkinmodel) {
		for (int i = 0; i < flightCollection.size(); i++) {
			this.add(comp)
		}
		
		
	
	
	//if fake time is greater than departure time then hide the flights
			//there is a 
			//actionlistener is updating the model and fed into the checkin timer task
			//checkin desks that have a close button 

	
}