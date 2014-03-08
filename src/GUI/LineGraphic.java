package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

public class LineGraphic {
	private Point p1, p2;
	
	public LineGraphic(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHint(
			    RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.draw(new Line2D.Double(p1.X(), p1.Y(), p2.X(), p2.Y()));
	}
}
