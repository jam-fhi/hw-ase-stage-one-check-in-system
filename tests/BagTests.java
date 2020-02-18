import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import CheckIn.Bag;


public class BagTests {

		private int validWidth = 20;
		private int validLength = 58;
		private int validHeight = 38;
		private double validWeight = 7;
		private double validVolume = validLength*validWidth*validHeight;
		private Bag myBag = null;
		private double deltaPrecisionLoss = 0.01;

		@Before
		public void beforeEach() {
			myBag = new Bag(validWidth, validLength, validHeight, validWeight);
		}

		@Test
		public void testWidth() {

		
			int resultWidth = myBag.getWidth();
			assertEquals(resultWidth, validWidth);
		}


		@Test
		public void testLength() {

		
			int resultLength = myBag.getLength();
			assertEquals(resultLength, validLength);
		}


		@Test
		public void testHeight() {

		
			int resultHeight = myBag.getHeight();
			assertEquals(resultHeight, validHeight);
		}


		@Test
		public void testWeight() {

		
			double resultWeight = myBag.getWeight();
			assertEquals(resultWeight, validWeight, deltaPrecisionLoss);
		}

		@Test
		public void testVolume() {

		
			double resultVolume = myBag.getVolume();
			assertEquals(resultVolume, validVolume, deltaPrecisionLoss);
		}


}
