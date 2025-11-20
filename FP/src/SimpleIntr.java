import java.util.Scanner;

public class SimpleIntr {
static class cal{
	public void cali(int amt,int tenure,int rate)
	{
		int tot=(amt*tenure*rate)/100;
		System.out.println(tot);
	}
	  public void compcali(int p,int t,int r)
	  {
		   double amount = p * Math.pow((1 + r / (100.0)), t);
	        double ci = amount - p;
	        System.out.println(ci);
	  }
}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("type of car");
		String type=sc.next();
		
		int amt=0;
		switch(type) {
		case "delta":
			 amt= 800000;
			break;
		case "beta":
			amt= 1000000;
			break;
		case "alpha":
			amt= 1200000;
			break;
			}
		System.out.println("enter down payment");
		int dp=sc.nextInt();
		System.out.println("enter colour:");
		String color=sc.next();
		System.out.println("enter tenure");
		int tenure=sc.nextInt();
		System.out.println("enter rate");
		int rate=sc.nextInt();
		cal obj=new cal();
		obj.cali(amt-dp,tenure,rate);
		obj.compcali(amt-dp, tenure, rate);

	}

}
