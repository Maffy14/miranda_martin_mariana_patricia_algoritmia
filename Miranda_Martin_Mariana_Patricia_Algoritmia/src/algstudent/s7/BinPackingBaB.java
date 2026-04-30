package algstudent.s7;

import java.util.*;

public class BinPackingBaB {

    protected PriorityQueue<Node> ds;
    protected Node bestNode;
    protected Node rootNode;
    
    // minimum bins needed to store all objects
    protected int pruneLimit;

    private int capacity;
    private List<Integer> sizesObjects;

    public BinPackingBaB(int capacity, List<Integer> sizes) {
        this.capacity = capacity;

        this.sizesObjects = new ArrayList<>(sizes);
        this.sizesObjects.sort(Collections.reverseOrder()); // heuristic

        this.ds = new PriorityQueue<>(Comparator.comparingInt(Node::getHeuristicValue));

        this.rootNode = new Node(capacity, sizesObjects);
    }

    public void branchAndBound() {

        ds.add(rootNode); // first node to be explored
        this.pruneLimit = rootNode.initialValuePruneLimit();

        while (!ds.isEmpty() && ds.peek().getHeuristicValue() < pruneLimit) {

            Node node = ds.poll(); // extract best node

            ArrayList<Node> children = node.expand();

            for (Node child : children) {
                if (child.isSolution()) {
                    int cost = child.getHeuristicValue();
                    if (cost < pruneLimit) {
                        pruneLimit = cost;
                        bestNode = child;
                    }
                }
                else if (child.getHeuristicValue() < pruneLimit) {
                    ds.add(child);
                }
            }
        }
    }

    public int getBinsNeededSolution() {
        return pruneLimit;
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

 	    if (bestNode == null) {
 	        System.out.println("No solution found.");
 	        return;
 	    }

 	    List<List<Integer>> bestBins = bestNode.getBins();

 	    System.out.println("List of bins and their objects:");

 	    for (int i = 0; i < bestBins.size(); i++) {
 	        System.out.print("Bin " + (i + 1) + ": ");

 	        for (int obj : bestBins.get(i)) {
 	            System.out.print(obj + " ");
 	        }

 	        System.out.println();
 	    }
 	    System.out.println("The minimum number of bins is " + bestBins.size());
 	}
}