package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class TurtleGraphic {
	private double heading;
	private Point p;


	public TurtleGraphic(Point p, double h){
		this.p = p;
		this.heading = h;
	}

	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.GREEN);
		Rectangle rect2 = new Rectangle(p.X() - 2, p.Y() - 9, 5, 8);
//		g2d.rotate(Math.toRadians(45));
		g2d.draw(rect2);
		g2d.fill(rect2);
	}
}
