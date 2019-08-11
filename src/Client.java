import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

public class Client implements Runnable {
	
	Socket s;
	
	DataOutputStream output;
	DataInputStream input;
	
	JFrame frame;
	
	boolean isRunning = false;
	boolean connected = false;
	
	GameBoard gb;
	
	public Client() {
		try {
			System.out.println("Starting up client");
			s = new Socket("127.0.0.1", 3000);
			output = new DataOutputStream(s.getOutputStream());
			input = new DataInputStream(s.getInputStream());
			new Thread(this).start();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
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
