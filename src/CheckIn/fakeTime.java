package CheckIn;

import java.util.Date;

public final class fakeTime {
	
	private String startTime = "2020-03-01T00:00:00.000Z";
	private Date startDate = new Date (startTime);
	private Date systemTime = new Date();
	
	public Date getCurrentTime() {
		
		Date currentSystemTime = new Date();
		long deltaTime = currentSystemTime.getTime() - systemTime.getTime();
		return new Date(startDate.getTime() + (deltaTime * 100 * 60 * 60 * 60));
		
	}
	


}
