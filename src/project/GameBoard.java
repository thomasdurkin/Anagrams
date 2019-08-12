package project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

public class GameBoard implements MouseListener{
	
	JFrame gameFrame;
	JPanel gamePanel = new JPanel();
	CheckWord checker = new CheckWord();
	
	Letter l = new Letter(' ');
	int t = 60;
	Timer timer;
	JLabel timeLabel = new JLabel("00:"+Integer.toString(t));
	int scores = 0;
	int hostScore = 0;
	int clientScore = 0;
	JLabel scoreLabel = new JLabel(Integer.toString(scores));
	
	boolean isHost;
	
	DataOutputStream output;
	
	public String strLetters;
	
	ArrayList<JLabel> word = new ArrayList<JLabel>();
	ArrayList<JLabel> givenLetters = new ArrayList<JLabel>();
	Font font = null;
	ArrayList<Letter> letters = new ArrayList<Letter>();
	boolean[] click;
	int[] tracked;
	
	String[] formed;
	ArrayList<String> scored = new ArrayList<String>();
	
	int index = 0;
	
	JPanel completed = new JPanel();
	Box content = null;
	
	boolean val = false;
	boolean inval = false;
	
	

	public GameBoard(boolean host, DataOutputStream o, String s) {
		tracked = new int[6];
		formed = new String[6];
		for (int i = 0; i<6; i++) {
			formed[i] = "";
			tracked[i] = -1;
		}
		click = new boolean[6];
		for (int i = 0; i<6; i++) {
			click[i] = false;
		}
		this.isHost = host;
		output = new DataOutputStream(o);
		strLetters = s;
		
	
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("../resources/KBChatterBox.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		}
		catch(IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int SCREEN_HEIGHT = (int) screenSize.getHeight();
		final int SCREEN_WIDTH = (int) screenSize.getWidth();
		
//		final int SCREEN_HEIGHT = 300;
//		final int SCREEN_WIDTH = 300;
		
		
		
		
		
		gameFrame = new JFrame("Anagrams");
		gameFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		//set background color
		gameFrame.getContentPane().setBackground(Color.CYAN);
		
		JLabel background = new GameBackground();
		
		gameFrame.addMouseListener(this);

		//if the gameboard is for the host then get the letters for the game
		// and send them to the client
		if(isHost) {
			letters = l.getLetters();
			try {
				strLetters = stringLetters(letters);
				//output.writeUTF(strLetters);
				Host.sendLetters(strLetters);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			for(int i = 0; i < strLetters.length();i++) {
				letters.add(new Letter(strLetters.charAt(i)));
			}
		}
		
		int start_x = SCREEN_WIDTH / 2 - 160, start_y = SCREEN_HEIGHT - 300;
		for(int i = 0; i < 6; i++) {
			JLabel empty = new JLabel("_");
			empty.setBounds(start_x, start_y - 75, 60, 60);
			empty.setFont(font.deriveFont(70f));
			word.add(empty);
			empty.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("clicked on word " + empty.getText() +" !");
					String clicked = empty.getText();
					if (word.indexOf(empty) == index-1) {
						word.get(word.indexOf(empty)).setText("_");
						word.get(word.indexOf(empty)).setFont(font.deriveFont(70f));
						formed[word.indexOf(empty)] = "";
//						int d = 0;
//						for (int i = 0; i<6; i++) {
//							if (clicked.equals(String.valueOf(letters.get(i).letter))) {
//								d = i;
//								break;
//							}
//						}
						givenLetters.get(tracked[index-1]).setText(clicked);
						givenLetters.get(tracked[index-1]).setFont(font.deriveFont(70f));
						click[tracked[index-1]] = false;
						tracked[index-1] = -1;
						index--;
					}
				}	
			});
			background.add(empty);
			start_x += 65;
		}
		start_x = SCREEN_WIDTH / 2 - 160; 
		start_y = SCREEN_HEIGHT - 300;
		for(int i = 0; i < letters.size(); i++) {
			Letter l = letters.get(i);
			l.letterLabel.setBounds(start_x, start_y, 60, 60);
			givenLetters.add(l.letterLabel);
			l.letterLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("clicked " + l.letter +" !");
					if (!click[givenLetters.indexOf(l.letterLabel)]) {
						click[givenLetters.indexOf(l.letterLabel)] = true;
						tracked[index] = givenLetters.indexOf(l.letterLabel);
						word.get(index).setText(String.valueOf(l.letter));
						formed[index] = String.valueOf(l.letter);
						givenLetters.get(givenLetters.indexOf(l.letterLabel)).setText("_");
						givenLetters.get(givenLetters.indexOf(l.letterLabel)).setFont(font.deriveFont(70f));
						index++;
					}
				}	
			});
			background.add(l.letterLabel);
			start_x += 65;
		}
		
		
		
		timeLabel.setFont(new Font(timeLabel.getFont().getName(), Font.PLAIN, 60));
		timeLabel.setBounds(SCREEN_WIDTH/2 - 50, 50, 100, 100);
		background.add(timeLabel);
		
		
		JLabel sc = new JLabel("Score: ");
		sc.setFont(font.deriveFont(40f));
		sc.setBounds(SCREEN_WIDTH/2 - 100, 120, 250, 50);
		background.add(sc);

		scoreLabel.setFont(font.deriveFont(60f));
		scoreLabel.setBounds(SCREEN_WIDTH/2 + 50, 120, 250, 50);
		background.add(scoreLabel);
		
		background.add(completed);
		completed.setOpaque(false);;
		completed.setBounds(SCREEN_WIDTH/2-150,200,250,500);
		Box b = Box.createVerticalBox();
		b.add(new JLabel("Your words:"));
		
		JLabel enter = new JLabel("ENTER");
		enter.setFont(font.deriveFont(30f));
		enter.setBounds(SCREEN_WIDTH/2 - 125, SCREEN_HEIGHT - 225, 120, 30);
		enter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel valid = new JLabel("Nice Job! +");
		JLabel invalid = new JLabel("Not a Word");
		valid.setFont(font.deriveFont(70f));
		invalid.setFont(font.deriveFont(70f));
		
		enter.addMouseListener(new MouseAdapter() {
			int t;
			@Override
			public void mouseClicked(MouseEvent e) {
				t = 2;
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					public void run() {
						t--;
						System.out.println(t);
						if(t == 0) {
							if(val) {
								System.out.println("removed");
								
								background.remove(valid);
							}
							else if(inval) {
								background.remove(invalid);
							}
							timer.cancel();
						}
						
					}
				}, 1000, 1000);
				
				String d_word = "";
				for (int i = 0; i<6; i++) {
					if (!formed[i].equals("")) {
						d_word+=formed[i];
					}
				}
				if (d_word.equals("")) {
					return ;
				}
//				System.out.println("submit word "+d_word);
				int p = 0;
				try {
					p = checker.check(d_word);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (p>0 && !scored.contains(d_word)) {
					scores+=p;
					scored.add(d_word);
					val = true;
					t = 1;
					for(int i = 0; i < word.size(); i++) {
						word.get(i).setOpaque(true);
						word.get(i).setBackground(Color.GREEN);
					}
					
					timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						public void run() {
							t--;
							if(t == 0) {
								for(int i = 0; i < word.size(); i++) {
									word.get(i).setOpaque(false);
									word.get(i).setBackground(Color.BLUE);
								}
								timer.cancel();
							}
						}
					}, 200, 200);
					Box box = b;
					JLabel tp = new JLabel(d_word);
					tp.setFont(font.deriveFont(40f));
					box.add(tp);
					content = box;
					completed.removeAll();
					completed.add(box);
					System.out.println("added");
					
				} 
				else {
					inval = true;
					t = 1;
					for(int i = 0; i < word.size(); i++) {
						word.get(i).setOpaque(true);
						word.get(i).setBackground(Color.RED);
					}
					
					timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						public void run() {
							t--;
							if(t == 0) {
								for(int i = 0; i < word.size(); i++) {
									word.get(i).setOpaque(false);
									word.get(i).setBackground(Color.BLUE);
								}
								timer.cancel();
							}
						}
					}, 200, 200);
				}
				scoreLabel.setText(String.valueOf(scores));
				System.out.println("word worth "+p);
				scoreLabel.setFont(font.deriveFont(60f));
				
				
				
				clear();
			}	
		});
		background.add(valid);
		background.add(enter);
		
		JLabel reset = new JLabel("RESET");
		reset.setFont(font.deriveFont(30f));
		reset.setBounds(SCREEN_WIDTH/2 + 50, SCREEN_HEIGHT - 225, 120, 30);
		reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("reseting word");
				clear();
			}	
		});
		background.add(reset);

		startTime();
		gameFrame.setContentPane(background);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		
	}
	
	public void clear() {
		for (int i = 0; i<6; i++) {
			word.get(i).setText("_");
			word.get(i).setFont(font.deriveFont(70f));
			givenLetters.get(i).setText(String.valueOf(strLetters.charAt(i)));
			givenLetters.get(i).setFont(font.deriveFont(70f));
			formed[i] = "";
			click[i] = false;
			tracked[i] = -1;
		}
		index = 0;
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
			String words = "";
			for (int i = 0; i<scored.size(); i++) {
				words += scored.get(i)+",";
			}
			if(isHost){
				Host.sendScore(scores, words);
				clientScore = Host.receiveScore();
				System.out.println("client finale is "+Host.finale);
				if(scores > clientScore){
					Host.sendResult("host");
					String temp = "Congrats you WIN! \nYour Score: " + Integer.toString(scores) + " \nClient's Score: " + Integer.toString(clientScore)+"\n\n";
					temp += "Opponent words:\n";
					String[] l = Host.finale.split("\n");
					for (int i = 0; i<l.length; i++) {
						temp += l[i]+"\n";
					}
					JOptionPane.showMessageDialog(null, temp);
				}
				else if(scores < clientScore){
					Host.sendResult("client");
					String temp = "You Lost! \nYour Score: " + scores + " \nClient's Score: " + clientScore+"\n\n";
					temp += "Opponent words:\n";
					String[] l = Host.finale.split("\n");
					for (int i = 0; i<l.length; i++) {
						temp += l[i]+"\n";
					}
					JOptionPane.showMessageDialog(null, temp);
				}
				else{
					Host.sendResult("tie");
					String temp =  "TIE! \nYour Score: " + scores + " \nClient's Score: " + clientScore+"\n\n";
					temp += "Opponent words:\n";
					String[] l = Host.finale.split("\n");
					for (int i = 0; i<l.length; i++) {
						temp += l[i]+"\n";
					}
					JOptionPane.showMessageDialog(null,temp);
				}
				Host.disconnect();
			}
			else{
				hostScore = Client.receiveScore();
				Client.sendScore(scores, words);
				System.out.println("host finale is "+Client.finale);
				String result = Client.receiveResult();
				if(result.compareTo("host") == 0){
					String temp = "You Lost! \nYour Score: " + scores + " \nHost's Score: " + hostScore+"\n\n";
					temp += "Opponent words:\n";
					String[] l = Client.finale.split("\n");
					for (int i = 0; i<l.length; i++) {
						temp += l[i]+"\n";
					}
					JOptionPane.showMessageDialog(null, temp);
				}
				else if (result.compareTo("client") == 0){
					String temp = "Congrats you WIN! \nYour Score: " + scores + " \nHost's Score: " + hostScore+"\n\n";
					temp += "Opponent words:\n";
					String[] l = Client.finale.split("\n");
					for (int i = 0; i<l.length; i++) {
						temp += l[i]+"\n";
					}
					JOptionPane.showMessageDialog(null, temp);
				}
				else{
					String temp = "TIE! \nYour Score: " + scores + " \nHost's Score: " + hostScore + "\n\n";
					temp += "Opponent words:\n";
					String[] l = Client.finale.split("\n");
					for (int i = 0; i<l.length; i++) {
						temp += l[i]+"\n";
					}
					JOptionPane.showMessageDialog(null, temp);
				}

				Client.disconnect();
			}

			gameFrame.dispose();
			new StartScreen();
			
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
	
	public String stringLetters(ArrayList<Letter> letters) {
		String allLetters = "";
		for(Letter l: letters) {
			allLetters += l.letter;
		}
		return allLetters;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(arg0.getX() + " " + arg0.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
