package networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * @author Robert
 * send to server chat message
 */
public class ClientSendMessage extends Thread {

	private String message;
	private Socket client = Client.socket;
	public ClientSendMessage(String message) {
		this.message = message;
	}
	
	public void run () {
		String username = Client.me.getPlayerName();
		System.out.println("Sending message");
		MessagePacket data = new MessagePacket(message, username);
		ObjectOutputStream oOutputStream;
		try {
			 oOutputStream = new ObjectOutputStream(client.getOutputStream());
			 oOutputStream.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
