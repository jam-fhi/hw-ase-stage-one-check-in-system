package model;

import CheckIn.Booking;
import CheckIn.BookingCollection;
import CheckIn.Flight;
import CheckIn.FlightCollection;
import CheckIn.FlightException;
import CheckIn.ThreadNewPassenger;

public class QueueProducer implements Runnable {

	private ThreadNewPassenger aThread;
	private BookingCollection allBookings;
	private FlightCollection allFlights;
	private CheckIn model;
	
	public QueueProducer(ThreadNewPassenger aThread, BookingCollection allBookings, FlightCollection allFlights, CheckIn model) {
		this.model = model;
		this.aThread = aThread;
		this.allBookings = allBookings;
		this.allFlights = allFlights;
	}
	
	@Override
	public void run() {
		int count = 0;
		while(count < 10) {
			System.out.println("Running put passenger");
			try {
				Thread.sleep(1000);
				Booking aPassenger = allBookings.getPassengerNotCheckedIn();
				Flight aFlight = this.allFlights.findFlight(aPassenger.getFlightCode());
				aThread.put(aPassenger, aFlight);
				model.notifyObservers();
			} catch(FlightException e) {
				System.out.println(e.getMessage());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
	}

}
