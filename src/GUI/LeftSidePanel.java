package GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LeftSidePanel extends JPanel {
	CanvasContainer canvas;
	ButtonPanel buttonPane;
	public LeftSidePanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		canvas = new CanvasContainer();
		buttonPane = new ButtonPanel(canvas);
		
		canvas.setPreferredSize(new Dimension(500, 500));
		canvas.setMaximumSize(new Dimension(600, 508));
		buttonPane.setPreferredSize(new Dimension(240, 240));
		buttonPane.setMaximumSize(new Dimension(240, 240));
		
		this.add(canvas);
		this.add(buttonPane);
	}
	
	public JPanel getCanvas(){
		return canvas.getInteriorCanvas();
	}
}
