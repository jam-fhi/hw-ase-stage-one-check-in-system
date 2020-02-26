package CheckIn;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ReportGenerator
 * Provides functionality to generate 
 * a flight summary report.
 * @author jamiehill
 *
 */
public class ReportGenerator {
	
	/**
	 *  Flight and booking collection variables
	 */
	private BookingCollection bookings;
	private FlightCollection flights;
	
	/**
	 * ReportGenerator
	 * Constructor, it will take in a collection of bookings and flights
	 * before generating the the statistics per flight before writing
	 * the report to the specified filename.
	 * @param bookings
	 * @param flights
	 * @param reportFileName
	 * @throws CheckInIOException
	 */
	public ReportGenerator(BookingCollection bookings, FlightCollection flights, String reportFileName) throws CheckInIOException {
		/**
		 *  Store the booking and flights collections in class variables
		 */
		this.bookings = bookings;
		this.flights = flights;
		/**
		 *  Generate the report with the specified file name
		 */
		generateReport(reportFileName);
	}
	
	/**
	 * generateReport
	 * It will use the class variables for the 
	 * booking and flight collection and generate
	 * statistics per flight before writing out
	 * a report on all flights to file.
	 * @param reportFileName
	 * @throws CheckInIOException
	 */
	private void generateReport(String reportFileName) throws CheckInIOException {
		/**
		 *  Create an iterator to loop through all the flights
		 */
		Iterator<Flight> flightsIt = flights.getFlightCollection().iterator();
		/**
		 *  Create an array list of string arrays to store the csv report output
		 */
		ArrayList<String[]> flightReport = new ArrayList<String[]>();
		/**
		 *  Loop through all the flights
		 */
		while(flightsIt.hasNext()) {
			/**
			 *  Get the next flight
			 */
			Flight aFlight = flightsIt.next();
			/**
			 *  Find all bookings for this flight
			 */
			ArrayList<Booking> flightBookings = bookings.getBookingByFlightCode(aFlight.getFlightCode());
			/**
			 *  Temporary storage for one line of the report
			 */
			String[] flightReportLine = new String[5];
			/**
			 *  If there are bookings then we want to process them
			 */
			if(flightBookings.size() > 0) {
				/**
				 *  Get the number of passengers who are checked into a flight
				 */
				int passengersCheckedIn = getPassengersCheckedIn(flightBookings);
				/**
				 *  Get the total baggage weight from the passengers who are checked in to the flight
				 */
				double totalBaggageWeight = getPassengersTotalBaggageWeight(flightBookings);
				/**
				 *  Get the total baggage volume from the passengers who are checked into the flight
				 */
				double totalBaggageVolume = getPassengersTotalBaggageVolume(flightBookings);
				/**
				 *  Get the total excess baggage weight charge from the passengers who are checked into the flight
				 */
				double totalExcessCharge = getPassengersTotalExcessCharge(flightBookings);
				/**
				 *  Get the flights remaining capacity by subtracting the checked in passenger count from the maximum passengers allowed
				 */
				int flightCapacityRemaining = aFlight.getMaximumPassengers() - passengersCheckedIn;
				/**
				 *  Added the generated report data to the temporary line string array
				 */
				flightReportLine[0] = String.valueOf(passengersCheckedIn);
				flightReportLine[1] = String.valueOf(totalBaggageWeight);
				flightReportLine[2] = String.valueOf(totalBaggageVolume);
				flightReportLine[3] = String.valueOf(totalExcessCharge);
				flightReportLine[4] = String.valueOf(flightCapacityRemaining);
				/**
				 *  Add the line to the flight report.
				 */
				flightReport.add(flightReportLine);
			}
		}
		/**
		 *  If there are entries in the flight report, write it to file
		 */
		if(flightReport.size() > 0) {
			/**
			 *  Create a new CSV processor object
			 */
			CSVProcessor csvProc = new CSVProcessor();
			/**
			 *  Give it the report file name and the flight report contents to write it to file
			 */
			csvProc.parseStringArrayToCSV(reportFileName, flightReport);
		}
	}
	
