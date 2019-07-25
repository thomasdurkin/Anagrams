import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartScreen implements ActionListener{
	
	JButton hostButton;
	JButton joinButton;
	
	public StartScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
		JFrame home = new JFrame("Anagrams");
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(2,1));
		buttonPanel.setSize(125,320);
		
		home.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		home.setLayout(null);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setResizable(false);
		
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
		
		home.validate();
		home.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(hostButton) == true) {
			System.out.println("hosting");
		}
		if(e.getSource().equals(joinButton) == true) {
			System.out.println("joining");
		}
		
	}
}
