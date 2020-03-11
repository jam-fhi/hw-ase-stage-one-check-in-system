package CheckIn;

/**
 * CheckInIOException
 * @author jamiehill
 *
 */
public class CheckInIOException extends Exception {

	/**
	 * Required by Java for compatibility.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * CheckInIOException
	 * Takes a exception message and passes it to the parent class.
	 * @param message
	 */
	public CheckInIOException(String message) {
		super(message);
	}
}
