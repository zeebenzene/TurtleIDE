package Parser;

import static org.junit.Assert.*;

import org.junit.Test;

import GUI.Canvas;
import GUI.Commands;
import GUI.Point;

public class TestEval {
	Canvas canvas = new Canvas();
	Commands cmd = new Commands(canvas);
	
	@Test
	public void testForward() {
		Point p = cmd.getCurrent();
		double y = p.Y();
		cmd.forward(10);
		assertTrue("moved up 10", cmd.getCurrent().Y() == y-10 );
		cmd.forward(5);
		assertTrue("moved up 5 more", cmd.getCurrent().Y() == y - 15);
	}
	
	@Test
	public void testBackward() {
		Point p = cmd.getCurrent();
		double y = p.Y();
		cmd.back(10);
		assertTrue("moved up 10", cmd.getCurrent().Y() == y+10 );
		cmd.back(5);
		assertTrue("moved up 5 more", cmd.getCurrent().Y() == y+15);
	}
	
	@Test
	public void testLeftRight() {
		double h = cmd.getHeading();
		cmd.left(50.0);
		double expectedHeading = h + 50.0;
		assertTrue("changed heading left", cmd.getHeading() == expectedHeading);
		cmd.right(50.0);
		assertTrue("changed right back to original", cmd.getHeading() == h);
	}
	
	@Test
	public void testHome(){
		//home coordinates: 260, 230
//		double 
	}
	
	@Test
	public void testClearScreen(){
		double y = cmd.getCurrent().Y();
		cmd.forward(30);
		assertTrue("went forward 30", cmd.getCurrent().Y() == (y - 30));
		cmd.home();
		assertTrue("went to original spot ", cmd.getCurrent().Y() == y);
	}

}
