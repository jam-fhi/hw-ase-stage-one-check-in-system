import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import checkInModel.SimulationTimeSingleton;

public class FakeTimeTest {

	private long validWaitFor = 5;
	
	private void waitForMilliseconds(long miliseconds) {
		Date startDate = new Date();
		Date currentDate = new Date();
		while(currentDate.getTime() - startDate.getTime() < miliseconds) {
			currentDate = new Date();
		}
	}

	@Test
	public void testFakeTimeSuccess() {
		Date currentTime = SimulationTimeSingleton.getCurrentTime();
		Calendar currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time one: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		waitForMilliseconds(validWaitFor);
		System.out.println("Time two: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		waitForMilliseconds(validWaitFor);
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time three: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		waitForMilliseconds(validWaitFor);
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time four: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		waitForMilliseconds(validWaitFor);
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time five: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		waitForMilliseconds(validWaitFor);
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time six: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		waitForMilliseconds(validWaitFor);
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time seven: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		waitForMilliseconds(validWaitFor);
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time eight: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		waitForMilliseconds(validWaitFor);
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time nine: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
		
		waitForMilliseconds(validWaitFor);
		currentTime = SimulationTimeSingleton.getCurrentTime();
		currentCal = Calendar.getInstance();
		currentCal.setTime(currentTime);
		System.out.println("Time ten: " + currentCal.get(Calendar.YEAR) + "-" + currentCal.get(Calendar.MONTH) + "-" + currentCal.get(Calendar.DAY_OF_MONTH) + " " + currentCal.get(Calendar.HOUR_OF_DAY) + ":" + + currentCal.get(Calendar.MINUTE));
	}
}
