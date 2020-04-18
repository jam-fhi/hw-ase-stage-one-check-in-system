package CheckIn;

public class CheckInDeskCountSingleton {

	private static CheckInDeskCountSingleton checkindeskcount_instance = null;
	private static int totalDesks = 4;
	private int activeDesks = 0;
	private boolean inUse = false;
	
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
		takeInUse();
		if(activeDesks < totalDesks) {
			++activeDesks;
		}
		freeInUse();
	}
	
	public void decActiveDesks() {
		takeInUse();
		if(activeDesks > 0) {
			--activeDesks;
			System.out.println("Decremented active desks to " + activeDesks);
		}
		freeInUse();
	}
	
	public int getDeskCount() {
		return activeDesks;
	}
	
	private synchronized void takeInUse() {
		while (inUse) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inUse = true;
	}
	
	public synchronized void freeInUse() {
		inUse = false;
		notifyAll();
	}
}
