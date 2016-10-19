package utils;
import java.util.LinkedList;
import java.util.List;


public class Group {
    private List<Position> components;
    private double load;
    
    public Group() {
        this.components = new LinkedList<Position>();
    }
    
    
    public void addComponent(int x, int y, double val) {
        this.addComponent(new Position(x, y, val));
        this.load += val;
    }
    
    public void addComponent(Position p) {
        this.components.add(p);
        this.load += p.val;
    }
    
    
    
    public double getLoad() {
        return this.load;
    }
    
    public List<Position> getComponents(){
        return this.components;
    }
    
}
