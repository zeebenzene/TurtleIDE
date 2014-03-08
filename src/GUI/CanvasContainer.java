package GUI;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasContainer extends JPanel{
	public CanvasContainer(Canvas canvas){
		this.setBorder(BorderFactory.createTitledBorder("Turtle Canvas"));
		this.setLayout(new BorderLayout());
		this.add(canvas);
	}
}
