package algstudent.s2;

/* This class measures times for the Bubble method
for the 3 assumptions: (already ordered, reverse ordered and random ordered) */
public class QuicksortLowTimes {
	static int[] v;

	public static void main(String arg[]) {
		long t1, t2;
		int nTimes = Integer.parseInt(arg[0]);
		
		for (int n = 10; n <= 600; n += 5) {
			v = new int[n];
			
			Vector.randomSorted(v);


			t1 = System.currentTimeMillis();
			
			for (int repetitions = 1; repetitions <= nTimes; repetitions++)
				Quicksort.quicksort(v);
			

			t2 = System.currentTimeMillis();

			System.out.println("Repetitions: " + nTimes+ " -> " + n + "\t" + (t2 - t1));
		}
	}
}
