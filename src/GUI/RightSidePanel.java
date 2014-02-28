package GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class RightSidePanel extends JPanel {
	TextEditor editor;
	CommandLine cmd;
	
	public RightSidePanel(Canvas canvas){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		editor = new TextEditor(canvas);
		cmd = new CommandLine();
		
		editor.setMinimumSize(new Dimension(400, 600));
		editor.setMaximumSize(new Dimension(1000, 1000));
		
		cmd.setMinimumSize(new Dimension(400, 200));
		cmd.setMaximumSize(new Dimension(1000, 400));
		
		this.add(editor);
		this.add(cmd);
	}
	
	public JTextArea getTextArea(){
		return editor.getTextArea();
	}
}
