package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.ui.GGraph;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

    @FXML
    Pane mainPane;

    boolean isExploring; // Trạng thái của 1 node
    GGraph tGraph; // Graph tổng

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
}
