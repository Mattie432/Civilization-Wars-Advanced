package networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * @author Robert
 * send to server that client has ended his turn
 */
public class ClientEndTurn extends Thread {
	private Socket client = Client.socket;
	public boolean endTurn = false;
	public ClientEndTurn(boolean endTurn) {
		this.endTurn = endTurn;
	}
	public void run() {
		System.out.println("Sending end turn");
		EndTurnPacket endTurnPacket = new EndTurnPacket(endTurn);
		ObjectOutputStream oOutputStream;
		try {
			 oOutputStream = new ObjectOutputStream(client.getOutputStream());
			 oOutputStream.writeObject(endTurnPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
