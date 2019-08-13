package View;
import java.awt.*;
import javax.swing.*;

class GameBackground extends JLabel{
	Image bg = new ImageIcon("../resources/gbBackground.jpg").getImage();
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}