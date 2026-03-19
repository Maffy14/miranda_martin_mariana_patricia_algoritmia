package algstudent.s3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {

	private static int COLUMN_X_AND_Y = 2;
	
    /**
     * Reads a coordinates from file
     * * @param fileName Path to the text file
     * @return 2D integer array [employees][positions]
     */
    public double[][] loadCoordinatesFromFile(String fileName) {
        double[][] matrix = null;

        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            // 1. Read the first line to get the system dimension (N)
            String line = buffer.readLine();
            if (line == null) return null;
            
            int n = Integer.parseInt(line.trim());
            matrix = new double[n][COLUMN_X_AND_Y]; // matrix n * 2 (the two coordinates)

            // 2. Process each "Sub-Process" (row/employee)
            for (int i = 0; i < n; i++) {
                line = buffer.readLine();
                if (line != null) {
                    // Split the string by comma
                    String[] values = line.split(",");
                    for (int j = 0; j < COLUMN_X_AND_Y; j++) {
                        // Populate the position for the current employee
                        matrix[i][j] = Double.parseDouble(values[j].trim());
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Terminal Error reading file: " + e.getMessage());
        }

        return matrix;
        
    }
    
}