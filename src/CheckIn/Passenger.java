package CheckIn;

/**
 * Passenger
 * Passenger creating instance variables checkIn, baggage, firstName, and lastName.
 * @author Haikah Ghoghari
 */
public class Passenger { 
	/**
	 * Passenger detail class variables.
	 */
	private boolean checkIn = false;
	private Bag baggage;
	private String firstName;
	private String lastName;
	private boolean securityComplete = false;
	/**
	 * Passenger creating constructor
	 * 
	 * @param firstName
	 * @param lastName
	 * 
	 */
	public Passenger(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * getFirstName
	 * Get method to return first name.
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * getLastName
	 * Get method to return last name.
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * doesLastNameMatch
	 * Creating a method to check if the last names match.
	 * @return false when last name does not match
	 * @return true when last name does match
	 */
	public Boolean doesLastNameMatch(String checkName) {
		if (lastName.compareTo(checkName) != 0) {
			return false;	
		} else {
			return true;
		}
	}

	/**
	 * isCheckIn
	 * Creating method to set check-in to true.
	 * @return true
	 */
	public boolean isCheckIn() {
		return checkIn;
	}
	
	/**
	 * setCeckIn
	 * Creating method to check if check-in is done.
	 */
	public void setCheckIn() {
		this.checkIn = true;
	}
	
	/**
	 * addBaggage
	 * Creating a method that adds baggage for passenger.
	 */
	public void addBaggage(Bag baggage) {
		this.baggage  = baggage; 	
	}
	
	/**
	 * getBaggage
	 * Returns the passengers baggage object
	 * @return Bag
	 */
	public Bag getBaggage() {
		return baggage;
	}
	
	public void setSecurityComplete () {
		securityComplete = true;
	}
	
	public boolean getSecurityComplete() {
		return securityComplete;
	}
}
