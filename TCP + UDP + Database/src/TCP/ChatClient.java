package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("localhost",7000);
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream din = new DataInputStream(socket.getInputStream());
		Scanner kb = new Scanner(System.in);
		while (true) {
			System.out.print("Client:");
			String msg = kb.nextLine();
			dos.writeUTF("Client:" +msg);
			dos.flush();
			String st = din.readUTF();
			System.out.println(st);
			kb = kb.reset();
		}
	}
}
