import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;


public class Letter{
	JLabel letterLabel;
	Font font;
	char letter;
	
	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public Letter(char letter) {
		this.letterLabel = new JLabel(String.valueOf(letter));
		this.letter = letter;
		//load in custom font
		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, new File("../resources/KBChatterBox.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(this.font);
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		this.letterLabel.setFont(this.font.deriveFont(70f));
		this.letterLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	
	
	public ArrayList<Letter> getLetters(){
		
		ArrayList<Letter> letters = new ArrayList<Letter>();
		for(int i = 0; i < 6; i++) {
			letters.add(randomLetter());
		}
		return letters;
	}
	
	Letter randomLetter() {
		
		Random rand = new Random();
		int rand_int = rand.nextInt(25);
		char letter = alphabet.charAt(rand_int);
		
		System.out.println(letter);
		Letter l = new Letter(letter);
		return l;
		
	}
}
