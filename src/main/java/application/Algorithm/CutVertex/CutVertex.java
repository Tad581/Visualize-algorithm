package application.Algorithm.CutVertex;

import java.util.ArrayList;

import application.Algorithm.Algorithm;
import application.Algorithm.Step.Step;
import application.Algorithm.Step.Detail.Detail;
import application.Algorithm.Step.Pseudo.Pseudo;
import application.Graph.Graph;
import application.Graph.Vertex.Vertex;

public class CutVertex extends Algorithm{
    public CutVertex(){

    }
    public CutVertex(Graph g){
        super(g);
    }

    public int DFScount = -1;
    private int Pseucount = 0;
    private int Detailcount = 0;

    public ArrayList<Step> setPseu(){
        ArrayList<Step> list = new ArrayList<Step>();
        list.add(new Pseudo(0)); 
        list.get(0).setStepContent("try all vertex u, if u hasnt been visited, DFS(u)");


        list.add(new Pseudo(1)); 
        list.get(1).setStepContent("DFS(u), initiate num[u] = low[u] = DFSCount");


        list.add(new Pseudo(2)); 
        list.get(2).setStepContent("\ttry all neighbor v of u");


        list.add(new Pseudo(3)); 
        list.get(3).setStepContent("\t\tif v is free, DFS(v)");


        list.add(new Pseudo(4)); 
        list.get(4).setStepContent("\t\telse low[u] = min(low[u], num[v])");

        list.add(new Pseudo(5)); 
        list.get(5).setStepContent("\t\t\tlow[u] = min(low[u], low[v])");

        list.add(new Pseudo(6)); 
        list.get(6).setStepContent("\t\t\tcheck the condition");

        list.add(new Pseudo(7)); 
        list.get(7).setStepContent("\tbacktrack");

        return list;
    }

    public void CutverTraversal(int idCur,int idPre){
        this.getListofStep().add(new Pseudo(0));
        this.getListofStep().get(Pseucount).setStepContent("try all vertex u, if u hasnt been visited, DFS(u)");
        Pseucount++;

        this.getListofDetail().add(new Detail());
        this.getListofDetail().get(Detailcount).setStepContent("Vertex 0 has not been visited.\nDFSCount = -1.");
        Detailcount++;

        traversal(idCur,idPre);
    }

