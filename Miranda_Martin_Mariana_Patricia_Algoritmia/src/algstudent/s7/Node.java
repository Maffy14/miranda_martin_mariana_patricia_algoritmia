package algstudent.s7;

import java.util.ArrayList;
import java.util.List;

class Node {

    private int capacity;
    private List<Integer> sizesObjects;

    private List<List<Integer>> bins;
    private List<Integer> capacityBins;

    private int index; // next object

    public Node(int capacity, List<Integer> objects) {
        this.capacity = capacity;
        this.sizesObjects = objects;

        this.bins = new ArrayList<>();
        this.capacityBins = new ArrayList<>();

        this.index = 0;
    }

    // Copy constructor
    public Node(Node other) {
        this.capacity = other.capacity;
        this.sizesObjects = other.sizesObjects;
        this.index = other.index;

        this.bins = new ArrayList<>();
        for (List<Integer> bin : other.bins) {
            this.bins.add(new ArrayList<>(bin));
        }

        this.capacityBins = new ArrayList<>(other.capacityBins);
    }

    public ArrayList<Node> expand() {
        ArrayList<Node> children = new ArrayList<>();

        int currentObjectSize = sizesObjects.get(index);

        // Try existing bins
        for (int i = 0; i < bins.size(); i++) {
            if (capacityBins.get(i) + currentObjectSize <= capacity) {
                Node child = new Node(this);

                child.bins.get(i).add(currentObjectSize);
                child.capacityBins.set(i, capacityBins.get(i) + currentObjectSize);
                child.index++;

                children.add(child);
            }
        }

        // Try new bin
        Node child = new Node(this);
        List<Integer> newBin = new ArrayList<>();
        newBin.add(currentObjectSize);

        child.bins.add(newBin);
        child.capacityBins.add(currentObjectSize);
        child.index++;

        children.add(child);

        return children;
    }

    public boolean isSolution() {
        return index == sizesObjects.size(); // All objects were processed
    }

    // Estimates the total number of bins required by adding the number of bins currently in use 
    // and an optimistic guess of the bins needed for the remaining objects.
    // Even in the best possible case, is this node better than my current best solution? (pruning)
    public int getHeuristicValue() {

        int sizesObjectsRemaining = 0;
        for (int i = index; i < sizesObjects.size(); i++) {
            sizesObjectsRemaining += sizesObjects.get(i);
        }

        int minExtraBins = (sizesObjectsRemaining + capacity - 1) / capacity;

        return bins.size() + minExtraBins;
    }

    public int initialValuePruneLimit() {
        return sizesObjects.size(); // worst case
    }
    
    public List<List<Integer>> getBins() {
        return bins;
    }
}