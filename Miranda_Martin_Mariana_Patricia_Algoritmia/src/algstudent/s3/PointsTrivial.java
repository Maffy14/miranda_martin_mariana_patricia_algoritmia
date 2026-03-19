package algstudent.s3;

public class PointsTrivial {
	
	private static String FILENAME = "src/algstudent/s3/datos8192.txt"; // I prefer a constant instead of a argument
	private static int X_COORDINATE = 0;
	private static int Y_COORDINATE = 1;
	
	double[][] coordinates;
	private StringBuilder finalOutput;
	
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
	
	public PointsTrivial(double[][] coordinates) {
		this.coordinates = coordinates;
		finalOutput = new StringBuilder();
	}
	
	/**
	 * Solve the problem using a trivial algorithm with a class called PointsTrivial.java, 
	 * which calculates the solution by determining the distance between every pair of distinct points, 
	 * thus revealing the desired pair of points and their minimum distance. 
	 * 
	 * The complexity is clearly quadratic
	 * @param filename
	 * @return
	 */
	public String getDistanceNearestPoints() {
		
		int numPoints = coordinates.length;
		
		double minDistance = Double.MAX_VALUE;
		
		String nearestPoints = null;
		
		for (int i = 0; i < numPoints; i++) { // For each point
			for (int j = 0; j < numPoints; j++) {	// Compare with all the rest
				if (i != j) { // Except itself
					double currentDistance = getDistanceBetweenPairDistinctPoints(coordinates[i], coordinates[j]);
					
					if (currentDistance < minDistance) {
						minDistance = currentDistance;
						nearestPoints = pairDistinctPointsToString(coordinates[i], coordinates[j]); // to print it later						
					}
				} 
			}
		}
	
		finalOutput.append("NEAREST POINTS: ");
		finalOutput.append(nearestPoints);
		finalOutput.append("\nMINIMUM DISTANCE = ");
		finalOutput.append(String.format("%.6f", minDistance)); // for it to print 6 decimals, though decimal separator is a comma
		return finalOutput.toString();
		
	}
	
	/**
	 * distance = sqrt((x2 - x1)^2 + (y2 - y1)^2)
	 * @param coordinate1
	 * @param corodinate2
	 * @return
	 */
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
