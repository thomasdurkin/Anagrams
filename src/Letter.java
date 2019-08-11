import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
		
		int vowelcount = 0;
		int consonantcount = 0;
		int size = 0;
		String vowels = "AEIOU";
		String consonants = "BCDFGHJKLMNPQRSTVWXYZ";

		ArrayList<Letter> letters = new ArrayList<Letter>();
		ArrayList<Character> vowelList = new ArrayList<Character>();
		ArrayList<Character> consonantList = new ArrayList<Character>();

		while(size < 6){
			Letter temp = randomLetter();
	
			//Checking Rules to see if current letter can be added to list
			if(vowels.indexOf(temp.letter) != -1){ //current letter is vowel
				vowelcount++;
				vowelList.add(temp.letter);
		
				if((vowelcount < 4) && (Collections.frequency(vowelList, temp.letter) < 3)){ //if vowel can be added to Letters List
					letters.add(temp);
					size++;
				}
				else{ //if vowel cannot be added to list, remove from counter and vowel list
					vowelcount--;
					vowelList.remove((Character)temp.letter);
				}
			}
			
			else{
				if(consonantcount < 4) {//current letter is consonant
					consonantcount++;
					consonantList.add(temp.letter);
			
					if(Collections.frequency(consonantList, temp.letter) < 3){ //if consonant can be added to letters list
						letters.add(temp);
						size++;
					}
					else{ //if consonant cannot be added to letters list, remove from consonant list
						consonantList.remove((Character)temp.letter);
						consonantcount--;
					}
				}
			}
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
