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
		/**
		 * Due to the way the random number is generated, the bound must always be
		 * greater than the start. By ensuring we always start on >= 1 we won't have
		 * a problem. Introduces more randomness as higher values can result in an
		 * excess bag charge.
		 */
		double useWeightLimit = weightLimit < 1 ? 1 : weightLimit;
		int useVolumeLimit = volumeLimit < 1 ? 1 : volumeLimit;
		long time = SimulationTimeSingleton.getCurrentTime().getTime();
		boolean excessBag = time % 23 == 0 ? true : false;
		int width = excessBag ? ThreadLocalRandom.current().nextInt(useVolumeLimit, useVolumeLimit * 2) : ThreadLocalRandom.current().nextInt(0, useVolumeLimit);
		int height = excessBag ? ThreadLocalRandom.current().nextInt(useVolumeLimit, useVolumeLimit * 2) : ThreadLocalRandom.current().nextInt(0, useVolumeLimit);
		int length = excessBag ? ThreadLocalRandom.current().nextInt(useVolumeLimit, useVolumeLimit * 2) : ThreadLocalRandom.current().nextInt(0, useVolumeLimit);
		double weight = excessBag ? ThreadLocalRandom.current().nextInt(((int) useWeightLimit), ((int) useWeightLimit * 2)) : ThreadLocalRandom.current().nextInt(0, ((int) useWeightLimit));
		return new Bag(width, length, height, weight);
	}
}
