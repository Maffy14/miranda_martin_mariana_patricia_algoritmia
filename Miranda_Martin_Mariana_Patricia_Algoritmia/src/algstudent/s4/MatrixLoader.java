package algstudent.s4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MatrixLoader {

    /**
     * Reads a square matrix from a file.
     * Logic: First line is dimension N, following N lines are comma-separated data.
     * * @param fileName Path to the text file
     * @return 2D integer array [employees][positions]
     */
    public int[][] readMatrixFromFile(String fileName) {
        int[][] matrix = null;

        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            // 1. Read the first line to get the system dimension (N)
            String line = buffer.readLine();
            if (line == null) return null;
            
            int n = Integer.parseInt(line.trim());
            matrix = new int[n][n];

            // 2. Process each "Sub-Process" (row/employee)
            for (int i = 0; i < n; i++) {
                line = buffer.readLine();
                if (line != null) {
                    // Split the string by comma
                    String[] values = line.split(",");
                    for (int j = 0; j < n; j++) {
                        // Populate the position for the current employee
                        matrix[i][j] = Integer.parseInt(values[j].trim());
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Terminal Error reading file: " + e.getMessage());
        }

        return matrix;
        
    }
        
        
        /**
         * Reads a square matrix from a file.
         * Logic: First line is dimension N, following N lines are comma-separated data.
         * * @param fileName Path to the text file
         * @return 2D integer array [employees][positions]
         */
        public String readOutputFile(String fileName) {
            	 Path filePath = Paths.get(fileName);
            	 String outputMessage = null;
                 
                 try {
                    outputMessage = Files.readString(filePath);                     
                
                 } catch (IOException e) {
                	 e.printStackTrace(); // Handle the IOException
                 }
            
                return outputMessage;
            }
    
}