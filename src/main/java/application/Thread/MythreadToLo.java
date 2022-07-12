package application.Thread;

import application.Algorithm.Step.Step;
import application.Algorithm.Step.Detail.Detail;
import application.Algorithm.Step.Pseudo.Pseudo;
import application.Algorithm.Topological.TOPOLO;
import application.ui.GEdge;
import application.ui.GGraph;
import application.ui.GNode;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class MythreadToLo extends RootThread{
    public MythreadToLo(ListView<Step> mylist, Label mylabel) {
        super(mylist, mylabel);
    }
    public MythreadToLo(){
        
    }
    public TOPOLO mytolo = new TOPOLO();
    public GGraph g;

    public void run(){
        for(int i =0;i<mytolo.getListofStep().size();i++){
            int j = ((Pseudo) mytolo.getListofStep().get(i)).getStepID();
            changvertex(j, mytolo.getListofDetail().get(i));
            String s = mytolo.getListofDetail().get(i).toString();
            changeLabel(s);
            this.getMylist().getSelectionModel().select(j);
            
            try{
                Thread.currentThread();
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void changvertex(int stepid, Step detail){
        Platform.runLater(() -> {
            switch(stepid){
                case 0:
                    GNode v = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    v.getC().setFill(Color.ORANGE);
                    break;
                case 1:
                    int checkPre = ((Detail) detail).getFromVerID();
                    if(checkPre >= 0){
                        GNode vPre = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                        vPre.getC().setFill(Color.WHITE);
                        vPre.getC().setStroke(Color.ORANGE);
                    }
                    GNode vfrom = (GNode) g.getVertices().get(((Detail) detail).getToVerID());
                    vfrom.getC().setFill(Color.WHITE);
                    vfrom.getC().setStroke(Color.SKYBLUE);
                    break;
                case 2:
                    GNode vto = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    vto.getC().setFill(Color.ORANGE);
                    break;
                case 3:
                    GEdge e = (GEdge) g.GetedgewithFromTo(((Detail) detail).getFromVerID(), ((Detail) detail).getToVerID());
                    e.getLine().setFill(Color.GRAY);
                    break;
                case 4:
                    int check = ((Detail) detail).getToVerID();
                    if(check != 0){
                        GNode setv = (GNode) g.getVertices().get(((Detail) detail).getToVerID());
                        setv.getC().setFill(Color.WHITE);
                        setv.getC().setStroke(Color.SKYBLUE);
                    }
                    break;
                default:
                    break;
            }
          });
    }

    public void changeLabel(String s){
        Platform.runLater(() -> {
            this.getMylabel().setText(s);
          });
    }

    public TOPOLO getMytolo() {
        return mytolo;
    }

    public void setMytolo(TOPOLO mytolo) {
        this.mytolo = mytolo;
    }
    public void setG(GGraph g) {
        this.g = g;
    }

    
}
