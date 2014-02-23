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
	private int curX, curY;
	private Canvas canvas;
	
	
	
	
	//dude I jujst sent this to a repository brah!!!!
	
	public ButtonPanel(CanvasContainer turtleCanvas){
		rootPane = new JPanel(new BorderLayout());
		canvas = turtleCanvas.getInteriorCanvas();
		curX = 300;
		curY = 485;
		
		initButtons();
		
		rootPane.add(up, BorderLayout.PAGE_START);
		rootPane.add(down, BorderLayout.PAGE_END);
		rootPane.add(left, BorderLayout.LINE_START);
		rootPane.add(right, BorderLayout.LINE_END);
		rootPane.add(clear, BorderLayout.CENTER);
		this.add(rootPane);
		this.addKeyListener(new KeyControls());
	}
	
	public void initButtons(){
		up = makeIconButton("upU.png", "upP.png");
		down = makeIconButton("downU.png", "downP.png");
		left = makeIconButton("leftU.png", "leftP.png");
		right = makeIconButton("rightU.png", "rightP.png");		
		clear = makeIconButton("clear.png", "clear2.png");
		
		up.addActionListener(new moveUp());
		up.addKeyListener(new KeyControls());
		down.addActionListener(new moveDown());
		left.addActionListener(new moveLeft());
		right.addActionListener(new moveRight());
		clear.addActionListener(new clearCanvas());
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

	public void up(){
		canvas.addShape(new LineGraphic("up", curX, curY));
		canvas.moveTurtle(curX - 5, curY);
		curY -= 10;
		canvas.repaint();
	}
	public void down(){
		canvas.addShape(new LineGraphic("down", curX, curY));
		canvas.moveTurtle(curX - 5, curY + 30);
		curY += 10;
		canvas.repaint();
	}
	public void left(){
		canvas.addShape(new LineGraphic("left", curX, curY));
		canvas.moveTurtle(curX - 20, curY + 15);
		curX -= 10;
		canvas.repaint();
	}
	public void right(){
		canvas.addShape(new LineGraphic("right", curX, curY));
		canvas.moveTurtle(curX + 10, curY + 15);
		curX += 10;
		canvas.repaint();
	}
	public void clear() {
		canvas.clear();
		curX = 300;
		curY = 485;
	}
	
	public void keyAction(KeyEvent event) {
	    if (event.getKeyCode() == KeyEvent.VK_UP) {
	        up();
	        System.out.println();
	    }
	    if (event.getKeyCode() == KeyEvent.VK_DOWN) {
	        down();
	    }
	    if (event.getKeyCode() == KeyEvent.VK_LEFT) {
	        left();
	    }
	    if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
	        right();
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
			up();
		}
	}	
	public class moveDown implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			down();
		}
	}
	public class moveLeft implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			left();
		}			
	}	
	public class moveRight implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			right();
		}
	}
	public class clearCanvas implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			clear();
		}
	}
}