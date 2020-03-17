package controller;

import model.CheckIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import checkInGUI.CheckInSimulation;

public class CheckInController {


	private CheckInSimulation checkInView; 

	private CheckIn checkInModel; 
	

	public CheckInController(CheckInSimulation view, CheckIn model) {
		checkInView = view;
		checkInModel = model;
		checkInView.update(checkInModel);
		checkInModel.registerObserver(checkInView);
		// checkInView.addNextButtonActionListener(new NextFlightActionSetter());
		// specify the listener for the view
		//view.addSetListener(new SetListener());
	}


	public class NextFlightActionSetter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			checkInModel.getFlightCollection().setNextFlight();
			checkInModel.notifyObservers();
		}
		
	}

}
