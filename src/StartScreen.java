import java.awt.BorderLayout;
import java.awt.Dimension;
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
	
	public StartScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
		JFrame home = new JFrame("Anagrams");
		JPanel buttonPanel = new JPanel();
		
		home.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		home.setLayout(new BorderLayout());
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setResizable(false);
		
		ImageIcon hostImage = new ImageIcon("../images/hostButton.png");
		hostButton = new JButton("", hostImage);
		hostButton.setSize(50,50);
		hostButton.setBorder(BorderFactory.createEmptyBorder());
		hostButton.setContentAreaFilled(false);
		hostButton.addActionListener(this);
		buttonPanel.add(hostButton);
		home.add(buttonPanel, BorderLayout.CENTER);
		
		home.validate();
		home.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(hostButton) == true) {
			System.out.println("hosting");
		}
		
	}
}
