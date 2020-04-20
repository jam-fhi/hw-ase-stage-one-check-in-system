import static org.junit.Assert.*;
import org.junit.Test;
import checkInModel.Bag;
import checkInModel.RandomBagGenerator;

/**
 * 
 * RandomBagGeneratorTest Test suite for the RandomBagGeneratorTest class
 * 
 * @author christophermuir
 *
 */
public class RandomBagGeneratorTest {

	private double validWeightLimit = 10;
	private double invalidWeightLimit = 0;
	private int invalidVolumeLimit = 0;
	private int validVolumeLimit = 7;
	private double validWeight = 1;
	private double validVolume = 1;

	@Test
	public void testValidWeight() {
		Bag bag = RandomBagGenerator.getRandomBag(validWeightLimit, validVolumeLimit);
		assertTrue(bag.getWeight() >= validWeight);
	}

	@Test
	public void testInvalidWeight() {
		Bag bag = RandomBagGenerator.getRandomBag(invalidWeightLimit, validVolumeLimit);
		assertTrue(bag.getWeight() == validWeight);
	}

	@Test
	public void testInvalidVolume() {
		Bag bag = RandomBagGenerator.getRandomBag(invalidWeightLimit, invalidVolumeLimit);
		assertTrue(bag.getVolume() >= validVolume);
	}

	@Test
	public void testValidVolume() {
		Bag bag = RandomBagGenerator.getRandomBag(invalidWeightLimit, validVolumeLimit);
		assertTrue(bag.getVolume() == validVolume);
	}

}