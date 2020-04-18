package CheckIn;

/**
 * Bag creating width, length, height, weight, and excessCharge.
 * @author Haikah Ghoghari
 */
public class Bag {

	private int width;
	private int length;
	private int height;
	private double weight;
	private double excessCharge = 0;

	/**
	 * Bag
	 * Bag creating constructor.
	 * 
	 * @param width
	 * @param length
	 * @param height
	 * @param weight
	 * 
	 */
	public Bag(int width, int length, int height, double weight) {
		this.width = width;
		this.length = length;
		this.height = height;
		this.weight = weight;
	}

	/**
	 * getWidth
	 * Get method to return width.
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * getLength
	 * Get method to return length.
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * gtHeight
	 * Get method to return height.
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * getWeight
	 * Get method to return weight.
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * getVolume
	 * Get method to return volume.
	 * Volume is calculated by multiplying length * width * height.
	 * @return volume
	 */	
	public double getVolume() {
		double volume = length * width * height;
		return volume;
	}

	/**
	 * setExcessCharge
	 * Creating a method to set excess charge for baggage that weight more than allowed weight.
	 * Excess weight is calculated by subtracting allowed weight from total weight.
	 * Excess charge is then calculated by multiplying excess weight with set excess charge.
	 */
	public void setExcessCharge(double allowedWeight, double excessCharge) {
		double excessWeight = weight - allowedWeight;
		if(excessWeight >  0) {
			this.excessCharge = excessWeight * excessCharge;
		}
	}

	/**
	 * getExcessCharge
	 * Get method to return excess charge.
	 * @return excessCharge
	 */
	public double getExcessCharge() {
		return excessCharge;
	}
}
