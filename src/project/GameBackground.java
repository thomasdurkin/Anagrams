package project;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

class GameBackground extends JLabel{
	Image bg = new ImageIcon("../resources/gbBackground.jpg").getImage();
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}