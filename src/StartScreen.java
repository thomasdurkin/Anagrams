
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.io.File;
import java.io.IOException;

public class StartScreen extends JFrame{
	private Font font;
	boolean hostSelected, clientSelected;
	
	
	public StartScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
//		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
		final int SCREEN_HEIGHT = 300;
		final int SCREEN_WIDTH = 300;
		
		//load in custom font
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("../resources/KBChatterBox.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		//create new JFrame with some settings
		JFrame frame = new JFrame();
		frame.setSize(SCREEN_WIDTH ,SCREEN_HEIGHT);
		frame.setTitle("Anagrams");
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel background = new Background();
		
		//button for host
		JLabel host = new JLabel("Host");
		host.setFont(font.deriveFont(60f));
		host.setCursor(new Cursor(Cursor.HAND_CURSOR));
		host.setBounds(SCREEN_WIDTH/2 - 125, SCREEN_HEIGHT - 300, 300, 50);
		
		//button for joining a game
		JLabel join = new JLabel("Join");
		join.setFont(font.deriveFont(60f));
		join.setCursor(new Cursor(Cursor.HAND_CURSOR));
		join.setBounds(SCREEN_WIDTH/2 - 125, SCREEN_HEIGHT - 225, 300, 50);
		
		host.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				background.remove(join);
				background.remove(host);
				JLabel waiting = new JLabel("<html>Waiting<br/>for player</html>", SwingConstants.CENTER);
				waiting.setFont(font.deriveFont(50f));
				waiting.setBounds(SCREEN_WIDTH/2 - 400, SCREEN_HEIGHT - 400, 800, 300);
				background.add(waiting);
				frame.setContentPane(background);
				frame.revalidate();
				hostSelected = true;
			}
		});
		
		
		join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				background.remove(join);
				background.remove(host);
				//display waiting for host
				JLabel waiting = new JLabel("<html>Waiting<br/>for host</html>", SwingConstants.CENTER);
				waiting.setFont(font.deriveFont(50f));
				waiting.setBounds(SCREEN_WIDTH/2 - 400, SCREEN_HEIGHT -400, 800, 300);
				background.add(waiting);
				frame.setContentPane(background);
				frame.revalidate();
				clientSelected = true;
			}
		});
		
		background.add(host);
		background.add(join);
	
		frame.setContentPane(background);
		
		frame.setVisible(true);
		
		while(!hostSelected && !clientSelected) {
			try {
				Thread.sleep(1);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		if(hostSelected) {
			System.out.println("Creating Host");
			new Host();
			frame.dispose();
		}
		else {
			System.out.println("Creating Client");
			new Client();
			frame.dispose();
		}
		
	}

	
}
