package algstudent.s3;

/* We will solve in 4 different ways the famous 
 * Fibonacci series: 0 1 1 2 3 5 8 13 21 34 55 89 ...*/
public class Fibonacci1 {
	
	//MAIN METHOD TO VERIFY PROPER OPERATION
	public static void main (String[] arg) { 
	  int n= Integer.parseInt(arg[0]);
	    
	  System.out.println("The Fibonacci number of order "+n+" is " + fib1(n));
	  int[]v = new int[60];  
	  System.out.println("The Fibonacci number of order "+n+" is " + fib2(n,v));
	  System.out.println("The Fibonacci number of order "+n+" is " + fib3(n));
	  System.out.println("The Fibonacci number of order "+n+" is " + fib4(n));
	}   

	public static int fib1(int n) {
	  int n1 = 0;
	  int n2 = 1;
	  for (int i = 1; i <= n; i++) {
	      int s= n1+n2;
	      n1 = n2;
	      n2 = s;
	  }
	  return n1;
	}  
	
	public static int fib2(int n, int[]v) {
	  v[0] = 0;
	  v[1] = 1;
	  for (int i=2; i <= n; i++) 
	      v[i]=v[i-1]+v[i-2];
	  return v[n];
	}
	
	public static int fib3(int n) {
	    return aux(0, 1, n);
	}
	
	private static int aux(int n1, int n2, int n) {
	  if (n < 1) return n1;
	    return aux(n2, n1+n2, n-1);
	}
	
	public static int fib4(int n) {
	  if (n<=1)
	     return n;
	  return fib4(n-1) + fib4(n-2);
	}

}
