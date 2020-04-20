import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import checkInModel.Bag;


public class BagTests {

	private int validWidth = 20;
	private int validLength = 58;
	private int validHeight = 38;
	private double validWeight = 7;
	private double validAllowedWeight = 6;
	private double invalidAllowedWeight = 10;
	private double invalidExcessCharge = 0;
	private double validVolume = validLength*validWidth*validHeight;
	private double validExcessCharge = 10;
	private double expectedExcessCharge = 10;
	private Bag myBag = null;
	private double deltaPrecisionLoss = 0.01;

	@Before
	public void beforeEach() {
		myBag = new Bag(validWidth, validLength, validHeight, validWeight);
	}

	@Test
	public void testWidth() {
		int resultWidth = myBag.getWidth();
		assertEquals(validWidth, resultWidth);
	}

	@Test
	public void testLength() {	
		int resultLength = myBag.getLength();
		assertEquals(validLength, resultLength);
	}

	@Test
	public void testHeight() {
		int resultHeight = myBag.getHeight();
		assertEquals(validHeight, resultHeight);
	}

	@Test
	public void testWeight() {
		double resultWeight = myBag.getWeight();
		assertEquals(validWeight, resultWeight, deltaPrecisionLoss);
	}

	@Test
	public void testVolume() {		
		double resultVolume = myBag.getVolume();
		assertEquals(validVolume, resultVolume, deltaPrecisionLoss);
	}
	
	@Test
	public void testExcessCharge() {
		myBag.setExcessCharge(validAllowedWeight, validExcessCharge);
		double actualExcessCharge = myBag.getExcessCharge();
		assertEquals(expectedExcessCharge, actualExcessCharge, deltaPrecisionLoss);
	}
	
	@Test
	public void testUnsuccessfulExcessCharge() {
		myBag.setExcessCharge(invalidAllowedWeight, validExcessCharge);
		double actualExcessCharge = myBag.getExcessCharge();
		assertEquals(invalidExcessCharge, actualExcessCharge, deltaPrecisionLoss);
	}
}
