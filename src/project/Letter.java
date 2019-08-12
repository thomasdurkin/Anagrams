package project;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.swing.JLabel;


public class Letter{
	JLabel letterLabel;
	Font font;
	char letter;
	
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
		
		int vowelcount = 0;
		
		String vowels = "AEIOU";

		HashMap<Character, Double > all = generate();
		ArrayList<Letter> letters = new ArrayList<Letter>();
		
		double total = 0.0;
		
		for(Double val: all.values()) {
			total += val;
			System.out.println(total);
		}
		//loop until we get 6 letters
		while(letters.size() < 6){
			double rand = Math.random() * total;
			double weightcnt = 0.0;
			for(Character l: all.keySet()) {
				weightcnt += all.get(l);
				//grab letter based on weight
				if(weightcnt >= rand) {
					String s = l.toString();
					if(vowels.contains(s)) {
						vowelcount++;
					}
					//make sure there are not more then 4 vowels
					if(vowelcount > 3)
						continue;
					Letter temp = new Letter(l);
					//no more than two of the same letters
					if(Collections.frequency(letters, temp) > 2)
						continue;
					letters.add(temp);
					break;
				}
			}
			//make sure we have more then 2 vowels
			if(letters.size() == 6 && vowelcount < 2) {
				letters.clear();
				vowelcount = 0;
			}
		}
		return letters;
	}
	
	//generate all letters and how common they are
	public HashMap<Character, Double> generate(){
		HashMap<Character, Double> l = new HashMap<Character, Double>();
		l.put('A', 8.50);
		l.put('B', 2.07);
		l.put('C', 4.54);
		l.put('D', 3.38);
		l.put('E', 11.16);
		l.put('F', 1.81);
		l.put('G', 2.47);
		l.put('H', 3.00);
		l.put('I', 7.55);
		l.put('J', 0.20);
		l.put('K', 1.10);
		l.put('L', 5.49);
		l.put('M', 3.01);
		l.put('N', 6.65);
		l.put('O', 7.16);
		l.put('P', 3.17);
		l.put('Q', 0.20);
		l.put('R', 7.58);
		l.put('S', 5.74);
		l.put('T', 6.95);
		l.put('U', 3.63);
		l.put('V', 1.01);
		l.put('W', 1.29);
		l.put('X', 0.29);
		l.put('Y', 1.78);
		l.put('Z', 0.27);
		return l;
	}
}