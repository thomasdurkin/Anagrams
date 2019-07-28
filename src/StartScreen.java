
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
	
	
	public StartScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("../resources/Scramble.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame();
		frame.setSize(SCREEN_WIDTH ,SCREEN_HEIGHT);
		frame.setTitle("Anagrams");
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel background = new Background();
		
	
		
		
		JLabel host = new JLabel("Host");
		host.setFont(font.deriveFont(50f));
		host.setCursor(new Cursor(Cursor.HAND_CURSOR));
		host.setBounds(SCREEN_WIDTH/2 - 125, SCREEN_HEIGHT - 300, 300, 50);
		
		JLabel join = new JLabel("Join");
		join.setFont(font.deriveFont(50f));
		join.setCursor(new Cursor(Cursor.HAND_CURSOR));
		join.setBounds(SCREEN_WIDTH/2 - 125, SCREEN_HEIGHT - 225, 300, 50);
		
		host.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(join);
				frame.remove(host);
				frame.repaint();
				new Host();
				JLabel waiting = new JLabel("<html>Waiting<br/>for player</html>", SwingConstants.CENTER);
				waiting.setFont(font.deriveFont(40f));
				waiting.setBounds(SCREEN_WIDTH/2 - 375, SCREEN_HEIGHT - 400, 800, 300);
				frame.add(waiting);
			}
		});
		
		
		join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.remove(join);
				frame.remove(host);
				frame.repaint();
				new Client();
				JLabel waiting = new JLabel("<html>Waiting<br/>for host</html>", SwingConstants.CENTER);
				waiting.setFont(font.deriveFont(40f));
				waiting.setBounds(SCREEN_WIDTH/2 - 375, SCREEN_HEIGHT -400, 800, 300);
				frame.add(waiting);
			}
		});
		
		background.add(host);
		background.add(join);
	
		frame.setContentPane(background);
		
		frame.setVisible(true);
		
	}

	
}
