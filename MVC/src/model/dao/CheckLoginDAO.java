package model.dao;

import java.util.ArrayList;
import java.sql.*;
import javax.sql.*;

import model.bean.Wife;

public class CheckLoginDAO {

	public boolean isExistUser(String userName, String password){
		int dem = 0;
		try {
		// Connect vao Database lam mo so viec
		// Tat ca nhung cau SQL deu phai dat o DAO
		Connection conn = null;
		String msAccDB = "C:/Users/laptop88/Desktop/Work/Coding Document/Code Java/DangNhap/WebContent/dangnhap.accdb";
		String dbURL = "jdbc:ucanaccess://"+ msAccDB; 
		conn = DriverManager.getConnection(dbURL);
		Statement stmt = conn.createStatement();
		String sql = "SELECT username,password FROM Table1";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			String user = rs.getString("username");
			String pass = rs.getString("password");
			if (user.equals(userName) && (pass.equals(password))) {
				dem++;
			}
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		if (dem==0) return false;
		return true;
	}

	public ArrayList<Wife> getWifeList(String userName) {
		ArrayList<Wife> result = new ArrayList<Wife>();
		// Connect vao Database lam mo so viec
		Wife wife = new Wife();
		wife.setName("Nguyen Thi No");
		wife.setAddress("Lò vôi");
		wife.setAlive(false);
		result.add(wife);
		
		wife = new Wife();
		wife.setName("Tran Van Tam");
		wife.setAddress("Ho ca");
		wife.setAlive(true);
		result.add(wife);
		
		return result;
	}
	
}
