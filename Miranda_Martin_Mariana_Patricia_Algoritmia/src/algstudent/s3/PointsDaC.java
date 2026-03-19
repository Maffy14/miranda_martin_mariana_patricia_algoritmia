package algstudent.s3;

import java.util.Arrays;
import java.util.Comparator;

/**
 ----------------- D & C LOGIC -------------------
 To achieve this, the DaC algorithm relies on a very specific logic sequence:
	 1. Sort the points by their X-coordinates.
	 
	 2. Divide the points into a Left half and a Right half.
	 
	 3. Conquer by recursively finding the minimum distance in both halves 
	 to get a provisional minimum distance, $d = \min(d_L, d_R)$.
	 
	 4. Combine by checking the "Strip" (the boundary between the Left and Right halves). 
	 There might be a point on the far right of the Left half and 
	 a point on the far left of the Right half that are closer to each other than $d$. 
 
 
 */

public class PointsDaC {
	
	private static String FILENAME = "src/algstudent/s3/datos8192.txt";
	private static final int X_COORDINATE = 0;
    private static final int Y_COORDINATE = 1;
    
    private double[][] coordinates;
    private StringBuilder finalOutput;
    
    // High-level tracking variables to maintain state across recursive calls
    private double globalMinDistance = Double.MAX_VALUE;
    private String globalNearestPoints = null;

    /*
	 * Run the class, passing each of the provided filenames as a parameter, 
	 * to verify the algorithm's functionality: 
	 */
	public static void main(String[] args) {
		FileLoader loader = new FileLoader();
		double[][] coordinates = loader.loadCoordinatesFromFile(FILENAME);
		
		PointsTrivial trivial = new PointsTrivial(coordinates);
		
		System.out.println( trivial.getDistanceNearestPoints() );
	}
	
    public PointsDaC(double[][] coordinates) {
        this.coordinates = coordinates;
        this.finalOutput = new StringBuilder();
    }

    public String getDistanceNearestPoints() {
        // Step 1: Sort the array by X-coordinate before starting the recursive division
        Arrays.sort(coordinates, Comparator.comparingDouble(p -> p[X_COORDINATE]));
        
        // Trigger the recursive process
        closestPairRec(coordinates, 0, coordinates.length - 1);
        
        finalOutput.append("NEAREST POINTS: ");
        finalOutput.append(globalNearestPoints);
        finalOutput.append("\nMINIMUM DISTANCE = ");
        finalOutput.append(String.format("%.6f", globalMinDistance));
        
        return finalOutput.toString();
    }

    /**
     * The core Divide and Conquer recursive method.
     */
    private double closestPairRec(double[][] pointsByX, int left, int right) {
        int numPoints = right - left + 1;

        // Base Case: If the sub-problem is 3 points or fewer, use the trivial algorithm
        if (numPoints <= 3) {
            return trivialSearch(pointsByX, left, right);
        }

        // Divide: Find the middle point
        int mid = left + (numPoints / 2);
        double[] midPoint = pointsByX[mid];

        // Conquer: Recursively find the minimum distance in both halves
        double dl = closestPairRec(pointsByX, left, mid);
        double dr = closestPairRec(pointsByX, mid + 1, right);
        double d = Math.min(dl, dr);

        // Combine: Build the "Strip" of points close to the dividing line
        // We only care about points whose X-distance to the midPoint is less than 'd'
        double[][] strip = new double[numPoints][];
        int stripSize = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pointsByX[i][X_COORDINATE] - midPoint[X_COORDINATE]) < d) {
                strip[stripSize] = pointsByX[i];
                stripSize++;
            }
        }

        // Process the strip to see if any points spanning the boundary are closer than 'd'
        return stripClosest(strip, stripSize, d);
    }

    /**
     * Optimized trivial search for the base cases (n <= 3).
     * Notice the inner loop starts at i + 1. This prevents checking the same pair twice 
     * and avoids checking a point against itself.
     */
    private double trivialSearch(double[][] pointsByX, int left, int right) {
        double minD = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double distance = getDistanceBetweenPairDistinctPoints(pointsByX[i], pointsByX[j]);
                updateGlobalMin(distance, pointsByX[i], pointsByX[j]);
                if (distance < minD) {
                    minD = distance;
                }
            }
        }
        return minD;
    }

    /**
     * Checks points inside the central strip.
     */
    private double stripClosest(double[][] strip, int size, double d) {
        double minD = d; // Initialize minimum distance as 'd'

        // Sort the strip array according to the Y-coordinate. 
        // This ensures we only compare points that are vertically close to each other.
        Arrays.sort(strip, 0, size, Comparator.comparingDouble(p -> p[Y_COORDINATE]));

        // Pick all points one by one and try the next points until the Y-distance exceeds minD.
        // Geometrically, the inner loop executes at most 7 times, keeping this O(n).
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j][Y_COORDINATE] - strip[i][Y_COORDINATE]) < minD; j++) {
                double distance = getDistanceBetweenPairDistinctPoints(strip[i], strip[j]);
                updateGlobalMin(distance, strip[i], strip[j]);
                if (distance < minD) {
                    minD = distance;
                }
            }
        }
        return minD;
    }

    private void updateGlobalMin(double currentDistance, double[] p1, double[] p2) {
        if (currentDistance < globalMinDistance) {
            globalMinDistance = currentDistance;
            globalNearestPoints = pairDistinctPointsToString(p1, p2);
        }
    }

    private double getDistanceBetweenPairDistinctPoints(double[] coordinate1, double[] coordinate2) {
        double x = Math.pow(coordinate1[X_COORDINATE] - coordinate2[X_COORDINATE], 2);
        double y =  Math.pow(coordinate1[Y_COORDINATE] - coordinate2[Y_COORDINATE], 2);
        return Math.sqrt(x + y);
    }

    private String pairDistinctPointsToString(double[] coordinate1, double[] coordinate2) {
        return "[" + coordinate1[X_COORDINATE] + ", " + coordinate1[Y_COORDINATE] + "]"
                + " [" + coordinate2[X_COORDINATE] + ", " + coordinate2[Y_COORDINATE] + "]";
    }
}


