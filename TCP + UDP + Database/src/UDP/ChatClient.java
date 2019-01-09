package UDP;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
	
	private DatagramSocket clientSocket = null;
	InetAddress IPAddress = null;
	byte[] receiveData = new byte[1024];
	byte[] sendData = new byte[1024];
	int port = 9877;
	
	public ChatClient(int port) {
		try {
			clientSocket = new DatagramSocket();
			IPAddress = InetAddress.getByName("localhost");

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
				clientSocket.receive(receivePacket);
				String str = new String(receivePacket.getData());
				System.out.println(str);
				System.out.print("Client: ");
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
				sendData = ("Client: " + msg).getBytes();
				if(IPAddress != null) {
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					clientSocket.send(sendPacket);
				}
				in.reset();
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	
	public static void main(String[] args) throws Exception{
//		DatagramSocket clientSocket = new DatagramSocket();
//		InetAddress IPAddress = InetAddress.getByName("localhost");
//		byte[] receiveData = new byte[1024];
//		byte[] sendData = new byte[1024];
//		Scanner in = new Scanner(System.in);
//		while(true) {
//			in = in.reset();
//			System.out.print("Client: ");
//			String msg = in.nextLine();
//			sendData = ("Client: " + msg).getBytes();
//			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
//			clientSocket.send(sendPacket);
//			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//			clientSocket.receive(receivePacket);
//			String str = new String(receivePacket.getData());
//			System.out.println(str);
//
//		}

		new ChatClient(9877);
	}
}
