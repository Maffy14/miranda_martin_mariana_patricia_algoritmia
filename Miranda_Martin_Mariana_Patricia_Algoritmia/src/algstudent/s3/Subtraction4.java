package algstudent.s3;

/* Class that models T(n) =  T(n-1)+O(1)
 * Params: a=1;b=1;k=2
 * The time complexity is cubic O(n^3) 
 */
public class Subtraction4 {
	public static long rec4(int n) {
		long cont = 0;
		if (n <= 0)
			cont++;
		else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)  {
					cont++; // O(n^2)
				}
			}
			rec4(n - 1);
		}
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2, cont = 0;
		for (int n = 100; n <= 200000; n*=2) {
			t1 = System.currentTimeMillis();

			cont = rec4(n);

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main
} // class