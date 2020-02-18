package CheckIn;

public class InvalidBookingCode extends Exception {
	public InvalidBookingCode(String erronouscode){
		
		super("Code " + erronouscode + " invalid" );
    
		
	}
}
