package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Algorithm.Algorithm;
import application.Algorithm.DFS.DFS;
import application.Algorithm.Step.Step;
import application.ui.GGraph;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
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

        // initAlgoArea();

        // initGoBtn();
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
    MyThread myThread = new MyThread();

    public void DFS(){
        DFS dfs = new DFS(tGraph);
        dfs.traversal(0);

        myThread.setG(tGraph);
        SetPseuStep(dfs);
        myThread.setMydfs(dfs);
        myThread.start();
        
    }

    

    public void SetPseuStep(DFS dfs){
        ArrayList<Step> list = dfs.setPseu();
        for(int i = 0; i<list.size();i++){
            Step s = list.get(i);
            listPseu.getItems().add(s);
            
        }
        myThread.setMylist(listPseu);
        myThread.setMylabel(ListDetail);
        ListDetail.setText("step");
    }

    @FXML
    Label ListDetail;

}
