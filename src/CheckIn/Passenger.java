package CheckIn;

public class Passenger { 
	private boolean checkIn = false;
	private Bag baggage;
	private String firstName;
	private String lastName;
	
	public Passenger(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Boolean doesLastNameMatch(String checkName) {
		if (lastName.compareTo(checkName) != 0) {
			return false;	
		} else {
			return true;
		}
	}

	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn() {
		this.checkIn = true;
	}

	public void addBaggage(Bag baggage) {
		this.baggage  = baggage; 	
	}

	public Bag getBaggage() {
		return baggage;
	}
}
