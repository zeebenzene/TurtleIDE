package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		repaint();
	}
	
	public void moveTurtle(Point p, double heading){
		turtle = new TurtleGraphic(p, heading);
		if(p.X() >= 600 || p.X() <= 0){
			showAlert();
		}
		if(p.Y() >= 475 || p.Y() <= 0){
			showAlert();
		}
	}
	
	public void showAlert(){
		JFrame f = new JFrame();
		String errorMessage = "Turtle is out of the pane. You can type \"clear\" to bring it back to the starting position. Or you can.. you know, not.";
		String errorTitle = "Turtle Out Of Bounds";
		JOptionPane.showMessageDialog(f, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (LineGraphic s: shapes){
			s.draw(g);
		}
		turtle.draw(g);
	}
}