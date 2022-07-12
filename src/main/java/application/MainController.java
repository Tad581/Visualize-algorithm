package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Algorithm.Algorithm;
import application.Algorithm.DFS.DFS;
import application.Algorithm.Step.Step;
import application.Algorithm.Topological.TOPOLO;
import application.Thread.MyThreadDFS;
import application.Thread.MythreadToLo;
import application.Thread.RootThread;
import application.ui.GGraph;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

    @FXML
    Pane mainPane;

    boolean isExploring; // Trạng thái của 1 node
    GGraph tGraph; // Graph tổng

    @FXML
    ListView<Step> listPseu;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        isExploring = false;
        tGraph = new GGraph(mainPane);
        initDrawPane();

    }

    private void initDrawPane() {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (!isExploring) {
                    int x = (int) e.getX();
                    int y = (int) e.getY();
                    tGraph.processClick(x, y);
                }
            }
        };
        mainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    @FXML
    Label ListDetail;

    public void SetPseuStep(Algorithm algorithm, RootThread thread){
        ArrayList<Step> list = algorithm.setPseu();
        for(int i = 0; i<list.size();i++){
            Step s = list.get(i);
            listPseu.getItems().add(s);
            
        }
        thread.setMylist(listPseu);
        thread.setMylabel(ListDetail);
    }

    @FXML
    MyThreadDFS myThreadDFS = new MyThreadDFS(listPseu,ListDetail);

    public void DFS(){
        DFS dfs = new DFS(tGraph);
        dfs.traversal(0);

        myThreadDFS.setG(tGraph);
        SetPseuStep(dfs,myThreadDFS);
        myThreadDFS.setMydfs(dfs);
        myThreadDFS.start();
        
    }

    @FXML
    MythreadToLo mythreadToLo = new MythreadToLo(listPseu,ListDetail);

    public void ToLo(){
        TOPOLO topolo = new TOPOLO(tGraph);
        topolo.traversal();

        mythreadToLo.setG(tGraph);
        SetPseuStep(topolo,mythreadToLo);
        mythreadToLo.setMytolo(topolo);
        mythreadToLo.start();
    }

}
