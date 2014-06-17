package networking;


import game.GameType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;

import player.Player;
/**
 * @author Robert
 * server object
 */
public class Server {
	//server variables
	public static int port = 4567;
	public static ServerSocket server;

	public static int startGame = 0;	
	public static DefaultListModel<String> list_clients_model =  new DefaultListModel<String>();
	
	public static ArrayList<ServerListen> clientThreads = new ArrayList<ServerListen>();
	public static LinkedList<Player> players = new LinkedList<Player>();
	static AcceptConnections acceptConnections;
	/**
	 * start server function
	 */
	public void startServer()  {
		try {
			server = new ServerSocket(port, 0, InetAddress.getLocalHost());	
			System.out.println("Server started!");
			acceptConnections = new AcceptConnections();
			acceptConnections.start();
		} catch (IOException e) {
			System.out.println("Error during server start!");
		}
	}
	
	//invoke that method when server is full and ready to start.
	/**
	 * invoke that method when server is full and ready to start
	 * @param startGame 
	 */
	@SuppressWarnings("static-access")
	public void setStartGame(int startGame) {
		this.startGame = startGame;
	}
	
	/**
	 * stop server 
	 * @throws IOException
	 */
	public static void stopServer() throws IOException {
		acceptConnections.stopThread();
		server.close();
		
	}
	/**
	 * disconnect a client given a thread
	 * @param serverListen client thread
	 */
	public static void disconnectClient(ServerListen serverListen) {
		try {
			list_clients_model.removeElement(serverListen.client.getPlayerName() + " - " + serverListen.clientIp);
			serverListen.stopThread();
			clientThreads.remove(serverListen);
			players.remove(serverListen.client);
			System.out.println("disconnecting client");
		}
		catch (Exception ex) {
			System.out.println("error disconnecting client");
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		final Server server = new Server();
		server.startServer();
	}
	/**
	 * start game 
	 * @param mapNameHost game variable
	 * @param gameTypeEnumHost game variable
	 * @param playerOrder game variable
	 */
	public void startGame(String mapNameHost, GameType gameTypeEnumHost, LinkedList<Player> playerOrder) {
		ObjectOutputStream oOutputStream;
		int gameState = 1;
		ServerListen client;
		Socket socket;
		ClientGamePacket clientGamePacket;
		for (int i = 0; i < clientThreads.size(); i++) {
			client = clientThreads.get(i);
			socket= client.connection;
			try {
				clientGamePacket = new ClientGamePacket(gameState, mapNameHost, gameTypeEnumHost, playerOrder);	
				oOutputStream = new ObjectOutputStream(socket.getOutputStream());
				oOutputStream.writeObject(clientGamePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * notify a client for game state
	 * @param gameState game state
	 * @param index index for client to know notification type
	 */
	public static void notifyClient(int gameState, int index) {
		ObjectOutputStream oOutputStream;
		ServerListen client;
		Socket socket;
		client = clientThreads.get(index);
		socket= client.connection;
		try {
			oOutputStream = new ObjectOutputStream(socket.getOutputStream());
			oOutputStream.writeObject(gameState);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//disconnectClient(clientThreads.get(index));
	}
}
