package checkInGUI;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.CheckIn;

/**
 * 
 * This class sets up the main GUI, which contains the Views.
 * @author amymcfarland
 */
@SuppressWarnings("deprecation")
public class CheckInSimulation extends JFrame implements Observer, WindowListener  {
	
	/**
	 * Adding in instance variables
	 */
	private static final long serialVersionUID = 1L;
	private CheckInDeskSummary checkInDeskSummary;
	private JPanel flightSummary = new JPanel();
	private ActionListener closeStatusButton;
	private CheckIn checkInDesk;
	private SimControl simControl = null;
	private ActionListener closeWindow;
	private FlightSummary flightOverview;
	private PassengerQueue securityQueue;
	private PassengerQueue checkInQueue;
	private PassengerQueue priorityQueue;

	/**
	 * The view takes the model as a constructor parameter and adds itself as the observer on the observable model
	 * adding in a right side and left side panel
	 * adding in security, priority and checkin queue using the passengerqueue type and adding them all to the left side
	 * panel
	 * @param checkInDesk adding in the model
	 */
	public CheckInSimulation(CheckIn checkInDesk) {
		this.checkInDesk = checkInDesk;
		this.checkInDesk.addObserver(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(this);
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BorderLayout());
		securityQueue = new PassengerQueue(checkInDesk.getSecurityQueue(), "Security");
		checkInQueue = new PassengerQueue(checkInDesk.getCheckInQueue(), "Check In");
		priorityQueue = new PassengerQueue(checkInDesk.getPriorityQueue(), "Priority");
		leftSide.add(priorityQueue, BorderLayout.NORTH);
		leftSide.add(checkInQueue, BorderLayout.CENTER);
		leftSide.add(securityQueue, BorderLayout.SOUTH);
		/**
		 * Simulation Controls
		 */
		simControl = new SimControl(checkInDesk.getSimulationTime() + "x", checkInDesk.getSimulationRunning(), checkInDesk.getSimulationDateTime());
		rightSide.add(simControl, BorderLayout.NORTH);
		
		flightOverview = new FlightSummary(checkInDesk.getFlightCollection(), checkInDesk.getBookingCollection());
		rightSide.add(flightOverview, BorderLayout.SOUTH);
		
		flightSummary.setLayout(new BorderLayout());
		flightSummary.setName("flightSummary");
		flightSummary.add(rightSide, BorderLayout.EAST);
		flightSummary.add(leftSide, BorderLayout.WEST);
		flightSummary.setVisible(true);
		
		this.add(flightSummary);
		this.setSize(100, 300);
		this.setTitle("Check In Simulator");
		pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}

	/**
	 * adding ActionListener to the close button
	 * @param e
	 */
	public void setCheckInDeskAction(ActionListener e) {
		this.closeStatusButton = e;
	}
	
	/**
	 * adding ActionListener to the simulation speeds
	 * @param e
	 */
	public void setSimSpeedAction(ActionListener e) {
		simControl.setSimSpeedAction(e);
	}
	
	/**
	 * adding ActionListener to the start simulation button
	 * @param e
	 */
	public void setStartSimulationAction(ActionListener e) {
		simControl.setSimRunningAction(e);
	}

	@Override
	public synchronized void update(Observable o, Object arg) {
		
		//securityQueue = new PassengerQueue(checkInDesk.getBookingCollection().getAllBookings());
		securityQueue.updatePassengerQueue(checkInDesk.getSecurityQueue());
		checkInQueue.updatePassengerQueue(checkInDesk.getCheckInQueue());
		priorityQueue.updatePassengerQueue(checkInDesk.getPriorityQueue());
		/**
		 * On update remove any existing components on this JFrame
		flightSummary.removeAll();
		flightSummary.revalidate();
		flightSummary.repaint();

		/**
		 * Add the passenger queue to the view.
		 *
		PassengerQueue passengerQueue = new PassengerQueue(checkinmodel.getPassengerQueue());
		flightSummary.add(passengerQueue, BorderLayout.WEST);*/
		

		
		/**
		 * Simulation controls
		 */
		simControl.updateSimControlDate(checkInDesk.getSimulationTime() + "x", checkInDesk.getSimulationRunning(), checkInDesk.getSimulationDateTime());
		
		/**
		 * Flight Summary
		 */
		flightOverview.updateSummary(checkInDesk.getFlightCollection(), checkInDesk.getBookingCollection());
		
		
		/**
		 * Add checkin desks
		 *
		checkInDeskSummary = new CheckInDeskSummary(checkinmodel.getCheckInDesk());
		checkInDeskSummary.setDeskStatusActionListener(closeStatusButton);
		rightSide.add(checkInDeskSummary, BorderLayout.CENTER);
		
		/**
		 * Add flights to the flight display.
		 *
		FlightSummary flightSummaryList = new FlightSummary(checkinmodel.getFlightCollection());
		rightSide.add(flightSummaryList, BorderLayout.SOUTH);*/
		pack();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		closeWindow.actionPerformed(new ActionEvent(this, 0, "WindowClosing"));
	}

	public void setWindowClosingAction(ActionListener e) {
		closeWindow = e;
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
