package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class ButtonPanel extends JPanel{
	private JButton up, down, left, right, clear, penup, home;
	private JPanel directionPane, rootPane, homePanel, penupPanel; 
	private Commands cmd;
	private boolean pendown;
	
	public ButtonPanel(Commands c){
		pendown = true;
		cmd = c;
		
		initRootPane();
		initDirectionPane();
		initButtons();
		createPanelButtons();
		addButtons();

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(directionPane);
		this.add(Box.createHorizontalGlue());
		this.add(rootPane);
		this.add(Box.createHorizontalGlue());
	}
	
	public void initDirectionPane(){
		directionPane = new JPanel(new BorderLayout());
		directionPane.setFocusable(true);
		directionPane.setFocusTraversalKeysEnabled(true);
		directionPane.requestFocusInWindow();
		directionPane.setMaximumSize(new Dimension(240, 240));
	}
	
	public void initRootPane(){
		rootPane = new JPanel();
		rootPane.setLayout(new BoxLayout(rootPane, BoxLayout.Y_AXIS));
		rootPane.setMaximumSize(new Dimension(160, 80));
	}
	
	public void initButtons(){
		createButtons();
		addListeners();
	}
	
	public void createButtons(){
		up = makeIconButton("upU.png", "upP.png");
		down = makeIconButton("downU.png", "downP.png");
		left = makeIconButton("leftU.png", "leftP.png");
		right = makeIconButton("rightU.png", "rightP.png");		
		clear = makeIconButton("clear.png", "clear2.png");
		
		penup = new JButton("Pen DOWN");
		home = new JButton("Home");
		
		penup.setBackground(Color.YELLOW);
	}
	
	public void createPanelButtons(){
		homePanel = new JPanel(new FlowLayout());
		penupPanel = new JPanel(new FlowLayout());
		
		homePanel.setMaximumSize(new Dimension(120, 35));
		penupPanel.setMaximumSize(new Dimension(120, 35));
		
		homePanel.add(home);
		penupPanel.add(penup);
	}
	
	public void addListeners(){
		directionPane.addKeyListener(new KeyControls());
		
		up.addActionListener(new moveUp());
		down.addActionListener(new moveDown());
		left.addActionListener(new moveLeft());
		right.addActionListener(new moveRight());
		clear.addActionListener(new clearCanvas());
		penup.addActionListener(new penDown(penup));
		home.addActionListener(new goHome());
	}
	
	public void addButtons(){
		directionPane.add(up, BorderLayout.PAGE_START);
		directionPane.add(down, BorderLayout.PAGE_END);
		directionPane.add(left, BorderLayout.LINE_START);
		directionPane.add(right, BorderLayout.LINE_END);
		directionPane.add(clear, BorderLayout.CENTER);
		
		rootPane.add(penupPanel);
		rootPane.add(homePanel);
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
	
	public class goHome implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0){
			cmd.home();
		}
	}
	
	public class penDown implements ActionListener {
		JButton button;
		public penDown(JButton b){
			button = b;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(pendown == false) {
				cmd.pendown();
//				button.setText("Pen UP");
				button.setBackground(Color.YELLOW);
				pendown = true;
			}
			else if(pendown == true) {
				cmd.penup();
				pendown = false;
//				button.setText("Pen DOWN");
				button.setBackground(null);
			}
		}
		
	}
}