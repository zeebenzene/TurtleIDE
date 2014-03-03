package GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ButtonPanel extends JPanel{
	private JButton up, down, left, right, clear;
	private JPanel rootPane; 
	private Canvas canvas;
	private Commands cmd;
	
	public ButtonPanel(CanvasContainer container){
		rootPane = new JPanel(new BorderLayout());
		rootPane.setFocusable(true);
		rootPane.setFocusTraversalKeysEnabled(true);
		rootPane.requestFocusInWindow();
		
		canvas = container.getInteriorCanvas();
		cmd = new Commands(canvas);
		initButtons();
		initRootPane();

		this.add(rootPane);
		this.addKeyListener(new KeyControls());
	}
	
	public void initButtons(){
		up = makeIconButton("upU.png", "upP.png");
		down = makeIconButton("downU.png", "downP.png");
		left = makeIconButton("leftU.png", "leftP.png");
		right = makeIconButton("rightU.png", "rightP.png");		
		clear = makeIconButton("clear.png", "clear2.png");
		
		rootPane.addKeyListener(new KeyControls());
		up.addActionListener(new moveUp());
		down.addActionListener(new moveDown());
		left.addActionListener(new moveLeft());
		right.addActionListener(new moveRight());
		clear.addActionListener(new clearCanvas());
	}
	
	public void initRootPane(){
		rootPane.add(up, BorderLayout.PAGE_START);
		rootPane.add(down, BorderLayout.PAGE_END);
		rootPane.add(left, BorderLayout.LINE_START);
		rootPane.add(right, BorderLayout.LINE_END);
		rootPane.add(clear, BorderLayout.CENTER);
	}
	
	public Image openAndScaleImage(int size, String filePath){
		Image image = null;
		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			JFrame f = new JFrame();
			String errorMessage = "Could not open the image: " + filePath + "\nCheck to see if the image exists.";
			String errorTitle = "Image Read Error";
			JOptionPane.showMessageDialog(f, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
		}

		image = image.getScaledInstance(size, size, java.awt.Image.SCALE_AREA_AVERAGING);
		return image;
	}

	public JButton makeIconButton(String unpressedFilePath, String pressedFilePath){
		Image pressedIcon = openAndScaleImage(80, pressedFilePath);
		Image unpressedIcon = openAndScaleImage(80, unpressedFilePath);
		
		JButton button = new JButton();
		button.setIcon(new ImageIcon(unpressedIcon));
		button.setContentAreaFilled(false);
		button.setDisabledIcon(new ImageIcon(pressedIcon));
		button.setPressedIcon(new ImageIcon(pressedIcon));
		button.setBorder(null);

		return button;
	}
	
	public void keyAction(KeyEvent event) {
	    if (event.getKeyCode() == KeyEvent.VK_UP) {
	        cmd.forward(5.0);
	    }
	    if (event.getKeyCode() == KeyEvent.VK_DOWN) {
	        cmd.back(5.0);
	    }
	    if (event.getKeyCode() == KeyEvent.VK_LEFT) {
	        cmd.left(5.0);
	    }
	    if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
	        cmd.right(5.0);
	    }
	}
	public class KeyControls implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) { keyAction(e); }
			
		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) { keyAction(e); }
		
	}
	
	public class moveUp implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			cmd.forward(5);
		}
	}	
	public class moveDown implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cmd.back(5);
		}
	}
	public class moveLeft implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cmd.left(5);
		}			
	}	
	public class moveRight implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cmd.right(5);
		}
	}
	public class clearCanvas implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			cmd.clear();
		}
	}
}