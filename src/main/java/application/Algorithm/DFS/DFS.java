package application.Algorithm.DFS;

import application.Algorithm.Algorithm;
import application.Algorithm.Step.Step;
import application.Algorithm.Step.Detail.Detail;
import application.Algorithm.Step.Pseudo.Pseudo;
import application.Graph.Graph;
import application.Graph.Vertex.Vertex;

import java.util.ArrayList;
import java.util.Stack;
public class DFS extends Algorithm{
    private Stack<Vertex> Travel = new Stack<Vertex>();
    public DFS(Graph g) {
        super(g);
    }

    public DFS(){
        
    }

    public ArrayList<Step> setPseu(){
        ArrayList<Step> list = new ArrayList<Step>();
        list.add(new Pseudo(0)); 
        list.get(0).setStepContent("DFS(u)");


        list.add(new Pseudo(1)); 
        list.get(1).setStepContent("For each neighbor v of u");


        list.add(new Pseudo(2)); 
        list.get(2).setStepContent("if v is unvisited, tree edge, DFS(v)");


        list.add(new Pseudo(3)); 
        list.get(3).setStepContent("else if v is explored, back edge");


        list.add(new Pseudo(4)); 
        list.get(4).setStepContent("else if v is visited, cross edge");

        
        return list;
    }

    
    public Stack<Vertex> getTravel() {
        return Travel;
    }


    public void setTravel(Stack<Vertex> travel) {
        Travel = travel;
    }


    public void traversal(int id){
        Stack<Vertex> stack = new Stack<>();
        ArrayList<Step> listStep = new ArrayList<Step>();
        ArrayList<Step> listStepDetail = new ArrayList<Step>();
        stack.push(this.getG().getVertices().get(id));
        int StepId = 0;
        int StepDeId = 0;
        this.getG().getVertices().get(id).setTraveled(true);

        while(!stack.isEmpty()){
            Vertex curVertex = stack.peek();

            ArrayList<Vertex> neighbor = this.getG().neighborUncheck(curVertex);
            listStep.add(new Pseudo(0)); 
            listStep.get(StepId).setStepContent("DFS(u)");

            StepId++;

            listStepDetail.add(new Detail());
            listStepDetail.get(StepDeId).setStepContent("DFS(" + curVertex.getId() + ")");
            ((Detail) listStepDetail.get(StepDeId)).setFromVerID(curVertex.getId());
            StepDeId++;

            if(neighbor.size() == 0){
                System.out.println("there no way to travel");
                break;
            }
            else{
                int check = 0;
                for(int i = 0;i < neighbor.size(); i++){
                    if(neighbor.get(i).isTraveled() == false && this.getG().neighborCheck(curVertex, neighbor.get(i)) == true){
                        
                        listStep.add(new Pseudo(1));
                        listStep.get(StepId).setStepContent("For each neighbor v of u");

                        StepId++;

                        listStepDetail.add(new Detail(curVertex.getId(),neighbor.get(i).getId()));
                        listStepDetail.get(StepDeId).setStepContent("Try " + curVertex.getId() + " -> " + neighbor.get(i).getId());

                        StepDeId++;

                        listStep.add(new Pseudo(2));
                        listStep.get(StepId).setStepContent("if v is unvisited, tree edge, DFS(v)");

                        StepId++;

                        listStepDetail.add(new Detail());
                        listStepDetail.get(StepDeId).setStepContent("Vertex " + neighbor.get(i).getId() + " is unvisited ");
                        ((Detail) listStepDetail.get(StepDeId)).setToVerID(neighbor.get(i).getId());

                        StepDeId++;

                        neighbor.get(i).setTraveled(true);

                        stack.push(neighbor.get(i));
                        check = 1;
                        break;
                    }
                    else if (neighbor.get(i).isTraveled() == true && this.getG().neighborCheck(curVertex, neighbor.get(i)) == true){

                        listStep.add(new Pseudo(3));
                        listStep.get(StepId).setStepContent("else if v is explored, back edge");

                        StepId++;

                        listStepDetail.add(new Detail(curVertex.getId(),neighbor.get(i).getId()));
                        listStepDetail.get(StepDeId).setStepContent("Vertex " + neighbor.get(i).getId() + " is explored ,back edge ");

                        StepDeId++;
                    }
                }
                if(check == 0){

                    listStep.add(new Pseudo(4));
                    listStep.get(StepId).setStepContent("else if v is visited, cross edge");

                    StepId++;

                    listStepDetail.add(new Detail());
                    listStepDetail.get(StepDeId).setStepContent("Finish DFS(" + curVertex.getId() + ") backtrack ");
                    ((Detail) listStepDetail.get(StepDeId)).setFromVerID(curVertex.getId());


                    StepDeId++;

                    this.Travel.add(curVertex);
                    stack.pop();
                }
            }
        }
        this.setListofStep(listStep);
        this.setListofDetail(listStepDetail);
    }
}
