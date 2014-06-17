package networking;
/**
 * @author Robert
 * Thread for each connected client
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import player.Player;

import com.google.gson.Gson;

public class ServerListen extends Thread {
	Socket connection;
	String username;
	String clientIp;
	boolean isRunning = true;
	Player client;
	/**
	 * 
	 * @param connection
	 */
	public ServerListen(Socket connection) {
		try {
			ObjectInputStream oInputStream;
			ObjectOutputStream oOutputStream;
			this.connection = connection; 
			oInputStream = new ObjectInputStream(connection.getInputStream());
			client = (Player) oInputStream.readObject();
			
			clientIp = connection.getInetAddress().getHostAddress();
			username = client.getPlayerName();
			for (int i = 0; i < Server.players.size(); i++) {
				if(Server.players.get(i).getPlayerName().equals(username)) {
					client.setPlayerName(username + "(1)"); 
				}
			}
		
			Server.players.add(client);
			
			oOutputStream = new ObjectOutputStream(connection.getOutputStream());
			oOutputStream.writeObject(client.getPlayerName());
			
			System.out.println("Server: Client with username:"+ client.getPlayerName() + " connected successfully");
			Server.list_clients_model.addElement(client.getPlayerName() + " - " + clientIp);
			System.out.println("Server: Thread for user " + client.getPlayerName() + " successfully started!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * stop thread from execution
	 */
	public void stopThread() {
		this.isRunning = false;
	}
	/**
	 * listen for client input while the server is running and the user is connected
	 */
	@Override
	public void run() {
		ObjectInputStream oInputStream;
		ObjectOutputStream oOutputStream;
		while(isRunning) {
			try {
				oInputStream = new ObjectInputStream(connection.getInputStream());
				Object recievedData = oInputStream.readObject();
				if(recievedData instanceof String) {
					String dataString = (String) recievedData;
					Gson gson = new Gson();
					DataSent data = gson.fromJson(dataString, DataSent.class);
						
					System.out.println("Server recieved data for current cell:"
							+ "(" + data.x1 + ", " + data.y1 + ") and move to cell:"
						    + "(" + data.x2 + ", " + data.y2 + ")");
					
					for (int i = 0; i < Server.clientThreads.size(); i++) {
						if(Server.clientThreads.get(i).connection != connection) {
							System.out.println("Sending move to client:" + Server.clientThreads.get(i).username);
							oOutputStream = new ObjectOutputStream(
									Server.clientThreads.get(i).connection.getOutputStream());
							oOutputStream.writeObject(dataString);
							System.out.println("Sent!");
						}
					}
				} else if (recievedData instanceof MessagePacket) {
					MessagePacket messagePacket = (MessagePacket) recievedData;
					String username = messagePacket.username;
					String message = messagePacket.message;
					System.out.println("Server recieved chat message "+ message + " from username:" + username);
					for (int i = 0; i < Server.clientThreads.size(); i++) {
						if(Server.clientThreads.get(i).connection != connection) {
							System.out.println("Sending chat message");
							oOutputStream = new ObjectOutputStream(
									Server.clientThreads.get(i).connection.getOutputStream());
							oOutputStream.writeObject(messagePacket);
							System.out.println("Sent!");
						}
					}
				} else if(recievedData instanceof EndTurnPacket) {
					EndTurnPacket endTurnPacket = (EndTurnPacket) recievedData;
					boolean endTurn = endTurnPacket.endTurn;
					System.out.println("Server recieved end turn:" + endTurn);
					for (int i = 0; i < Server.clientThreads.size(); i++) {
						if(Server.clientThreads.get(i).connection != connection) {
							System.out.println("Sending end turn");
							oOutputStream = new ObjectOutputStream(
									Server.clientThreads.get(i).connection.getOutputStream());
							oOutputStream.writeObject(endTurnPacket);
							System.out.println("Sent!");
						}
					}
				}
			} catch (IOException e) {
				//disconnect and delete any clients data.
				System.out.println("Client is disconnected!!");
				Server.disconnectClient(this);
			} catch (ClassNotFoundException e) {}
		
		}
		
	}

}
