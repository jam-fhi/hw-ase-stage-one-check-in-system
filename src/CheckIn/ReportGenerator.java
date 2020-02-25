package CheckIn;

import java.util.ArrayList;
import java.util.Iterator;

public class ReportGenerator {
	
	private BookingCollection bookings;
	private FlightCollection flights;
	
	public ReportGenerator(BookingCollection bookings, FlightCollection flights, String reportFileName) throws CheckInIOException {
		this.bookings = bookings;
		this.flights = flights;
		generateReport(reportFileName);
	}
	
	private void generateReport(String reportFileName) throws CheckInIOException {
		Iterator<Flight> flightsIt = flights.getFlightCollection().iterator();
		ArrayList<String[]> flightReport = new ArrayList<String[]>();
		while(flightsIt.hasNext()) {
			Flight aFlight = flightsIt.next();
			ArrayList<Booking> flightBookings = bookings.getBookingByFlightCode(aFlight.getFlightCode());
			String[] flightReportLine = new String[5];
			if(flightBookings.size() > 0) {
				int passengersCheckedIn = getPassengersCheckedIn(flightBookings);
				double totalBaggageWeight = getPassengersTotalBaggageWeight(flightBookings);
				double totalBaggageVolume = getPassengersTotalBaggageVolume(flightBookings);
				double totalExcessCharge = getPassengersTotalExcessCharge(flightBookings);
				int flightCapacityRemaining = aFlight.getMaximumPassengers() - passengersCheckedIn;
				flightReportLine[0] = String.valueOf(passengersCheckedIn);
				flightReportLine[1] = String.valueOf(totalBaggageWeight);
				flightReportLine[2] = String.valueOf(totalBaggageVolume);
				flightReportLine[3] = String.valueOf(totalExcessCharge);
				flightReportLine[4] = String.valueOf(flightCapacityRemaining);
				flightReport.add(flightReportLine);
			}
		}
		if(flightReport.size() > 0) {
			CSVProcessor csvProc = new CSVProcessor();
			csvProc.parseStringArrayToCSV(reportFileName, flightReport);
		}
	}
	
	private int getPassengersCheckedIn(ArrayList<Booking> flightBookings) {
		int checkedInCount = 0;
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		while(flightBookingsIt.hasNext()) {
			Passenger traveller = flightBookingsIt.next().getPassenger();
			if(traveller.isCheckIn()) {
				checkedInCount++;
			}
		}
		return checkedInCount;
	}
	
	private double getPassengersTotalBaggageWeight(ArrayList<Booking> flightBookings) {
		double totalBaggageWeight = 0;
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		while(flightBookingsIt.hasNext()) {
			Booking aBooking = flightBookingsIt.next();
			if(aBooking.getPassenger().isCheckIn()) {
				totalBaggageWeight += aBooking.getPassenger().getBaggage().getWeight();
			}
		}
		return totalBaggageWeight;
	}
	
	private double getPassengersTotalBaggageVolume(ArrayList<Booking> flightBookings) {
		double totalBaggageVolume = 0;
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		while(flightBookingsIt.hasNext()) {
			Booking aBooking = flightBookingsIt.next();
			if(aBooking.getPassenger().isCheckIn()) {
				totalBaggageVolume += aBooking.getPassenger().getBaggage().getVolume();
			}
		}
		return totalBaggageVolume;
	}
	
	private double getPassengersTotalExcessCharge(ArrayList<Booking> flightBookings) {
		double totalExcessCharge = 0;
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		while(flightBookingsIt.hasNext()) {
			Booking aBooking = flightBookingsIt.next();
			if(aBooking.getPassenger().isCheckIn()) {
				totalExcessCharge += aBooking.getPassenger().getBaggage().getExcessCharge();
			}
		}
		return totalExcessCharge;
	}
}
