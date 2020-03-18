package controller;

import model.CheckIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import checkInGUI.CheckInSimulation;

public class CheckInController {

	private CheckInSimulation checkInView;

	private CheckIn checkInModel;

	public CheckInController(CheckInSimulation view, CheckIn model) {

		checkInView = view;
		checkInModel = model;
		checkInView.update(checkInModel);
		checkInModel.registerObserver(checkInView);

		// specify the listener for the view
		view.setCheckInDeskAction(new CheckInDeskStatusActionSetter());
	}

	public class CheckInDeskStatusActionSetter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton deskStatusButton = (JButton) e.getSource();
			String name = deskStatusButton.getName();
			checkInModel.getCheckInDesk(Integer.parseInt(name)).toggleclosestatus();
			checkInModel.notifyObservers();
			System.out.println("button clicked" + deskStatusButton.getName() + "   " + checkInModel.getCheckInDesk(Integer.parseInt(name)).isClosestatus());
		}

	}

}
