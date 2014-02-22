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
		
		clear();
	}

	public void addShape(LineGraphic s){
		shapes.add(s);
	}

	public void clear(){
		shapes = new ArrayList<LineGraphic>();
		turtle = new TurtleGraphic(-10, -10);
	}
	
	public void moveTurtle(int x, int y){
		turtle = new TurtleGraphic(x, y - 20);
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