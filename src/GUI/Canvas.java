package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Canvas extends JPanel{	
	ArrayList<LineGraphic> shapes;
	TurtleGraphic turtle;
	
	public Canvas(){
		super();
		this.setBackground(Color.PINK);
		this.setMinimumSize(new Dimension(500, 500));
		this.setMaximumSize(new Dimension(500, 500));
		turtle = new TurtleGraphic(new Point(0, 0), 180.0);
		clear();
	}

	public void addShape(LineGraphic s){
		shapes.add(s);
	}

	public void clear(){
		shapes = new ArrayList<LineGraphic>();
		turtle = new TurtleGraphic(new Point(-10, -10), 180.0);
	}
	
	public void moveTurtle(Point p, double heading){
		turtle = new TurtleGraphic(p, heading);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		turtle.draw(g2d);
		for (LineGraphic s: shapes){
			s.draw(g2d);
		}

		this.repaint();
	}
}