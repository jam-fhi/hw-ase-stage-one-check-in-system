package CheckIn;

public class CheckInDeskCountSingleton {

	private static CheckInDeskCountSingleton checkindeskcount_instance = null;
	private static int totalDesks = 4;
	private int activeDesks = 0;

	private CheckInDeskCountSingleton() {
		
	}

	public static CheckInDeskCountSingleton getInstance() {
		if(checkindeskcount_instance == null) {
			checkindeskcount_instance = new CheckInDeskCountSingleton();
		}
		return checkindeskcount_instance;
	}
	
	public boolean canAddNewDesk() {
		return activeDesks < totalDesks ? true : false;
	}
	
	public void incActiveDesks() {
		if(activeDesks < totalDesks) {
			++activeDesks;
		}
	}
	
	public void decActiveDesks() {
		if(activeDesks > 0) {
			--activeDesks;
		}
	}
	
	public int getDeskCount() {
		return activeDesks;
	}
}
