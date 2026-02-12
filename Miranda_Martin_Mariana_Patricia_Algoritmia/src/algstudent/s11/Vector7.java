package algstudent.s11;

/**
 * This program serves to measure times automatically increasing 
 * the size of the problem. In addition, we use a repetition value 
 * determined by nTimes, an argument of the program
 */
public class Vector7 {
	static int[]v1;
	static int[]v2;
	
	public static void main(String arg []) {
		int repetitions = 1000;
		long t1,t2;
		int matches = 0;
		
		for (int n=10000; n<=Integer.MAX_VALUE; n*=2){ //n is increased *5   
			  v1 = new int[n];
			  Vector1.fillIn(v1);
			  
			  v2 = new int[n];
			  Vector1.fillIn(v2);
			  
			  t1 = System.currentTimeMillis();
			  //We have to repeat the whole process to be measured
			  for (int repetition=1; repetition<=repetitions; repetition++){    	
			     matches = Vector1.matches2(v1, v2);
			  }
			  t2 = System.currentTimeMillis();
			  System.out.printf("SIZE = %d TIME = %d microseconds MATCHES = %d NTIMES = %d\n", n, t2-t1, matches, repetitions);	
		}//for
	}//main

}
