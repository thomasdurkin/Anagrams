import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoard {
	
	JFrame gameFrame;
	JPanel gamePanel = new JPanel();
	
	Letter l = new Letter(' ');
	int t = 60;
	Timer timer;
	JLabel timeLabel = new JLabel("00:"+Integer.toString(t));
	int scores = 0;
	JLabel scoreLabel = new JLabel(Integer.toString(scores));
	
	public GameBoard() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
		gameFrame = new JFrame("Anagrams");
		gameFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		//set background color
		gameFrame.getContentPane().setBackground(Color.CYAN);
		
		
		ArrayList<Letter> letters = l.getLetters();
		int start_x = SCREEN_WIDTH / 2 - 150, start_y = SCREEN_HEIGHT - 300;
		for(int i = 0; i < letters.size(); i++) {
			Letter l = letters.get(i);
			l.letterLabel.setBounds(start_x, start_y, 75, 75);
			l.letterLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("clicked " + l.letter +" !");
				}	
			});
			gameFrame.add(l.letterLabel);
			start_x += 65;
		}
		
		Box b = Box.createVerticalBox();
		b.add(timeLabel);
		b.add(new JLabel("Scores"));
		b.add(scoreLabel);
		gamePanel.add(b);
		gameFrame.add(gamePanel, BorderLayout.CENTER);
		startTime();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		
	}

	public void make(JLabel label) {
		while (label.getText().length()<4) {
			label.setText("0"+label.getText());
		}
	}

	public String sec() {
		if (t == 1) {
			timer.cancel();
			JOptionPane.showMessageDialog(null, "Time is UP!");
		}
		return Integer.toString(--t);
	}
	
	public void startTime() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				timeLabel.setText(sec());
			}
		}, 1000, 1000);
	}

}
