package application.ui;

import application.Graph.Vertex.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GNode extends Vertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4572159178583259675L;
	static final int R = 18;
	int x, y;
	GGraph g;
	Circle c;
	Text label;
	boolean selected;

	public GNode(GGraph g, int id, int x, int y) {
		super(id);
		this.x = x;
		this.y = y;
		this.g = g;
		draw();
		selected = false;
	}

	public void draw() {
		c = new Circle(x, y, R);
		c.setFill(Color.WHITE);
		c.setStroke(Color.BLACK);
		c.setStrokeWidth(2);
		String idStr = Integer.valueOf(getId()).toString();
		if (getId() < 10) {
			label = new Text(c.getCenterX() - (R >> 2) - idStr.length(), c.getCenterY() + (R >> 2), idStr);
		} else {
			label = new Text(c.getCenterX() - (R >> 2) - idStr.length() - 4, c.getCenterY() + (R >> 2), idStr);
		}
		label.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		g.drawPane.getChildren().addAll(c, label);
		g.vertexCirles.add(c);
		g.vertexLabel.add(label);
	}

	public void toggleSelected() {
		if (selected) {
			c.setFill(Color.WHITE);
		} else {
			c.setFill(Color.ORANGE);
		}
		selected = !selected;
	}

	public void remove() {
		g.drawPane.getChildren().removeAll(c, label);
	}

	public void setC(Circle c) {
		this.c = c;
	}

	public Circle getC() {
		return c;
	}
}
