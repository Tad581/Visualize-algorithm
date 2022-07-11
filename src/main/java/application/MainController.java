package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.ui.GGraph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

    @FXML
    Pane mainPane;
    // Button drawGraph;
    boolean isExploring; // Trạng thái của 1 node
    GGraph tGraph; // Graph tổng
    boolean isDrawGraph;
    MenuItem chooseExampleGraph;


    private int[][] exGraph1Vertex = {{100, 100}, {200, 100}, {300, 100}, {200, 200}};
    private int[][] exGraph1Edge = {{100, 100}, {200, 100}, {200, 100}, {300, 100}};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        isExploring = false;
        isDrawGraph = false;
        tGraph = new GGraph(mainPane);

        // initAlgoArea();

        // initGoBtn();
    }

    public void handleDrawGraph() {
        EventHandler<MouseEvent> eventHandler;
        System.out.println(isDrawGraph);
        if (!isDrawGraph) {
            eventHandler = new EventHandler<MouseEvent>() {
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
        } else {
            eventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    mainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                }
            };
            // mainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        }
        isDrawGraph = !isDrawGraph;
    }

    public void handleExampleGraph() {
        for (int i = 0; i < exGraph1Vertex.length; i++) {
            tGraph.processClick(exGraph1Vertex[i][0], exGraph1Vertex[i][1]);
        }
        for (int i = 0; i < exGraph1Edge.length; i++) {
            tGraph.processClick(exGraph1Edge[i][0], exGraph1Edge[i][1]);
        }
    }
}
