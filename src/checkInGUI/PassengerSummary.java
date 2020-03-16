package checkInGUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PassengerSummary extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private int rowcount = 0;
	private ArrayList<String> queue = new ArrayList<String>();
	
	public void addPassengerList(String bookingCode, String passengerName) {
		//this.add(passenger);
		queue.add( bookingCode + " " + passengerName);
		//JList queueList = new JList(queue);
		//rowcount ++;
		//this.setLayout(new GridLayout(1, 1));
		//this.setVisible(true);
		
		
	}
	
	public void displayList() {
		
		FlowLayout queueLayout = new FlowLayout();
		String[] queueStrArr = new String[queue.size()];
		
		Iterator<String> queueIt = queue.iterator();
		int count = 0;
		while(queueIt.hasNext()) {
			queueStrArr[count] = queueIt.next();
			count++;
		}

		JList queueList = new JList(queueStrArr);
		queueList.setSize(50, 50);
		JLabel passengerDetails = new JLabel();
		passengerDetails.setText("There are currently" + count + " passengers waiting in the queue.");
		this.add(passengerDetails);
		
		this.add(queueList);
		//rowcount ++;
		//this.setLayout(queueLayout);
		this.setVisible(true);
	}
	

}
