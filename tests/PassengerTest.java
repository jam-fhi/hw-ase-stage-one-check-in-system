	import static org.junit.Assert.assertEquals;

	import org.junit.Before;
	import org.junit.Test;
	import CheckIn.Bag;
	import CheckIn.Passenger;
	
public class PassengerTest{

			private boolean validCheckIn = false;
			private boolean validDoesLastNameMatch = true;
			private Bag validBaggage = new Bag(24,30,12,15);
			private String validFirstName = "Haikah";
			private String validLastName = "Ghoghari";
			private Passenger myPassenger = null;
			private double validBaggageWeight = 15;
			private double validBaggageVolume = 24*30*12;
			private double deltaPrecisionLoss = 0.01;

			@Before
			public void beforeEach() {
				myPassenger = new Passenger(validCheckIn, validBaggage, validFirstName, validLastName);
			}

			@Test
			public void testCheckIn() {

			
				boolean resultCheckIn = myPassenger.isCheckIn();
				assertEquals(resultCheckIn, validCheckIn);
			}

			@Test
			public void testDoesLastNameMatch() {

				
				boolean resultDoesLastNameMatch = myPassenger.doesLastNameMatch(validLastName);
				assertEquals(resultDoesLastNameMatch, validDoesLastNameMatch);
			}

			@Test
			public void testBaggage() {

			
				Bag resultBaggage = myPassenger.getBaggage();
				assertEquals(resultBaggage, validBaggage);
			}

			@Test
			public void testFirstName() {

			
				String resultFirstName = myPassenger.getFirstName();
				assertEquals(resultFirstName, validFirstName);
			}
			


			@Test
			public void testLastName() {

			
				String resultLastName = myPassenger.getLastName();
				assertEquals(resultLastName, validLastName);
			}

			
			@Test
			public void testBaggageWeight() {

			
				double resultBaggageWeight = myPassenger.getBaggageWeight();
				assertEquals(resultBaggageWeight, validBaggageWeight, deltaPrecisionLoss);
			}

			@Test
			public void testBaggageVolume() {

			
				double resultBaggageVolume = myPassenger.getBaggageVolume();
				assertEquals(resultBaggageVolume, validBaggageVolume, deltaPrecisionLoss);
			}
}
