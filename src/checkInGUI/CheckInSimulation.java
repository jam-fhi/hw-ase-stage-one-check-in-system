package checkInGUI;

/**
 * Import packages that are used to make our User Interface work.
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import checkInModel.LoggingSingleton;

/**
 * Import the Observer pattern classes that allows our UI to
 * be updated when data in the model changes.
 */
import java.util.Observable;
import java.util.Observer;

import model.CheckIn;

/**
 * 
 * This class sets up the main GUI, which contains the Views.
 * @author amymcfarland
 */

@SuppressWarnings("deprecation")
public class CheckInSimulation extends JFrame implements Observer, WindowListener  {
	
	/**
	 * Required by Java for compatibility.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * JPanel that all our components on the UI.
	 */
	private JPanel simulationUI = new JPanel();
	
	/**
	 * Model of the data in our system.
	 */
	private CheckIn checkInDesk;
	
	/**
	 * Custom JPanel that controls our simulation.
	 */
	private SimControl simControl = null;
	
	/**
	 * Action listener defined in our controller,
	 * this is used when the application exits
	 * so we can log the simulation to file.
	 */
	private ActionListener closeWindow;
	
	/**
	 * Custom JPanel that displays our flight information.
	 */
	private FlightSummary flightOverview;
	
	/**
	 * We have 3 queues to display. These custom JPanels display
	 * each of the queues.
	 */
	private PassengerQueue securityQueue;
	private PassengerQueue economyQueue;
	private PassengerQueue businessQueue;
	
	/**
	 * Custom JPanel that displays each of our check in desks
	 */
	private CheckInDeskSummary checkInDeskSummary;
	
	/**
	 * Logging singleton to log issues on the UI.
	 */
	private LoggingSingleton log;

	/**
	 * 
	 * Constructor, creates the object
	 * @param checkInDesk the model that the view uses.
	 */
	public CheckInSimulation(CheckIn checkInDesk) {
		/**
		 * Store the model in a class variable, then
		 * add the view (this) as the observer on
		 * that model.
		 */
		this.checkInDesk = checkInDesk;
		this.checkInDesk.addObserver(this);
		
		/**
		 * Get the instance from the logging singleton.
		 */
		log = LoggingSingleton.getInstance();
		
		/**
		 * When the JFrame is closed, exit/terminate the application.
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/**
		 * Listen for window events.
		 */
		addWindowListener(this);
		
		/**
		 * Configure the left hand side of our UI.
		 */
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BorderLayout());
	
		try {
			securityQueue = new PassengerQueue(checkInDesk.getBookingCollection().getBookingsInQueue("Security"), "Security");
			economyQueue = new PassengerQueue(checkInDesk.getBookingCollection().getBookingsInQueue("Economy"), "Economy");
			businessQueue = new PassengerQueue(checkInDesk.getBookingCollection().getBookingsInQueue("Business"), "Business");
			leftSide.add(businessQueue, BorderLayout.NORTH);
			leftSide.add(economyQueue, BorderLayout.CENTER);
			leftSide.add(securityQueue, BorderLayout.SOUTH);
		} catch (Exception e) {
			log.addLog("Failed to create passenger queue views due to " + e.getMessage(), "error");
			// Exit application.
		}

		/**
		 * Configure the right hand side of our UI.
		 */
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		
		/**
		 * Simulation Controls
		 */
		simControl = new SimControl(checkInDesk.getSimulationTime() + "x", checkInDesk.getSimulationRunning(), checkInDesk.getSimulationDateTime());
		rightSide.add(simControl, BorderLayout.NORTH);
		
		/**
		 * Flight over view display.
		 */
		flightOverview = new FlightSummary(checkInDesk.getFlightCollection(), checkInDesk.getBookingCollection());
		rightSide.add(flightOverview, BorderLayout.SOUTH);

		/**
		 * Setup our simulation UI with right and left hand sides.
		 */
		simulationUI.setLayout(new BorderLayout());
		simulationUI.setName("flightSummary");
		simulationUI.add(rightSide, BorderLayout.EAST);
		simulationUI.add(leftSide, BorderLayout.WEST);
		simulationUI.setVisible(true);
		
		/**
		 * Add our simulation UI to our JFrame.
		 */
		this.add(simulationUI);
		
		/**
		 * Set window title.
		 */
		this.setTitle("Check In Simulator");
		
		/**
		 * Pack, set visible and position our window.
		 */
		pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}
	
	/**
	 * setSimSpeedAction
	 * Action listener that sends the simulation speed
	 * from a drop down list to the model which allows
	 * the model to adjust the speed it simulates the
	 * system at.
	 * @param e
	 */
	public void setSimSpeedAction(ActionListener e) {
		simControl.setSimSpeedAction(e);
	}
	
	/**
	 * setStartSimulationAction
	 * Action listener that sends a signal to start or
	 * stop our simulation within the model.
	 * @param e
	 */
	public void setStartSimulationAction(ActionListener e) {
		simControl.setSimRunningAction(e);
	}

	/**
	 * update
	 * Implementation of the observable interface. When the model 
	 * notifies the view of changes, this method is called so that
	 * the user interface can be updated with the changed data.
	 * @param o the observable object
	 * @param arg the arguments that accompany the model change
	 */
	@Override
	public synchronized void update(Observable o, Object arg) {
		
		/**
		 * Passenger queue updates.
		 */
		try {
			securityQueue.updatePassengerQueue(checkInDesk.getBookingCollection().getBookingsInQueue("Security"));
			economyQueue.updatePassengerQueue(checkInDesk.getBookingCollection().getBookingsInQueue("Economy"));
			businessQueue.updatePassengerQueue(checkInDesk.getBookingCollection().getBookingsInQueue("Business"));
		} catch (Exception e) {
			log.addLog("There was an error updating the queues " + e.getMessage(), "error");
		}
		
		/**
		 * Simulation controls
		 */
		simControl.updateSimControl(checkInDesk.getSimulationTime() + "x", checkInDesk.getSimulationRunning(), checkInDesk.getSimulationDateTime());
		
		/**
		 * Flight Summary
		 */
		flightOverview.updateSummary(checkInDesk.getFlightCollection(), checkInDesk.getBookingCollection());
		
		
		/**
		 * Check In Desk Updates
		 *
		checkInDeskSummary = new CheckInDeskSummary(checkinmodel.getCheckInDesk());
		checkInDeskSummary.setDeskStatusActionListener(closeStatusButton);
		rightSide.add(checkInDeskSummary, BorderLayout.CENTER);
		*/

		/**
		 * Pack changes, which updates the visible components to look right.
		 */
		pack();
	}

	/**
	 * windowClosing
	 * Window listener interface method, called when
	 * the window closes and used to trigger write to
	 * file of our log and ensure our simulation stops.
	 * @param e the window event trigger on close.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		closeWindow.actionPerformed(new ActionEvent(this, 0, "WindowClosing"));
	}

	/**
	 * setWindowClosingAction
	 * Takes an action listener that will be called when
	 * the window closes.
	 * @param e action listener called when window closes.
	 */	
	public void setWindowClosingAction(ActionListener e) {
		closeWindow = e;
	}

	/**
	 * windowOpened
	 * Creating method stubs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * windowClosed
	 * Creating method stubs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * windowIconified
	 * Creating method stubs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * windowDeiconified
	 * Creating method stubs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * windowActivated
	 * Creating method stubs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	/**
	 * windowDeactivated
	 * Creating method stubs to conform to the
	 * window listener abstract interface
	 * @param e which indicates that the window has changed status.
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}
