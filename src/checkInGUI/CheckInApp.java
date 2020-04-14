package checkInGUI;

import javax.swing.JOptionPane;
import CheckIn.BookingException;
import CheckIn.CheckInIOException;
import CheckIn.FlightException;
import controller.CheckInController;
import model.CheckIn;

/**
 * @author amymcfarland
 */

public class CheckInApp {
   
    /**
	 * Method to run GUI 
	 * @param arg
     * @throws Exception 
     * @throws FlightException 
	 */
    public static void main (String arg[]) throws FlightException, Exception  {
		try {
			CheckIn checkInDesk = new CheckIn();
			CheckInSimulation checkInView = new CheckInSimulation(checkInDesk);
			new CheckInController(checkInView, checkInDesk);
		} catch(BookingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch(CheckInIOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
}
