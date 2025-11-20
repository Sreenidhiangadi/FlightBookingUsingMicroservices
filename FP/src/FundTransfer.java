
public class FundTransfer {

	public static void main(String[] args) {
//      System.out.println("This is test");
//      print();
//      add(3,4);
		 int a[] = {2, 6, 7, 17, 29, 31, 67, 89, 101};
		 int len=a.length;
		 int mid=a[len/2];
      fib(10);
      prime(500);
      linearsearch(8);
      binarysearch(8,a);
      insertionSort(a);
	}
	  public static void insertionSort(int arr[]) {
	        int n = arr.length;
	        for (int i = 1; i < n; i++) {
	            int key = arr[i];
	            int j = i - 1;
	            while (j >= 0 && arr[j] > key) {
	                arr[j + 1] = arr[j];
	                j = j - 1;
	            }
	            arr[j + 1] = key; 
	        }
	    }
public static void linearsearch(int n) {
    int a[] = {2, 6, 7, 17, 29, 31, 67, 89, 101};
    boolean found = false;

    for (int i = 0; i < a.length; i++) {
        if (a[i] == n) {
            System.out.println("It is present at index " + i);
            found = true;
            break;
        }
    }

    if (!found)
        System.out.println("It is not present");
}

public static void binarysearch(int n,int[] a)
{
	int low=0,high=a.length-1;

	while(low<=high) {
		int mid=(low+high)/2;
	if(a[mid]==n)
		{System.out.println("present");
		return;}
		
	else if(n<a[mid])
		high=mid-1;
	else
		low=mid+1;
	}
	
	System.out.println("not present");
}
public static void prime(int n)
{boolean flag=true;
	for(int i=2;i<n;i++)	
	{  flag=true;
		for(int j=2;j<=Math.sqrt(i);j++)
		{
			    if(i%j==0)
				{flag=false;
				break;
		        }
		}
			if(flag)
				System.out.println(i);
				
	}
	}

public static void fib(int n)
{  int fib[]=new int[n];
	 fib[0]=0;
	 fib[1]=1;
	for(int i=2;i<n;i++) {
		 fib[i]=fib[i-1]+fib[i-2];
	}
	for(int i=0;i<n;i++)
		System.out.print(fib[i]+" ");
	System.out.println();
		
	
}
public static void print() {
	
	System.out.println("inside print");
	
	int i = 0;
	
	  i =i+2;   //=+2;
	  System.out.println("inside print - "+i);
	  i = i-1;
	  
	  i = i*2;
	  
	  i = i/3;
	  
	  i = i%2;
}


public static void add(int i,int j) {
	 
	int result = i+j;
	
	int arr[] = {1,2,5,7,9,10};
	
	for (int k=0;k<arr.length;k++) {
		System.out.println("value of k in for loop with array  "+arr[k]);
	}
	
	System.out.println("result - "+result);
	
	for (int k=0;k<10;k++) {
		System.out.println("value of k in for loop  "+k);
	}
	
	while(result<10 ) {
		
		System.out.println("value of result in while - "+ result);
		result = result+1;
	}
	
	do {
		result++;
		System.out.println("value of result in do while = "+result);
	}
	while(result<20);
}


}