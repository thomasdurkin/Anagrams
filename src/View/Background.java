package View;
import java.awt.*;
import javax.swing.*;

public class Background extends JLabel{
	Image bg = new ImageIcon("../resources/background.jpg").getImage();
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}
