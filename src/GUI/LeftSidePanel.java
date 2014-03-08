package GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LeftSidePanel extends JPanel {
	CanvasContainer canvasBox;
	ButtonPanel buttonPane;
	public LeftSidePanel(Canvas canvas, Commands c){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		buttonPane = new ButtonPanel(c);
		canvasBox = new CanvasContainer(canvas);
		
		canvasBox.setPreferredSize(new Dimension(600, 500));
		canvasBox.setMaximumSize(new Dimension(600, 500));		
		
		this.add(canvasBox);
		this.add(buttonPane);
	}
}
