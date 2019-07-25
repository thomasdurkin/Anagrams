import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameBoard {
	
	JFrame gameFrame;
	
	public GameBoard() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
		gameFrame = new JFrame("Anagrams");
		gameFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		
		gameFrame.setVisible(true);
		
	}

}
