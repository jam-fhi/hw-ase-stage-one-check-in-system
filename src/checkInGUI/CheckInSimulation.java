package checkInGUI;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import model.CheckIn;

/**
 * 
 * This class sets up the main GUI, which contains the Views.
 * @author amymcfarland
 */
@SuppressWarnings("deprecation")
public class CheckInSimulation extends JFrame implements Observer, WindowListener  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CheckInDeskSummary checkInDeskSummary;
	private JPanel flightSummary = new JPanel();
	private ActionListener closeStatusButton;
	private CheckIn checkInDesk;
	private SimControl simControl = null;
	private ActionListener closeWindow;
	private FlightSummary flightOverview;

	public CheckInSimulation(CheckIn checkInDesk) {
		this.checkInDesk = checkInDesk;
		this.checkInDesk.addObserver(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(this);
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		/**
		 * Simulation Controls
		 */
		simControl = new SimControl(checkInDesk.getSimulationTime() + "x", checkInDesk.getSimulationRunning(), checkInDesk.getSimulationDateTime());
		rightSide.add(simControl, BorderLayout.NORTH);
		
		flightOverview = new FlightSummary(checkInDesk.getFlightCollection());
		rightSide.add(flightOverview, BorderLayout.SOUTH);

		flightSummary.setLayout(new BorderLayout());
		flightSummary.setName("flightSummary");
		flightSummary.add(rightSide, BorderLayout.EAST);
		flightSummary.setVisible(true);
		
		this.add(flightSummary);
		this.setSize(100, 300);
		this.setTitle("Check In Simulator");
		pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}

	public void setCheckInDeskAction(ActionListener e) {
		this.closeStatusButton = e;
	}
	
	public void setSimSpeedAction(ActionListener e) {
		simControl.setSimSpeedAction(e);
	}
	
	public void setStartSimulationAction(ActionListener e) {
		simControl.setSimRunningAction(e);
	}

	@Override
	public synchronized void update(Observable o, Object arg) {
		
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
		flightOverview.updateSummary(checkInDesk.getFlightCollection());
		
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
