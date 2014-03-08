package GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class RightSidePanel extends JPanel {
	TextEditor editor;
	CommandLine cmdLine;
	
	public RightSidePanel(Commands c){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		cmdLine = new CommandLine(c);
		editor = new TextEditor(c, cmdLine);
		
		editor.setMinimumSize(new Dimension(400, 600));
		editor.setMaximumSize(new Dimension(1000, 1000));
		
		cmdLine.setMinimumSize(new Dimension(400, 200));
		cmdLine.setMaximumSize(new Dimension(1000, 400));
		
		this.add(editor);
		this.add(cmdLine);
	}
	
	public JTextArea getTextArea(){
		return editor.getTextArea();
	}
}
