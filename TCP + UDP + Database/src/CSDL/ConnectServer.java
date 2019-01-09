package CSDL;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectServer {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(7000);
		System.out.println("Server is started");
		Socket socket = server.accept();
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		while(true) {
			String str = dis.readUTF();
			System.out.println(str);
			try {
				String msg = "";
				Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/laptop88/Desktop/Work/Coding Document/Code Java/LapTrinhMang/src/CSDL/Khachhang.accdb");
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(str);
				while(rs.next()) {
					if (str.contains("Id")) {
						int id = rs.getInt("Id");
						msg += "ID="+id+" ";
					}
					if (str.contains("Luong")) {
						double luong = rs.getDouble("Luong");
						msg += "Luong="+luong+" ";
					}
					if (str.contains("TenKH")) {
						String s = rs.getString("TenKH");
						msg += "TenKH="+s+" ";
					}
					if (str.contains("Diachi")) {
						String d = rs.getString("Diachi");
						msg += "Diachi="+d+" ";
					}
					msg += "\n";  
				}
				dos.writeUTF(msg);
				dos.flush();
			}
			catch (Exception e) {
				System.out.println(e);
				dos.writeUTF("Cau lenh sai cu phap");
				dos.flush();
				server.close();
			}
		}
	}
}
