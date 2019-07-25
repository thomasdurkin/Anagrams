import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class Main {
	
	
	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
		JFrame home = new JFrame("Anagrams");
		home.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		home.setLayout(new BorderLayout());
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setResizable(false);
		
		
		
		
		home.setVisible(true);
	}
	
	
	
}
