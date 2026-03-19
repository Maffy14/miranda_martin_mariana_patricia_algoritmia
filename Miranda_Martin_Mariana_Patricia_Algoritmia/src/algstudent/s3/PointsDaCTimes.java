package algstudent.s3;

import java.util.Random;

public class PointsDaCTimes {
	
	private static int COLUMN_X_AND_Y = 2;
	private static int MIN = 1000;
	private static int MAX = 9999;
	private static int X_COORDINATE = 0;
	private static int Y_COORDINATE = 1;
	
	private static Random random = new Random();

	public static void main(String[] args) {
		double[][] coordinates;
		PointsDaC divideAndconquer;
		long start;
		long end;
		String output;
		
		for (int n = 1024; n <= 1000000000; n *= 2) {
			coordinates = getRandomlyGenerateNPoints(n);
			divideAndconquer = new PointsDaC(coordinates);
			
			start = System.currentTimeMillis();
			output = divideAndconquer.getDistanceNearestPoints();
			end = System.currentTimeMillis();
			
			System.out.println("n = " + n + " ** "+ "TIME = " + (end - start));
		}
	}

	private static double[][] getRandomlyGenerateNPoints(int n) {
		
		double [][] randomCoordinates = new double[n][COLUMN_X_AND_Y];
		
		for (int i = 0; i < n; i++) {
			randomCoordinates[i][X_COORDINATE] = getRandomDoubleBetween0And100();
			randomCoordinates[i][Y_COORDINATE] = getRandomDoubleBetween0And100();
		}
		
		return randomCoordinates;
	}
	
	private static double getRandomDoubleBetween0And100() {
		return random.nextDouble() * (MAX - MIN); 
		
	}
			
}


