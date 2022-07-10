package application.Algorithm.DFS;

import application.Algorithm.Algorithm;
import application.Algorithm.Step.Step;
import application.Algorithm.Step.CreateStep.CreateDetailStep;
import application.Algorithm.Step.CreateStep.CreatePseudoStep;
import application.Algorithm.Step.CreateStep.CreateStep;
import application.Graph.Graph;
import application.Graph.Vertex.Vertex;

import java.util.ArrayList;
import java.util.Stack;
public class DFS extends Algorithm{
    private Stack<Vertex> Travel = new Stack<Vertex>();
    public DFS(Graph g) {
        super(g);
    }

    
    public Stack<Vertex> getTravel() {
        return Travel;
    }


    public void setTravel(Stack<Vertex> travel) {
        Travel = travel;
    }

    public static Step createStep(CreateStep step){
        return step.CreateOneStep();
    }

    public void traversal(int id){
        Stack<Vertex> stack = new Stack<>();
        ArrayList<Step> listStep = new ArrayList<Step>();
        stack.push(this.getG().getVertices().get(id));
        int StepId = 0;
        this.getG().getVertices().get(id).setTraveled(true);
        // System.out.println("Start travel " + this.getG().getVertices().get(id).getId());
        // this.travellist = this.travellist + this.g.getVertices().get(0).getId();
        while(!stack.isEmpty()){
            Vertex curVertex = stack.peek();
            // System.out.println("Searching neighbor vertices of " + curVertex.getId());
            // Vertex ver = this.getG().neighborVertex(curVertex);
            ArrayList<Vertex> neighbor = this.getG().neighborUncheck(curVertex);
            listStep.add(createStep(new CreatePseudoStep())); 
            listStep.get(StepId).setStepContent("DFS(u)");
            // System.out.println(listStep.get(StepId).toString());
            StepId++;
            
            listStep.add(createStep(new CreateDetailStep()));
            listStep.get(StepId).setStepContent("DFS(" + curVertex.getId() + ")");

            if(neighbor.size() == 0){
                System.out.println("there no way to travel");
            }
            else{
                int check = 0;
                for(int i = 0;i < neighbor.size(); i++){
                    if(neighbor.get(i).isTraveled() == false){
                        
                        listStep.add(createStep(new CreatePseudoStep()));
                        StepId++;
                        listStep.get(StepId).setStepContent("For each neighbor v of u");

                        listStep.add(createStep(new CreateDetailStep()));
                        StepId++;
                        listStep.get(StepId).setStepContent("Try" + curVertex.getId() + " -> " + neighbor.get(i).getId());

                        listStep.add(createStep(new CreatePseudoStep()));
                        StepId++;
                        listStep.get(StepId).setStepContent("if v is unvisited, tree edge, DFS(v)");

                        listStep.add(createStep(new CreateDetailStep()));
                        StepId++;
                        listStep.get(StepId).setStepContent("Vertex" + neighbor.get(i).getId() + " is unvisited ");

                        neighbor.get(i).setTraveled(true);
                        // System.out.println("push neibor to stack");
                        stack.push(neighbor.get(i));
                        check = 1;
                        break;
                    }
                    else{
                        // System.out.println("neighbor istraveled");
                        listStep.add(createStep(new CreatePseudoStep()));
                        StepId++;
                        listStep.get(StepId).setStepContent("else if v is explored, back edge");

                        listStep.add(createStep(new CreateDetailStep()));
                        StepId++;
                        listStep.get(StepId).setStepContent("Vertex" + neighbor.get(i).getId() + " is exp]lored ,back edge ");
                    }
                }
                if(check == 0){
                    // System.out.println("No neighbor vertices...");
                    // System.out.println("Clear stack...");
                    listStep.add(createStep(new CreatePseudoStep()));
                    StepId++;
                    listStep.get(StepId).setStepContent("else if v is visited, cross edge");

                    listStep.add(createStep(new CreateDetailStep()));
                    StepId++;
                    listStep.get(StepId).setStepContent("Finish DFS(" + curVertex.getId() + ") backtrack ");

                    this.Travel.add(curVertex);
                    stack.pop();
                }
            }
        }
        this.setListofStep(listStep);
    }
}
