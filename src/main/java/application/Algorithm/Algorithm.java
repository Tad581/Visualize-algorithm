package application.Algorithm;

import java.util.ArrayList;

import application.Algorithm.Step.Step;
import application.Graph.Graph;

public abstract class Algorithm{
    private Graph g;
    ArrayList<Step> listofStep = new ArrayList<Step>();  //Pseu
    ArrayList<Step> listofDetail = new ArrayList<Step>(); //Detail
    public Algorithm(Graph g){
        this.g =g;
    }
    public Algorithm(){

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

    public ArrayList<Step> setPseu(){
        return null;
    }

    public void PrintStep(){
        for (int i = 0; i< this.listofStep.size(); i++){
            if(this.listofStep.get(i).toString() != null){
                System.out.println(this.listofStep.get(i).toString());
                System.out.println(this.listofDetail.get(i).toString());
            }
            
        }
    }
    public ArrayList<Step> getListofDetail() {
        return listofDetail;
    }
    public void setListofDetail(ArrayList<Step> listofDetail) {
        this.listofDetail = listofDetail;
    }
    
}