package checkInGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CheckInDesk;

public class SimControl extends JPanel {
	
	private String[] simSpeeds = { "-5x", "-4x", "-3x", "-2x", "-1x", "0x", "1x", "2x", "3x", "4x", "5x" };
	private JComboBox<String> speedList = new JComboBox<String>(simSpeeds);
	
	public SimControl(String speed) {
		/**
		 * Simulation speed drop down list
		 */
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridLayout(1, 2));
		this.add(new JLabel("Simulation Speed:"));
		
		
		int selectedIndex = findSimSpeed(speed);
		speedList.setSelectedIndex(selectedIndex);
		this.add(speedList);
	}

	public void setSimSpeedAction(ActionListener e) {
		speedList.addActionListener(e);
	}
	
	private int findSimSpeed(String speed) {
		int count =0;
		int found= -1;
		
		while(count< simSpeeds.length && found ==-1) {
			if(simSpeeds[count]==speed ) {
				found = count;
			}
			count++;
		}
		if(found == -1 ) {
		
		return 5;
		}else {
			return found;
		}
	}
}

