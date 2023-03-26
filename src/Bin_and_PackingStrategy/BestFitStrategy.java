package Bin_and_PackingStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BestFitStrategy implements PackingStrategy {
    public Set<Bin> pack(int capacity, List<Integer> values) {
        Set<Bin> bins = new HashSet<Bin>();
        int bestfit = 101;
        for (int value : values) {
            Bin bestBin = null;
            for (Bin bin : bins) {
               int space = bin.getSpace();
               if(space >= value && space - value < bestfit) {
                   bestfit = space - value;
                   bestBin = bin;
               }
            }
            if (bestBin == null) {
                bestBin = new Bin(capacity);
                bins.add(bestBin);
            }
            bestBin.store(value);
        }
        return bins;
    }
}
