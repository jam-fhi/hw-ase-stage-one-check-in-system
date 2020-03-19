package checkInGUI;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import observer.Observer;
import model.CheckIn;

/**
 * 
 * This class sets up the main GUI, which contains the Views.
 * @author amymcfarland
 */
public class CheckInSimulation extends JFrame implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CheckInDeskSummary checkInDeskSummary;
	private JPanel flightSummary = new JPanel();
	private ActionListener closeStatusButton;
	private ActionListener simSpeed;

	public CheckInSimulation() {
		this.setSize(100, 300);
		this.setTitle("Check In Simulator");
	    pack();
	    this.setVisible(true);
		
		flightSummary.setLayout(new BorderLayout());
		flightSummary.setName("flightSummary");
	    this.setLocationRelativeTo(null);
	}

	public void update(CheckIn checkinmodel) {
		
		/**
		 * On update remove any existing components on this JFrame
		 */
		flightSummary.removeAll();
		flightSummary.revalidate();
		flightSummary.repaint();

		/**
		 * Add the passenger queue to the view.
		 */
		PassengerQueue passengerQueue = new PassengerQueue(checkinmodel.getBookingCollection().getBookingCollection());
		flightSummary.add(passengerQueue, BorderLayout.WEST);
		
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		
		/**
		 * Simulation controls
		 */
		SimControl simControl = new SimControl(checkinmodel.getSimulationTime());
		simControl.setSimSpeedAction(simSpeed);
		rightSide.add(simControl, BorderLayout.NORTH);
		
		/**
		 * Add checkin desks
		 */
		checkInDeskSummary = new CheckInDeskSummary(checkinmodel.getCheckInDesk());
		checkInDeskSummary.setDeskStatusActionListener(closeStatusButton);
		rightSide.add(checkInDeskSummary, BorderLayout.CENTER);
		
		/**
		 * Add flights to the flight display.
		 */
		FlightSummary flightSummaryList = new FlightSummary(checkinmodel.getFlightCollection());
		rightSide.add(flightSummaryList, BorderLayout.SOUTH);
		
		flightSummary.add(rightSide, BorderLayout.EAST);
		this.add(flightSummary);
		pack();
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	}

	public void setCheckInDeskAction(ActionListener e) {
		this.closeStatusButton = e;
	}
	
	public void setSimSpeedAction(ActionListener e) {
		this.simSpeed = e;
	}
}	
