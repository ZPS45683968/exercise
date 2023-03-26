package Bin_and_PackingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    private int capacity;
    private List<Integer> contents;
    // Constructor
    public Bin(int capacity) {
        this.capacity = capacity;
        this.contents = new ArrayList<Integer>();
    }
    public int getSpace() {
        return capacity - contents.stream().mapToInt(s -> s).sum();
    }
    public void store(int weight) throws IllegalArgumentException{
        if (weight > getSpace()) {
            throw new IllegalArgumentException("No space: space "
                    + getSpace() + ", size " + weight);
        }else {
            contents.add(weight);
        }
    }
    public String toString() {
        return contents.toString();
    }
}
