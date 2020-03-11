package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import CheckIn.Flight;
import model.CheckIn;

public class FlightInformation extends JFrame {
	

	private CheckIn checkIn;
	JTextArea queue, checkindesk1 ,checkindesk2 ,flight1 , flight2, flight3, displayList;
	JScrollPane scrollList;
	JTextArea result, result1, result2;
	
	
	public void flightInfo(String flightCode, String destination) {
	final String  FlightCode = "BA123";
	final String Destination = "Bali";
	
	}
	public FlightInformation(CheckIn model) {
		checkIn = model;
		

		setLayout(new BorderLayout());

		// set up window title
		setTitle("Check In Desk");
		// ensure program ends when window closes
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createNorthPanel();
		createSouthPanel(title, title);
		createCenterPanel();
		createmiddlePanel();


		setSize(600, 500);
		setVisible(true);
		

	}
	
	private void createCenterPanel() {
		
		JPanel centerPanel = new JPanel();
		JLabel title = new JLabel("Welcome to the check-in system.");
		centerPanel.add(title);
		this.add(centerPanel, BorderLayout.CENTER);
		
	}

	public void createNorthPanel() {
		// add north panel containing some buttons
		
		displayList = new JTextArea(10, 30);
		displayList.add(new JLabel("There are " + "people in the queue:"));
		displayList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		displayList.setEditable(false);
		scrollList = new JScrollPane(displayList);
		this.add(scrollList, BorderLayout.NORTH);
		


	}
	
	public void createmiddlePanel() {
	
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(1, 3));
		
		westPanel.setBackground(Color.LIGHT_GRAY);
		result = new JTextArea(10,15);
		result.setEditable(false);
		westPanel.add(result);
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(1, 2));
		eastPanel.setBackground(Color.LIGHT_GRAY);
		
		result1 = new JTextArea(10,15);
		result1.setEditable(false);
		eastPanel.add(result1);
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		westPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// add south panel to the content pane
		this.add(westPanel, BorderLayout.EAST);
		
		this.add(eastPanel, BorderLayout.WEST);

	}


public void createSouthPanel(String flightCode, String destination) {
	JPanel southPanel = new JPanel();
	southPanel.setLayout(new GridLayout(1, 3));
	JLabel flightinfo = new JLabel(flightInfo(flightCode, destination));
	JPanel flight1 = new JPanel();
	JPanel flight2 = new JPanel();
	JPanel flight3 = new JPanel();
	
	
	southPanel.add(flight1);
	southPanel.add(flight2);
	southPanel.add(flight3);
	flight1.setBorder(BorderFactory.createLineBorder(Color.black));
	flight2.setBorder(BorderFactory.createLineBorder(Color.black));
	flight3.setBorder(BorderFactory.createLineBorder(Color.black));
	result = new JTextArea(10,15);
	result.setEditable(false);
	result1 = new JTextArea(10,15);
	result1.setEditable(false);
	result2 = new JTextArea(10,15);
	result2.setEditable(false);
	
	// set up south panel containing 2 previous areas
	//JPanel flightPanel = new JPanel();
	//flightPanel.setLayout(new GridLayout(2, 10));
	flight1.add(result);
	flight2.add(result1);
	flight3.add(result2);
	

	

	// add south panel to the content pane
	//this.add(flightPanel, BorderLayout.SOUTH);

	
	//westPanel.add(result);
	this.add(southPanel, BorderLayout.SOUTH);

}
}