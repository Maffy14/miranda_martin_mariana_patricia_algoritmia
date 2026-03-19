package algstudent.s4;

import java.util.Random;

/**
 * Create an AssignmentTimes.java class that, after randomly generating an n x n productivity 
 * matrix, calculates the execution time of the assignment algorithm: 
 */
/*
 -----------------------  java.lang.OutOfMemoryError: Java heap space -------------------------------
Option 2: Increase the JVM Heap Size (Temporary Fix)If you must run it for slightly larger numbers (like $32,768$ or $65,536$), 
you can tell the JVM to allocate more RAM to your program.In Eclipse or IntelliJ:Go to Run Configurations.
Find the VM Arguments box.Add -Xmx8G (This gives Java 8 Gigabytes of RAM. Adjust based on your computer's actual RAM).
(Note: Even with 64GB of RAM, you will never reach $n = 1,000,000$ with an $n \times n$ matrix).

 */
public class AssignmentTimes {
	
	private static int MIN = 1000;
	private static int MAX = 10000; // exclusive
	
	private static Random random = new Random();
	
	public static void main(String[] args) {
		 
		int[][] productivity;
		Assignment assignment;
		long start;
		long end;
		String output;
		
		for (int n = 1024; n <= 1000000; n *= 2) {
			productivity = getRandomMatrixProductivity(n);
			assignment = new Assignment();
			
			start = System.currentTimeMillis();
			output = assignment.determineAssingmentEmployee(productivity);
			end = System.currentTimeMillis();
			
			System.out.println("n = " + n + " ** "+ "TIME = " + (end - start));
		}
	}
	
	private static int[][] getRandomMatrixProductivity(int n) {
		
		int [][] productivity = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				productivity[i][j] = random.nextInt(MIN, MAX);;
			}
		}
		return productivity;
	}
	
	

}
