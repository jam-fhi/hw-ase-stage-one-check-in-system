package checkInModel;

/**
 * Import random number packages.
 */
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
	 * getRandomBag
	 * This method determines if the bag is to be illegal,
	 * The manner in which it is to be illegal
	 * Then calls methods to initialise the corresponding type of randomised bag
	 * @param weightlimit
	 * @param volumelimit
	 * @param illegalbagchange
	 */
	public static Bag getRandomBag(double weightLimit, int volumeLimit) {
		long time = SimulationTimeSingleton.getCurrentTime().getTime();
		boolean excessBag = time % 23 == 0 ? true : false;
		int width = excessBag ? ThreadLocalRandom.current().nextInt(volumeLimit, volumeLimit * 2) : ThreadLocalRandom.current().nextInt(0, volumeLimit);
		int height = excessBag ? ThreadLocalRandom.current().nextInt(volumeLimit, volumeLimit * 2) : ThreadLocalRandom.current().nextInt(0, volumeLimit);
		int length = excessBag ? ThreadLocalRandom.current().nextInt(volumeLimit, volumeLimit * 2) : ThreadLocalRandom.current().nextInt(0, volumeLimit);
		double weight = excessBag ? ThreadLocalRandom.current().nextInt(((int) weightLimit), ((int) weightLimit * 2)) : ThreadLocalRandom.current().nextInt(0, ((int) weightLimit));
		return new Bag(width, length, height, weight);
	}
}
