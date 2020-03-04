import static org.junit.Assert.*;

import org.junit.Test;

import CheckIn.Bag;
import CheckIn.RandomBagGenerator;

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
		RandomBagGenerator generator = new RandomBagGenerator();
		Bag bag1 = generator.getRandomBag(weightlimit, volumelimit, illegalbagchance);
		Bag bag2 = generator.getRandomBag(weightlimit, volumelimit, illegalbagchance);
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
		RandomBagGenerator generator = new RandomBagGenerator();
		Bag bag1 = generator.getRandomBag(weightlimit, volumelimit, illegalbagchance);
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
		RandomBagGenerator generator = new RandomBagGenerator();
		Bag bag1 = generator.getRandomBag(weightlimit, volumelimit, illegalbagchance);
		assertTrue(bag1.getVolume()<volumelimit && bag1.getWeight()<weightlimit);
	}
}