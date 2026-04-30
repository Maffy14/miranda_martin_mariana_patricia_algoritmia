package algstudent.s6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

	The Bin Packing Problem is considered in its classic form: 
	
	Given a finite set of objects, each with a positive size, and an unlimited number of identical 
	containers (bins), each with a fixed maximum capacity, the goal is to 
		Determine the object-to-container assignment that minimizes the total number of containers used, 
		while always satisfying the capacity constraint. 
	
	This problem is recognized as NP-hard, meaning that no polynomial-time algorithms are known that 
	guarantee obtaining the optimal solution for general instances. 
	
	The objective of this exercise is to design and implement an exact backtracking algorithm (with 
	pruning?) capable of exploring the solution space and determining the minimum number of 
	containers needed to store all the objects of a given instance. 
	
	The solution must, at a minimum: 

	1. Recursively explore all possible object-to-container assignments. 
	2. Maintain and continuously update the best solution found.  
	3. Maintain a state representation that allows for efficient forward and backward operations. 
	
	FORMAL SPECIFICATION OF THE PROBLEM 
	
	Let: 
		𝑆={𝑠1,𝑠2,…,𝑠𝑛} be a set of objects, where each object Si has a positive integer size. 
		𝐶∈ℕ  is the fixed integer capacity of each container. 
		Sets 𝐵1,𝐵2,…,𝐵𝑘 represent the containers used, where each 𝐵𝑗 ⊆ 𝑆. 
	
	The goal is to find the minimum value k such as: 
	
		No container exceeds the maximum permitted capacity: 
				∑    𝑠≤𝐶 ∀𝑗 ∈ {1,…,𝑘} 
				𝑠∈𝐵𝑗
		
		The union of all containers contains exactly the set of all objects, and the containers form a 
		partition of the set of objects: 
			k
			⋃𝐵𝑗 = 𝑆, 𝐵𝑖∩𝐵𝑗 = ∅ if 𝑖 ≠ 𝑗. 
			𝑗=1

	INPUT AND OUTPUT OF THE PROGRAM 
	
	Input 
	
	A text file with the following format: 
	
		First line: a positive integer C, representing the capacity of the containers. 
		Second line: a sequence of positive integers representing the sizes of the objects. 
	
	For example, in test00.txt the following input is provided: 
	
		10 
		7 6 5 5 4 3 2 

	Output 
	
	The program must produce: 
		1. Minimum number of containers needed to store all objects. 
		2. Final distribution of the objects in each container, enumerated from 1 to 
		 	the total amount of used containers 
	
	For example, considering the previous input a solution would be the following one: 
		List of bins and their objects: 
		
			Bin 1: 3 7  
			Bin 2: 4 6  
			Bin 3: 5 5  
			Bin 4: 2  
		
		The minimum number of bins is 4. 
	
	3. Optionally, the number of recursive calls made can also be printed

 */
public class BinPackingBactracking {
	
	private int capacity;
	private List<Integer> sizesObjects;
	
	// Current state
	private List< List<Integer> > bins;
	private List<Integer> capacityBins; 
	
	// Best solution
	private List< List<Integer> > bestBins; 
	private int minBins;
	
	
	public static void main(String[] args) {

	}
	
	// Constructor
	public BinPackingBactracking(int capacity, List<Integer> sizes) {
		this.capacity = capacity;
		this.sizesObjects = sizes;
		
		this.bins = new ArrayList<>();
		this.capacityBins = new ArrayList<>();
		this.bestBins = new ArrayList<>();
		
		// Initialize to the worst possible case (1 bin per object)
		this.minBins = sizes.size() + 1;
	}

	
	/**
	 * As the test uses a backtracking method with no parameters, this public method  
	 * calls my implementation of a recursive backtracking, which uses the item index as a parameter
	 */
	public void backtracking() {
		// By sorting objects from largest to smallest, pruning works much better
		sizesObjects.sort(Collections.reverseOrder());
		
        minBins = getGoodInitialSolution();
		
		backtrackingRecursive(0);
	}
	

