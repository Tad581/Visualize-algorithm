package application.Thread;

import application.Algorithm.DFS.DFS;
import application.Algorithm.Step.Step;
import application.Algorithm.Step.Detail.Detail;
import application.Algorithm.Step.Pseudo.Pseudo;
import application.ui.GEdge;
import application.ui.GGraph;
import application.ui.GNode;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

public class MyThreadDFS extends RootThread{
    public MyThreadDFS(ListView<Step> mylist, Label mylabel) {
        super(mylist, mylabel);
    }
    public MyThreadDFS(){
        
    }
    public DFS mydfs = new DFS();
    public GGraph g;

    public void run(){
        for(int i =0;i<mydfs.getListofStep().size();i++){
            int j = ((Pseudo) mydfs.getListofStep().get(i)).getStepID();
            changvertex(j, mydfs.getListofDetail().get(i));
            String s = mydfs.getListofDetail().get(i).toString();
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

    
    public void setMydfs(DFS mydfs) {
        this.mydfs = mydfs;
    }

    public void setG(GGraph g) {
        this.g = g;
    }

    public void changeLabel(String s){
        Platform.runLater(() -> {
            this.getMylabel().setText(s);
          });
    }

    public void changvertex(int stepid, Step detail){
        Platform.runLater(() -> {
            switch(stepid){
                case 0:
                    GNode v = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    v.getC().setFill(Color.ORANGE);
                    break;
                case 1:
                    GEdge e = (GEdge) g.GetedgewithFromTo(((Detail) detail).getFromVerID(), ((Detail) detail).getToVerID());
                    e.getLine().setFill(Color.ORANGE);
                    GNode vfrom = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    vfrom.getC().setFill(Color.WHITE);
                    vfrom.getC().setStroke(Color.SKYBLUE);
                    break;
                case 2:
                    GNode vto = (GNode) g.getVertices().get(((Detail) detail).getToVerID());
                    vto.getC().setStroke(Color.SKYBLUE);
                    break;
                case 3:
                    break;
                case 4:
                    GNode setv = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    setv.getC().setFill(Color.WHITE);
                default:
                    break;
            }
          });
    }
    
}
