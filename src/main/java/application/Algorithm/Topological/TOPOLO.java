package application.Algorithm.Topological;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import application.Algorithm.Algorithm;
import application.Algorithm.Step.Step;
import application.Algorithm.Step.Detail.Detail;
import application.Algorithm.Step.Pseudo.Pseudo;
import application.Graph.Graph;
import application.Graph.Vertex.Vertex;

public class TOPOLO extends Algorithm{

    public TOPOLO(Graph g) {
        super(g);
    }

    public TOPOLO(){

    }

    public ArrayList<Step> setPseu(){
        ArrayList<Step> list = new ArrayList<Step>();
        list.add(new Pseudo(0)); 
        list.get(0).setStepContent("add vertices with no incoming edge to queue Q");

        list.add(new Pseudo(1)); 
        list.get(1).setStepContent("while !Q.empty // Q is a normal queue");

        list.add(new Pseudo(2)); 
        list.get(2).setStepContent("u = Q.front, Q.pop, add u to the back of list");

        list.add(new Pseudo(3)); 
        list.get(3).setStepContent("for each neighbor v of u \n   delete edge u → v");

        list.add(new Pseudo(4)); 
        list.get(4).setStepContent("if v has no incoming edge, add v to queue");

        return list;
    }

    public void traversal(){
        Queue<Vertex>  queue = new LinkedList<>();
        ArrayList<Step> PseuStep = new ArrayList<Step>();
        ArrayList<Step> DetailStep = new ArrayList<Step>();
        int Pseucount = 0;
        int Detailcount  = 0;
        int preVerID = -1;
        String q = "Queue = [";
        
        PseuStep.add(new Pseudo(0)); 
        PseuStep.get(Pseucount).setStepContent("add vertices with no incoming edge to queue Q");

        Pseucount++;

        this.getG().SetVerDegree();

        for(int i = 0; i < this.getG().getVertices().size(); i++){
            if(this.getG().getVertices().get(i).getDegree() == 0){
                
                queue.offer(this.getG().getVertices().get(i));
                q = q + queue.element() + ",";
                
            }
        }
        q = q + "].";
        q = q.replace(",]", "]");

        DetailStep.add(new Detail());
        DetailStep.get(Detailcount).setStepContent(q);
        ((Detail) DetailStep.get(Detailcount)).setFromVerID(queue.element().getId());

        Detailcount++;

        int count = 0;
        while(!queue.isEmpty()){
            PseuStep.add(new Pseudo(1)); 
            PseuStep.get(Pseucount).setStepContent("while !Q.empty // Q is a normal queue");

            Pseucount++;

            DetailStep.add(new Detail());
            DetailStep.get(Detailcount).setStepContent(q);
            ((Detail) DetailStep.get(Detailcount)).setToVerID(queue.element().getId());
            if(preVerID >= 0){
                ((Detail) DetailStep.get(Detailcount)).setFromVerID(preVerID);
            }

            Detailcount++;

            Vertex curVertex = queue.poll();
            preVerID = curVertex.getId();

            q = q.replace(curVertex.toString(), "");
            q = q.replaceFirst(",", "");
            
            PseuStep.add(new Pseudo(2)); 
            PseuStep.get(Pseucount).setStepContent("u = Q.front, Q.pop, add u to the back of list");

            Pseucount++;

            DetailStep.add(new Detail());
            DetailStep.get(Detailcount).setStepContent("Pop vertex " +curVertex.getId()+ " from queue and add it to the back of the list.");
            ((Detail) DetailStep.get(Detailcount)).setFromVerID(curVertex.getId());

            Detailcount++;


            ArrayList<Vertex> neighbor = this.getG().neighborUncheck(curVertex);

            for(int i = 0; i< neighbor.size(); i++){
                if(this.getG().neighborCheck(curVertex, neighbor.get(i)) == true){
                    neighbor.get(i).setDegree(neighbor.get(i).getDegree() - 1);

                    PseuStep.add(new Pseudo(3)); 
                    PseuStep.get(Pseucount).setStepContent("for each neighbor v of u \n   delete edge u → v");

                    Pseucount++;

                    DetailStep.add(new Detail(curVertex.getId(),neighbor.get(i).getId()));
                    DetailStep.get(Detailcount).setStepContent(q + "\nDelete edge" + curVertex.getId() + " -> " + neighbor.get(i).getId());

                    Detailcount++;

                    if(neighbor.get(i).getDegree() == 0){
                        PseuStep.add(new Pseudo(4)); 
                        PseuStep.get(Pseucount).setStepContent("if v has no incoming edge, add v to queue");

                        Pseucount++;

                        q  = q.replace("]", neighbor.get(i).toString()+","+"]");
                        DetailStep.add(new Detail());
                        DetailStep.get(Detailcount).setStepContent(q + "\nVertex " + neighbor.get(i).getId() + "now has no incoming edge, add it to queue.");
                        ((Detail) DetailStep.get(Detailcount)).setToVerID(neighbor.get(i).getId());

                        Detailcount++;

                        queue.offer(neighbor.get(i));
                    }
                    else{
                        PseuStep.add(new Pseudo(4)); 
                        PseuStep.get(Pseucount).setStepContent("if v has no incoming edge, add v to queue");

                        Pseucount++;
                        
                        DetailStep.add(new Detail());
                        DetailStep.get(Detailcount).setStepContent(q + "\nVertex " + neighbor.get(i).getId() + "still has incoming edge, ignore it.");
                        // ((Detail) DetailStep.get(Detailcount)).setToVerID(neighbor.get(i).getId());

                        Detailcount++;
                    }
                }
            }
            count++;
        }
        if(count > this.getG().getVertices().size()){
            System.out.println("The graph has cycle");
        }
        this.setListofStep(PseuStep);
        this.setListofDetail(DetailStep);
    }

    public void replaceString(String s,Character tmp){
        for(int  i= 0;i<s.length();i++){
            if(s.charAt(i) == tmp){

            }
        }
    }

}
