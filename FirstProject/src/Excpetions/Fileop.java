package Excpetions;

import java.util.*;
import java.io.*;
 class Fileop {

	public static void main(String[] args) throws FileNotFoundException {
		
		BufferedReader reader=new BufferedReader(new FileReader("C:\\Eclipse\\eclipse\\filesop.txt"));
		String line;
		try {
			double totalAmountHDFC = 0;
		while((line=reader.readLine())!=null)
		{
			  String[] data = line.split(",");

              String senderName = data[0];
              String senderIFSC = data[3];
              double senderBalance = Double.parseDouble(data[4]);
              double transferAmount = Double.parseDouble(data[5]);
              String receiverName = data[7];
              String receiverIFSC = data[10];
              if (transferAmount > 0 && senderBalance >= transferAmount) {
                  System.out.println("Transfer Approved: " + senderName + " → " + receiverName +
                          " | Amount: " + transferAmount);

                  if (senderIFSC.startsWith("HDFC")) {
                      totalAmountHDFC += transferAmount;
                  }

              } else {
                  System.out.println(" Transfer Rejected: " + senderName + 
                          " | Amount: " + transferAmount + " | Balance: " + senderBalance);
              }
              
		}
		 System.out.println("\n Total amount paid by HDFC Bank: " + totalAmountHDFC);

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
