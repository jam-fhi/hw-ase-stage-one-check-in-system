import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import org.junit.Test;

import checkInModel.Bag;
import checkInModel.BookingCollection;
import checkInModel.BookingException;
import checkInModel.CSVProcessor;
import checkInModel.CheckInIOException;
import checkInModel.Flight;
import checkInModel.FlightCollection;
import checkInModel.ReportGenerator;

/**
 * ReportGeneratorTest
 * Test suite to ensure that the report
 * generator functionality will correctly
 * output the flights report.
 * @author jamiehill
 *
 */
public class ReportGeneratorTest {

	/**
	 * Test files to test with
	 */
	private String reportFile = "FlightsReportTest.csv";
	private String bookingFile = "bookings.csv";
	private String flightFile = "flights.csv";
	
	/**
	 *  Valid baggage weight test variable
	 */
	private double validAllowedBaggageWeight = 10.0;
	
	/**
	 *  Valid excess charge test variable
	 */
	private double validExcessCharge = 10;
	
	/**
	 *  Expected number of lines in the flight report
	 */
	private int expectedFlightReportLines = 4;
	
	/**
	 *  Valid width, length, height and weight to test with
	 */
	private int validWidth = 15;
	private int validLength = 15;
	private int validHeight = 15;
	private double validWeight = 15.0;
	
	/**
	 *  Expected number of passengers checked in
	 */
	private int expectedCheckedIn = 1;
	
	/**
	 *  Expected Weight, volume test variables
	 */
	private double expectedWeight = 15.0;
	private double expectedVolume = 3375.0;
	
	/**
	 *  Expected excess charge amount
	 */
	private double expectedExcessCharge = 50.0;
	
	/**
	 *  Expected remaining capacity test variable
	 */
	private double expectedRemainingCapacity = 119;
	
	/**
	 *  Delta Precision Loss, required for asserting double types
	 */
	private double deltaPrecisionLoss = 0.01;
	
	/**
	 *  First report index in the array list of read in report data
	 */
	private int firstReport = 0;
	
	/**
	 * Baggage object to generate test data with.
	 */
	private Bag aBag = new Bag(validWidth, validLength, validHeight, validWeight);
	
	/**
	 * testReportGenerator
	 * Tests that the report generator
	 * will correctly generate the expected
	 * report.
	 * @throws BookingException
	 * @throws CheckInIOException
	 */
	@Test
	public void testReportGenerator() throws BookingException, CheckInIOException {
		/**
		 *  Create a new booking collection and load bookings
		 */
		BookingCollection Bookings = new BookingCollection();
		/**
		 *  Add a bag to one passenger
		 */
		Bookings.getNextBooking("BA123-121", "Hill").getPassenger().addBaggage(aBag);
		/**
		 *  Set the passenger as checked in
		 */
		Bookings.getNextBooking("BA123-121", "Hill").getPassenger().setCheckIn();
		/**
		 *  Calculate the excess charge
		 */
		Bookings.getNextBooking("BA123-121", "Hill").getPassenger().getBaggage().setExcessCharge(validAllowedBaggageWeight, validExcessCharge);
		/**
		 *  Add bags to the other passengers
		 */
		Bookings.getNextBooking("BA124-122", "Abulhawa").getPassenger().addBaggage(aBag);
		Bookings.getNextBooking("BA123-123", "Ghoghari").getPassenger().addBaggage(aBag);
		Bookings.getNextBooking("BA124-124", "McFarland").getPassenger().addBaggage(aBag);
		Bookings.getNextBooking("BA125-125", "Muir").getPassenger().addBaggage(aBag);
		/**
		 *  Create a flight collection from the flight collection file
		 */
		Flight Flights = new Flight(bookingFile, bookingFile, bookingFile, expectedCheckedIn, deltaPrecisionLoss, deltaPrecisionLoss, deltaPrecisionLoss, bookingFile, bookingFile);
		/**
		 *  Create a report generator object, taking the test data to output in a report file
		 */
		new ReportGenerator(Bookings, Flights);
		/**
		 *  Get a file handle for the test report file
		 */
		File file = new File(reportFile); 
		/**
		 *  Delete the test report file as part of test clean up
		 */
		file.delete();
		/**
		 *  Assert the expected number of lines in the test report file
		 */
		assertEquals(expectedFlightReportLines, report.size());
		/**
		 *  Extract the first entry in the test report file
		 */
		String[] reportLine = report.remove(firstReport);
		/**
		 *  Assert that the number of passengers checked in is as expected
		 */
		assertEquals(expectedCheckedIn, Integer.parseInt(reportLine[0]));
		/**
		 *  Assert the weight checked in is as expected
		 */
		assertEquals(expectedWeight, Double.parseDouble(reportLine[1]), deltaPrecisionLoss);
		/**
		 *  Assert the volume checked in is as expected
		 */
		assertEquals(expectedVolume, Double.parseDouble(reportLine[2]), deltaPrecisionLoss);
		/**
		 *  Assert the excess charge checked in is as expected
		 */
		assertEquals(expectedExcessCharge, Double.parseDouble(reportLine[3]), deltaPrecisionLoss);
		/**
		 *  Assert that the remaining capacity checked in is as expected
		 */
		assertEquals(expectedRemainingCapacity, Integer.parseInt(reportLine[4]), deltaPrecisionLoss);
		
	}
	
}
