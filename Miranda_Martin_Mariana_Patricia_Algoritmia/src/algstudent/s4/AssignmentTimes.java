package algstudent.s4;

public class AssignmentTimes {

	private static String fileName = "src/algstudent/s4/Datos4.txt"; 
	
	public static void main(String[] args) {
		 
		FileLoader buffer = new FileLoader();
		String outputMessage = buffer.loadOutputFile("src/algstudent/s4/Resultados4.txt");
		
		int[][] productivity = buffer.loadMatrixFromFile(fileName);
		
		Assignment assingment = new Assignment(productivity);
		
		System.out.println(assingment.determineAssingmentEmployee());
		
		FileLoader loader = new FileLoader();
		
		
		System.out.println(outputMessage);

	}

}
