package distributions;
import java.util.LinkedList;
import java.util.List;

import utils.Grid;
import utils.Group;
import utils.Position;


public abstract class Distribution {
    protected Grid grid;
    protected double mean = 0;
    protected final int numMachines = 4;
    protected final int dimx = 5;
    protected final int dimy = 5;
    
    public Distribution(Grid g) {
        if(g==null) {
            grid = new Grid(dimx, dimy, numMachines);
            grid.initializeGroups();
        } else {
            grid = g;
        }

        fillGrid(grid);
        
        print();
    }
    
    public abstract void fillGrid(Grid g);
    
    
    protected List<Position> getNeighbors(Group grp) {
        List<Position> lp = new LinkedList<Position>();
        for(Position p: grp.getComponents()) {
            List<Position> pointNeighbors = this.grid.getFreeNeighbors(p);
            lp.addAll(pointNeighbors);
        }
        return lp;
    }
    
    protected void updateLoadMean() {
        int count = 0;
        double sum = 0;
        for(Group grp: grid.getListGroups()) {
            count++;
            sum += grp.getLoad();
        }
        this.mean = (sum/count);
    }
    
    public void print() {
        int count = 1;
        System.out.println("Load mean: " + mean);
        for(Group gp: grid.getListGroups()) {
            System.out.printf("S%d - {", count++);
            for(Position p: gp.getComponents()) {
                System.out.printf("([%d,%d] %f), ", p.x, p.y, p.val );
            }
            System.out.printf("} -- %f\n", gp.getLoad());
        }
        
        System.out.println(this.grid);
    }
}
