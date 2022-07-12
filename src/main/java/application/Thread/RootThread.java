package application.Thread;

import application.Algorithm.Step.Step;
import application.ui.GGraph;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public abstract class RootThread extends Thread{
    private ListView<Step> mylist = new ListView<>();
    private Label mylabel = new Label();
    public RootThread(ListView<Step> mylist,Label mylabel){
        this.mylist = mylist;
        this.mylabel = mylabel;
    }
    public RootThread(){

    }
    public void setMylist(ListView<Step> mylist) {
        this.mylist = mylist;
    }
    public void setMylabel(Label mylabel) {
        this.mylabel = mylabel;
    }
    public ListView<Step> getMylist() {
        return mylist;
    }
    public Label getMylabel() {
        return mylabel;
    }
    
}
