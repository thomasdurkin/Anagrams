import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client implements Runnable {
	
	Socket s;
	
	DataOutputStream output;
	DataInputStream input;
	
	JFrame frame;
	
	boolean isRunning = false;
	boolean connected = false;
	
	String ip;
	
	GameBoard gb;
	
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
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
