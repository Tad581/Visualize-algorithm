package application.Graph.Edge;

import application.Graph.Vertex.Vertex;

public class Edge {
    private Vertex start;
    private Vertex end;
    private boolean isDirected; 
    public Edge(){
        
    }
    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }
    // private boolean isDirected = false;
    // private boolean isWeighted = false;
    // private int label = 0;

    public Vertex getStart() {
        return start;
    }
    public void setStart(Vertex start) {
        this.start = start;
    }
    public Vertex getEnd() {
        return end;
    }
    public void setEnd(Vertex end) {
        this.end = end;
    }

}
