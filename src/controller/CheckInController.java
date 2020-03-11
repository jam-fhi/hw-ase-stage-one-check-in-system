package controller;

import model.CheckIn;
import checkInGUI.CheckInWireframe;

public class CheckInController {


	private CheckInWireframe checkInView; 

	private CheckIn checkInModel; 
	

	public CheckInController(CheckInWireframe view, CheckIn model) {
		checkInView = view;
		checkInModel = model;
		checkInView.update(checkInModel);
		// specify the listener for the view
		//view.addSetListener(new SetListener());
	}


	

}
