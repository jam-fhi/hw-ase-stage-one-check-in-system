package checkInGUI;

import javax.swing.JOptionPane;

import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.FlightCollection;
import controller.CheckInController;
import model.CheckIn;

/**
 * @author amymcfarland
 */

public class CheckInApp {
	
	private CheckInController checkInController;
	
	/**
	 * method to show each GUI 
	 */
    public void showGUI() throws BookingException, CheckInIOException {
		try {
			//BookingCollection bookingCollection = new BookingCollection("bookings.csv");
			//FlightCollection flightCollection = new FlightCollection("flights.csv");
		    //checkinGUI = new CheckInGUI(bookingCollection, flightCollection);
		    //checkinGUI.setVisible(true);
		    //checkinGUI.setLocationRelativeTo(null);
			CheckIn checkInDesk = new CheckIn("flights.csv", "bookings.csv");
			CheckInSimulation  checkInView = new CheckInSimulation();
			checkInController = new CheckInController(checkInView, checkInDesk);
			
			
		} catch(BookingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch(CheckInIOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }    
    /**
	 * Method to run GUI 
	 * @param arg
	 */
    public static void main (String arg[]) throws BookingException, CheckInIOException  {
    	CheckInApp cia = new CheckInApp();
    	cia.showGUI();
    }
}