	private void backtrackingRecursive(int itemIndex) {
		
		// Stop if current path is already worse than our best solution
		// In that way, we don't waste time nor space. (pruning)
		if (bins.size() >= minBins) {
			return; 
		}
		
		
		// Even in the best possible case, is it better than my current best solution? (pruning)
        int sizesObjectsRemaining = 0;
        for (int i = itemIndex; i < sizesObjects.size(); i++) {
            sizesObjectsRemaining += sizesObjects.get(i);
        }
        
        // Minimum of extra bins we need to fit the remaining objects
        int minExtraBins = (sizesObjectsRemaining + capacity - 1) / capacity;

        // This solution is worse or equal to the best solution we already found
        if (bins.size() + minExtraBins >= minBins) 
        	return;
		
		// We have iterated through all objects.
		if (itemIndex == sizesObjects.size()) {
			if (bins.size() < minBins) {
				minBins = bins.size();
				
				// We store the solution
				bestBins = new ArrayList<>();
				for (List<Integer> bin : bins) {
					bestBins.add(new ArrayList<>(bin));
				}
			}
			return;
		}
		
		int currentObjectSize = sizesObjects.get(itemIndex);
		
		// We first try to put the current object into EXISTING bins
		for (int currentBin = 0; currentBin < bins.size(); currentBin++) {
			if (capacityBins.get(currentBin) + currentObjectSize <= capacity) {
				
				bins.get(currentBin).add(currentObjectSize);
				capacityBins.set(currentBin, capacityBins.get(currentBin) + currentObjectSize);
				
				backtrackingRecursive(itemIndex + 1);
				
				bins.get(currentBin).remove(bins.get(currentBin).size() - 1);
				capacityBins.set(currentBin, capacityBins.get(currentBin) - currentObjectSize);
			}
		}
		
		// There is not enough space ->  We put the current object into a NEW bin
		if (bins.size() + 1 < minBins) {
			List<Integer> newBin = new ArrayList<>();
			newBin.add(currentObjectSize);
			bins.add(newBin);
			capacityBins.add(currentObjectSize);
			
			backtrackingRecursive(itemIndex + 1);
			
			bins.remove(bins.size() - 1);
			capacityBins.remove(capacityBins.size() - 1);
		}
	}
	
	
	// We provide a good initial guess but not the optimal so pruning works better later
	// We place the item in the first bin where it fits, not the best one.
	private int getGoodInitialSolution() {
        List<Integer> currentUsedCapacityBins = new ArrayList<>();

        for (int size : sizesObjects) {
            boolean placed = false;
            
            // We first try to put the current object into EXISTING bins
            for (int i = 0; i < currentUsedCapacityBins.size(); i++) {
                if (currentUsedCapacityBins.get(i) + size <= capacity) {
                	this.bestBins.get(i).add(size);
                    currentUsedCapacityBins.set(i, currentUsedCapacityBins.get(i) + size);
                    placed = true;
                    break;
                }
            }
            
            // There is not enough space ->  We put the current object into a NEW bin
            if (!placed) {
                currentUsedCapacityBins.add(size);
                List<Integer> newBin = new ArrayList<>();
                newBin.add(size);
                this.bestBins.add(newBin);
            }
        }

        return currentUsedCapacityBins.size(); 
    }
	
	
	// Prints the input data of the problem
	public void printData() {
		System.out.println("Capacity for each bin = " + this.capacity);
		
		System.out.print("Size objects-to-be-stored in bins -> ");
		
		for (int i = 0; i < this.sizesObjects.size(); i++) {
			System.out.print(sizesObjects.get(i) + " ");
		}
		
		System.out.println();
		
	}

	// Prints the output data of the problem
	public void printSolution() {
	    System.out.println("List of bins and their objects: ");
	    
	    // Logic check: We must print the BEST solution found, 
	    // because 'bins' is empty after backtracking finishes.
	    if (this.bestBins.isEmpty()) {
	        System.out.println("No distribution found or backtracking not yet executed.");
	        return;
	    }

	    for (int i = 0; i < this.bestBins.size(); i++) {
	        System.out.print("Bin " + (i + 1) + ": "); // Standard 1-based indexing for humans
	        for (int j = 0; j < this.bestBins.get(i).size(); j++) {
	            System.out.print(this.bestBins.get(i).get(j) + " ");
	        }
	        System.out.println();
	    }
	    
	    System.out.println("The minimum number of bins is " + this.minBins);
	}

	/**
	 * @return minimum number of containers needed to store all objects. For testing
	 */
	public int getBinsNeededSolution() {
		return minBins;
	}

}
