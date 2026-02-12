package prime;

public class A2 {

public static void main(String[] args) {
		
		int n = 5000;
		
		for (int i = 0; i < 8; i++) {
			long start = System.currentTimeMillis();
			int[] primes = listOfPrimesA2(n);
			long end = System.currentTimeMillis();
			
			
			long executionTime = end - start;
			
			System.out.println("n =" + n + "***" + "time =" + 
					(int) (executionTime) + "milliseconds)");
			
			n = n*2;
		}
	}

	private static int[] listOfPrimesA2(int n) {

		int[] primes = new int[n];
		
		int primeIndex = 0;
		
		for (int number = 2; number < (n + 1); number++) {
			if (isPrime(number)) {
				primes[primeIndex] = number;
				primeIndex++;
			}
		}
		return primes;
	}

	private static boolean isPrime(int n) {

		boolean isPrime = true;
		
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				isPrime = false;
			}
		}
		return isPrime;
	}
}
