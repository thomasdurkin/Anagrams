package project;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Host implements Runnable{
	
	static ServerSocket ss;
	static Socket s;
	
	static DataInputStream input;
	static DataOutputStream output;
	
	boolean isRunning = false;
	boolean connected = false;
	
	static String finale = "";
	
	public Host() {
		try {
			System.out.println("Starting up host");
			ss = new ServerSocket(3000);
			s = ss.accept();
			output = new DataOutputStream(s.getOutputStream());
			input = new DataInputStream(s.getInputStream());
			new GameBoard(true, output, null);
			//new Thread(this).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void run() {
		isRunning = true;
		System.out.println("Connected to client");
		while(isRunning) {
			try {
				String str_in = input.readUTF();
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
			ss.close();
		}
		catch(Exception e) {
			
		}
		
	}
	
	public static void sendLetters(String letters) {
		try {
			output.writeUTF(letters);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendScore(int s, String words){
		String temp = Integer.toString(s)+","+words;
		try{
			output.writeUTF(temp);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int receiveScore(){
		String temp = "";
		while(temp.compareTo("") == 0) {
			try{
			temp = input.readUTF();
			}
			catch(Exception e){
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
	
	public static void sendResult(String r){
		try{
			output.writeUTF(r);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
}
