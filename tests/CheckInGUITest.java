import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import CheckIn.BookingException;
import CheckIn.CheckInGUI;
import CheckIn.CheckInIOException;

public class CheckInGUITest {

	@Test
	public void testCheckInGUI() {
		CheckInGUI checkInGUI = new CheckInGUI();
		assertNotNull(checkInGUI);
	}
}
