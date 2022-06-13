package Graph.Vertex;

public class Vertex {
    private int id;
    private boolean isTraveled = false;

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTraveled() {
        return isTraveled;
    }

    public void setTraveled(boolean isTraveled) {
        this.isTraveled = isTraveled;
    }
    
}
