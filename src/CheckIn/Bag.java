package CheckIn;

/**
 * Bag creating width, length, height, weight, and excessCharge
 * 
 * @author Haikah Ghoghari
 */
public class Bag {

	private int width;
	private int length;
	private int height;
	private double weight;
	private double excessCharge = 0;
	
	/**
	 * Bag creating constructor
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
	 * get method to return width
	 * 
	 * @return width
	 */
	
	public int getWidth() {
		return width;
	}


	/**
	 * get method to return length
	 * 
	 * @return length
	 */
	
	public int getLength() {
		return length;
	}


	/**
	 * get method to return height
	 * 
	 * @return height
	 */
	
	public int getHeight() {
		return height;
	}
	

	/**
	 * get method to return weight
	 * 
	 * @return weight
	 */
	

	public double getWeight() {
		return weight;
	}
	

	/**
	 * get method to return volume
	 *  volume is calculated by multiplying length * width * height
	 * @return volume
	 */
	
	public double getVolume() {
		double volume = length * width * height;
		return volume;
	}
	

	/**
	 *Creating a method to set excess charge for baggage that weight more than allowed weight
	 * Excess weight is calculated by subtracting allowed weight from total weight
	 * Excess charge is then calculated by multiplying excess weight with set excess charge
	 */

	public void setExcessCharge(double allowedWeight, double excessCharge) {
		double excessWeight = weight - allowedWeight;
		if(excessWeight >  0) {
			this.excessCharge = excessWeight * excessCharge;
		}
	}
	

	/**
	 * get method to return excess charge
	 * 
	 * @return excessCharge
	 */
	
	public double getExcessCharge() {
		return excessCharge;
	}
	/**
	 * get method to return baggage
	 * 
	 * @return baggage
	 */
	public Bag getBaggage() {
		return baggage;
	}
}
