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
        
        new GreedyDistribution(g1);
        
        System.out.println("\n\n");
        new RandonDistribution(g2);
    }
    
    public static void main(String[] args) {
        new Tester();
    }
}
