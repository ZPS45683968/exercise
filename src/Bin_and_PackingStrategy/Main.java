package Bin_and_PackingStrategy;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int capacity = 100;
        List<Integer> values = Arrays.asList(75,50,20,60,40,50);
        PackingStrategy firstFit = new FirstFitStrategy();
        PackingStrategy nextFit = new NextFitStrategy();
        PackingStrategy bestFit = new BestFitStrategy();
        System.out.println("First Fit: " + firstFit.pack(capacity, values).stream().mapToInt(s-> s.getSpace()).sum());
        System.out.println("First Fit: " + firstFit.pack(capacity, values));
        System.out.println("Next Fit: " + nextFit.pack(capacity, values).stream().mapToInt(s-> s.getSpace()).sum());
        System.out.println("Next Fit: " + nextFit.pack(capacity, values));
        System.out.println("Best Fit: " + bestFit.pack(capacity, values).stream().mapToInt(s-> s.getSpace()).sum());
        System.out.println("Best Fit: " + bestFit.pack(capacity, values));
    }
}
