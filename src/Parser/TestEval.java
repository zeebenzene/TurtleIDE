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
		int x = p.X();
		int y = p.Y();
		cmd.forward(10);
		assertTrue("moved up 10", cmd.getCurrent().Y() == y-10 );
		cmd.forward(5);
		assertTrue("moved up 5 more", cmd.getCurrent().Y() == y - 15);
	}
	
	@Test
	public void testBackward() {
		Point p = cmd.getCurrent();
		int x = p.X();
		int y = p.Y();
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
		cmd.right(360.0);
		assertTrue("360 rotation made it back to original", cmd.getHeading() == h);
	}
	
	@Test
	public void testHome(){
		cmd.forward(30);
		cmd.home();
	}
	
	@Test
	public void testClearScreen(){
		cmd.forward(20);
		cmd.clear();
	}
	
	@Test
	public void penup(){
		cmd.forward(10);
		cmd.penup();
		cmd.forward(20);
	}
	
	@Test 
	public void pendown(){
		cmd.forward(10);
		cmd.penup();
		cmd.forward(20);
		cmd.pendown();
		cmd.back(20);
	}
	
	@Test
	public void hideturtle(){
		cmd.forward(10);
		cmd.hideturtle();
	}
}
