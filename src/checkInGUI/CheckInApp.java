package checkInGUI;

import javax.swing.JOptionPane;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.FlightException;
import CheckIn.ThreadNewPassenger;
import controller.CheckInController;
import model.CheckIn;

/**
 * @author amymcfarland
 */

public class CheckInApp {
	
	private CheckInController checkInController;
	
	/**
	 * method to show each GUI 
	 * @throws Exception 
	 * @throws FlightException 
	 */
    public void showGUI() throws FlightException, Exception {
		try {
			ThreadNewPassenger aThread = new ThreadNewPassenger();
			
			CheckIn checkInDesk = new CheckIn("flights.csv", "bookings.csv", aThread);
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
     * @throws Exception 
     * @throws FlightException 
	 */
    public static void main (String arg[]) throws FlightException, Exception  {
    	CheckInApp cia = new CheckInApp();
    	cia.showGUI();
    }
}
