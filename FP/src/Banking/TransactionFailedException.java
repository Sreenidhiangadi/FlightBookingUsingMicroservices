package Banking;

public class TransactionFailedException extends Exception {
    public TransactionFailedException(String message) {
        super(message);
//        System.out.println(message);
    }
}
 class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
        System.out.println(message);
    }
}
 class InvalidAmountException extends Exception {
	    public InvalidAmountException(String message) {
	        super(message);
	        System.out.println(message);
	    }
	}