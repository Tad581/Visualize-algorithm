package Algorithm;

import java.util.ArrayList;

import Algorithm.Step.Step;
import Graph.Graph;

public abstract class Algorithm{
    private Graph g;
    ArrayList<Step> listofStep = new ArrayList<Step>();
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

    }
    public ArrayList<Step> getListofStep() {
        return listofStep;
    }
    public void setListofStep(ArrayList<Step> listofStep) {
        this.listofStep = listofStep;
    };

    public void PrintStep(){
        for (int i = 0; i< this.listofStep.size(); i++){
            if(this.listofStep.get(i).toString() != null){
                System.out.println(this.listofStep.get(i).toString());
            }
            
        }
    }
    
}