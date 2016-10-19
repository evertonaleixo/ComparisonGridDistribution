package distributions;
import java.util.List;

import utils.Grid;
import utils.Group;
import utils.Position;


public class GreedyDistribution extends Distribution {

    public GreedyDistribution(Grid g) {
        super(g);
    }

    @Override
    public void fillGrid(Grid g) {
        int toAllocate = (dimx * dimy) - numMachines;
        
        for(int i=0 ; i<toAllocate ; i++) {
            updateLoadMean();
            Group grp = nextGroup();
            Position selectedPos = selectNextCell(grp);
            
            grp.addComponent(selectedPos);
            
            g.setAllocated(selectedPos);
        }
    }
    

    private Position selectNextCell(Group grp) {
        List<Position> lp = getNeighbors(grp);
        Position selected = null;
        if(lp.isEmpty())
            lp.add(grid.getRandomFreeCell());
        
        if(!lp.isEmpty()) {
            selected = lp.get(0);
            double bestValue = Math.abs((grp.getLoad() +  lp.get(0).val) - this.mean);
            for(Position p: lp) {
                if(Math.abs((grp.getLoad() +  p.val) - this.mean) < bestValue ){
                    selected = p;
                    bestValue = Math.abs((grp.getLoad() +  p.val) - this.mean);
                }
            }
        }
            
        return selected;
    }

    private Group nextGroup() {
        Group toReturn = grid.getListGroups().get(0);
        for(Group g: grid.getListGroups()) {
            if(toReturn.getLoad() > g.getLoad())
                toReturn = g;
        }
        return toReturn;
    }

}
