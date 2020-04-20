package checkInModel;

import java.text.DecimalFormat;
/**
 * Import data structure packages
 */
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
	 * Round to 2 decimal places.
	 */
	private static DecimalFormat decPlace = new DecimalFormat("#.##");
	
	/**
	 *  Flight and booking collection variables.
	 */
	private BookingCollection bookings;
	private Flight aFlight;
	
	/**
	 * Logging Singleton
	 */
	private LoggingSingleton log;
	
	/**
	 * ReportGenerator
	 * Constructor, it will take in a collection of bookings and a flight
	 * before generating the the statistics for that flight and outputting
	 * a log entry.
	 * @param bookings
	 * @param aFlight
	 * @throws CheckInIOException
	 */
	public ReportGenerator(BookingCollection bookings, Flight aFlight) {
		/**
		 * Get the logging singleton instance.
		 */
		log = LoggingSingleton.getInstance();

		/**
		 *  Store the booking and flights collections in class variables.
		 */
		this.bookings = bookings;
		this.aFlight = aFlight;
		/**
		 *  Generate the report with the specified file name.
		 */
		generateReport();
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
	private void generateReport() {
		/**
		 *  Find all bookings for this flight.
		 */
		ArrayList<Booking> flightBookings = bookings.getBookingsByFlightCode(aFlight.getFlightCode());
		/**
		 *  If there are bookings then we want to process them.
		 */
		if(flightBookings.size() > 0) {
			/**
			 *  Get the number of passengers who are checked into a flight.
			 */
			int passengersCheckedIn = getPassengersCheckedIn(flightBookings);
			/**
			 *  Get the total baggage weight from the passengers who are checked in to the flight.
			 */
			double totalBaggageWeight = getPassengersTotalBaggageWeight(flightBookings);
			/**
			 *  Get the total baggage volume from the passengers who are checked into the flight.
			 */
			double totalBaggageVolume = getPassengersTotalBaggageVolume(flightBookings);
			/**
			 *  Get the total excess baggage weight charge from the passengers who are checked into the flight.
			 */
			double totalExcessCharge = getPassengersTotalExcessCharge(flightBookings);
			/**
			 *  Get the flights remaining capacity by subtracting the checked in passenger count from the maximum passengers allowed.
			 */
			int flightCapacityRemaining = aFlight.getMaximumPassengers() - passengersCheckedIn;
			/**
			 * Log a flight report
			 */
			String intro = "Flight " + aFlight.getFlightCode();
			String maxCapacity = " has a passenger capacity of " + aFlight.getMaximumPassengers();
			String bookingsMade = " with " + flightBookings.size() + " bookings ";
			String checkedInPassengers = "and " + passengersCheckedIn + " passengers checked in ";
			String capacityRemaining = "leaving " + flightCapacityRemaining + " seats unbooked.";
			String totalWeightVolume = " The flight has " + decPlace.format(totalBaggageWeight) + "kg of baggage taking a volume of " + decPlace.format(totalBaggageVolume) + "m3. ";
			String excessCharge = "Generating £" + decPlace.format(totalExcessCharge) + " in excess charges.";
			log.addLog(intro + maxCapacity + bookingsMade + checkedInPassengers + capacityRemaining + totalWeightVolume + excessCharge, "FlightReport");
		} else {
			log.addLog("There are no bookings on flight " + aFlight.getFlightCode(), "FlightReport");
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
		 *  Set the initial checked in passenger count to 0.
		 */
		int checkedInCount = 0;
		/**
		 *  Create an iterator to process the bookings.
		 */
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		/**
		 *  Loop through the bookings.
		 */
		while(flightBookingsIt.hasNext()) {
			/**
			 *  Extract the passenger from the booking.
			 */
			Passenger traveller = flightBookingsIt.next().getPassenger();
			/**
			 *  If the passenger is checked in.
			 */
			if(traveller.isCheckIn()) {
				/**
				 *  Increment the passenger checked in count.
				 */
				checkedInCount++;
			}
		}
		/**
		 *  Return the count of checked in passengers.
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
		 *  Create a total baggage weight variable and initialise it to 0.
		 */
		double totalBaggageWeight = 0;
		/**
		 *  Create an iterator to process all the bookings.
		 */
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		/**
		 *  Loop through the bookings while there is a next booking.
		 */
		while(flightBookingsIt.hasNext()) {
			/**
			 *  Get the booking to process.
			 */
			Booking aBooking = flightBookingsIt.next();
			/**
			 *  If the passenger is checked in.
			 */
			if(aBooking.getPassenger().isCheckIn()) {
				/**
				 *  Add the baggage weight to the total baggage weight count.
				 */
				totalBaggageWeight += aBooking.getPassenger().getBaggage().getWeight();
			}
		}
		/**
		 *  Return the total baggage weight.
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
		 *  Create a total baggage volume variable and initialise it to 0.
		 */
		double totalBaggageVolume = 0;
		/**
		 *  Create an iterator to loop through all the bookings.
		 */
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		/**
		 *  Loop through the bookings while there is a next booking.
		 */
		while(flightBookingsIt.hasNext()) {
			/**
			 *  Get the next booking object.
			 */
			Booking aBooking = flightBookingsIt.next();
			/**
			 *  If the passenger is checked in to the flight.
			 */
			if(aBooking.getPassenger().isCheckIn()) {
				/**
				 *  Add the passengers baggage volume to the total.
				 */
				totalBaggageVolume += aBooking.getPassenger().getBaggage().getVolume();
			}
		}
		/**
		 *  Return the total baggage volume.
		 */
		return totalBaggageVolume;
	}
	
	/**
	 * getPassengersTotalExcessCharge
	 * Will return the total excess charge
	 * for all passengers who have an excess
	 * charge on a given flight.
	 * @param flightBookings
	 * @return double total excess charge
	 */
	private double getPassengersTotalExcessCharge(ArrayList<Booking> flightBookings) {
		/**
		 *  Create a total excess charge variable and initialise it to 0.
		 */
		double totalExcessCharge = 0;
		/**
		 *  Get an iterator to loop through all the bookings.
		 */
		Iterator<Booking> flightBookingsIt = flightBookings.iterator();
		/**
		 *  While there is a next flight booking.
		 */
		while(flightBookingsIt.hasNext()) {
			/**
			 *  Get the next flight booking.
			 */
			Booking aBooking = flightBookingsIt.next();
			/**
			 *  If the passenger is checked into the flight.
			 */
			if(aBooking.getPassenger().isCheckIn()) {
				/**
				 *  Add the passengers excess baggage charge to the total.
				 */
				totalExcessCharge += aBooking.getPassenger().getBaggage().getExcessCharge();
			}
		}
		
		/**
		 *  Return the total excess charge for the flight.
		 */
		return totalExcessCharge;
	}
}
