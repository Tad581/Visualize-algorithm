package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.ui.GGraph;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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

    @FXML
    CheckBox isWeight;
    @FXML
    CheckBox isDirection;

    private int[][] exGraph1Vertex = { { 1, 1 }, { 2, 1 }, { 2, 2 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 4, 2 }, { 3, 2 },
            { 5, 2 } };
    private int[][] exGraph1Edge = { { 1, 1 }, { 2, 1 }, { 2, 1 }, { 2, 2 }, { 2, 1 }, { 3, 1 }, { 3, 1 }, { 4, 1 },
            { 4, 2 }, { 3, 2 }, { 4, 2 }, { 5, 2 } };

    private int[][] exGraph2Vertex = { { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 1, 2 }, { 2, 2 }, { 3, 2 }, { 4, 2 },
            { 1, 3 }, { 1, 4 }, { 2, 4 }, { 3, 4 }, { 4, 4 } };
    private int[][] exGraph2Edge = { { 1, 1 }, { 2, 1 }, { 2, 1 }, { 3, 1 }, { 3, 1 }, { 4, 1 }, { 1, 1 }, { 1, 2 },
            { 2, 1 }, { 2, 2 }, { 3, 1 }, { 3, 2 }, { 4, 1 }, { 4, 2 }, { 2, 2 }, { 3, 2 }, { 1, 2 }, { 1, 3 },
            { 1, 3 }, { 1, 4 }, { 1, 4 }, { 2, 4 }, { 2, 4 }, { 3, 4 }, { 3, 4 }, { 4, 4 }, { 2, 2 }, { 2, 4 },
            { 3, 2 }, { 3, 4 }, { 4, 2 }, { 4, 4 } };

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

    public void handleExampleGraph(ActionEvent e) {
        String itemId = ((Styleable) e.getSource()).getId();
        if (itemId.equals("exGraph1")) {
            for (int i = 0; i < exGraph1Vertex.length; i++) {
                tGraph.processClick(exGraph1Vertex[i][0] * 100, exGraph1Vertex[i][1] * 100);
            }
            for (int j = 0; j < exGraph1Edge.length; j++) {
                tGraph.processClick(exGraph1Edge[j][0] * 100, exGraph1Edge[j][1] * 100);
            }
            for (int j = exGraph1Edge.length - 1; j >= 0; j--) {
                tGraph.processClick(exGraph1Edge[j][0] * 100, exGraph1Edge[j][1] * 100);
            }
        } else if (itemId.equals("exGraph2")) {
            for (int i = 0; i < exGraph2Vertex.length; i++) {
                tGraph.processClick(exGraph2Vertex[i][0] * 100, exGraph2Vertex[i][1] * 100);
            }
            for (int j = 0; j < exGraph2Edge.length; j++) {
                tGraph.processClick(exGraph2Edge[j][0] * 100, exGraph2Edge[j][1] * 100);
            }
            for (int j = exGraph2Edge.length - 1; j >= 0; j--) {
                tGraph.processClick(exGraph2Edge[j][0] * 100, exGraph2Edge[j][1] * 100);
            }
        }
    }

    public void handleOptionCheckbox(ActionEvent e) {

        System.out.println(isDirection.isSelected());
    }

}
