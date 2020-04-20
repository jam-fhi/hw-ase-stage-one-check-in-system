package checkInModel;

/**
 * FlightException
 * Exception for flight related issues.
 * @author NadiaAbulhawa
 *
 */
public class FlightException extends Exception {
	
	/**
	 * Required by Java for compatibility.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * FlightException
	 * Takes an exception message and passes it to the parent class.
	 * @param message
	 */
	public FlightException(String message){
		super(message);
	}
}
