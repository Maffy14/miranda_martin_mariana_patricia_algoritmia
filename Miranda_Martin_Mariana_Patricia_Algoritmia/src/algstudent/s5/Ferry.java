package algstudent.s5;

import java.util.List;

/*
	[0][0] true, all the rest false [0][i] (= vehicles -> O occupation) initialization
	Is there a valid state that makes sense
	
	Transition for i≥1: from any p where DP[i−1][p] is True
	▪ Place on port if p + vi ≤  L → DP[i][p+vi] = True
	▪ Place on starboard if Si − p  ≤ L → DP[i][p] = True
	
	totalSizeVehicle - leftport
	
	[1][0] true, lo podemos colocar en la parte derecha total occupance = 10 > tamañovehiculo
	[1][4] true, lo podemos colocar en la parte iquierda total occupance = 10 > tamañovehiculo + 4 espaxcio disponible
	
	
	[2][0] true, lo podemos colocar en la parte derecha total occupance = 10 > tamañovehiculo (3)
	[2][4] true, lo podemos colocar en la parte iquierda total occupance = 10 > tamañovehiculo (3) + 3 espacio disponible
	
	[3][3] true, lo podemos colocar dos a la iquierda total occupance = 10 > tamañovehiculo (3) + tamañovehiculo (3) 
	
	En funcion de la linea anterior, puedo coocar nu nuevo vehiculo o excede occupance
	
	Reconstrunction T que mas reconnstruccion tiene -> 10
	Si T viene de arriba, derecha (starboard). sin embargo, retsa t tamaño vehiculo (izquierda) hasta que me venga de arriba (derecho)
	
	Misma comprobacion para mete, misma construcciom
	
	----------------------------------------------------------------------------------------
	
	A ferry is preparing to load vehicles to cross the canal between Gijón and London.
	The ferry has two parallel lanes (port and starboard), 
	both with the same maximum length of L meters.
	
	Loading Operation: 
	
	A group of vehicles arrives at the port in an ordered queue. 
		* Vehicles must be loaded strictly in the order of arrival (indices 1, 2, ..., n). 
		
		* When considering vehicle i, 
		  it must be placed completely in either the port or starboard lane. 
		  
		* If vehicle i does not fit in either lane 
		  (given the positions of the preceding i-1 vehicles), 
		  the loading process ends. 
		  It is not possible to "skip" a vehicle to load the next one.
		  
	Objective:  
	
	Your mission is to design an algorithm that determines the maximum number of vehicles k 
	(the first k in the queue) that can be loaded onto the ferry without exceeding 
	the length L in any of the lanes.
	 

	Warning: an exact and efficient solution is required. 
	If you do not achieve this in pseudo polynomial time, 
	the shipping company will replace you with someone who has passed 
	
	
	Program Input: 
		- An integer L (1 <= L <= 1000): length of each lane 
		  (in meters or arbitrary units). 
		- A sequence of integers: lengths of the vehicles in order of arrival. 
		  The list ends when there are no more entries (EOF).
	
	Expected program output: 
		- Maximum number (k): number of vehicles that manage to travel. 
		- Memoization table: display the table used by the 
		  Dynamic Programming algorithm that justifies the calculation. 
		- Solution reconstruction: a valid assignment (port/starboard) for 
		those k vehicles. 
		- Verification: total sum of length occupied in each lane. 

	------------------------------------------------------------------------------
	
	YOU ARE REQUESTED TO: 
	
	A report and code covering the following points must be submitted: 
	
	1. STATE DEFINITION: clearly explain what the indexes in your table/data structure mean. 
		a. Hint: given that the total sum of the lengths of the first i vehicles is known, if you 
		know the length occupied in the port lane, is it necessary to explicitly store the 
		starboard length in the state? 
		
		 Let Si = sum of lengths of the first i vehicles (total occupied length)
		 
		 We define DP[i][p] = True if it is possible to place the i vehicle on the port side (0≤p≤L), 
		 without exceeding L (the total occupied length on the port side is p)
		 
		 it is not necessary to store the length being occupied on the starboard side: 
		 		lengthOccupiedStarboard = Si − p (implicitly)
		
	2. BASE CASES AND RECURRENCY RELATIONSHIP: write the mathematical formulation that 
	allows you to calculate state (i, j) from the previous states. Base cases must be included.
	
		Base: DP[0][0] = True; DP[0][p] = False for p>0
		
		Let vi the length of the vehicle i
		
		Transition for i≥1: from any p where DP[i−1][p] is True
			- Place on port if p + vi ≤  L → DP[i][p+vi] = True 
			- Place on starboard if Si − p  ≤ L → DP[i][p] = True
		
		The process stops at the first i whose state DP[i][*] is empty 
		(all False) or when we finish with all the vehicles
	 
	3. Complexity: Analyze the time cost of the algorithm as a function of N (number of vehicles) 
	and L (length of the ferry). 
	
	4. Implementation: Code in the Ferry.java class that solves the problem, passing all the test 
	cases of the companion file. 
	

		

*/

