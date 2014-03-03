package GUI;

public class Commands {
	Canvas canvas;
	Point startP;
	boolean pendown;
	double heading, h;
	
	public Commands(Canvas canvas){
		this.canvas = canvas;
		this.startP = new Point(295, 250);
		this.pendown = true;
		heading = 180.0;
	}
	
	public Point endPointForward(int length){
		int endX = (int) (startP.X() + (length * Math.sin(Math.toRadians(heading))));
		int endY = (int) (startP.Y() + (length * Math.cos(Math.toRadians(heading))));
		return new Point(endX, endY);
	}
	
	public Point endPointBack(int length){
		int endX = (int) (startP.X() - (length * Math.sin(Math.toRadians(heading))));
		int endY = (int) (startP.Y() - (length * Math.cos(Math.toRadians(heading))));
		return new Point(endX, endY);
	}
	
	public double normalizeDegree(double deg){
		if(deg > 360){
			deg = deg - 360;
		}
		else if (deg <= 0){
			deg = 360 - deg;
		}
		return deg;
	}
	
	public void forward(double length){
		Point endP = endPointForward((int)length);
		if(pendown == true){
			canvas.addShape(new LineGraphic(startP, endP));	
		}
		canvas.moveTurtle(endP, heading);
		startP = endP;
		
		canvas.repaint();
	}
	public void back(double length){
		Point endP = endPointBack((int)length);
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
		this.startP = new Point(295, 250);
		this.heading = 180.0;
	}
	
	public void penup(){
		pendown = false;
	}
	public void pendown(){
		pendown = true;
	}
	public void home(){
		startP = new Point(295, 250);
		canvas.moveTurtle(startP, 180.0);
	}
	public void hideturtle(){
		canvas.moveTurtle(new Point(-10, -10), 180.0);
	}
	
	// test helpers
	
	public double getHeading(){
		return heading;
	}
	public Point getCurrent(){
		return startP;
	}
}
