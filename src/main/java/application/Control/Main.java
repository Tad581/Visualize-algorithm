package application.Control;

// import Algorithm.Algorithm;
import application.Algorithm.DFS.DFS;
import application.Graph.Graph;
// import Graph.Vertex.Vertex;

public class Main {
    public static void main(String[] args) {
        Graph graph = Graph.autoGenerateGraph();
        graph.displayGraph(graph);
        DFS dfs = new DFS(graph);
        dfs.traversal(5);
        dfs.PrintStep();
        // System.out.println("" + dfs.getTravellist());
        // for(int i = 0; i< dfs.getTravel().size(); i++){
        //     Vertex cur = dfs.getTravel().peek();
        //     System.out.print(" " + cur.getId());
        //     dfs.getTravel().pop();
        // }
    }
}
