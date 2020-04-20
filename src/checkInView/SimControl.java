package checkInView;

/**
 * Import packages needed to display our simulation controls.
 */
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * SimControls
 * Displays controls for the simulation and the
 * current status of the simulation.
 * @author jamiehill
 */
public class SimControl extends JPanel {
	
	/**
	 * Defaults for interface compatibility.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Simulation speed options as a string array.
	 */
	private String[] simSpeeds = { "1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x" };
	
	/**
	 * Combo / Drop down box to display and choose simulation speed.
	 */
	private JComboBox<String> speedList = new JComboBox<String>(simSpeeds);
	
	/**
	 * Button to start / stop simulation.
	 */
	private JButton simRunning = new JButton("Start");
	
	/**
	 * Label to display the current time of the simulation, or not started if stopped.
	 */
	private JLabel simTime = new JLabel("Not started");
	
	/**
	 * Constructor that creates our simulation control.
	 * @param speed
	 * @param running
	 * @param simDateTime
	 */
	public SimControl(String speed, boolean running, String simDateTime) {
		/**
		 * Setup board of 10 pixels to pad the displayed components and
		 * set up the layout of the components to be a Border Layout.
		 */
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout());

		/**
		 * Start and stop the simulation.
		 */
		String simRunningText = running ? "Stop" : "Start";
		simRunning.setText(simRunningText);
		this.add(simRunning, BorderLayout.WEST);
		
		/**
		 * Show current simulation time
		 */
		String simDateTimeText = running ? simDateTime : "Not started";
		simTime.setText(simDateTimeText);
		this.add(simTime, BorderLayout.CENTER);
		
		/**
		 * Simulation speed drop down label and list, put these
		 * into their own panel to keep them together within
		 * the main panel. Pad the left side so that it does not
		 * rub against the simulation time label.
		 */
		JPanel simSpeedPanel = new JPanel();
		simSpeedPanel.setLayout(new BorderLayout());
		simSpeedPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		simSpeedPanel.add(new JLabel("Simulation Speed:"), BorderLayout.WEST);
		
		/**
		 * Get the index of the current simulation speed, use
		 * this to set the selection in the speed drop down.
		 */
		int selectedIndex = findSimSpeed(speed);
		speedList.setSelectedIndex(selectedIndex);
		simSpeedPanel.add(speedList, BorderLayout.EAST);
		
		/**
		 * Add the speed panel to the simulation control panel.
		 */
		this.add(simSpeedPanel, BorderLayout.EAST);
		this.setVisible(true);
	}

	/**
	 * updateSimControl
	 * Updates the data displayed on this simulation control panel.
	 * @param speed
	 * @param running
	 * @param simDateTime
	 */
	public void updateSimControl(String speed, boolean running, String simDateTime) {
		/**
		 * Update start/stop button text
		 */
		String simRunningText = running ? "Stop" : "Start";
		simRunning.setText(simRunningText);
		
		/**
		 * Update simulation date and time label
		 */
		String simDateTimeText = running ? simDateTime : "Not started";
		simTime.setText(simDateTimeText);
		
		/**
		 * Update simulation speed
		 */
		String currentSpeed = (String)speedList.getSelectedItem();
		if(currentSpeed.compareTo(speed) != 0) {
			/**
			 * We only want to apply an update if the speed has changed.
			 */
			int selectedIndex = findSimSpeed(speed);
			speedList.setSelectedIndex(selectedIndex);
		}
	}
	
	/**
	 * setSimSpeedAction
	 * Adds the action listener to the simulation speed
	 * drop down list so that the controller can update
	 * the model with the newly selected speed.
	 * @param e
	 */
	public void setSimSpeedAction(ActionListener e) {
		speedList.addActionListener(e);
	}
	
	/**
	 * setSimRunningAction
	 * Adds the action listener to the start/stop
	 * button so that button clicks can toggle the
	 * whole simulation to either started or stopped.
	 * @param e
	 */
	public void setSimRunningAction(ActionListener e) {
		simRunning.addActionListener(e);
	}
	
	/**
	 * findSimSpeed
	 * Finds the index in the simSpeed array a specified
	 * speed is at.
	 * @param speed to find in the simSpeed array
	 * @return int index the speed was found at in the simSpeed array
	 */
	private int findSimSpeed(String speed) {
		int count = 0;
		int found = -1;
		/**
		 * Loop through the speed list until the specified
		 * speed is found or we are at the end of the list.
		 */
		while(count < simSpeeds.length && found == -1) {
			if(simSpeeds[count].compareTo(speed) == 0 ) {
				found = count;
			}
			count++;
		}
		if(found == -1) {
			/**
			 *  Return default
			 */
			return 0;
		} else {
			/**
			 * Return index the speed was found at
			 */
			return found;
		}
	}
}
