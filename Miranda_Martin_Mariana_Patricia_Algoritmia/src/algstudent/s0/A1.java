package algstudent.s0;


public class A1 {

	public static void main(String[] args) {
		
		int n = 5000;
		
		for (int i = 0; i < 8; i++) {
			long start = System.currentTimeMillis();
			int[] primes = listOfPrimesA1(n);
			long end = System.currentTimeMillis();
			
			
			long executionTime = end - start;
			
			System.out.println("n =" + n + "***" + "time =" + 
					(int) (executionTime) + " milliseconds");
			
			n = n*2;
		}
	}

	private static int[] listOfPrimesA1(int n) {

		int[] primes = new int[n];
		
		int primeIndex = 0;
		
		for (int number = 2; number < (n + 1); number++) {
			if (isPrimeA2(number)) {
				primes[primeIndex] = number;
				primeIndex++;
			}
		}
		return primes;
	}

	private static boolean isPrimeA1(int n) {

		boolean isPrime = true;
		
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				isPrime = false;
			}
		}
		return isPrime;
	} 
	
	private static boolean isPrimeA2(int n) {

		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isPrimeA3(int n) {

		for (int i = 2; i < (n / (2 + 1)); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}


}
