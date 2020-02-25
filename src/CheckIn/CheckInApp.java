package CheckIn;

import javax.swing.JOptionPane;

public class CheckInApp {
	
	private CheckInGUI checkinGUI;

    public void showGUI() throws BookingException, CheckInIOException {
		try {
			BookingCollection bookingCollection = new BookingCollection("bookings.csv");
			FlightCollection flightCollection = new FlightCollection("flights.csv");
		    checkinGUI = new CheckInGUI(bookingCollection, flightCollection);
		    checkinGUI.setVisible(true);
		    checkinGUI.setLocationRelativeTo(null);
		} catch(BookingException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch(CheckInIOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }    
    
    public static void main (String arg[]) throws BookingException, CheckInIOException  {
    	CheckInApp cia = new CheckInApp();
    	cia.showGUI();
    }
}
