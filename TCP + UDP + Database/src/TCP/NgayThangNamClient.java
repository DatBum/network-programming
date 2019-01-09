package TCP;

import java.io.DataInputStream;
import java.net.Socket;

public class NgayThangNamClient {
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("localhost",8977);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		String time = din.readUTF();
		System.out.println(time);
	}
}
