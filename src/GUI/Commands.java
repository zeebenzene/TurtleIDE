package GUI;

public class Commands {
	Canvas canvas;
	Point startP;
	boolean pendown;
	double heading, h;
	
	public Commands(Canvas canvas){
		this.canvas = canvas;
		this.startP = new Point(300, 475);
		this.pendown = true;
		heading = 180.0;
	}
	
	public Point endPointForward(double length){
		double endX = (startP.X() + (length * Math.sin(Math.toRadians(heading))));
		double endY = (startP.Y() + (length * Math.cos(Math.toRadians(heading))));
		return new Point(endX, endY);
	}
	
	public Point endPointBack(double length){
		double endX = (startP.X() + (length * Math.sin(Math.toRadians(-heading))));
		double endY = (startP.Y() - (length * Math.cos(Math.toRadians(-heading))));
		
		return new Point(endX, endY);
	}
	
	public double normalizeDegree(double deg){
		if(deg >= 360){
			deg = deg - 360;
		}
		else if (deg <= 0){
			deg = 360 - deg;
		}
		return deg;
	}
	
	public void forward(double length){
		Point endP = endPointForward(length);
		if(pendown == true){
			canvas.addShape(new LineGraphic(startP, endP));	
		}
		canvas.moveTurtle(endP, heading);
		startP = endP;
		
		canvas.repaint();
	}
	public void back(double length){
		Point endP = endPointBack(length);
		if(pendown == true){
			canvas.addShape(new LineGraphic(startP, endP));
		}
		canvas.moveTurtle(endP, heading);
		startP = endP;
		
		canvas.repaint();
	}
	public void left(double degrees){
		double change = heading + degrees;
		heading = normalizeDegree(change);
		canvas.repaint();
	}
	public void right(double degrees){
		double change = heading - degrees;
		heading = normalizeDegree(change);
		canvas.repaint();
	}
	public void clear() {
		canvas.clear();
		this.startP = new Point(300.0, 475.0);
		this.heading = 180.0;
	}
	public void penup(){
		pendown = false;
	}
	public void pendown(){
		pendown = true;
	}
	public void home(){
		startP = new Point(300.0, 230.0);
		canvas.moveTurtle(startP, 180.0);
		this.heading = 180.0;
		canvas.repaint();
	}
	public void hideturtle(){
		canvas.moveTurtle(new Point(-10.0, -10.0), 180.0);
	}
		
	public double getHeading(){
		return heading;
	}
	public Point getCurrent(){
		return startP;
	}
}
