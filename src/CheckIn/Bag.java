package CheckIn;

public class Bag {

	private int width;
	private int length;
	private int height;
	private double weight;
	private double excessCharge = 0;
	
	public Bag(int width, int length, int height, double weight) {
		this.width = width;
		this.length = length;
		this.height = height;
		this.weight = weight;
	}

	public int getWidth() {
		return width;
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}
	
	public double getVolume() {
		double volume = length * width * height;
		return volume;
	}

	public void setExcessCharge(double allowedWeight, double excessCharge) {
		double excessWeight = weight - allowedWeight;
		if(excessWeight >  0) {
			this.excessCharge = excessWeight * excessCharge;
		}
	}
	
	public double getExcessCharge() {
		return excessCharge;
	}
}
