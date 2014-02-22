package GUI;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class TextEditor extends JPanel{
	private JTextArea textArea;
	private JButton runButton;
	private JScrollPane scrollPane;
	private JPanel buttonPanel;

	public TextEditor(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Editor"));
		initialize();
	}
	
	private void initialize(){
		buttonPanel = createButtonPanel();
		scrollPane = createScrollPane();		
		
		textArea = createTextArea();
		runButton = createRunButton();
		scrollPane.getViewport().add(textArea);
		buttonPanel.add(runButton);
		
		this.add(scrollPane);
		this.add(buttonPanel);
	}
	
	private JScrollPane createScrollPane(){
		JScrollPane panel = new JScrollPane();
		setScrollSettings(panel);
		return panel;
	}
	
	private void setScrollSettings(JScrollPane panel){
		panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.getVerticalScrollBar().setUnitIncrement(16);
	}
	
	private JPanel createButtonPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(new EmptyBorder(3, 0, 3, 5));
		panel.add(Box.createHorizontalGlue());
		return panel;
	}
	
	private JButton createRunButton(){
		JButton button = new JButton("Run");
		return button;
	}
	
	private JTextArea createTextArea(){
		JTextArea area = new JTextArea(5, 30);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		return area;
	}
	
	public JTextArea getTextArea(){
		return textArea;
	}
}
