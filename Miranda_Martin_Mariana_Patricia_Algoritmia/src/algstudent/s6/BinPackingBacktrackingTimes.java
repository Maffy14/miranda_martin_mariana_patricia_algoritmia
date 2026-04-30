package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BinPackingBacktrackingTimes {
	private static int capacity;
	private static List<Integer> sizes;
	private static final int N_TIMES = 50000; // Constant for repetitions

	public static void main(String[] args) {
		long t1, t2;

		System.out.println("Repetitions: " + N_TIMES);
		System.out.println();
		System.out.println("TestFile\tTime(ms)\tBins");
		System.out.println("--------------------------------------------");

		for (int i = 0; i <= 9; i++) {
			String path = "src/algstudent/s6/test0" + i + ".txt";
			loadData(path);

			t1 = System.currentTimeMillis();
			
			BinPackingBactracking problem = null; 
			
			for (int repetitions = 1; repetitions <= N_TIMES; repetitions++) {
				problem = new BinPackingBactracking(capacity, sizes);
				problem.backtracking();
			}

			t2 = System.currentTimeMillis();

			System.out.println("test0" + i + ".txt\t" + (t2 - t1) + "\t\t" + problem.getBinsNeededSolution());
		}
	}

	private static void loadData(String file) {
        sizes = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            capacity = Integer.parseInt(br.readLine().trim());

            String[] parts = br.readLine().trim().split("\\s+");
            for (String s : parts) sizes.add(Integer.parseInt(s));

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading file " + file);
        }
    }
}