package CheckIn;

/**
 * BookingException
 * @author christophermuir
 *
 */
public class BookingException extends Exception {

	/**
	 * Required by Java for compatibility.
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * BookingException
	 * Constructor that passes the exception
	 * message to the parent class.
	 */
	public BookingException(String message){
		super(message);
	}
}
