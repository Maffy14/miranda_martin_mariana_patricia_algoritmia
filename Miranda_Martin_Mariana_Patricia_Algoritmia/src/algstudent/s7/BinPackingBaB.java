package algstudent.s7;

import java.util.ArrayList;
import java.util.List;

/*
	Clear clase heap, priority, sacar estimacion mejor estimacion Heap ds.get.pull?
	BIN PACKING PROBLEM
	
	This is the same problem as the previous exercise. However, the previous exercise was solved by 
	backtracking, yielding times that we now intend to significantly improve. To do this, we propose 
	developing the problem's state tree using a branching heuristic aimed at finding, as quickly as 
	possible, the ideal combination of objects that achieves the minimum number of containers. 
	
	3. Optionally, the number of recursive calls made can also be printed
	
	
	YOU ARE REQUESTED TO: 
	PART A: 
	Explain what the proposed branching heuristic consists of. 
	
	PART B: 
	A class BinPackingBaB.java that after being executed in the same way as in the previous session, 
	offers a solution to the problem, ideally improving the times that had been obtained. 
	
	PART C: 
	A class BinPackingBaBTimes.java that will run all the test files to calculate the time needed to solve 
	the problem. If the program takes too long for any of them, it can be stopped for that specific case. 
	Case 3 and, especially, case 4 are challenging. 
	
	PART D: 
	Finally, fill in a table showing the times obtained (in milliseconds) for the test files. Comparing these 
	times with those obtained in the previous exercise, conclude to what extent the desired objective 
	was achieved (significantly reducing the execution time needed to obtain the solution).

 */
public class BinPackingBaB {
	
	private int capacity;
	private List<Integer> sizesObjects;
	private List< List<Integer> > bins;
	private List<Integer> capacityBins; 
	private List<List<Integer>> bestBins;
	int currentCapacityBin;
	private int minBins;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// Constructor
	public BinPackingBaB(int capacity, List<Integer> sizes) {
		this.capacity = capacity;
		this.sizesObjects = sizes;
		
		this.bins = new ArrayList<>();
		this.capacityBins = new ArrayList<>();
		this.bestBins = new ArrayList<>();
		
		// Initialize minBins to the maximum possible (one object per bin)
		this.minBins = sizes.size();
	}
	
	// Public backtracking method that initializes the recursion
	public void backtracking() { 
		// Objects are pre-sorted in descending order 
		backtrackingRecursive(0);
	}
	
	// Recursive backtracking method.
	private void backtrackingRecursive(int itemIndex) {
		
		
		// Pruning -> If our current path uses as many or more bins than the best found, terminate this branch.
		if(bins.size() >= minBins) { // We have processed all objects
			return;
		}
		
		// Base case -> We have found the solution
		if (itemIndex == sizesObjects.size()) { // we have explored all possible choices
			if (bins.size() < minBins) {
				minBins = bins.size();
				
				bestBins = new ArrayList<>();
				for (List<Integer> bin : bins) {
					bestBins.add(new ArrayList<>(bin)); // Record solution
				}
			}
			return;
		}
		
		int currentObjectSize = sizesObjects.get(itemIndex);
		
		// OPTION 1 -> Add object into an existing bin
		for (int i = 0; i < bins.size(); i++) {
			if (capacityBins.get(i) + currentObjectSize <= capacity) {
				// Forward logic
				bins.get(i).add(currentObjectSize);
				capacityBins.set(i, capacityBins.get(i) + currentObjectSize);
				
				backtrackingRecursive(itemIndex + 1);
				
				// Backward logic (Backtrack)
				bins.get(i).remove(bins.get(i).size() - 1);
				capacityBins.set(i, capacityBins.get(i) - currentObjectSize);
			}
		}
		
		// OPTION 2 -> Open a new bin (Only if we aren't already at minBins limit)
		if (bins.size() + 1 < minBins) {
			List<Integer> newBin = new ArrayList<>();
			newBin.add(currentObjectSize);
			bins.add(newBin);
			capacityBins.add(currentObjectSize);
			
			backtrackingRecursive(itemIndex + 1);
			
			// Backtrack
			bins.remove(bins.size() - 1);
			capacityBins.remove(capacityBins.size() - 1);
		}
		
		
	}
	

	/**
	 * Prints the input data of the problem
	 */
	public void printData() {
		System.out.println("Capacity for each bin = " + this.capacity);
		
		System.out.print("Size objects-to-be-stored in bins -> ");
		
		for (int i = 0; i < this.sizesObjects.size(); i++) {
			System.out.print(sizesObjects.get(i) + " ");
		}
		
		System.out.println();
		
	}

	public void printSolution() {
		System.out.println("List of bins and their objects: ");
		
		
		for (int i = 0; i < this.bins.size(); i++) {
			System.out.print("Bin " + i + ": ");
			for (int j = 0; j < this.bins.get(i).size(); j++) {
				System.out.print(bins.get(i).get(j) + " ");
			}
			
			System.out.println();
		}
		
		
		System.out.println("The minimum number of bins is " + this.minBins);
	}

	/**
	 * @return minimum number of containers needed to store all objects. 
	 */
	public int getBinsNeededSolution() {
		return minBins;
	}

}
