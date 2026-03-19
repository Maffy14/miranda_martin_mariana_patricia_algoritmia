package algstudent.s3;

import java.util.Arrays;
import java.util.Comparator;

/*
----------------- D & C LOGIC -------------------
	 1. Sort the points by their X-coordinates.
	 
	 2. Divide the points into a Left half and a Right half.
	 
	 3. Conquer by recursively finding the minimum distance in both halves 
	 to get a provisional minimum distance, i.e., distance = min(distanceLeft, distanceRight).
	 
	 4. Combine by checking the boundary between the Left and Right halves. 
		 There might be a point on the far right of the Left half and 
		 a point on the far left of the Right half that are closer to each other than currentMinimumDistance. 


*/

/**
 * Optimized Divide and Conquer algorithm to find the closest pair of points.
 * Complexity: O(n log n)
 */
public class PointsDaC {
    
	private static String FILENAME = "src/algstudent/s3/datos8192.txt";
	
    // Coordinate index constants
    private static final int X_COORDINATE = 0;
    private static final int Y_COORDINATE = 1;
    
    private double[][] coordinates;
    private StringBuilder finalOutput;
    
    // Global variables to store the final results
    private double globalMinDistance = Double.MAX_VALUE;
    private String globalNearestPoints = null;
    
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

    /**
     * Public method that calls auxiliary private method.
     * It prepares the sorted arrays required.
     * Complexity -> O(n log n).
     */
    public String getDistanceNearestPoints() {
        // Initial sort by X-coordinate -> O(n log n)
        double[][] pointsSortedByX = coordinates.clone();
        Arrays.sort(pointsSortedByX, Comparator.comparingDouble(p -> p[X_COORDINATE]));
        
        // Initial sort by Y-coordinate -> O(n log n)
        // This is the key to avoiding re-sorting inside the recursion.
        double[][] pointsSortedByY = coordinates.clone();
        Arrays.sort(pointsSortedByY, Comparator.comparingDouble(p -> p[Y_COORDINATE]));
        
        // Start the recursive process
        closestPairRec(pointsSortedByX, pointsSortedByY, 0, pointsSortedByX.length - 1);
        
        // Build the final output message
        finalOutput.append("NEAREST POINTS: ");
        finalOutput.append(globalNearestPoints);
        finalOutput.append("\nMINIMUM DISTANCE = ");
        finalOutput.append(String.format("%.6f", globalMinDistance));
        
        return finalOutput.toString();
    }

    /**
     * Recursive method.
     * @param pointsByX Subarray already sorted by X.
     * @param pointsByY Subarray containing the same points but sorted by Y.
     */
    private double closestPairRec(double[][] pointsByX, double[][] pointsByY, int left, int right) {
        int numPoints = right - left + 1;

        // Base Case
        if (numPoints <= 3) {
            return trivialSearch(pointsByX, left, right);
        }

        // Find the middle point based on X-coordinates
        int mid = left + (numPoints / 2);
        double[] midPoint = pointsByX[mid];

        // pointsByY into two halves (Left and Right) while maintaining Y-order.

        double[][] yLeft = new double[mid - left + 1][];
        double[][] yRight = new double[right - mid][];
        int li = 0, ri = 0;
        
        // Kind of sort
        for (int i = 0; i < pointsByY.length; i++) {
            // A point goes to the left subarray if its X is <= the midpoint's X
            if (pointsByY[i][X_COORDINATE] <= midPoint[X_COORDINATE] && li < yLeft.length) {
                yLeft[li++] = pointsByY[i];
            } else {
                yRight[ri++] = pointsByY[i];
            }
        }

        // Recursive calls for both halves
        double distanceLeft = closestPairRec(pointsByX, yLeft, left, mid);
        double distanceRight = closestPairRec(pointsByX, yRight, mid + 1, right);
        
        // Initial minimum distance from the two halves
        double currentMinimumDistance = Math.min(distanceLeft, distanceRight);

        // Check the central strip for points that might be closer across the boundary.
        // (already sorted by Y)
        double[][] strip = new double[numPoints][];
        int stripSize = 0;
        for (int i = 0; i < pointsByY.length; i++) {
            if (Math.abs(pointsByY[i][X_COORDINATE] - midPoint[X_COORDINATE]) < currentMinimumDistance) {
                strip[stripSize++] = pointsByY[i];
            }
        }

        // Return the minimum distance found in this subproblem
        return stripClosest(strip, stripSize, currentMinimumDistance);
    }

    /**
     * Scans the central strip. Because the strip is already sorted by Y,
     * this function runs in O(n) time.
     */
    private double stripClosest(double[][] strip, int size, double d) {
        double minD = d;

        for (int i = 0; i < size; i++) {
            // Geometrically, the inner loop runs a maximum of 7 times per point.
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

    /**
     * Checks if the found distance is the new global minimum.
     */
    private void updateGlobalMin(double currentDistance, double[] p1, double[] p2) {
        if (currentDistance < globalMinDistance) {
            globalMinDistance = currentDistance;
            globalNearestPoints = pairDistinctPointsToString(p1, p2);
        }
    }

    /**
     * Standard O(n^2) search for small base cases.
     */
    private double trivialSearch(double[][] points, int left, int right) {
        double minDistance = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double currentMinimumDistance = getDistanceBetweenPairDistinctPoints(points[i], points[j]);
                updateGlobalMin(currentMinimumDistance, points[i], points[j]);
                if (currentMinimumDistance < minDistance) minDistance = currentMinimumDistance;
            }
        }
        return minDistance;
    }
    
    /**
     * Euclidean distance -> sqrt((x2-x1)^2 + (y2-y1)^2)
     */
    private double getDistanceBetweenPairDistinctPoints(double[] coordinate1, double[] coordinate2) {
        double x = Math.pow(coordinate1[X_COORDINATE] - coordinate2[X_COORDINATE], 2);
        double y =  Math.pow(coordinate1[Y_COORDINATE] - coordinate2[Y_COORDINATE], 2);
        return Math.sqrt(x + y);
    }

    /**
     * Format point coordinates for the final output string.
     */
    private String pairDistinctPointsToString(double[] coordinate1, double[] coordinate2) {
        return "[" + coordinate1[X_COORDINATE] + ", " + coordinate1[Y_COORDINATE] + "]"
                + " [" + coordinate2[X_COORDINATE] + ", " + coordinate2[Y_COORDINATE] + "]";
    }
}