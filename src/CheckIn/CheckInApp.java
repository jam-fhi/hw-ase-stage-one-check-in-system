package CheckIn;
public class CheckInApp {
	

//private CheckInGUI checkinGUI;

    
    /**
     * Show GUI
     */
    public void showGUI() {
       // checkinGUI = new CheckInGUI();
       // checkinGUI.setVisible(true);
       // checkinGUI.setLocationRelativeTo(null);
    }    
    
    
    public static void main (String arg[])  {
       	//creates demo object, with a populated staff list
    	CheckInApp cia = new CheckInApp();   
    	
    	//allow user to interact using a GUI
    	cia.showGUI();

    	
    }

}

