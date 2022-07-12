package application.Graph.Vertex;

public class Vertex {
    private int id;
    private boolean isTraveled = false;
    private int degree = 0;

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

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
    
    public String toString() {
        String str = String.valueOf(id);
        return str;
    }
}
