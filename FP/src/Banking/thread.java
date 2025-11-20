package Banking;
import java.util.*;
class transaction{
	private int amt;
	private int balance=5000;

	
	public void withdraw(int amt)
	{
		if(amt<balance)
		{
			balance=balance-amt;
			System.out.println(balance);
			System.out.println("transfer of rupess"+amt+"has be done sucessfully");
			
		}
		else
			System.out.println("insufficient balance");
	}
}
public class thread {

	public static void main(String[] args) throws InterruptedException {
		final Object inputLock = new Object();
		Scanner sc=new Scanner(System.in);
		transaction tr=new transaction();
		boolean flag=false;
		Thread t1=new Thread(()->{
			while(true) {
				synchronized (inputLock) {
					System.out.println("person 1 how much money u want to transfer or press -1 to exit");
				    int amt1=sc.nextInt();
				    if(amt1==-1) {
				    	break;
				    }
				    tr.withdraw(amt1);
				}
				
				 
			}
		   	
		});
		Thread t2=new Thread(()->{
			while(true) {
				synchronized (inputLock) {
					System.out.println("person 2 how much money u want to transfer or press -1 to exit");
				    int amt2=sc.nextInt();
				    if(amt2==-1) {
				    	break;
				    
				    }
				    tr.withdraw(amt2);
				}
				
				 
			}	
		});
		t1.start();
		t1.join();
		t2.start();
		t2.join();
				

	}

}
