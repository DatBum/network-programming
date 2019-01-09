package UDP;

import java.io.*;
import java.net.*;
import java.util.*;

public class XuLyChuoiServer {
	public static String chuoihoa(String s) {
		return s.toUpperCase();
	}
	public static String hoathuong(String s) {
		char[] sc = s.toCharArray(); 
        String s1 = ""; 
        for(int i = 0; i < sc.length; i++) 
        { 
            int ascii = sc[i]; 
            if(ascii >= 65 && ascii <= 90) 
            { 
                s1 += (char)(ascii += 32); 
            } 
            else if(ascii >= 97 && ascii <= 122) 
            { 
                s1 += (char)(ascii -= 32); 
            } 
            else 
            { 
                s1 += (char)ascii; 
            } 
        } 
        return s1;
	}
	public static String nguyenam(String s) {
		String s1="";
		if (s.contains("u") || s.contains("U")) {
			s1 += "u ";
		}
		if (s.contains("e") || s.contains("E")) {
			s1 += "e ";
		}
		if (s.contains("i") || s.contains("I")) {
			s1 += "i ";
		}
		if (s.contains("o") || s.contains("O")) {
			s1 += "o ";
		}
		if (s.contains("a") || s.contains("A")) {
			s1 += "a ";
		}
		return s1;	
	}
	public static int demtu(String s) {
		if (s == null) {
            return -1;
        }
        int count = 0;
        int size = s.length();
        boolean notCounted = true;
        for (int i = 0; i < size; i++) {
            if (s.charAt(i) != ' ' && s.charAt(i) != '\t' && s.charAt(i) != '\n') {
                if(notCounted) {
                    count++;
                    notCounted = false;
                }
            } else {
                notCounted = true;
            }
        }
        return count;
	}
	public static void main(String[] args) throws Exception {
		//Gan cong 9876 cho chuong trinh
		DatagramSocket serverSocket = new DatagramSocket(9876);
		//Tao cac mang byte de chua dl gui va nhan
		System.out.println("Server is started");
		byte[] receiveData = new byte[1024];
		//byte[] sendData = new byte[1024];
		StringBuffer rs = new StringBuffer();
		String[] response = new String[4];
		while(true) {
			//Tao goi rong de nhan du lieu tu client
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//Nhan du lieu tu client
			serverSocket.receive(receivePacket);
			//Lay dia chi IP cua may client
			InetAddress IPAddress = receivePacket.getAddress();
			//Lay port cua chuong trinh client
			int port = receivePacket.getPort();
			//Lay ngay gio de gui nguoc lai client
			String s = new String(receivePacket.getData());
			System.out.println(s);
			response[0] = chuoihoa(s);
	        response[1] = hoathuong(s);
	        response[2] = nguyenam(s);
	        response[3] = String.valueOf(demtu(s));
	        for(int i = 0; i < 4; i++) {
	        	rs.append(response[i].trim()); //Trim de loai 0 byte, tranh tran sendData
	        	rs.append("\n");
	        }
	        byte[] sendData = new byte[1024]; // Luon luon lam moi sendData
			sendData = rs.toString().getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			//Gui dl lai cho client
			serverSocket.send(sendPacket);
		}
	}
}
