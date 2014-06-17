package networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;
/**
 * @author Robert
 * send to server data for moving, attacking and etc..
 */
public class ClientSend extends Thread {
	private Socket client = Client.socket;
	private int x1, y1, x2, y2;
	String action;
	public ClientSend(int x1, int y1, int x2, int y2, String action) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.action = action;
	}
	public void run () {
		sendMoveToServer(x1, y1, x2, y2, action);
	}
	
	public void sendMoveToServer(int x1, int y1, int x2, int y2, String action) {
		
		System.out.println("Sending move");
		DataSent data = new DataSent(x1, y1, x2, y2, action);
		Gson gson = new Gson();
		String dataJson = gson.toJson(data);
		ObjectOutputStream oOutputStream;
		try {
			 oOutputStream = new ObjectOutputStream(client.getOutputStream());
			 oOutputStream.writeObject(dataJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
