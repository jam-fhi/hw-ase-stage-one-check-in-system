package CheckIn;

import java.util.concurrent.ThreadLocalRandom;
/**
 * 
 * RandomBagGenerator
 * Generates random passenger bags for the simulation of real passengers
 * @author christophermuir
 *
 */
public class RandomBagGenerator {
	
	
	/**
	 *  This method generates a random legal or illegal height/weight/length of a bag
	 *  The method currently does not reproduce certain bag volumes that are extremely skewed 
	 *  as this was not felt necessary to the simulation and would have increased computation time
	 * @param weightlimit
	 * @param illegalweight
	 */
	private int randomvolume(int volumelimit , boolean illegal) {
		// checks if an illegal value or legal value is to be created then returns appropiate value
		if(illegal) {
			return ThreadLocalRandom.current().nextInt(volumelimit/3, volumelimit); 
		}else {
			return ThreadLocalRandom.current().nextInt(volumelimit/30 , volumelimit/3);
		}
		
		
	}
	/**
	 *  This method generates a random legal or illegal weight value for a bag
	 * @param weightlimit
	 * @param illegalweight
	 */
	private double randomweight(double weightlimit , boolean illegalweight) {
		// checks if an illegal value or legal value is to be created then returns appropiate value
		if(illegalweight) {
			return ThreadLocalRandom.current().nextDouble(weightlimit, weightlimit*3); 
		}else {
			return ThreadLocalRandom.current().nextDouble(weightlimit/10 , weightlimit);
		}
	}
	
	/**
	 *  This method determines if the bag is to be illegal,
	 *  The manner in which it is to be illegal
	 *  Then calls methods to initialise the corresponding type of randomised bag
	 * @param weightlimit
	 * @param volumelimit
	 * @param illegalbagchange
	 */
	public Bag getRandomBag(double weightlimit , int volumelimit , double illegalbagchance ) {
		if (Math.random() <= illegalbagchance) { // determines legality of bag
			int randomNum = ThreadLocalRandom.current().nextInt(0, 2 + 1); // determines manner in which bag is illegal
			switch(randomNum) {
			case 0:
				return new Bag(randomvolume(volumelimit,true),
						randomvolume(volumelimit,true),
						randomvolume(volumelimit,true),
						randomweight(weightlimit,false)); // illegal volume legal weight
			case 1: 
				return new Bag(randomvolume(volumelimit,false),
						randomvolume(volumelimit,false),
						randomvolume(volumelimit,false),
						randomweight(weightlimit,true)); // legal volume illegal weight
			case 2:
				return new Bag(randomvolume(volumelimit,true),
						randomvolume(volumelimit,true),
						randomvolume(volumelimit,true),
						randomweight(weightlimit,true)); // illegal volume and weight
			default: // this should never happen
				return new Bag(randomvolume(volumelimit,true),
					randomvolume(volumelimit,true),
					randomvolume(volumelimit,true),
					randomweight(weightlimit,true)); // illegal volume and weight
			
			}
		}
		else {
			return new Bag(randomvolume(volumelimit,false),
					randomvolume(volumelimit,false),
					randomvolume(volumelimit,false),
					randomweight(weightlimit,false)); // legal bag
		}
	
		
	}
}
