package application.ui;

import java.util.ArrayList;

import application.Graph.Graph;
import application.Graph.Vertex.*;
import application.ui.math.Vector2;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

// Sẽ kế thừa lại cái graph không UI 
public class GGraph extends Graph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4967818067696204778L;
	Pane drawPane;
	private GNode selectedNode;
	private GEdge selectedEdge;

	public GGraph(Pane drawPane) {
		super();
		this.drawPane = drawPane;
		selectedNode = null;
		selectedEdge = null;
	}

	private GNode getNodeAt(int x, int y) {
		GNode n = null;
		Vector2 dis = Vector2.Zero();
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
		Vector2 dis = Vector2.Zero();
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

		// check whether 3 point on the same line
		vertices.add(new GNode(this, numberOfVertices++, x, y));
		// adjList.add(new ArrayList<Integer>());
	}

	// public void deleteNode() throws Exception {
	// // super.deleteNode(selectedNode.getId());
	// selectedNode.remove();
	// selectedNode = null;
	// }

	private void addEdge(GNode n1, GNode n2) {
		GEdge edge = new GEdge(this, n1, n2);
		if (edges.contains(edge)) {
			return;
		}
		edges.add(edge);
		// adjList.get(n1.getId()).add(n2.getId());
	}

	// public void deleteEdge() throws Exception {
	// 	// super.deleteEdge(selectedEdge.getStart(), selectedEdge.getEnd());
	// 	selectedEdge.remove();
	// 	selectedEdge = null;
	// }

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
