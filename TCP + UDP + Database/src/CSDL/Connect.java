package CSDL;

import java.sql.*;
//import javax.sql.*;

public class Connect {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		System.out.println("Ket noi CSDL");
		try {
//			Class.forName(“sun.jdbc.odbc.JdbcOdbcDriver”);
//			Connection con=DriverManager.getConnection("jdbc:odbc:csdl");
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/khachhang", "root", "");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/laptop88/Desktop/Work/Coding Document/Code Java/LapTrinhMang/src/CSDL/Khachhang.accdb");
			Statement stmt = conn.createStatement();
			//Them record vao bang
//			String sql21 = "INSERT INTO Table1(Id,TenKH,DiaChi,Luong) VALUES('4','MaiMinh','ABC','123')";
//			stmt.executeUpdate(sql21);
			//Cap nhat du lieu (Tang luong cho cac cong nhan)
//			String sql22 = "UPDATE Table1 SET Luong = Luong+Luong*0.1";
//			stmt.executeUpdate(sql22);
			String sql32 = "DELETE from Table1 where ID=11";
			stmt.executeUpdate(sql32);
			//Doc Record ra mh
			String sql33 = "SELECT Id,TenKH,Diachi,Luong FROM Table1";
			ResultSet rs = stmt.executeQuery(sql33);
			while (rs.next()) {
				int id = rs.getInt("Id");
				double luong = rs.getDouble("Luong");
				String s = rs.getString("TenKH");
				String d = rs.getString("Diachi");
				System.out.println("ID="+id+" TenKH="+s+" Diachi="+d+" Luong="+luong);
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
}
