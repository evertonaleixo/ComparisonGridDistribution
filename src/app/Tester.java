package app;

import distributions.GreedyDistribution;
import distributions.RandonDistribution;
import utils.Grid;

public class Tester {
    private final int numMachines = 4;
    private final int dimx = 5;
    private final int dimy = 5;
    
    public Tester() {
        Grid base = new Grid(dimx, dimy, numMachines);
        base.initializeGroups();
        
        Grid g1 = (Grid) base.copy();
        Grid g2 = (Grid) base.copy();
        
        // Execute Greedy algorithm
        new GreedyDistribution(g1);
        System.out.println("\n\n");
        
        // Execute Random algorithm
        new RandonDistribution(g2);
        System.out.println("\n\n");
        
        // Execute Genetic algorithm
    }
    
    public static void main(String[] args) {
        new Tester();
    }
}
