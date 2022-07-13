package application.ui;

import application.Graph.Edge.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class GEdge extends Edge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8049073912384547909L;

	GGraph g;
	GNode nFrom, nTo;
	Arrow line;

	public GEdge(GGraph g, GNode n1, GNode n2) {
		super(n1, n2);
		this.nFrom = n1;
		this.nTo = n2;
		this.g = g;
		draw();
	}

	public void draw() {

		if (line != null)
			g.drawPane.getChildren().remove(line);

		CusVector dis = new CusVector(nTo.x - nFrom.x, nTo.y - nFrom.y);
		double rat = (GNode.R + 2) / dis.length();
		int disX = (int) (rat * (nTo.x - nFrom.x));
		int disY = (int) (rat * (nTo.y - nFrom.y));

		line = new Arrow(
				nFrom.x + disX, nFrom.y + disY,
				nTo.x - disX, nTo.y - disY);

		g.drawPane.getChildren().addAll(line);
		g.lines.add(line);
	}

	public void remove() {
		g.drawPane.getChildren().removeAll(line);
	}

	public Arrow getLine() {
		return line;
	}

}