public class Ferry {

	private int length;
	
	private List<Integer> vehicles;
	
	private boolean[][] DP;
	
	private String dataOutput;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param length
	 * @param vehicles
	 */
	public Ferry(int length, List<Integer> vehicles) {
		this.length = length; // length of each lane [1, 1000]
		this.vehicles = vehicles; // lengths of the vehicles in order of arrival
	}

	

	/**
	 * Perform algorithm 
	 * 		Base: DP[0][0] = True; DP[0][p] = False for p>0
	 * 
	 * 		Let vi the length of the vehicle i
	 * 		
	 * 		Transition for i≥1: from any p where DP[i−1][p] is True
	 * 			- Place on port if p + vi ≤  L → DP[i][p+vi] = True 
	 * 			- Place on starboard if Si − p  ≤ L → DP[i][p] = True
	 */
	public void run() {
		
		// Initialize DP matrix (number of Vehicles x length port lane)
		int numVehicles = vehicles.size();
		DP = new boolean[numVehicles][this.length]; 
		
		int si = 0; // sum of lengths of the first i vehicles (total occupied length)
		
		// Base case: 0 vehicles = 0 occupation
		
		// auxiliary row to know when to stop the loop, i.e., if a whole role has not been modified (false)
		boolean isRowAllFalse = true;
		
		// auxiliary variable to sum the number of vehicles who will actually travel (to print)
		
		int numTravellingVehicles = 0;
		
		DP[0][0] = true; 
		
		for (int p = 1; p < this.length; p++) {
			DP[0][p] = false; 
		}
		
		// Recurrence relationship
		
		for (int i = 0; i < numVehicles; i++) {
			
			isRowAllFalse = true; // for each vehicle
			
			for (int p = 0; p < this.length; p++) {
				
				// Place on port
				int vi = vehicles.get(i); // length vehicle i
				
				if ((p + vi) <= this.length) { // Place on port
					DP[i][p + vi] = true;
					si += vi;
					isRowAllFalse = false;
				}
				else if ((si - p) <= this.length) { // Place on starboard
					DP[i][p] = true;
					si += vi;
					isRowAllFalse = false;
				}	
			}
			// Exit condition : After iterating a vehicle through the whole port lane, 
			// was the vehicle able to be placed in a lane?
			
			if (isRowAllFalse) break;
			
			numTravellingVehicles++; // vi is able to travel
		}	
		
		dataOutput = String.format("A total of %d vehicles have arrived (%d will travel)",
				numVehicles, numTravellingVehicles);
	}

	public int getMaximumNumberOfVehicles() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	public void printData() {
		String data = this.dataOutput;		
		System.out.println(data);
	}

	public void printPossibleAssignation() {
		// TODO Auto-generated method stub
		
	}
	
	public void printSolutionTable() {
		// TODO Auto-generated method stub
		
	}

}
