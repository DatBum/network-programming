package CSDL;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ConnectClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 7000);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print("Cau lenh SQL: ");
			String msg = in.nextLine();
			dos.writeUTF(msg);
			dos.flush();
			String str = dis.readUTF();
			System.out.println(str);
			in = in.reset();
		}
	}
}
