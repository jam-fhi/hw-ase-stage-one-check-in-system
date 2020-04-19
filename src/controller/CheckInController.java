package controller;

import model.CheckIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import checkInGUI.CheckInSimulation;

public class CheckInController {

	private CheckInSimulation checkInView;

	private CheckIn checkInModel;
	private Thread checkInSimulation;

	public CheckInController(CheckInSimulation view, CheckIn model) {

		checkInView = view;
		checkInModel = model;
		// specify the listener for the view
		checkInView.setStartSimulationAction(new CheckInSimulationStartAction());
		//view.setCheckInDeskAction(new CheckInDeskStatusActionSetter());
		checkInView.setSimSpeedAction(new SpeedSimulatorActionSetter());
		checkInView.setWindowClosingAction(new CheckInDeskCloseWindowAction());
		//checkInView.update(checkInModel);
		//checkInModel.registerObserver(checkInView);
	}
	
	public class CheckInSimulationStartAction implements ActionListener {
		
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(checkInModel.getSimulationRunning() == false) {
				checkInSimulation = new Thread (checkInModel);
				checkInSimulation.start();
			} else {
				checkInSimulation.stop();
				checkInModel.toggleSimulationRunning();
			}
		}
	}

	/*public class CheckInDeskStatusActionSetter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton deskStatusButton = (JButton) e.getSource();
			String name = deskStatusButton.getName();
			checkInModel.getCheckInDesk(Integer.parseInt(name)).toggleclosestatus();
			checkInModel.notifyObservers();
		}

	}*/

	public class CheckInDeskCloseWindowAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(checkInModel.getSimulationRunning() == true) {
				checkInSimulation.stop();
				// write log file
			}
		}
	}

	public class SpeedSimulatorActionSetter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> speedList = (JComboBox<String>) e.getSource();
			String selectedValue = (String) speedList.getSelectedItem();
			checkInModel.setSimulationTime(selectedValue);
		}
	}
}
