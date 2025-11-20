package Banking;

public class UPIPayment implements Payment {

	 private String upiPin;

	    public UPIPayment(String upiPin) {
	        this.upiPin = upiPin;
	    }
	    public void processPayment(int amt) throws TransactionFailedException {
	    	try {
	    	if(amt<0) throw new TransactionFailedException("Amount must be positive");
	    	 if (!"1234".equals(upiPin))
	    		 System.out.println("Entered invalid credentials");
	    	 System.out.println("UPI Payment of ₹" + amt + " processed successfully!");
	    	}
	    	catch(Exception e){
	    		System.out.println("caught wiht an exception:"+e.getMessage());
	    	}
	    }

}
