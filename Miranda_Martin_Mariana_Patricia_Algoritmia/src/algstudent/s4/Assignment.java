package algstudent.s4;

/*
 * The Board of Directors of a company on the verge of bankruptcy proposed, 
 * with the primary objective of keeping its n employees (employee0, ..., employeen-1), 
 * in a final attempt to save it:
 * 		The creation of n new positions for those employees: position0, ..., positionn-1.
 * 
 * It has commissioned a renowned consulting firm to calculate 
 * (based on a multitude of parameters) the productivity that each of its employees 
 * would have in each of the positions.
 * 
 * This study has already been delivered and 
 * is in an input file where each productivity value is in the range [1000 .. 9999], 
 * and the element [i][j] is the estimated productivity that employee i would have in position j.
 * 
 * We need to determine the assignment of each employee to one of the newly created positions. 
 * For this assignment, the company's CEO has established the following 3 steps:
 * 		1. An ALGORITHM 1 that simulates assigning to each employee (by index order) the job 
 * 		   position in which they have the highest productivity among the positions 
 *         that have not been previously assigned. 
 *         This assignment will result in a total productivity of P1 
 *         (the sum of the n assigned productivities).
 *         
 *		2. An ALGORITHM 2 that simulates assigning each position (by index order) 
 *         to the employee who has the highest productivity among the employees 
 *         who have not been previously assigned. 
 *         This assignment will result in a total productivity of P2 
 *         (the sum of the n assigned productivities).
 *         
 *		3. Finally, the actual assignment to be carried out is chosen based on P = maximum(P1, P2).
 *
 *		Datos4.txt
 *
 *			pos0	pos1	pos2	pos3
 *		e0  5960	7118	6751	1772
 *		e1	8927	4255	6801	6731
 *		e2	4078	4907	7164	7211
 *		e3	4336	7767	5849	5482
 * 
 * 	ASIGNACION DEL ALGORITMO 1:
 * 
 * 	AL EMPLEADO 0 SE LE ASIGNA EL PUESTO 1
 *	AL EMPLEADO 1 SE LE ASIGNA EL PUESTO 0
 * 	AL EMPLEADO 2 SE LE ASIGNA EL PUESTO 3
 * 	AL EMPLEADO 3 SE LE ASIGNA EL PUESTO 2
 *
 * 	PRODUCTIVIDAD TOTAL DEL ALGORITMO 1= 29105
 * 
 * 	*********************************************
 * 	ASIGNACION DEL ALGORITMO 2:
 * 
 * 	EL PUESTO 0 SE LE ASIGNA AL EMPLEADO 1
 * 	EL PUESTO 1 SE LE ASIGNA AL EMPLEADO 3
 *	EL PUESTO 2 SE LE ASIGNA AL EMPLEADO 2
 *	EL PUESTO 3 SE LE ASIGNA AL EMPLEADO 0
 * 
 * 	PRODUCTIVIDAD TOTAL DEL ALGORITMO 1= 25630
 * 
 * 	*********************************************
 * 
 * 	LUEGO PROCEDE APLICAR EL ALGORITMO 1
 * 
 * 	PRODUCTIVIDAD TOTAL= 29105

 *
 */

public class Assignment {
	
	private static int[][] productivity;
	
	private StringBuilder outputMessage;
	
	public Assignment(int[][] productivity) {
		this.productivity = productivity; 
		outputMessage = new StringBuilder();
	}
	
	/**
	 * Simulates assigning to each employee (by index order) the job position in which they have 
	 * the highest productivity among the positions that have not been previously assigned.
	 *  
	 * @return p1, sum sum of the n assigned productivities
	 */
	public static int algorithm1() { // UNFINISHEDDDD
		
		// Create a boolean array to know which position has already beeen chosen.
		boolean[] chosenPosition = new boolean[productivity.length - 1]; //?
		
		int currentIndex = -1;
		int maxProductivity = 0;
		
		// Iterate through each row, i.e., each employee
		for (int employee = 0; employee < productivity.length; employee++) {
			
			maxProductivity = 0; // Initialize to 0 each time a new employee is chosen			
			
			for (int position = 0; position < productivity.length; position++) {
				
				// Find the position in which employee_i has the most productivity
				if (productivity[employee][position] > maxProductivity && !chosenPosition[position]) {
					maxProductivity = productivity[employee][position];
					chosenPosition[position] = true;
				}
				
				
			}
		}
		
		return 0;
	}
	

	
	
	
	

}
