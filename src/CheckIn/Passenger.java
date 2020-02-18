package CheckIn;


public class Passenger { 
	private boolean checkIn = false;
	private Bag baggage;
	private String firstName;
	private String lastName;
	
	public Passenger(boolean checkIn,Bag baggage, String firstName, String lastName) {
		
		this.checkIn = checkIn;
		this.baggage = baggage;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean isCheckIn() {
		return checkIn;
	}


	public Bag getBaggage() {
		return baggage;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Boolean doesLastNameMatch() {
		if (lastName != lastName) {
			return false;
			
		} else 
			return true;
		
	}

	public void setCheckIn() {
		this.checkIn = true;
	}
	
	public void addBaggage(Bag newbaggage) {
		
		baggage = newbaggage; 	
	
	}
	
	public double getBaggageWeight() {
		if(baggage != null) {
			
			return baggage.getWeight();
		   } else {
			   return 0;
		   }
	
	}
	
public double getBaggageVolume() {
	
	if(baggage != null) {
		
		return baggage.getVolume();
	   } else {
		   return 0;
	   }
 	}
}
