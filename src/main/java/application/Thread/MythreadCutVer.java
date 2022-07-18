package application.Thread;

import application.Algorithm.CutVertex.CutVertex;
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

public class MythreadCutVer extends RootThread {
    public MythreadCutVer() {

    }

    public MythreadCutVer(ListView<Step> mylist, Label mylabel) {
        super(mylist, mylabel);
    }

    public CutVertex mycutver = new CutVertex();
    public GGraph g;

    public void run() {
        for (int i = 0; i < mycutver.getListofStep().size(); i++) {
            int j = ((Pseudo) mycutver.getListofStep().get(i)).getStepID();
            changvertex(j, mycutver.getListofDetail().get(i));
            String s = mycutver.getListofDetail().get(i).toString();
            changeLabel(s);
            this.getMylist().getSelectionModel().select(j);

            try {
                Thread.currentThread();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeLabel(String s) {
        Platform.runLater(() -> {
            this.getMylabel().setText(s);
        });
    }

    public void changvertex(int stepid, Step detail) {
        Platform.runLater(() -> {
            switch (stepid) {
                case 0:
                    // GNode v = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    // v.getC().setFill(Color.ORANGE);
                    break;
                case 1:
                    GNode v = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    v.getC().setFill(Color.ORANGE);
                    break;
                case 2:
                    GEdge e = (GEdge) g.GetedgewithFromTo(((Detail) detail).getFromVerID(),
                            ((Detail) detail).getToVerID());
                    e.getLine().setFill(Color.ORANGE);
                    GEdge e1 = (GEdge) g.GetedgewithFromTo(((Detail) detail).getToVerID(),
                            ((Detail) detail).getFromVerID());
                    e1.getLine().setFill(Color.ORANGE);
                    GNode vfrom = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    vfrom.getC().setFill(Color.WHITE);
                    vfrom.getC().setStroke(Color.SKYBLUE);
                    break;
                case 3:
                    GNode vnot = (GNode) g.getVertices().get(((Detail) detail).getToVerID());
                    vnot.getC().setFill(Color.WHITE);
                    vnot.getC().setStroke(Color.SKYBLUE);
                    break;
                case 4:
                    break;
                case 5:
                    GNode vfinal = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    vfinal.getC().setFill(Color.WHITE);
                    if (vfinal.getC().getStroke() != Color.GREEN) {
                        vfinal.getC().setStroke(Color.ORANGE);
                    }
                    break;
                case 6:
                    int checkfrom = ((Detail) detail).getFromVerID();
                    int checkto = ((Detail) detail).getToVerID();
                    boolean check = ((Detail) detail).isCutVer();
                    if (check == true) {
                        GNode vcut = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                        vcut.getC().setFill(Color.WHITE);
                        vcut.getC().setStroke(Color.GREEN);
                    }
                    if (checkfrom >= 0 && checkto >= 0) {
                        GEdge ecut = (GEdge) g.GetedgewithFromTo(((Detail) detail).getFromVerID(),
                                ((Detail) detail).getToVerID());
                        ecut.getLine().setFill(Color.GREEN);
                        GEdge ecut1 = (GEdge) g.GetedgewithFromTo(((Detail) detail).getToVerID(),
                                ((Detail) detail).getFromVerID());
                        ecut1.getLine().setFill(Color.GREEN);
                    }
                    break;
                case 7:
                    GNode vback = (GNode) g.getVertices().get(((Detail) detail).getFromVerID());
                    vback.getC().setFill(Color.ORANGE);
                    break;
                default:
                    break;
            }
        });
    }

    public CutVertex getMycutver() {
        return mycutver;
    }

    public void setMycutver(CutVertex mycutver) {
        this.mycutver = mycutver;
    }

    public GGraph getG() {
        return g;
    }

    public void setG(GGraph g) {
        this.g = g;
    }

}
