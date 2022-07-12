package application;

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

public class MyThread extends Thread{
    private ListView<Step> mylist = new ListView<>();
    private Label mylabel = new Label();
    public GGraph g;
    public DFS mydfs = new DFS();


    public void run(){
        for(int i =0;i<mydfs.getListofStep().size();i++){
            int j = ((Pseudo) mydfs.getListofStep().get(i)).getStepID();
            changvertex(j, mydfs.getListofDetail().get(i));
            String s = mydfs.getListofDetail().get(i).toString();
            changeLabel(s);
            mylist.getSelectionModel().select(j);
            
        try{
            Thread.currentThread();
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        }
    }


    public void setMylist(ListView mylist) {
        this.mylist = mylist;
    }


    public void setG(GGraph g) {
        this.g = g;
    }


    public void setMydfs(DFS mydfs) {
        this.mydfs = mydfs;
    }


    public void setMylabel(Label mylabel) {
        this.mylabel = mylabel;
    }

    public void changeLabel(String s){
        Platform.runLater(() -> {
            mylabel.setText(s);
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
                    e.getLine().setFill(Color.SKYBLUE);
                    GNode vfrom = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    vfrom.getC().setFill(Color.WHITE);
                    vfrom.getC().setStroke(Color.ORANGE);
                    break;
                case 2:
                    GNode vto = (GNode) g.getVertices().get(((Detail) detail).getToVerID());
                    vto.getC().setStroke(Color.ORANGE);
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
