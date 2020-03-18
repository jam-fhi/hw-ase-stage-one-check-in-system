package controller;

import model.CheckIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import checkInGUI.CheckInSimulation;

public class CheckInController {

	private CheckInSimulation checkInView;

	private CheckIn checkInModel;

	public CheckInController(CheckInSimulation view, CheckIn model) {

		checkInView = view;
		checkInModel = model;
		// specify the listener for the view
		view.setCheckInDeskAction(new CheckInDeskStatusActionSetter());
		view.setSimSpeedAction(new SpeedSimulatorActionSetter());
		checkInView.update(checkInModel);
		checkInModel.registerObserver(checkInView);
	}

	public class CheckInDeskStatusActionSetter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton deskStatusButton = (JButton) e.getSource();
			String name = deskStatusButton.getName();
			checkInModel.getCheckInDesk(Integer.parseInt(name)).toggleclosestatus();
			checkInModel.notifyObservers();
		}

	}
	public class SpeedSimulatorActionSetter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> speedList = (JComboBox<String>) e.getSource();
			String selectedValue = (String) speedList.getSelectedItem();
			checkInModel.setSimulationTime(selectedValue);
			checkInModel.notifyObservers();
		}

	}

}
