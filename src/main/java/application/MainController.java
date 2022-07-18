package application;

import java.net.URL;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ui.GEdge;
import application.ui.GGraph;
import application.ui.GNode;
import application.Algorithm.DFS.DFS;
import application.Algorithm.Step.Step;
import application.Algorithm.Algorithm;
import application.Algorithm.CutVertex.CutVertex;
import application.Algorithm.Topological.TOPOLO;
import application.Thread.MyThreadDFS;
import application.Thread.MythreadCutVer;
import application.Thread.MythreadToLo;
import application.Thread.RootThread;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainController implements Initializable {

    @FXML
    Pane mainPane;
    @FXML
    Button drawGraphButton;
    @FXML
    CheckBox isWeight;
    @FXML
    CheckBox isDirection;
    @FXML
    MenuItem chooseExampleGraph;
    @FXML
    Label ListDetail;
    @FXML
    ListView<Step> listPseu;
    @FXML
    MyThreadDFS myThreadDFS = new MyThreadDFS(listPseu, ListDetail);
    @FXML
    MythreadToLo mythreadToLo = new MythreadToLo(listPseu, ListDetail);
    @FXML
    MythreadCutVer mythreadCutVer = new MythreadCutVer(listPseu, ListDetail);
    @FXML
    TextField startVertexInput;
    @FXML
    Label inputErrorMessage;
    @FXML
    Label errorDrawingMessage;
    @FXML
    Label errorTranversingMessage;
    @FXML
    Button goButton;

    GGraph tGraph;
    boolean isStarting;
    boolean isDrawing;
    EventHandler<MouseEvent> drawEventHandler;
    int startVertex = 0;
    String algoName = "dfsAlgo";

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

    private int[][] exGraph5Vertex = { { 3, 3 }, { 1, 1 }, { 4, 4 }, { 6, 1 }, { 4, 6 } };
    private int[][] exGraph5Edge = { { 3, 3 }, { 1, 1 }, { 3, 3 }, { 6, 1 }, { 3, 3 }, { 4, 4 }, { 1, 1 }, { 6, 1 },
            { 1, 1 }, { 4, 6 }, { 4, 4 }, { 4, 6 }, { 6, 1 }, { 4, 6 } };

    private int[][] exGraph6Vertex = { { 1, 2 }, { 2, 1 }, { 2, 3 }, { 3, 2 }, { 4, 2 } };
    private int[][] exGraph6Edge = { { 1, 2 }, { 2, 1 }, { 1, 2 }, { 2, 3 }, { 2, 1 }, { 3, 2 }, { 2, 3 }, { 3, 2 },
            { 3, 2 }, { 4, 2 } };

    private int[][] exGraph7Vertex = { { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 2, 2 } };
    private int[][] exGraph7Edge = { { 1, 1 }, { 2, 1 }, { 1, 1 }, { 2, 2 }, { 2, 1 }, { 3, 1 }, { 3, 1 }, { 2, 1 },
            { 3, 1 }, { 4, 1 } };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        isStarting = false;
        isDrawing = false;
        tGraph = new GGraph(mainPane);
    }

    public void handleDrawGraph() {
        if (!isStarting) {
            errorTranversingMessage.setTextFill(Color.TRANSPARENT);
            if (!isDrawing) {
                drawEventHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (!isStarting) {
                            int x = (int) e.getX();
                            int y = (int) e.getY();
                            tGraph.processClick(x, y);
                        }
                    }
                };
                mainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, drawEventHandler);
                drawGraphButton.setText("Exit Draw");
                goButton.setDisable(true);
            } else {
                goButton.setDisable(false);
                drawGraphButton.setText("Draw Graph");
                mainPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, drawEventHandler);
            }
            isDrawing = !isDrawing;
        } else {
            errorTranversingMessage.setTextFill(Color.RED);
        }
    }

    public void handleExampleGraph(ActionEvent e) {
        if (!isDrawing && !isStarting) {
            errorDrawingMessage.setTextFill(Color.TRANSPARENT);
            errorTranversingMessage.setTextFill(Color.TRANSPARENT);
            handleClearDrawPane();
            tGraph.removeGraph();
            String itemId = ((Styleable) e.getSource()).getId();
            if (itemId.equals("exGraph1")) {
                for (int i = 0; i < exGraph1Vertex.length; i++) {
                    tGraph.processClick(exGraph1Vertex[i][0] * 100 + 300, exGraph1Vertex[i][1] * 100);
                }
                for (int j = 0; j < exGraph1Edge.length; j++) {
                    tGraph.processClick(exGraph1Edge[j][0] * 100 + 300, exGraph1Edge[j][1] * 100);
                }
                for (int j = exGraph1Edge.length - 1; j >= 0; j--) {
                    tGraph.processClick(exGraph1Edge[j][0] * 100 + 300, exGraph1Edge[j][1] * 100);
                }
            } else if (itemId.equals("exGraph2")) {
                for (int i = 0; i < exGraph2Vertex.length; i++) {
                    tGraph.processClick(exGraph2Vertex[i][0] * 100 + 300, exGraph2Vertex[i][1] * 100);
                }
                for (int j = 0; j < exGraph2Edge.length; j++) {
                    tGraph.processClick(exGraph2Edge[j][0] * 100 + 300, exGraph2Edge[j][1] * 100);
                }
                for (int j = exGraph2Edge.length - 1; j >= 0; j--) {
                    tGraph.processClick(exGraph2Edge[j][0] * 100 + 300, exGraph2Edge[j][1] * 100);
                }
            } else if (itemId.equals("exGraph3")) {
                for (int i = 0; i < exGraph3Vertex.length; i++) {
                    tGraph.processClick(exGraph3Vertex[i][0] * 100 + 300, exGraph3Vertex[i][1] * 100);
                }
                for (int j = 0; j < exGraph3Edge.length; j++) {
                    tGraph.processClick(exGraph3Edge[j][0] * 100 + 300, exGraph3Edge[j][1] * 100);
                }
            } else if (itemId.equals("exGraph4")) {
                for (int i = 0; i < exGraph4Vertex.length; i++) {
                    tGraph.processClick(exGraph4Vertex[i][0] * 100 + 300, exGraph4Vertex[i][1] * 100);
                }
                for (int j = 0; j < exGraph4Edge.length; j++) {
                    tGraph.processClick(exGraph4Edge[j][0] * 100 + 300, exGraph4Edge[j][1] * 100);
                }
            } else if (itemId.equals("exGraph5")) {
                for (int i = 0; i < exGraph5Vertex.length; i++) {
                    tGraph.processClick(exGraph5Vertex[i][0] * 100 + 300, exGraph5Vertex[i][1] * 50);
                }
                for (int j = 0; j < exGraph5Edge.length; j++) {
                    tGraph.processClick(exGraph5Edge[j][0] * 100 + 300, exGraph5Edge[j][1] * 50);
                }
            } else if (itemId.equals("exGraph6")) {
                for (int i = 0; i < exGraph6Vertex.length; i++) {
                    tGraph.processClick(exGraph6Vertex[i][0] * 100 + 300, exGraph6Vertex[i][1] * 100);
                }
                for (int j = 0; j < exGraph6Edge.length; j++) {
                    tGraph.processClick(exGraph6Edge[j][0] * 100 + 300, exGraph6Edge[j][1] * 100);
                }
            } else if (itemId.equals("exGraph7")) {
                for (int i = 0; i < exGraph7Vertex.length; i++) {
                    tGraph.processClick(exGraph7Vertex[i][0] * 100 + 300, exGraph7Vertex[i][1] * 100);
                }
                for (int j = 0; j < exGraph7Edge.length; j++) {
                    tGraph.processClick(exGraph7Edge[j][0] * 100 + 300, exGraph7Edge[j][1] * 100);
                }
            }
        }
        if (isDrawing) {
            errorDrawingMessage.setTextFill(Color.RED);
        }
        if (isStarting) {
            errorTranversingMessage.setTextFill(Color.RED);
        }
    }

    public void handleClearColor() {
        for (int i = 0; i < tGraph.vertexCirles.size(); i++) {
            tGraph.vertexCirles.get(i).setFill(Color.WHITE);
            tGraph.vertexCirles.get(i).setStroke(Color.BLACK);
        }
        for (int i = 0; i < tGraph.lines.size(); i++) {
            tGraph.lines.get(i).setFill(Color.BLACK);
        }
    }

    public void handleClearDrawPane() {
        for (int i = 0; i < tGraph.vertexCirles.size(); i++) {
            this.mainPane.getChildren().removeAll(tGraph.vertexCirles.get(i), tGraph.vertexLabel.get(i));
            tGraph.vertexCirles.remove(i);
            tGraph.vertexLabel.remove(i);
            tGraph.vertices.remove(i);
            i--;
        }
        for (int i = 0; i < tGraph.lines.size(); i++) {
            this.mainPane.getChildren().removeAll(tGraph.lines.get(i));
            tGraph.lines.remove(i);
            tGraph.edges.remove(i);
            i--;
        }
        tGraph.numberOfVertices = 0;
    }

    public void DFS() {
        DFS dfs = new DFS(tGraph);
        dfs.traversal(startVertex);
        myThreadDFS.setG(tGraph);
        SetPseuStep(dfs, myThreadDFS);
        myThreadDFS.setMydfs(dfs);
        myThreadDFS.start();

    }

    public void ToLo() {
        TOPOLO topolo = new TOPOLO(tGraph);
        topolo.traversal();
        mythreadToLo.setG(tGraph);
        SetPseuStep(topolo, mythreadToLo);
        mythreadToLo.setMytolo(topolo);
        mythreadToLo.start();
    }

    public void CutVer() {
        CutVertex cutvertex = new CutVertex(tGraph);
        cutvertex.CutverTraversal(startVertex, startVertex);
        mythreadCutVer.setG(tGraph);
        SetPseuStep(cutvertex, mythreadCutVer);
        mythreadCutVer.setMycutver(cutvertex);
        mythreadCutVer.start();
    }

    public void handleChooseAlgorithm(ActionEvent e) {
        algoName = ((Styleable) e.getSource()).getId();
    }

    public void handleAlgorithm() {
        if (tGraph.vertices.size() != 0) {
            handleClearColor();
            if (!isDrawing && !isStarting) {
                errorDrawingMessage.setTextFill(Color.TRANSPARENT);
                errorTranversingMessage.setTextFill(Color.TRANSPARENT);
                try {
                    startVertex = Integer.parseInt(startVertexInput.getText());
                    if (startVertex >= 0 && startVertex <= tGraph.vertices.size()) {
                        inputErrorMessage.setTextFill(Color.TRANSPARENT);
                        // goButton.setText("Running");
                        // isStarting = true;
                        if (algoName.equals("dfsAlgo")) {
                            DFS();
                        } else if (algoName.equals("topoAlgo")) {
                            ToLo();
                        } else if (algoName.equals("cutBridgeAlgo")) {
                            System.out.println("run");
                            CutVer();
                        }
                    }
                } catch (NumberFormatException nfe) {
                    inputErrorMessage.setTextFill(Color.RED);
                    System.out.println("Error");
                }
            } else if (isDrawing) {
                errorDrawingMessage.setTextFill(Color.RED);
            } else if (isStarting) {
                errorTranversingMessage.setTextFill(Color.RED);
            }
        }

    }

    public void SetPseuStep(Algorithm algorithm, RootThread thread) {
        ArrayList<Step> list = algorithm.setPseu();
        for (int i = 0; i < list.size(); i++) {
            Step s = list.get(i);
            listPseu.getItems().add(s);

        }
        thread.setMylist(listPseu);
        thread.setMylabel(ListDetail);
    }
}
