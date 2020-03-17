package checkInGUI;

import javax.swing.JOptionPane;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
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
