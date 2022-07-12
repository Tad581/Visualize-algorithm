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

    private int[][] exGraph3Vertex = { { 1, 1 }, { 2, 1 }, { 2, 2 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 3, 2 }, { 4, 2 } };
    private int[][] exGraph3Edge = { { 1, 1 }, { 2, 1 }, { 1, 1 }, { 2, 2 }, { 2, 1 }, { 2, 2 }, { 2, 1 }, { 3, 1 },
            { 2, 2 }, { 3, 1 }, { 2, 2 }, { 5, 1 }, { 3, 1 }, { 4, 1 }, { 4, 2 }, { 3, 2 } };

    private int[][] exGraph4Vertex = { { 1, 1 }, { 2, 1 }, { 2, 2 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 4, 2 }, { 5, 2 } };
    private int[][] exGraph4Edge = { { 1, 1 }, { 2, 1 }, { 2, 1 }, { 3, 1 }, { 2, 2 }, { 2, 1 }, { 3, 1 }, { 2, 2 },
            { 3, 1 }, { 4, 1 }, { 4, 1 }, { 5, 1 }, { 5, 1 }, { 5, 2 }, { 5, 2 }, { 4, 2 }, { 4, 2 }, { 4, 1 } };

    private int[][] exGraph5Vertex = { { 3, 3 }, { 1, 1 }, { 4, 4 }, { 6, 1 }, { 5, 5 } };
    private int[][] exGraph5Edge = { { 3, 3 }, { 1, 1 }, { 3, 3 }, { 6, 1 }, { 3, 3 }, { 4, 4 }, { 1, 1 }, { 6, 1 },
            { 1, 1 }, { 5, 5 }, { 4, 4 }, { 5, 5 }, { 6, 1 }, { 5, 5 } };

    private int[][] exGraph6Vertex = { { 1, 2 }, { 2, 1 }, { 2, 3 }, { 3, 2 }, { 4, 2 } };
    private int[][] exGraph6Edge = { { 1, 2 }, { 2, 1 }, { 1, 2 }, { 2, 3 }, { 2, 1 }, { 3, 2 }, { 2, 3 }, { 3, 2 },
            { 3, 2 }, { 4, 2 } };

    private int[][] exGraph7Vertex = { { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 2, 2 } };
    private int[][] exGraph7Edge = { { 1, 1 }, { 2, 1 }, { 1, 1 }, { 2, 2 }, { 2, 1 }, { 3, 1 }, { 3, 1 }, { 2, 1 },
            { 3, 1 }, { 4, 1 } };

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
        // for (int i = 0; i < tGraph.vertices.size(); i++) {
        // tGraph.vertices[i].remove()
        // }
        tGraph.removeGraph();
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
        } else if (itemId.equals("exGraph3")) {
            for (int i = 0; i < exGraph3Vertex.length; i++) {
                tGraph.processClick(exGraph3Vertex[i][0] * 100, exGraph3Vertex[i][1] * 100);
            }
            for (int j = 0; j < exGraph3Edge.length; j++) {
                tGraph.processClick(exGraph3Edge[j][0] * 100, exGraph3Edge[j][1] * 100);
            }
        } else if (itemId.equals("exGraph4")) {
            for (int i = 0; i < exGraph4Vertex.length; i++) {
                tGraph.processClick(exGraph4Vertex[i][0] * 100, exGraph4Vertex[i][1] * 100);
            }
            for (int j = 0; j < exGraph4Edge.length; j++) {
                tGraph.processClick(exGraph4Edge[j][0] * 100, exGraph4Edge[j][1] * 100);
            }
        } else if (itemId.equals("exGraph5")) {
            for (int i = 0; i < exGraph5Vertex.length; i++) {
                tGraph.processClick(exGraph5Vertex[i][0] * 50, exGraph5Vertex[i][1] * 50);
            }
            for (int j = 0; j < exGraph5Edge.length; j++) {
                tGraph.processClick(exGraph5Edge[j][0] * 50, exGraph5Edge[j][1] * 50);
            }
        } else if (itemId.equals("exGraph6")) {
            for (int i = 0; i < exGraph6Vertex.length; i++) {
                tGraph.processClick(exGraph6Vertex[i][0] * 100, exGraph6Vertex[i][1] * 100);
            }
            for (int j = 0; j < exGraph6Edge.length; j++) {
                tGraph.processClick(exGraph6Edge[j][0] * 100, exGraph6Edge[j][1] * 100);
            }
        } else if (itemId.equals("exGraph7")) {
            for (int i = 0; i < exGraph7Vertex.length; i++) {
                tGraph.processClick(exGraph7Vertex[i][0] * 100, exGraph7Vertex[i][1] * 100);
            }
            for (int j = 0; j < exGraph7Edge.length; j++) {
                tGraph.processClick(exGraph7Edge[j][0] * 100, exGraph7Edge[j][1] * 100);
            }
        }
    }

}
