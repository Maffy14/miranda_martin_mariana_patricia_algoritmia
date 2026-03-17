package algstudent.s4;

public class AssignmentTimes {

	private static String fileName = "src/algstudent/s4/Datos4.txt"; 
	
	public static void main(String[] args) {
		 
		MatrixLoader buffer = new MatrixLoader();
		
		int[][] productivity = buffer.readMatrixFromFile(fileName);
		
		Assignment assingment = new Assignment(productivity);
		
		System.out.println(assingment.determineAssingmentEmployee());

	}

}
