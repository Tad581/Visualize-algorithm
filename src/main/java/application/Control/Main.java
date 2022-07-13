package application.Control;

import application.Algorithm.CutVertex.CutVertex;
// import Algorithm.Algorithm;
import application.Algorithm.DFS.DFS;
import application.Algorithm.Topological.TOPOLO;
import application.Graph.Graph;
// import Graph.Vertex.Vertex;

public class Main {
    public static void main(String[] args) {
        Graph graph = Graph.autoGenerateGraph();
        graph.displayGraph(graph);
        CutVertex cut = new CutVertex(graph);
        cut.traversal(0,0);
        for(int i = 0;i<cut.getListofStep().size();i++){
            System.out.println(cut.getListofStep().get(i));
            System.out.println(cut.getListofDetail().get(i));
        }
    }
}
