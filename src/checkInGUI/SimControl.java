package checkInGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimControl extends JPanel implements ActionListener {

	public SimControl() {
		/**
		 * Simulation speed drop down list
		 */
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridLayout(1, 2));
		this.add(new JLabel("Simulation Speed:"));
		String[] simSpeeds = { "-5x", "-4x", "-3x", "-2x", "-1x", "0x", "1x", "2x", "3x", "4x", "5x" };

		JComboBox<String> speedList = new JComboBox<String>(simSpeeds);
		speedList.setSelectedIndex(5);
		speedList.addActionListener(this);
		this.add(speedList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
