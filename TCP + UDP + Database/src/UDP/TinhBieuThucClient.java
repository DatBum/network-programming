package UDP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TinhBieuThucClient {
	public static void main(String[] args) throws Exception{
		DatagramSocket clientSocket = new DatagramSocket();//gan cong
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		Scanner in = new Scanner(System.in);
		System.out.print("Client: ");
		String msg = in.nextLine();
		sendData = (msg).getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String str = new String(receivePacket.getData());
		System.out.println(str);
	}
}
