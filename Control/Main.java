package Control;

// import Algorithm.Algorithm;
import Algorithm.DFS.DFS;
import Graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = Graph.autoGenerateGraph();
        graph.displayGraph(graph);
        DFS dfs = new DFS(graph);
        dfs.traversal(2);
        // System.out.println("" + dfs.getTravellist());
    }
}
