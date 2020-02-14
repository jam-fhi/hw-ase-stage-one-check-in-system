package CheckIn;

public class Bag {

	private int width;
	private int length;
	private int height;
	private double weight;
	
	public Bag(int width, int length, int height, double weight) {

		this.width = width;
		this.length = length;
		this.height = height;
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}
	
	public double getVolume() {
		double volume = length*width*height;
		return volume;
	}
	
}
