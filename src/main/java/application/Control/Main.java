package application.Control;

// import Algorithm.Algorithm;
import application.Algorithm.DFS.DFS;
import application.Algorithm.Topological.TOPOLO;
import application.Graph.Graph;
// import Graph.Vertex.Vertex;

public class Main {
    public static void main(String[] args) {
        Graph graph = Graph.autoGenerateGraph();
        graph.displayGraph(graph);
        TOPOLO topo = new TOPOLO(graph);
        topo.traversal();
        for(int  i= 0; i< topo.getListofDetail().size();i++){
            System.out.println(topo.getListofStep().get(i).toString());
            System.out.println(topo.getListofDetail().get(i).toString());
        }
    }
}
