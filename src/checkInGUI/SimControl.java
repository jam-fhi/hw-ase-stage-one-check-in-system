package checkInGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimControl extends JPanel {
	
	/**
	 * Defaults for interface compatibility.
	 */
	private static final long serialVersionUID = 1L;
	private String[] simSpeeds = { "1x", "2x", "3x", "4x", "5x", "6x", "7x", "8x", "9x", "10x" };
	private JComboBox<String> speedList = new JComboBox<String>(simSpeeds);
	private JButton simRunning = new JButton("Start");
	private JLabel simTime = new JLabel("Not started");
	
	public SimControl(String speed, boolean running, String simDateTime) {
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
		 * Simulation speed drop down label and list
		 */
		JPanel simSpeedPanel = new JPanel();
		simSpeedPanel.setLayout(new BorderLayout());
		simSpeedPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		simSpeedPanel.add(new JLabel("Simulation Speed:"), BorderLayout.WEST);
		
		int selectedIndex = findSimSpeed(speed);
		speedList.setSelectedIndex(selectedIndex);
		simSpeedPanel.add(speedList, BorderLayout.EAST);
		this.add(simSpeedPanel, BorderLayout.EAST);
		this.setVisible(true);
	}

	public void updateSimControlDate(String speed, boolean running, String simDateTime) {
		/**
		 * Update button text
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
			int selectedIndex = findSimSpeed(speed);
			speedList.setSelectedIndex(selectedIndex);
		}
	}

	public void setSimSpeedAction(ActionListener e) {
		speedList.addActionListener(e);
	}
	
	public void setSimRunningAction(ActionListener e) {
		simRunning.addActionListener(e);
	}
	
	private int findSimSpeed(String speed) {
		int count = 0;
		int found = -1;
		while(count < simSpeeds.length && found == -1) {
			if(simSpeeds[count].compareTo(speed) == 0 ) {
				found = count;
			}
			count++;
		}
		if(found == -1 ) {
			// Return default
			return 5;
		}else {
			// Return index found at
			return found;
		}
	}
}

