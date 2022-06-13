package Control;


import Graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = Graph.autoGenerateGraph();
        graph.displayGraph(graph);
    }    
}
