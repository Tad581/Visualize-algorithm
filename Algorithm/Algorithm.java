package Algorithm;

import java.util.ArrayList;

import Algorithm.Step.Step;
import Graph.Graph;
// import java.util.ArrayList;
public abstract class Algorithm extends Step{
    private Graph g;
    ArrayList<Step> listofStep = new ArrayList<>();
    public Algorithm(Graph g){
        this.g =g;
    }
    public Graph getG() {
        return g;
    }
    public void setG(Graph g) {
        this.g = g;
    }
    public void traversal(int id){

    };
}