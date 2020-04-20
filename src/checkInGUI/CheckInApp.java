package checkInGUI;

/**
 * Import packages to display options.
 */
import javax.swing.JOptionPane;

import checkInModel.BookingException;
import checkInModel.CheckInIOException;
import checkInModel.FlightException;
/**
 * Import our controller.
 */
import controller.CheckInController;

/**
 * Import our model.
 */
import model.CheckIn;

/**
 * CheckInApp
 * @author amymcfarland
 */
public class CheckInApp {
   
    /**
	 * Method to run GUI and the whole application.
	 * @param arg
     * @throws Exception 
     * @throws FlightException 
	 */
    public static void main (String arg[]) throws FlightException, Exception  {
		try {
			/**
			 * Create the model that our system uses.
			 */
			CheckIn checkInDesk = new CheckIn();
			/**
			 * Create the view that our system uses, pass the model as a parameter
			 * so that the view can observe it.
			 */
			CheckInSimulation checkInView = new CheckInSimulation(checkInDesk);
			/**
			 * Connect view and model in our controller.
			 */
			new CheckInController(checkInView, checkInDesk);
		} catch(CheckInIOException | BookingException e) {
			/**
			 * Catch any Check in or Booking exceptions, display message then terminate application.
			 */
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }
}
