package TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TinhBieuThucClient {
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("localhost",7000);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("Client:");
			String s = in.nextLine();
			dos.writeUTF(s);
			dos.flush();
			String s1 = din.readUTF();
			System.out.println(s1);
			in = in.reset();
		}
	}
}
