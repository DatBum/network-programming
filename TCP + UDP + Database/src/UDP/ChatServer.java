package UDP;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	
	private DatagramSocket serverSocket = null;
	byte[] receiveData = new byte[1024];
	byte[] sendData = new byte[1024];
	InetAddress IPAddress = null;
	int port = -1;
	
	public ChatServer(int port) {
		try {
			serverSocket = new DatagramSocket(port);
			System.out.println("Server is started");
			Thread t1 = new Thread() {
				
				@Override
				public void run() {
					receiveMsg();
				}
			};
			Thread t2 = new Thread() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg();
				}
			};
			t1.start();
			t2.start();
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	public void receiveMsg() {
		try {
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				IPAddress = receivePacket.getAddress();
				port = receivePacket.getPort();
				String str = new String(receivePacket.getData());
				System.out.println(str);
				System.out.print("Server: ");
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	
	public void sendMsg() {
		try {
			Scanner in = new Scanner(System.in);
			while(true) {
				String msg = in.nextLine();
				sendData = ("Server: " + msg).getBytes();
				if(IPAddress != null && port != -1) {
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(sendPacket);
				}
				in.reset();
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {

//		DatagramSocket serverSocket = new DatagramSocket(9876);
//		System.out.println("Server is started");
//		byte[] receiveData = new byte[1024];
//		byte[] sendData = new byte[1024];
//		Scanner in = new Scanner(System.in);
//		while(true) {
//			in = in.reset();
//			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//			serverSocket.receive(receivePacket);
//			InetAddress IPAddress = receivePacket.getAddress();
//			int port = receivePacket.getPort();
//			String str = new String(receivePacket.getData());
//			System.out.println(str);
//			System.out.print("Server: ");
//			String msg = in.nextLine();
//			sendData = ("Server: " + msg).getBytes();
//			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
//			serverSocket.send(sendPacket);
//		}

		new ChatServer(9877);
	}
}
