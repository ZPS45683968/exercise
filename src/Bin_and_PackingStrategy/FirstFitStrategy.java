package Bin_and_PackingStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstFitStrategy implements PackingStrategy {
    public Set<Bin> pack(int capacity, List<Integer> values) {
        Set<Bin> bins = new HashSet<Bin>();
        for (int value : values) {
            boolean stored = false;
            Bin firstBin = null;
            for (Bin bin : bins) {
                if (bin.getSpace() >= value) {
                    firstBin = bin;
                    break;
                }
            }
            if (firstBin == null) {
                firstBin = new Bin(capacity);
                bins.add(firstBin);
            }
            firstBin.store(value);
        }
        return bins;
    }
}
