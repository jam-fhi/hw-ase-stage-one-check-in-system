package CheckIn;

/**
 * Passenger creating instance variables checkIn, baggage, firstName, and lastName
 * 
 * @author Haikah Ghoghari
 */
public class Passenger { 
	private boolean checkIn = false;
	private Bag baggage;
	private String firstName;
	private String lastName;
	
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
	 * get method to return first name
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * get method to return last name
	 * 
	 * @return lastName
	 */
	
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * creating a method to check if the last names match
	 * 
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
	 * creating method to check if check-in is done
	 * 
	 * @return true if passenger has checked in
	 * @return false if passenger has not checked in 
	 */
	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn() {
		this.checkIn = true;
	}
	
	/**
	 * creating a method that adds baggage for passenger
	 * 
	 */
	public void addBaggage(Bag baggage) {
		this.baggage  = baggage; 	
	}

	/**
	 * get method to return baggage
	 * 
	 * @return baggage
	 */
	public Bag getBaggage() {
		return baggage;
	}
}
