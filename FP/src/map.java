import java.util.HashMap;
import java.util.List;
import java.util.*;

public class map {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		HashMap<String,List<String>> compEmp= new HashMap<>();
		ArrayList<String> list= new ArrayList<>(Arrays.asList("Sharan", "Karan", "Shivani"));
		compEmp.put("Chubb", new ArrayList<>(Arrays.asList("Ram", "Poojitha", "Sreenidhi","Pranitha")));
		compEmp.put("Google", list);
		compEmp.put("Microsoft",new ArrayList<>(Arrays.asList("Sharanya", "Karuna", "Shiva")));
		compEmp.put("Amazon", new ArrayList<>(Arrays.asList("Bharath", "Keerthika", "Roshini")));
		compEmp.put("JPMC", list);
		System.out.println("enter company");
		String comp=sc.next();
		boolean check=true;
		while(check) {
			
			
			if(compEmp.containsKey(comp))
			{
				System.out.println(comp+" is present");
				System.out.println("Enter name:");
				String name=sc.next();
				if(compEmp.get(comp).contains(name))
				{
					System.out.println(name+" is present in company");
				    break;
				}
				else
					{System.out.println(name+"  is not present in company");
					
					   break;
					}
			}
			else
			{   System.out.println(comp+" is not present takes few min to add");
				compEmp.put(comp,new ArrayList<>());
				System.out.println(comp+" is added do u want to search an employee now");
				String opt=sc.next();
				if(opt=="no")
				check=false;
			}
		}	
		
	}

}