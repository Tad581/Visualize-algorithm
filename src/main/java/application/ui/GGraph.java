package application.ui;

import java.util.ArrayList;

import application.Graph.Graph;
import application.Graph.Vertex.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

// Sẽ kế thừa lại cái graph không UI 
public class GGraph extends Graph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4967818067696204778L;
	Pane drawPane;
	private GNode selectedNode;
	private GEdge selectedEdge;
	public ArrayList<Arrow> lines = new ArrayList<Arrow>();
	public ArrayList<Circle> vertexCirles = new ArrayList<Circle>();
	public ArrayList<Text> vertexLabel = new ArrayList<Text>();

	public GGraph(Pane drawPane) {
		super();
		this.drawPane = drawPane;
		selectedNode = null;
		selectedEdge = null;
	}

	private GNode getNodeAt(int x, int y) {
		GNode n = null;
		CusVector dis = CusVector.Zero();
		for (Vertex vertex : vertices) {
			dis.x = ((GNode) vertex).x - x;
			dis.y = ((GNode) vertex).y - y;
			if (dis.length() < GNode.R) {
				n = (GNode) vertex;
				break;
			}
		}
		return n;
	}

	public void addNode(int x, int y) {
		// check intersection with other nodes
		boolean intersect = false;
		CusVector dis = CusVector.Zero();
		for (Vertex vertex : vertices) {
			dis.x = ((GNode) vertex).x - x;
			dis.y = ((GNode) vertex).y - y;
			if (dis.length() < (GNode.R << 1)) {
				intersect = true;
				break;
			}
		}
		if (intersect)
			return;

		vertices.add(new GNode(this, numberOfVertices++, x, y));
	}


	private void addEdge(GNode n1, GNode n2) {
		GEdge edge = new GEdge(this, n1, n2);
		if (edges.contains(edge)) {
			return;
		}
		edges.add(edge);
	}
	
	void setSelectedEdge(GEdge e) {
		selectedEdge = e;
	}

	private boolean checkCornerClicked(int x, int y) {
		if (x <= GNode.R || x >= drawPane.getWidth() - GNode.R ||
				y <= GNode.R || y >= drawPane.getHeight() - GNode.R) {
			return true;
		}
		return false;
	}

	public void processClick(int x, int y) {
		// check if any node is clicked
		GNode n = getNodeAt(x, y);
		addNode(x, y);
		if (n != null) {
			// if clicked -> toggle click that node
			if (selectedNode != null) {
				if (selectedNode == n) {
					n.toggleSelected();
				} else {
					addEdge(selectedNode, n);
					selectedNode.toggleSelected();
				}
				selectedNode = null;
			} else {
				selectedNode = n;
				n.toggleSelected();
			}
		} else if (!checkCornerClicked(x, y)) {
			if (selectedNode != null) {
				selectedNode.toggleSelected();
				selectedNode = null;
			} else {
				addNode(x, y);
			}
		}

	}
}
