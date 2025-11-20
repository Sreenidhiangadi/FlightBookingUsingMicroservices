import java.util.*;
public class HotelManagment {
	static class booking{
		public int addbooking(int hotel,int roomno) {
			if(hotels[hotel][roomno]==1) {
				System.out.println("Already booked by other person");
				return 0;
			}
			if(hotel>10) {
				System.out.println("Ivalid hotel");
				return 0;
			}
			if(roomno>10) {
				System.out.println("Invalid room no");
				return 0;
			}
			
			hotels[hotel][roomno]=1;
			
		
		return 1;}
	}
	static int hotels[][]=new int[10][10];
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name:");
		String name=sc.next();
		System.out.println("Enter your phone number:");
		String num=sc.next();
		System.out.println("Enter your mail:");
		String mail=sc.next();
		int cost=0;
		int flag=1;
		String res="";
		while(flag==1) {
			
		System.out.println("Choose a hotel:");
		
		int hotel=sc.nextInt();
		System.out.println("Choose a room number:");
		int roomno=sc.nextInt();
		booking b=new booking();
		int val=b.addbooking(hotel,roomno);
		
		if(val==1) {
			cost+=((hotel+1)*(roomno+1)*1000);
			res+="Hotel no: "+hotel+" Room no: "+roomno+"\n";
			System.out.println("Want to book another room");
			String ques=sc.next();
			if(ques.equals("yes")) {
				continue;
			}
			else {
				break;
			}
		}
		else {
			System.out.println("Check again");
			continue;
		}
		}
		System.out.println("Name: "+name+"\n Phone no: "+num+" \n mail: "+mail+"\n total cost: "+cost+"\n Rooms booked\n "+res);
	}
}