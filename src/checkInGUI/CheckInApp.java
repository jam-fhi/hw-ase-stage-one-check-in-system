package checkInGUI;

/**
 * Import Model and Controller packages.
 */
import checkInController.CheckInController;
import checkInModel.CheckIn;
import checkInModel.FlightException;

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
    }
}
