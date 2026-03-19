package algstudent.s4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Create an AssignmentTests.java class that contains 5 test cases (5 methods), 
 * one for each input file, to verify that the result is as expected. 
 * 
 * The test cases must run without requiring any modifications to your source code. 
 * Otherwise, the exercise will be graded as a 0. 
 */
class AssingmentTest {

	FileLoader loader;
	private Assignment assingment = new Assignment();;
	private int[][] productivity;
	String outputMessage;
	private String inputFileName;
	private String outputFileName;
	
	@BeforeEach
	void setUp() throws Exception {
		loader = new FileLoader();
		inputFileName = "src/algstudent/s4/";
		outputFileName = "src/algstudent/s4/";
	}

	@Test
	void datos4Test() {
		inputFileName += "Datos4.txt";
		outputFileName += "Resultados4.txt";
		
		productivity = loader.loadMatrixFromFile(inputFileName);
		outputMessage = loader.loadOutputFile(outputFileName);
		
		assertEquals(outputMessage, assingment.determineAssingmentEmployee(productivity));
	}
	
	@Test
	void datos16Test() {
		inputFileName += "Datos16.txt";
		outputFileName += "Resultados16.txt";
		
		productivity = loader.loadMatrixFromFile(inputFileName);
		outputMessage = loader.loadOutputFile(outputFileName);
		
		assertEquals(outputMessage, assingment.determineAssingmentEmployee(productivity));
	}
	
	@Test
	void datos64Test() {
		inputFileName += "Datos64.txt";
		outputFileName += "Resultados64.txt";
		
		productivity = loader.loadMatrixFromFile(inputFileName);
		outputMessage = loader.loadOutputFile(outputFileName);
		
		assertEquals(outputMessage, assingment.determineAssingmentEmployee(productivity));
	}
	
	@Test
	void datos256Test() {
		inputFileName += "Datos256.txt";
		outputFileName += "Resultados256.txt";
		
		productivity = loader.loadMatrixFromFile(inputFileName);
		outputMessage = loader.loadOutputFile(outputFileName);
		
		assertEquals(outputMessage, assingment.determineAssingmentEmployee(productivity));
	}
	
	@Test
	void datos1024Test() {
		inputFileName += "Datos1024.txt";
		outputFileName += "Resultados1024.txt";
		
		productivity = loader.loadMatrixFromFile(inputFileName);
		outputMessage = loader.loadOutputFile(outputFileName);
		
		assertEquals(outputMessage, assingment.determineAssingmentEmployee(productivity));
	}

}
