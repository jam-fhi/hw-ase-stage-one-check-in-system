package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.CheckIn;
import checkInGUI.CheckInWireframe;

public class CheckInController {


	private CheckInWireframe view; 

	private CheckIn checkInModel; 
	

	public CheckInController(CheckInWireframe view, CheckIn checkInmodel) {
		this.view = view;
		this.checkInModel = checkInmodel;
		// specify the listener for the view
		//view.addSetListener(new SetListener());
	}


	

}
