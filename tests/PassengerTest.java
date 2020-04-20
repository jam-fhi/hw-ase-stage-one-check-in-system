import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import checkInModel.Bag;
import checkInModel.Passenger;
	
public class PassengerTest{

	private boolean validDefaultCheckIn = false;
	private boolean validCheckIn = true;
	private boolean invalidDoesLastNameMatch = false;
	private boolean validDoesLastNameMatch = true;
	private Bag validBaggage = new Bag(24,30,12,15);
	private String validFirstName = "Haikah";
	private String validLastName = "Ghoghari";
	private String invalidLastName = "Hill";
	private Passenger myPassenger = null;

	@Before
	public void beforeEach() {
		myPassenger = new Passenger(validFirstName, validLastName);
	}

	@Test
	public void testCheckInDefaultValue() {
		boolean resultCheckIn = myPassenger.isCheckIn();
		assertEquals(validDefaultCheckIn, resultCheckIn);
	}
	
	@Test
	public void testCheckIn() {
		myPassenger.setCheckIn();
		boolean resultCheckIn = myPassenger.isCheckIn();
		assertEquals(validCheckIn, resultCheckIn);
	}
		
	@Test
	public void testDoesLastNameMatch() {
		boolean resultDoesLastNameMatch = myPassenger.doesLastNameMatch(validLastName);
		assertEquals(validDoesLastNameMatch, resultDoesLastNameMatch);
	}
	
	@Test
	public void testFailDoesLastNameMatch() {
		boolean resultDoesLastNameMatch = myPassenger.doesLastNameMatch(invalidLastName);
		assertEquals(invalidDoesLastNameMatch, resultDoesLastNameMatch);
	}

	@Test
	public void testBaggage() {
		myPassenger.addBaggage(validBaggage);
		Bag resultBaggage = myPassenger.getBaggage();
		assertEquals(validBaggage, resultBaggage);
	}

	@Test
	public void testFirstName() {
		String resultFirstName = myPassenger.getFirstName();
		assertEquals(validFirstName, resultFirstName);
	}

	@Test
	public void testLastName() {
		String resultLastName = myPassenger.getLastName();
		assertEquals(validLastName, resultLastName);
	}
}
