package utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Grid {
    private double [][] grid;
    private boolean [][] allocated;
    private int [][] visible;
    private int groups;
    private List<Group> listGroups;
    
    public Grid(int dimx, int dimy, int groups) {
        this.grid = new double[dimx][dimy];
        this.allocated = new boolean[dimx][dimy];
        this.visible = new int[dimx][dimy];

        this.groups = groups;
        this.listGroups = new ArrayList<Group>(this.groups);
        
        initGrid(dimx, dimy);
    }
    
    private void initGrid(int dimx, int dimy) {
        for(int i=0 ; i<dimx ; i++) {
            for(int j=0 ; j<dimy ; j++) {
                this.grid[i][j] = (dimy*i + j) + 1;
            }
        }
    }

    public List<Group> getListGroups(){
        return this.listGroups;
    }

    public void initializeGroups() {
        int c = this.groups;
        while(c > 0) {
            Group gp = new Group();
            Position position = this.getRandomFreeCell();
            gp.addComponent(position);
            this.listGroups.add(gp);
            allocated[position.y][position.x] = true;
            c--;
        }
        
//          Group gp = new Group();
//          Position position = new Position(0, 0, 1);
//          gp.addComponent(position);
//          this.listGroups.add(gp);
//          allocated[position.y][position.x] = true;
//          
//          gp = new Group();
//          position = new Position(2, 0, 3);
//          gp.addComponent(position);
//          this.listGroups.add(gp);
//          allocated[position.y][position.x] = true;
//          
//          gp = new Group();
//          position = new Position(0, 2, 7);
//          gp.addComponent(position);
//          this.listGroups.add(gp);
//          allocated[position.y][position.x] = true;
//          
//          gp = new Group();
//          position = new Position(2, 2, 9);
//          gp.addComponent(position);
//          this.listGroups.add(gp);
//          allocated[position.y][position.x] = true;
        
    }

    public void setAllocated(Position selectedPos) {
        this.allocated[selectedPos.y][selectedPos.x] = true;
    }

    public List<Position> getFreeNeighbors(Position p) {
        List<Position> lp = new LinkedList<Position>();
        if(p.x > 0 && !this.allocated[p.y][p.x-1]) {
            lp.add(new Position(p.x-1, p.y, grid[p.y][p.x-1]));
        }
        if(p.x < (grid[0].length - 1) && !this.allocated[p.y][p.x+1]) {
            lp.add(new Position(p.x+1, p.y, grid[p.y][p.x+1]));
        }
        
        if(p.y > 0 && !this.allocated[p.y-1][p.x]) {
            lp.add(new Position(p.x, p.y-1, grid[p.y-1][p.x]));
        }
        if(p.y < (grid.length - 1) && !this.allocated[p.y+1][p.x]) {
            lp.add(new Position(p.x, p.y+1, grid[p.y+1][p.x]));
        }
        
        return lp;
    }

    public Position getRandomFreeCell() {
        List<Position> lp = new LinkedList<Position>();
        for(int i=0 ; i<grid.length ; i++) {
            for(int j=0 ; j<grid[i].length ; j++) {
                if(!this.allocated[i][j]) {
                    lp.add(new Position(j, i, grid[i][j]));
                }
            }
        }
        
        Collections.shuffle(lp);
        return lp.get(0);
    }
    
    
    @Override
    public String toString() {
        int count = 1;
        for(Group gp: this.getListGroups()) {
            for(Position p: gp.getComponents()) {
                this.visible[p.y][p.x] = count; 
            }
            count++;
        }
        String out = "";
        for(int i=0 ; i<this.visible.length ; i++) {
            String line = "";
            for(int j=0 ; j<this.visible[i].length ; j++) {
                line += " | "+this.visible[i][j];
            }
            line += " |";
            line = line.trim();
            out += line + "\n";
        }
        
        return out;
    }

    public Grid copy() {
        Grid clone = new Grid(grid[0].length, grid.length, this.groups);
        
        for(int i=0 ; i<grid.length ; i++) {
            for(int j=0 ; j<grid[i].length ; j++) {
                clone.allocated[i][j] = this.allocated[i][j];
                clone.visible[i][j] = this.visible[i][j];
                clone.grid[i][j] = this.grid[i][j];
            }
        }
        
        for(Group gpr: this.listGroups) {
            Group cloneGrp = new Group();
            for(Position pos: gpr.getComponents()) {
                cloneGrp.addComponent(pos.x, pos.y, pos.val);
            }
            clone.listGroups.add(cloneGrp);
        }
        return clone;
    }

}
