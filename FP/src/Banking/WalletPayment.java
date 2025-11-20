package Banking;

public class WalletPayment implements Payment {
	 private int balance;

	    public WalletPayment(int balance) {
	        this.balance = balance;
	    }
	  
	    @Override
	 public void processPayment(int amt) throws TransactionFailedException{
		 try {
			 if(amt>balance)throw new TransactionFailedException("Insufficient balance");
			 System.out.println("amt credited/debited to ur account sycccessfully");
			 balance-=amt;
			 System.out.println("remaining balance is: "+balance);
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
	 }
}



   
