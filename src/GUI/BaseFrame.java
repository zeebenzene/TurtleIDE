package GUI;

import java.awt.Dialog.ModalityType;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BaseFrame extends JFrame{
	JPanel rootPanel, bodyPanel;
	LeftSidePanel leftSide; 
	RightSidePanel rightSide;
	Canvas canvas;
	Commands cmd;
	
	public BaseFrame(){
		super("LOGO Integrated Development Environment");
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initPanels();
		addPanels();
		
		this.add(rootPanel);
		pack();
	}
	
	public void addPanels(){
		bodyPanel.add(leftSide);
		bodyPanel.add(rightSide);
		rootPanel.add(new FileMenu(canvas, rightSide.getTextArea()));
		rootPanel.add(bodyPanel);
	}
	
	public void initPanels(){
		canvas = new Canvas();
		cmd = new Commands(canvas);
		
		leftSide = new LeftSidePanel(canvas, cmd);
		rightSide = new RightSidePanel(cmd);
		rootPanel = new JPanel();
		bodyPanel = new JPanel();
		
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		bodyPanel.setLayout(new GridLayout(1, 2, 10, 10));
	}
	
	public void setContinualFocus(){
		JDialog dialog = new JDialog ();
		dialog.setModal (true);
		dialog.setAlwaysOnTop (true);
		dialog.setModalityType (ModalityType.APPLICATION_MODAL);
		
	}
	
	public static void main(String[] args){
		BaseFrame b = new BaseFrame();
		b.setVisible(true);
	}
}
