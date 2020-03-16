package checkInGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import CheckIn.Bag;

/**
 * @author amymcfarland
 *
 */
public class PassengerInformation extends JPanel {
	
private static final long serialVersionUID = 1L;
private JTextArea displayList;
JTextArea result, result1, result2;
private JScrollPane scrollList;
	
	public PassengerInformation(String bookingCode, String passengerFirstName, String passengerLastName) {
		
		
		
	
		JLabel bookingCodeLbl = new JLabel();
		bookingCodeLbl.setText(bookingCode);
	
		JLabel passengerFirstNameLbl = new JLabel();
		passengerFirstNameLbl.setText(passengerFirstName);
		JLabel passengerLastNameLbl = new JLabel();
		passengerLastNameLbl.setText(passengerLastName);
		
		
		
		
		
		this.add(bookingCodeLbl);
		this.add(passengerFirstNameLbl);
		this.add(passengerLastNameLbl);
		
		
		//this.add(displayList, BorderLayout.NORTH);
		

		this.setVisible(true);
	}
	
	
}
