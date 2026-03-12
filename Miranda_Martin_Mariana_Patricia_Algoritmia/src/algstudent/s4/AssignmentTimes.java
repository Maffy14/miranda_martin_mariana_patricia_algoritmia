package algstudent.s4;

public class AssignmentTimes {

	private static String fileName = "Datos4.txt"; 
	
	public static void main(String[] args) {

		 
		MatrixLoader buffer = new MatrixLoader();
		
		int[][] productivity = buffer.readMatrixFromFile(fileName);
		
		Assignment assingment = new Assignment(productivity);

	}

}
