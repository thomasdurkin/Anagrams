package Control;
import java.io.*;
import java.net.*;
import javax.swing.*;

import View.*;

public class Client implements Runnable {
	
	static Socket s;
	
	static DataOutputStream output;
	static DataInputStream input;
	
	JFrame frame;
	
	boolean isRunning = false;
	boolean connected = false;
	String ip;
	GameBoard gb;
	public static String finale = "";
	
	public Client() {
		ip = JOptionPane.showInputDialog("Please enter a valid ip address to connect to.");
		
		while(!connected) {
			try {
				System.out.println("Starting up client");
				s = new Socket(ip, 3000);
				connected = true;
				output = new DataOutputStream(s.getOutputStream());
				input = new DataInputStream(s.getInputStream());
			}
			catch(UnknownHostException he) {
				ip = JOptionPane.showInputDialog("Unable to connect to host: Please enter a new ip address.");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		new Thread(this).start();
	}

	@Override
	public void run() {
		isRunning = true;
		System.out.println("Connected to host");
		while(isRunning) {
			try {
				String str_in = input.readUTF();
				if (str_in.length() == 6) {
					gb = new GameBoard(false, output, str_in);
					isRunning = false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	

	public static void disconnect() {
		try {
			input.close();
			output.close();
			s.close();
		}
		catch(Exception e) {
			
		}
		
	}
	
	public static void sendScore(int s, String words){
		String temp = Integer.toString(s)+","+words;
		try{
			output.writeUTF(temp);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static int receiveScore(){
		String temp = "";
		while(temp.compareTo("") == 0) {
			try{
			temp = input.readUTF();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		int re = Integer.valueOf(temp.split(",")[0]);
		String[] l = temp.split(",");
		for (int i = 1; i<l.length; i++) {
			finale += l[i]+"\n";
		}
		return re;
	}
	
	public static String receiveResult(){
		String temp = "";
		
		while(temp.compareTo("") == 0){
			try{
			temp = input.readUTF();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
		return temp;
	}

}
