package CheckIn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class FlightCollection {
	FlightComparator flightComp = new FlightComparator();
	TreeSet<Flight> flightCollection = new TreeSet<Flight>(flightComp)

	public void loadFlights(String fileName) throws FileNotFoundException, IOException {
	
	CSVProcessor csvProc = new CSVProcessor ();
	
	ArrayList<String[]> rawFlights = csvProc.parseCSVToStringArray(fileName);
	
	Iterator<String[]> flightIt = rawFlights.iterator();
	while(flightIt.hasNext()) {
		String[] aFlight = flightIt.next();
		String flightCode = aFlight[2];
		String destinationAirport = aFlight[0];
		String carrier = aFlight[1];
		int maximumPassengers = Integer.parseInt(aFlight[3]);
		double maximumBaggageWeight = Double.parseDouble(aFlight[4]);
		double maximumBaggageVolume = Double.parseDouble(aFlight[5]);
		double excessCharge = Double.parseDouble(aFlight[6]);
		
		
	Flight addFlight = new Flight(flightCode, destinationAirport, carrier, maximumPassengers, maximumBaggageWeight, maximumBaggageVolume, excessCharge);
		
	flightCollection.add(addFlight);
		
	}
	}
	
	public Flight findFlight(String flightCode) throws Exception {
		Iterator<Flight> iterator = flightCollection.iterator();
		while(iterator.hasNext()) {
			Flight aFlight = iterator.next();
			if(aFlight.getFlightCode() == flightCode) {
				return aFlight;
		}

	}
		throw new Exception("Flight not found");
}

}