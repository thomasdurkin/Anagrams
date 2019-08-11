import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Host implements Runnable{
	
	ServerSocket ss;
	Socket s;
	
	DataInputStream input;
	DataOutputStream output;
	
	boolean isRunning = false;
	boolean connected = false;
	
	
	public Host() {
		try {
			System.out.println("Starting up host");
			ss = new ServerSocket(3000);
			s = ss.accept();
			output = new DataOutputStream(s.getOutputStream());
			input = new DataInputStream(s.getInputStream());
			new GameBoard(true, output, null);
			new Thread(this).start();
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
}
