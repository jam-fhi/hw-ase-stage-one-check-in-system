import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import org.junit.Test;
import CheckIn.Bag;
import CheckIn.BookingCollection;
import CheckIn.BookingException;
import CheckIn.CSVProcessor;
import CheckIn.CheckInIOException;
import CheckIn.FlightCollection;
import CheckIn.ReportGenerator;

public class ReportGeneratorTest {

	private String reportFile = "FlightsReportTest.csv";
	private String bookingFile = "bookings.csv";
	private String flightFile = "flights.csv";
	private double validAllowedBaggageWeight = 10.0;
	private double validExcessCharge = 10;
	private int expectedFlightReportLines = 3;
	private int validWidth = 15;
	private int validLength = 15;
	private int validHeight = 15;
	private double validWeight = 15.0;
	private int expectedCheckedIn = 1;
	private double expectedWeight = 15.0;
	private double expectedVolume = 3375.0;
	private double expectedExcessCharge = 50.0;
	private double expectedRemainingCapacity = 122;
	private double deltaPrecisionLoss = 0.01;
	private int firstReport = 0;
	
	private Bag aBag = new Bag(validWidth, validLength, validHeight, validWeight);
	
	@Test
	public void testReportGenerator() throws BookingException, CheckInIOException {
		BookingCollection Bookings = new BookingCollection(bookingFile);
		Bookings.getBooking("BA123-121", "Hill").getPassenger().addBaggage(aBag);
		Bookings.getBooking("BA123-121", "Hill").getPassenger().setCheckIn();
		Bookings.getBooking("BA123-121", "Hill").getPassenger().getBaggage().setExcessCharge(validAllowedBaggageWeight, validExcessCharge);
		Bookings.getBooking("BA124-122", "Abulhawa").getPassenger().addBaggage(aBag);
		Bookings.getBooking("BA123-123", "Ghoghari").getPassenger().addBaggage(aBag);
		Bookings.getBooking("BA124-124", "McFarland").getPassenger().addBaggage(aBag);
		Bookings.getBooking("BA125-125", "Muir").getPassenger().addBaggage(aBag);
		FlightCollection Flights = new FlightCollection(flightFile);
		new ReportGenerator(Bookings, Flights, reportFile);
		CSVProcessor csvProc = new CSVProcessor();
		ArrayList<String[]> report = csvProc.parseCSVToStringArray(reportFile);
		File file = new File(reportFile); 
		file.delete();
		assertEquals(expectedFlightReportLines, report.size());
		String[] reportLine = report.remove(firstReport);
		assertEquals(expectedCheckedIn, Integer.parseInt(reportLine[0]));
		assertEquals(expectedWeight, Double.parseDouble(reportLine[1]), deltaPrecisionLoss);
		assertEquals(expectedVolume, Double.parseDouble(reportLine[2]), deltaPrecisionLoss);
		assertEquals(expectedExcessCharge, Double.parseDouble(reportLine[3]), deltaPrecisionLoss);
		assertEquals(expectedRemainingCapacity, Integer.parseInt(reportLine[4]), deltaPrecisionLoss);
	}
}
