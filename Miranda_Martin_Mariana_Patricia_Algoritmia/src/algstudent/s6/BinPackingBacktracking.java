package algstudent.s6;

import java.util.ArrayList;
import java.util.List;

// ordenar lista de elementos de menor a mayor
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
public class BinPackingBacktracking {
	
	private int capacity;
	private List<Integer> sizesObjects;
	private List< List<Integer> > bins;
	private List<Integer> capacityBins; 
	List<Integer> currentBin;
	int currentCapacityBin;
	private int minBins;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// Constructor
	public BinPackingBacktracking(int capacity, List<Integer> sizes) {
		this.capacity = capacity;
		this.sizesObjects = sizes;
		bins = new ArrayList<>();
		capacityBins = new ArrayList<>();
		currentBin = new ArrayList<>();
		
		// Initialize to the worst possible case (1 bin per object)
		this.minBins = sizes.size() + 1;
	}

public void backtracking() {
		
		// Base check -> We have found the solution
		if(currentCapacityBin > this.capacity) { // We have processed all objects
			// Let's find the solution in 
		
			return;
		}
		
		// we explore all possible choices
		bins.add(currentBin);
		
		for (int sizeObject = 0; sizeObject < sizesObjects.size(); sizeObject++) {
			
			if (sizeObject <= currentCapacityBin) {
				// We choose a path
				currentBin.add(sizeObject);
				currentCapacityBin += sizeObject;
				
				// We recursively explore the current path
				backtracking();
				
				// We undo last path
				currentBin.remove(currentBin.size() - 1); // remove last element
				currentCapacityBin -= sizeObject;
			} 
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
