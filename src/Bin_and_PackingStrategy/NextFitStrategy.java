package Bin_and_PackingStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NextFitStrategy implements PackingStrategy {
    public Set<Bin> pack(int capacity, List<Integer> values) {
        Set<Bin> bins = new HashSet<Bin>();
        Bin currentBin = null;
        for (int value : values) {
            if (currentBin == null || currentBin.getSpace() < value) {
                Bin newBin = new Bin(capacity);
                bins.add(newBin);
                currentBin = newBin;
            }
            currentBin.store(value);
        }
        return bins;
    }
}
