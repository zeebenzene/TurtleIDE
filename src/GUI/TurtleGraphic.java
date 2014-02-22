package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class TurtleGraphic {
	private int x, y;
	
	public TurtleGraphic(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		   Graphics2D g2d = (Graphics2D)g;
		   // Assume x, y, and diameter are instance variables.
		   Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 12, 12);
		   g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		   g2d.fill(circle);
		   g2d.setColor(Color.BLUE);
//		g.setColor(Color.RED);
//		g.drawOval(x, y, 10, 10);
	}
}
