package application.Graph;

import application.Graph.Edge.Edge;
import application.Graph.Vertex.Vertex;
import java.util.ArrayList;
// import java.util.List;

public class Graph {
    public int numberOfVertices = 0;
    public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    public ArrayList<Edge> edges = new ArrayList<Edge>();

    public Graph() {
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public static Graph autoGenerateGraph() {
        Graph graph = new Graph();

        for (int i = 0; i < 3; i++) {
            Vertex tempVertex = new Vertex(i);
            graph.getVertices().add(tempVertex);
        }

        graph.edges.add(new Edge(graph.getVertices().get(1), graph.getVertices().get(0)));
        graph.edges.add(new Edge(graph.getVertices().get(2), graph.getVertices().get(0)));

        return graph;
    }

    public void displayGraph(Graph graph) {
        System.out.println("*************************");
        System.out.println("Vertices: ");
        for (int i = 0; i < graph.getVertices().size(); i++) {
            System.out.print(graph.getVertices().get(i).getId() + " - ");
        }
        System.out.println("*************************");
        System.out.println("Edges: ");
        for (int i = 0; i < graph.edges.size(); i++) {
            System.out.println(graph.edges.get(i).getStart().getId() + " - " + graph.edges.get(i).getEnd().getId());
        }
    }

    public boolean neighborCheck(Vertex from, Vertex to) {
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getStart() == from && edges.get(i).getEnd() == to) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vertex> neighborUncheck(Vertex v) {
        ArrayList<Vertex> neighborVer = new ArrayList<Vertex>();
        Vertex tmp = null;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getStart() == v || edges.get(i).getEnd() == v) {
                if (edges.get(i).getStart() == v) {
                    tmp = edges.get(i).getEnd();
                    neighborVer.add(tmp);
                }
                if (edges.get(i).getEnd() == v) {
                    tmp = edges.get(i).getStart();
                    neighborVer.add(tmp);
                }
            }
        }
        return neighborVer;
    }

    public void removeGraph() {
        for (int i = 0; i < this.vertices.size(); i++) {
            this.vertices.remove(i);
        }
        for (int i = 0; i < this.edges.size(); i++) {
            this.edges.remove(i);
        }
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public Edge GetedgewithFromTo(int from, int to) {
        Edge e = new Edge();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getStart().getId() == from && edges.get(i).getEnd().getId() == to) {
                e = edges.get(i);
                break;
            }
        }
        return e;
    }

    public ArrayList<Vertex> neighborUncheckforCutver(Vertex v) {
        ArrayList<Vertex> neighborVer = new ArrayList<Vertex>();
        ArrayList<Vertex> neighborVerfinal = new ArrayList<Vertex>();
        Vertex tmp = null;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getStart() == v) {
                tmp = edges.get(i).getEnd();
                neighborVer.add(tmp);
            }
        }
        while (!neighborVer.isEmpty()) {
            tmp = neighborVer.get(0);
            for (int i = 1; i < neighborVer.size(); i++) {
                if (neighborVer.get(i).getId() < tmp.getId()) {
                    tmp = neighborVer.get(i);
                }
            }
            neighborVerfinal.add(tmp);
            neighborVer.remove(tmp);
        }

        return neighborVerfinal;
    }

    public void SetVerDegree() {
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (neighborCheck(vertices.get(i), vertices.get(j)) == true) {
                    vertices.get(j).setDegree(vertices.get(j).getDegree() + 1);
                }
            }
        }
    }
}