package checkInController;

/**
 * Import user interface packages to interact with the view.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 * Import our model and view classes
 */
import checkInModel.CheckIn;
import checkInView.CheckInSimulation;

/**
 * CheckInController
 * Connects the view and the model together
 * so that they can interact.
 * @author jamiehill
 *
 */
public class CheckInController {

	/**
	 * The view component.
	 */
	private CheckInSimulation checkInView;

	/**
	 * The model component.
	 */
	private CheckIn checkInModel;
	
	/**
	 * Thread that holds the execution of
	 * our simulation.
	 */
	private Thread checkInSimulation;

	/**
	 * CheckInController
	 * Constructor that creates the controller object
	 * @param view
	 * @param model
	 */
	public CheckInController(CheckInSimulation view, CheckIn model) {
		/**
		 * Store the view and model.
		 */
		checkInView = view;
		checkInModel = model;
		/**
		 * Load action listeners into the view so that
		 * user inputs can be sent into the model.
		 */
		checkInView.setStartSimulationAction(new CheckInSimulationStartAction());
		checkInView.setSimSpeedAction(new SpeedSimulatorActionSetter());
		checkInView.setWindowClosingAction(new CheckInDeskCloseWindowAction());
	}
	
	/**
	 * CheckInSimulationStartAction
	 * The action listener that allows the simulation
	 * to be started or stopped when a user clicks a
	 * button on the view.
	 * @author jamiehill
	 *
	 */
	public class CheckInSimulationStartAction implements ActionListener {
		
		/**
		 * actionPerformed
		 * Allows the simulation to be started or
		 * stopped when a user clicks a button on
		 * the view.
		 */
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(checkInModel.getSimulationRunning() == false) {
				checkInSimulation = new Thread (checkInModel);
				checkInSimulation.start();
			} else {
				checkInSimulation.stop();
				checkInModel.toggleSimulationRunning();
			}
		}
	}

	/**
	 * CheckInDeskCloseAction
	 * Action Listener that allows for the simulation
	 * to be stopped when the user clicks the X to
	 * close the window. Also writes the log to file
	 * on exit.
	 * @author jamiehill
	 *
	 */
	public class CheckInDeskCloseWindowAction implements ActionListener {
		
		/**
		 * actionPerformed
		 * Triggered when the user clicks the X on 
		 * the window and ensures that the simulation
		 * stops, cleaning up any running threads. Also
		 * writes the log to file.
		 */
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			if(checkInModel.getSimulationRunning() == true) {
				checkInSimulation.stop();
				// write log file
			}
		}
	}

	/**
	 * SpeedSimulatorActionSetter
	 * Sets the speed of the simulation in the model
	 * based on a user's selection from a drop down
	 * list.
	 * @author jamiehill
	 *
	 */
	public class SpeedSimulatorActionSetter implements ActionListener {

		/**
		 * actionPerformed
		 * Sets the speed of the simulation in the
		 * model based on a user's selection from
		 * a drop down box.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> speedList = (JComboBox<String>) e.getSource();
			String selectedValue = (String) speedList.getSelectedItem();
			checkInModel.setSimulationSpeed(selectedValue);
		}
	}
}
