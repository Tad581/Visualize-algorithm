package Algorithm.DFS;

import Algorithm.Algorithm;
import Graph.Graph;
import Graph.Vertex.Vertex;

import java.util.Stack;
public class DFS extends Algorithm{
    private String travellist;
    public DFS(Graph g) {
        super(g);
    }

    public String getTravellist() {
        return travellist;
    }

    public void traversal(int id){
        Stack<Vertex> stack = new Stack<>();
        stack.push(this.getG().getVertices().get(id));
        this.getG().getVertices().get(id).setTraveled(true);
        System.out.println("Start travel " + this.getG().getVertices().get(id).getId());
        // this.travellist = this.travellist + this.g.getVertices().get(0).getId();
        while(!stack.isEmpty()){
            Vertex curVertex = stack.peek();
            System.out.println("Searching neiborgh vertices of " + curVertex.getId());
            Vertex ver = this.getG().neiborghVertex(curVertex);
            if (ver != null){
                System.out.println("Push neiborgh vertex " + ver.getId() + " to stack");
                stack.push(ver);
                System.out.println("Travel " + ver.getId());
            }
            else{
                System.out.println("No neiborgh vertices...");
                System.out.println("Clear stack...");
                stack.pop();
            }
        }
    }
}
