import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	
	
	public Client() {
		try {
			s = new Socket("127.0.0.1", 3000);
			output = new DataOutputStream(s.getOutputStream());
			input = new DataInputStream(s.getInputStream());
			new GameBoard();
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
