package distributions;
import utils.Grid;
import utils.Group;
import utils.Position;



public class RandonDistribution extends Distribution {
    private int countGroups = 0;
    
    public RandonDistribution(Grid g) {
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
        return grid.getRandomFreeCell();
    }

    
    private Group nextGroup() {
        int idx = (this.countGroups++) % this.numMachines;
        return grid.getListGroups().get(idx);
    }

}
