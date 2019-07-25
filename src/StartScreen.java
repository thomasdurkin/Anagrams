import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class StartScreen implements ActionListener{
	
	JButton hostButton;
	JButton joinButton;
	
	public JFrame home;
	
	public StartScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
		home = new JFrame("Anagrams");
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.setSize(125,320);
		
		home.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		home.setLayout(null);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		BufferedImage i;
//		ImageIcon icon;
//		JPanel p = new JPanel();
//		JLayeredPane layer = new JLayeredPane();
//		try {
//			i = ImageIO.read(new File("../images/try.jpg"));
//			icon = new ImageIcon(i);
//			JLabel background = new JLabel("",icon, JLabel.CENTER);
//			p.add(background);
//			p.setBounds(0, 0, 1920, 1080);
//			home.add(p);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		layer.add(p,0);

		ImageIcon hostImage = new ImageIcon("../images/hostButton.png");
		hostButton = new JButton("", hostImage);
		hostButton.setBorder(BorderFactory.createEmptyBorder());
		hostButton.setContentAreaFilled(false);
		hostButton.setFocusable(false);
		hostButton.addActionListener(this);
		hostButton.setBounds(50,150,150,160);
		
		
		ImageIcon joinImage = new ImageIcon("../images/joinButton.png");
		joinButton = new JButton("", joinImage);
		joinButton.setIcon(joinImage);
		joinButton.setBorder(BorderFactory.createEmptyBorder());
		joinButton.setContentAreaFilled(false);
		joinButton.setFocusable(false);
		joinButton.addActionListener(this);
		joinButton.setBounds(50,50,150,160);
		


		home.add(joinButton);
		home.add(hostButton);
		
		home.revalidate();
		home.setVisible(true);
		home.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(hostButton) == true) {
			System.out.println("hosting");
			
			home.remove(joinButton);
			home.remove(hostButton);
			
			ImageIcon waitingImage = new ImageIcon("../images/waiting.png");
			JLabel waiting = new JLabel(waitingImage);
			waiting.setBounds(50,50,300,300);
			home.add(waiting);
			
			home.revalidate();
			home.repaint();
			
			new Host();
		}
		if(e.getSource().equals(joinButton) == true) {
			System.out.println("joining");
			
			home.remove(joinButton);
			home.remove(hostButton);
			home.revalidate();
			home.repaint();
			
			ImageIcon waitingImage = new ImageIcon("../images/waiting.png");
			JLabel waiting = new JLabel(waitingImage);
			waiting.setBounds(50,50,300,300);
			home.add(waiting);
			
			new Client();
		}
		
	}
}
