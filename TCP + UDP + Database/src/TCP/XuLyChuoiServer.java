package TCP;

import java.io.*;
import java.net.*;

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
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(6999);
		System.out.println("Server is started!");
		Socket socket = server.accept();
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		while (true) {
			String s = din.readUTF();
			dos.writeUTF("Chuoi hoa cua chuoi da cho: "+chuoihoa(s)+
					"\nChuoi vua hoa vua thuong: "+hoathuong(s)+ 
					"\nCac tu nguyen am co trong chuoi da cho: "+nguyenam(s)+
					"\nSo tu co trong chuoi da cho: "+demtu(s));
		}
	}
}