    public void traversal(int idCur,int idPre){
        Vertex curVertex = this.getG().getVertices().get(idCur);
        int min;
        DFScount++;
        curVertex.setNum(DFScount);
        curVertex.setLow(DFScount);
        this.getListofStep().add(new Pseudo(1));
        this.getListofStep().get(Pseucount).setStepContent("DFS(u), initiate num[u] = low[u] = DFSCount");
        Pseucount++;

        this.getListofDetail().add(new Detail());
        this.getListofDetail().get(Detailcount).setStepContent("DFS(" + curVertex.getId() + ").\nDFSCount = " + String.valueOf(DFScount));
        ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(curVertex.getId());
        Detailcount++;

        ArrayList<Vertex> neighbor = this.getG().neighborUncheckforCutver(curVertex);
        for(int i = 0;i < neighbor.size(); i++){
            this.getListofStep().add(new Pseudo(2));
            this.getListofStep().get(Pseucount).setStepContent("try all neighbor v of u");
            Pseucount++;

            this.getListofDetail().add(new Detail());
            this.getListofDetail().get(Detailcount).setStepContent("Try edge " + curVertex.getId() + " -> "+ neighbor.get(i).getId() +"\nDFSCount = " + String.valueOf(DFScount));
            ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(curVertex.getId());
            ((Detail) this.getListofDetail().get(Detailcount)).setToVerID(neighbor.get(i).getId());
            Detailcount++;

            if(neighbor.get(i).getId() == idPre){
                this.getListofStep().add(new Pseudo(4));
                this.getListofStep().get(Pseucount).setStepContent("else low[u] = min(low[u], num[v])");
                Pseucount++;

                this.getListofDetail().add(new Detail());
                this.getListofDetail().get(Detailcount).setStepContent(neighbor.get(i).getId()+"is the parent of"+curVertex.getId()+"ignore!" +"\nDFSCount = " + String.valueOf(DFScount));
                ((Detail) this.getListofDetail().get(Detailcount)).setToVerID(neighbor.get(i).getId());
                Detailcount++;
                continue;
            }
                
            if(neighbor.get(i).isTraveled() == false ){
                neighbor.get(i).setTraveled(true);
                
                this.getListofStep().add(new Pseudo(3));
                this.getListofStep().get(Pseucount).setStepContent("if v is free, DFS(v)");
                Pseucount++;

                this.getListofDetail().add(new Detail());
                this.getListofDetail().get(Detailcount).setStepContent(neighbor.get(i).getId()+"has not been visited" +"\nDFSCount = " + String.valueOf(DFScount));
                ((Detail) this.getListofDetail().get(Detailcount)).setToVerID(neighbor.get(i).getId());
                Detailcount++;

                traversal(neighbor.get(i).getId(),curVertex.getId());
                min  = Math.min(curVertex.getLow(), neighbor.get(i).getLow());
                int PreLow = curVertex.getLow();
                curVertex.setLow(min);

                this.getListofStep().add(new Pseudo(5));
                this.getListofStep().get(Pseucount).setStepContent("low[u] = min(low[u], low[v])");
                Pseucount++;

                if(curVertex.getLow() == PreLow){
                    this.getListofDetail().add(new Detail());
                    this.getListofDetail().get(Detailcount).setStepContent("low["+curVertex.getId() +"] is unchanged.");
                    ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(neighbor.get(i).getId());
                    Detailcount++;
                }
                else{
                    this.getListofDetail().add(new Detail());
                    this.getListofDetail().get(Detailcount).setStepContent("update low["+curVertex.getId() +"] from low[" + neighbor.get(i).getId()+"\nThere is another path to go from vertex " + curVertex.getId() + " to vertex with num " + neighbor.get(i).getId());
                    ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(neighbor.get(i).getId());
                    Detailcount++;
                }

                this.getListofStep().add(new Pseudo(6));
                this.getListofStep().get(Pseucount).setStepContent("check the condition");
                Pseucount++;

                this.getListofDetail().add(new Detail());
                

                String condition = "";
                if(neighbor.get(i).getLow() < curVertex.getNum()){
                    condition = condition + "low["+neighbor.get(i).toString()+"] < num["+curVertex.toString() +"], so " + curVertex.toString()+" is not a cut vertex.\n";
                    ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(curVertex.getId());
                    ((Detail) this.getListofDetail().get(Detailcount)).setToVerID(neighbor.get(i).getId());
                }

                if(neighbor.get(i).getLow() >= curVertex.getNum()){
                    if(curVertex.getId() == 0){
                        condition = condition + "low["+neighbor.get(i).toString()+"] < num["+curVertex.toString() +"], but " + curVertex.toString()+" is root so not a cut vertex.\n";
                        ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(curVertex.getId());
                        ((Detail) this.getListofDetail().get(Detailcount)).setToVerID(neighbor.get(i).getId());
                    }
                    else{
                        condition = condition + "low["+neighbor.get(i).toString()+"] >= num["+curVertex.toString() +"], so " + curVertex.toString()+" is a cut vertex.\n";
                        ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(curVertex.getId());
                        ((Detail) this.getListofDetail().get(Detailcount)).setToVerID(neighbor.get(i).getId());
                        ((Detail) this.getListofDetail().get(Detailcount)).setCutVer(true);
                    }
                }

                if(neighbor.get(i).getLow() <= curVertex.getNum()){
                    condition = condition + "low["+neighbor.get(i).toString()+"] <= num["+curVertex.toString() +"], so edge" + curVertex.toString()+" -> "+ neighbor.get(i).toString()+" is not a bridge.";
                    ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(curVertex.getId());
                    ((Detail) this.getListofDetail().get(Detailcount)).setToVerID(neighbor.get(i).getId());
                }

                if(neighbor.get(i).getLow() > curVertex.getNum()){
                    condition = condition + "low["+neighbor.get(i).toString()+"] > num["+curVertex.toString() +"], so edge" + curVertex.toString()+" -> "+ neighbor.get(i).toString()+" is a bridge.";
                    ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(curVertex.getId());
                    ((Detail) this.getListofDetail().get(Detailcount)).setToVerID(neighbor.get(i).getId());
                    ((Detail) this.getListofDetail().get(Detailcount)).setCutedge(true);
                }

                this.getListofDetail().get(Detailcount).setStepContent(condition);
                Detailcount++;
                
            }
            else{
                min = Math.min(curVertex.getLow(), neighbor.get(i).getNum());
                curVertex.setLow(min);
                this.getListofStep().add(new Pseudo(4));
                this.getListofStep().get(Pseucount).setStepContent("else low[u] = min(low[u], num[v])");
                Pseucount++;

                this.getListofDetail().add(new Detail());
                this.getListofDetail().get(Detailcount).setStepContent(neighbor.get(i).getId()+"is visited update low["+curVertex.getId() +"] from num[" + neighbor.get(i).getId()+"\nThere is another path to go from vertex " + curVertex.getId() + " to vertex with num " + neighbor.get(i).getId());
                Detailcount++;
            }
            
        }
        this.getListofStep().add(new Pseudo(7));
        this.getListofStep().get(Pseucount).setStepContent("backtrack");
        Pseucount++;

        this.getListofDetail().add(new Detail());
        this.getListofDetail().get(Detailcount).setStepContent("Finish DFS("+ curVertex.getId() +") backtrack.\nDFSCount = " + String.valueOf(DFScount));
        ((Detail) this.getListofDetail().get(Detailcount)).setFromVerID(curVertex.getId());
        Detailcount++;
    }

    public void sortneighbor(ArrayList<Vertex> neighbor){

    }
}
