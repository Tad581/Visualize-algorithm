package application.ui;

import application.Graph.Edge.*;
import application.ui.math.Vector2;
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

		Vector2 dis = new Vector2(nTo.x - nFrom.x, nTo.y - nFrom.y);
		double rat = (GNode.R + 2) / dis.length();
		int disX = (int) (rat * (nTo.x - nFrom.x));
		int disY = (int) (rat * (nTo.y - nFrom.y));

		line = new Arrow(
				nFrom.x + disX, nFrom.y + disY,
				nTo.x - disX, nTo.y - disY);

		// label = new Text(
		// 		(nFrom.x + nTo.x) >> 1,
		// 		(nFrom.y + nTo.y) >> 1,
		// 		Long.valueOf(1).toString());

		// label.getTransforms().add(new Rotate(dis.getAlpha(), (nFrom.x + nTo.x) >> 1, (nFrom.y + nTo.y) >> 1));

		g.drawPane.getChildren().addAll(line);

	}

	public void remove() {
		g.drawPane.getChildren().removeAll(line);
	}

}
