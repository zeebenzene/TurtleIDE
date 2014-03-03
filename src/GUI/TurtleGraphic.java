package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class TurtleGraphic {
	private double heading;
	private Point p;


	public TurtleGraphic(Point p, double h){
		this.p = p;
		this.heading = h;
	}

	public double direction(){
		return heading;
	}
	
	public void draw(Graphics g){
		Rectangle rectangle = new Rectangle(p.X(), p.Y(), 5, 8);
	
		AffineTransform transform = new AffineTransform();
		transform.rotate(-Math.toRadians(heading), rectangle.getX() + rectangle.width/2, rectangle.getY() + rectangle.height/2);
		Shape transformed = transform.createTransformedShape(rectangle);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.GREEN);
		g2d.fill(transformed);
	}
}
