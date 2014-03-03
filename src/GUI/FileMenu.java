package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class FileMenu extends JPanel{
	JMenuBar fileBar;
	JPanel canvas;
	JTextArea textEditor;
	
	public FileMenu(JPanel turtleCanvas, JTextArea textArea){
		canvas = turtleCanvas;
		textEditor = textArea;
		fileBar = createMenuBar();
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		this.add(fileBar);
        this.setMaximumSize(new Dimension(2000, 15));
        this.setMinimumSize(new Dimension(1000, 15));
        this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
	}
	
	private JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = createMenu("File");
		JMenuItem menuItem1 = createMenuItem("Exit", "Exit Application");
		JMenuItem menuItem2 = createMenuItem("Save LOGO File", "Save code to text file");
        JMenuItem menuItem3 = createMenuItem("Save Canvas", "Save Canvas to Image");
		
        menuItem1.addActionListener(new ActionClose());
        menuItem2.addActionListener(new ActionSaveText());
        menuItem3.addActionListener(new ActionSaveImage());
        
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);
        return menuBar;
	}
	
	private JMenu createMenu(String name){
		JMenu menu = new JMenu(name);
		menu.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		return menu;
	}
	
	private JMenuItem createMenuItem(String name, String tooltip){
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.setToolTipText(tooltip);
        return menuItem;
	}
	
	private void createImageSaveChooser(BufferedImage content){
	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("/Documents"));
	    int retreival = chooser.showSaveDialog(null);
	    if (retreival == JFileChooser.APPROVE_OPTION) {
	        try {
	        	ImageIO.write(content, "png", new File(chooser.getSelectedFile() + ".png"));
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	public void createTextSaveChooser(String text){
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("/Documents"));
	    int retreival = chooser.showSaveDialog(null);
	    if (retreival == JFileChooser.APPROVE_OPTION) {
	        try {
	        	File file = chooser.getSelectedFile();
	        	FileWriter fw = new FileWriter(file + ".logo");
	        	fw.write(text);
	        	fw.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
//	private void showAlert(String message, String title){
//		JFrame f = new JFrame();
//		JOptionPane.showMessageDialog(f, message, title, JOptionPane.ERROR_MESSAGE);
//	}
	
	////////////////ACTION LISTENERS/////////////////////
	private class ActionClose implements ActionListener{
        @Override
		public void actionPerformed(ActionEvent event) {
        	System.exit(0);
        }
	}
	
	private class ActionSaveText implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String ln = System.getProperty("line.separator");
			String text = textEditor.getText() ;
			String finalText = text.replaceAll("\n", ln);

			createTextSaveChooser(finalText);
		}
	}
	
	private class ActionSaveImage implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){
			BufferedImage im = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
			canvas.paint(im.getGraphics());
			createImageSaveChooser(im);
		}
	}
}