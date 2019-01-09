package UDP;

import java.io.*;
import java.net.*;

public class NgayThangNamClient {
	public static void main(String[] args) throws Exception{
		DatagramSocket clientSocket = new DatagramSocket();//gan cong
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		sendData = "getDate".getBytes();
		//tao datagram co noi dung yeu cau loai dl de gui cho server
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);//gui dl cho server
		//tao datagram rong de nhan dl
		DatagramPacket receivePacket = new DatagramPacket (receiveData, receiveData.length);
		//nhan dl tu server
		clientSocket.receive(receivePacket);
		//lay du lieu tu packet nhan duoc
		String str = new String(receivePacket.getData());
		System.out.println(str);
		clientSocket.close();
	}
}
