package Graph;

import Graph.Edge.Edge;
import Graph.Vertex.Vertex;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    public Graph() {
    }

    public static Graph autoGenerateGraph () {
        Graph graph = new Graph();
        
        for (int i = 0; i < 5; i++) {
            Vertex tempVertex = new Vertex(i);
            graph.vertices.add(tempVertex);
        }

        graph.edges.add(new Edge(graph.vertices.get(0), graph.vertices.get(1)));
        graph.edges.add(new Edge(graph.vertices.get(0), graph.vertices.get(2)));
        graph.edges.add(new Edge(graph.vertices.get(1), graph.vertices.get(3)));
        graph.edges.add(new Edge(graph.vertices.get(2), graph.vertices.get(4)));
        graph.edges.add(new Edge(graph.vertices.get(1), graph.vertices.get(4)));
        graph.edges.add(new Edge(graph.vertices.get(3), graph.vertices.get(4)));
        
        return graph;
    }

    public void displayGraph(Graph graph) {
        System.out.println("*************************");
        System.out.println("Vertices: ");
        for (int i = 0; i < graph.vertices.size(); i++) {
            System.out.print(graph.vertices.get(i).getId() + " - ");
        }
        System.out.println("*************************");
        System.out.println("Edges: ");
        for (int i = 0; i < graph.edges.size(); i++) {
            System.out.println(graph.edges.get(i).getStart().getId() + " - " + graph.edges.get(i).getEnd().getId());
        }
    } 

}