package CheckIn;

import java.util.concurrent.ThreadLocalRandom;
/**
 * 
 * RandomBagGenerator
 * Generates random passenger bags for the simulation of real passengers.
 * @author christophermuir
 *
 */
public class RandomBagGenerator {
	
	/**
	 * randomvolume
	 * This method generates a random legal or illegal height/weight/length of a bag
	 * the method currently does not reproduce certain bag volumes that are extremely skewed 
	 * as this was not felt necessary to the simulation and would have increased computation time.
	 * @param weightlimit
	 * @param illegalweight
	 */
	private static int randomvolume(double volumelimit, boolean illegal) {
		/**
		 *  Checks if an illegal value or legal value is to be created then returns appropriate value.
		 */
		if(illegal) {
			return ThreadLocalRandom.current().nextInt(((int)volumelimit), ((int)volumelimit*2)); 
		} else {
			return ThreadLocalRandom.current().nextInt(1, ((int)volumelimit));
		}
	}

	/**
	 * randomweight
	 * This method generates a random legal or illegal weight value for a bag.
	 * @param weightlimit
	 * @param illegalweight
	 */
	private static double randomweight(double weightlimit , boolean illegalweight) {
		/**
		 * Checks if an illegal value or legal value is to be created then returns appropriate value.
		 */
		if(illegalweight) {
			return ThreadLocalRandom.current().nextDouble(weightlimit, weightlimit*3); 
		} else {
			return ThreadLocalRandom.current().nextDouble(weightlimit/10 , weightlimit);
		}
	}
	
	/**
	 * getRandomBag
	 * This method determines if the bag is to be illegal,
	 * The manner in which it is to be illegal
	 * Then calls methods to initialise the corresponding type of randomised bag
	 * @param weightlimit
	 * @param volumelimit
	 * @param illegalbagchange
	 */
	public static Bag getRandomBag(double weightlimit, double volumelimit, double illegalbagchance ) {
		/**
		 * Determines legality of bag.
		 */
		if (Math.random() <= illegalbagchance) {
			/**
			 * Determines manner in which bag is illegal.
			 */
			int randomNum = ThreadLocalRandom.current().nextInt(0, 2 + 1);
			switch(randomNum) {
			case 0:
				/**
				 * Illegal volume legal weight.
				 */
				return new Bag(randomvolume(volumelimit,true), randomvolume(volumelimit,true), randomvolume(volumelimit,true), randomweight(weightlimit,false));
			case 1: 
				/**
				 * Legal volume illegal weight.
				 */
				return new Bag(randomvolume(volumelimit,false), randomvolume(volumelimit,false), randomvolume(volumelimit,false), randomweight(weightlimit,true));
			case 2:
				/**
				 * Illegal volume and weight.
				 */
				return new Bag(randomvolume(volumelimit,true), randomvolume(volumelimit,true), randomvolume(volumelimit,true), randomweight(weightlimit,true));
			default: 
				/**
				 *  This should never happen
				 *  Illegal volume and weight
				 */
				return new Bag(randomvolume(volumelimit,true), randomvolume(volumelimit,true), randomvolume(volumelimit,true), randomweight(weightlimit,true));
			}
		}
		else {
			/**
			 * Legal bag.
			 */
			return new Bag(randomvolume(volumelimit,false), randomvolume(volumelimit,false), randomvolume(volumelimit,false), randomweight(weightlimit,false));
		}
	}
}
