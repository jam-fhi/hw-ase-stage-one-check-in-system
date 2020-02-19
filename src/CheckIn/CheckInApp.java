package CheckIn;

public class CheckInApp {
	
	private CheckInGUI checkinGUI;

    public void showGUI() throws BookingException, CheckInIOException {
       checkinGUI = new CheckInGUI();
       checkinGUI.setVisible(true);
       checkinGUI.setLocationRelativeTo(null);
    }    
    
    public static void main (String arg[]) throws BookingException, CheckInIOException  {
    	CheckInApp cia = new CheckInApp();
    	cia.showGUI();
    }
}
