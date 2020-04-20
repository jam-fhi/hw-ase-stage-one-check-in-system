import static org.junit.Assert.*;

import org.junit.Test;

import checkInModel.Bag;
import checkInModel.RandomBagGenerator;

/**
 * 
 * RandomBagGeneratorTest
 * Test suite for the RandomBagGeneratorTest class
 * @author christophermuir
 *
 */
public class RandomBagGeneratorTest {

	@Test
	public void testgetRandomBag() {
		double weightlimit  = 10.0;
		int volumelimit = 7;
		String message1 = "bags are identical";
		double illegalbagchance = 0.5;
		Bag bag1 = RandomBagGenerator.getRandomBag(weightlimit, volumelimit);
		Bag bag2 = RandomBagGenerator.getRandomBag(weightlimit, volumelimit);
		assertNotEquals(message1, bag1, bag2);		
	}
	/**
	 *  test an illegal bag can be produced
	 */
	@Test
	public void testillegalBag() {
		double weightlimit  = 10.0;
		int volumelimit = 7;
		double illegalbagchance = 1;
		Bag bag1 = RandomBagGenerator.getRandomBag(weightlimit, volumelimit);
		assertFalse(bag1.getVolume()<volumelimit && bag1.getWeight()<weightlimit);
	}
	/**
	 *  test if  a legal bag can be produced
	 */
	@Test
	public void getlegalbag() {
		double weightlimit  = 10.0;
		int volumelimit = 7;
		double illegalbagchance = 0;
		Bag bag1 = RandomBagGenerator.getRandomBag(weightlimit, volumelimit);
		assertTrue(bag1.getVolume()<volumelimit && bag1.getWeight()<weightlimit);
	}
}