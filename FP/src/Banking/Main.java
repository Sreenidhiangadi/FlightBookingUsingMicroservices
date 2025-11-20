package Banking;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		HashMap<String,Integer> map=new HashMap<>();
		map.put("rahul", 1234);
		map.put("nisha", 245);
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name:");
		String name=sc.next();
		while(true) {
		
		System.out.println("enter ur credential id:");
		int id=sc.nextInt();
		if(!map.containsKey(name)||!map.get(name).equals(id)) {
			System.out.println("Invalid credentials");
			continue;	
		}
		System.out.println("Choose payment type:");
        System.out.println("1. UPI");
        System.out.println("2. Wallet");
        System.out.println("3. Credit Card");
        System.out.println("4. NetBanking");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
		try {
			System.out.println("Enter the amt:");
			int amt=sc.nextInt();
			Payment payment = switch (choice) {
            case 1 -> new UPIPayment("1234"); 
            case 2 -> new WalletPayment(11100); 
            case 3 -> new CreditCardPayment("1111-2222-3333-4444", "pass123");
            case 4 -> new NetBankingPayment("bank001", "net123");
            default -> throw new TransactionFailedException("Invalid payment type");
        };
        payment.processPayment(amt);

		}
		catch(TransactionFailedException e)
		{
			System.out.println("Transaction failed: " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Caught with exception:"+e.getMessage());
		}
		 System.out.println("\nDo you want to make another transaction? (yes/no): ");
         String cont = sc.next();
         if (!cont.equals("yes")) break;
		}
		
	}

}
