package GUI;


import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BaseFrame extends JFrame{
	JPanel rootPanel, bodyPanel;
	LeftSidePanel leftSide; 
	RightSidePanel rightSide;
	
	public BaseFrame(){
		super("LOGO Integrated Development Environment");
		rootPanel = new JPanel();
		bodyPanel = new JPanel();
		leftSide = new LeftSidePanel();
		rightSide = new RightSidePanel();
		
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		bodyPanel.setLayout(new GridLayout(1, 2, 10, 10));

//		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setMinimumSize(new Dimension(1200, 830));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bodyPanel.add(leftSide);
		bodyPanel.add(rightSide);
		rootPanel.add(new FileMenu(leftSide.getCanvas(), rightSide.getTextArea()));
		rootPanel.add(bodyPanel);
		this.add(rootPanel);
		pack();
	}
	
	public static void main(String[] args){
		BaseFrame b = new BaseFrame();
		b.setVisible(true);
	}
}