	/**
	 * getPassengersCheckedIn
	 * It will return the number of passengers 
	 * that are checked into the bookings for
	 * a given flight.
	 * @param flightBookings
	 * @return int count of checked in passengers
	 */
	private int getPassengersCheckedIn(ArrayList<Booking> flightBookings) {
		/**
		 *  Set the initial checked in passenger count to 0
		 */
		int checkedInCount = 0;
		/**
		 *  Create an iterator to process the bookings
		 */
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		/**
		 *  Loop through the bookings
		 */
		while(flightBookingsIt.hasNext()) {
			/**
			 *  Extract the passenger from the booking
			 */
			Passenger traveller = flightBookingsIt.next().getPassenger();
			/**
			 *  If the passenger is checked in
			 */
			if(traveller.isCheckIn()) {
				/**
				 *  Increment the passenger checked in count
				 */
				checkedInCount++;
			}
		}
		/**
		 *  Return the count of checked in passengers
		 */
		return checkedInCount;
	}
	
	/**
	 * getPassengersTotalBaggageWeight
	 * It will return the total baggage weight of
	 * all checked in passengers on a given flight.
	 * @param flightBookings
	 * @return double total baggage weight
	 */
	private double getPassengersTotalBaggageWeight(ArrayList<Booking> flightBookings) {
		/**
		 *  Create a total baggage weight variable and initialise it to 0
		 */
		double totalBaggageWeight = 0;
		/**
		 *  Create an iterator to process all the bookings
		 */
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		/**
		 *  Loop through the bookings while there is a next booking
		 */
		while(flightBookingsIt.hasNext()) {
			/**
			 *  Get the booking to process
			 */
			Booking aBooking = flightBookingsIt.next();
			/**
			 *  If the passenger is checked in
			 */
			if(aBooking.getPassenger().isCheckIn()) {
				/**
				 *  Add the baggage weight to the total baggage weight count
				 */
				totalBaggageWeight += aBooking.getPassenger().getBaggage().getWeight();
			}
		}
		/**
		 *  Return the total baggage weight
		 */
		return totalBaggageWeight;
	}
	
	/**
	 * getPassengersTotalBaggageVolume
	 * Returns the total volume of all baggage
	 * for all checked in passengers on a given flight.
	 * @param flightBookings
	 * @return double total baggage volume
	 */
	private double getPassengersTotalBaggageVolume(ArrayList<Booking> flightBookings) {
		/**
		 *  Create a total baggage volume variable and initialise it to 0
		 */
		double totalBaggageVolume = 0;
		/**
		 *  Create an iterator to loop through all the bookings
		 */
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		/**
		 *  Loop through the bookings while there is a next booking
		 */
		while(flightBookingsIt.hasNext()) {
			/**
			 *  Get the next booking object
			 */
			Booking aBooking = flightBookingsIt.next();
			/**
			 *  If the passenger is checked in to the flight
			 */
			if(aBooking.getPassenger().isCheckIn()) {
				/**
				 *  Add the passengers baggage volume to the total
				 */
				totalBaggageVolume += aBooking.getPassenger().getBaggage().getVolume();
			}
		}
		/**
		 *  Return the total baggage volume
		 */
		return totalBaggageVolume;
	}
	
	/**
	 * getPassengersTotalExcessCharge
	 * Will return the total excess charge
	 * for all passengers who have an excess
	 * charge on a given flight.
	 * @param flightBookings
	 * @return
	 */
	private double getPassengersTotalExcessCharge(ArrayList<Booking> flightBookings) {
		/**
		 *  Create a total excess charge variable and initialise it to 0
		 */
		double totalExcessCharge = 0;
		/**
		 *  Get an iterator to loop through all the bookings
		 */
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		/**
		 *  While there is a next flight booking
		 */
		while(flightBookingsIt.hasNext()) {
			/**
			 *  Get the next flight booking
			 */
			Booking aBooking = flightBookingsIt.next();
			/**
			 *  If the passenger is checked into the flight
			 */
			if(aBooking.getPassenger().isCheckIn()) {
				/**
				 *  Add the passengers excess baggage charge to the total
				 */
				totalExcessCharge += aBooking.getPassenger().getBaggage().getExcessCharge();
			}
		}
		/**
		 *  Return the total excess charge for the flight
		 */
		return totalExcessCharge;
	}
}
