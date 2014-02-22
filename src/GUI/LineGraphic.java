package GUI;

import java.awt.Color;
import java.awt.Graphics;

public class LineGraphic {
	private int x, y, width, height;
	
	public LineGraphic(String direction, int x, int y){
		this.x = x;
		this.y = y;
		assignDirection(direction);
	}
	
	public void assignDirection(String dir){
		if(dir == "up"){
			width = 2;
			height = 10;
			y -= 10;
		}
		else if(dir == "down"){
			width = 2; 
			height =10;
		}
		else if(dir == "left"){
			x -= 10;
			width = 10;
			height = 2;
		}
		else if(dir == "right"){
			width = 10;
			height = 2;
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}
}
