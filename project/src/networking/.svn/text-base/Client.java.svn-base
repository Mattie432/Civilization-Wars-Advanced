package networking;

import game.Game;
import game.GameType;

import java.awt.CardLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import player.Player;
import userInterface.gameInterface.MapPanel;
import userInterface.gameInterface.MapPanel_MouseListener;
import userInterface.menus.MainMenu;

import com.google.gson.Gson;
/**
 * @author Robert
 * Client class
 */
public class Client extends Thread{
	//declaring variables
	public static Socket socket;
	public static String serverIp = "";	
	public static int serverPort;
	public static String username = "";
	public static Player me;
	private Game g;
	private String mapName;
	private GameType gameType;
	private LinkedList<Player> players;
	private int startGame = 0;
	private MainMenu menu;
	private boolean stopThread;
	/**
	 * Constructor for client class
	 * @param serverIp: ip of server that client is connecting to
	 * @param serverPort: port of server that client is connecting to
	 * @param username: username of the client
	 */
	@SuppressWarnings("static-access")
	public Client(String serverIp, int serverPort, String username) {
		this.serverIp = serverIp;
		this.serverPort	= serverPort;
		this.username = username;
		this.me = new Player(username);
	}
	/**
	 * Constructor for client class
	 * @param serverIp: ip of server that client is connecting to
	 * @param serverPort: port of server that client is connecting to
	 * @param username: username of the client
	 * @param menu: MainMenu object
	 * 	 */
	@SuppressWarnings("static-access")
	public Client(String serverIp, int serverPort, String username, MainMenu menu) {
		this.serverIp = serverIp;
		this.serverPort	= serverPort;
		this.username = username;
		this.me = new Player(username);
		this.menu = menu;
	}
	/**
	 * Function to connect to the server once the client is initialized
	 */
	public void connectToServer() {
		try {
			ObjectOutputStream oOutputStream;
			ObjectInputStream oInputStream;
			System.out.println("Client: Connecting to server with ip: " + serverIp + " and port: " + serverPort);
			socket = new Socket(serverIp, serverPort);
			
			oOutputStream = new ObjectOutputStream(socket.getOutputStream());
			oOutputStream.writeObject(me);
			
			oInputStream = new ObjectInputStream(socket.getInputStream());
			me.setPlayerName((String) oInputStream.readObject());
			username = me.getPlayerName();
		} catch (IOException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Sending move to server
	 * @param x1 coordinate
	 * @param y1 coordinate
	 * @param x2 coordinate
	 * @param y2 coordinate
	 * @param action type of action (attack, move, spawn)
	 */
	public void sendMoveToServer(int x1, int y1, int x2, int y2, String action) {
		ClientSend sendMove = new ClientSend(x1, y1, x2, y2, action);
		sendMove.start();
	}
	/**
	 * Sending chat message
	 * @param message message to send
	 */
	public void sendMessage(String message) {
		ClientSendMessage sendMessage = new ClientSendMessage(message);
		sendMessage.start();
	}
	/**
	 * End of turn function
	 * @param endTurn true/false
	 */
	public void endTurn(boolean endTurn) {
		ClientEndTurn clientEndTurn = new ClientEndTurn(endTurn);
		clientEndTurn.start();
	}
	
	@SuppressWarnings("static-access")
	public void run() {
		ObjectInputStream oInputStream;
		//listening for server data
		while(true && stopThread == false) {
			try {
				oInputStream = new ObjectInputStream(socket.getInputStream());
				Object recievedObject = oInputStream.readObject();
				if(recievedObject instanceof ClientGamePacket) {
					System.out.println("Reading and creating a game object");
					ClientGamePacket clientGamePacket = (ClientGamePacket) recievedObject;
					startGame = clientGamePacket.startGame;
					mapName = clientGamePacket.mapName;
					gameType = clientGamePacket.gameType;
					players = clientGamePacket.players;
					if(startGame == 1) {
						g = new Game(mapName, gameType, players, me);
					}
				} else if(recievedObject instanceof String && startGame == 1) {
					System.out.println("recieved state from server");
					String dataString = (String) recievedObject;
					Gson gson = new Gson();
					DataSent data = gson.fromJson(dataString, DataSent.class);
					MapPanel_MouseListener listener = MapPanel.listener;
					int x1 = data.x1;
					int x2 = data.x2;
					int y1 = data.y1;
					int y2 = data.y2;
					String action = data.action;
					System.out.println("Server recieved action:" + action);
					if(action.equals("moveUnit")) {
						listener.moveUnit(x1, y1, x2, y2);
					} else if(action.equals("attackUnit")) {
						listener.attackUnit(x1, y1, x2, y2);
					} else if(action.equals("attackBuilding")) {
						listener.attackBuilding(x1, y1, x2, y2);
					} else if(action.equals("buildBarracks")) {
						listener.buildBarracks(x1, y1, x2, y2);
					} else if(action.equals("buildFactory")) {
						listener.buildFactory(x1, y1, x2, y2);
					} else if(action.equals("recruitMarine")) {
						g.uiInterfaceBottom.recruitMarine(x1, y1);
					} else if(action.equals("recruitTank")) {
						g.uiInterfaceBottom.recruitTank(x1, y1);
					} 
					
				} else if(recievedObject instanceof MessagePacket) {
					MessagePacket messagePacket = (MessagePacket) recievedObject;
					String username = messagePacket.username;
					String message = messagePacket.message;	
					if(menu.client != null && menu.server != null) {
						menu.serverChatMessagesText.append(username + ": "+ message + "\n");
					} else {
						menu.userChatMessagesTextArea.append(username + ": "+ message + "\n");
					}
					
				} else if (recievedObject instanceof EndTurnPacket){
					EndTurnPacket endTurnPacket = (EndTurnPacket) recievedObject;
					boolean endTurn = endTurnPacket.endTurn;
					if(endTurn) {
						g.turnFinshed();
					}
				} else if(recievedObject instanceof Integer) {
					int gameState = (Integer) recievedObject;
					if(gameState == 3) { //kicked
						
						MainMenu.cl = (CardLayout) (MainMenu.CardPanel.getLayout());
						MainMenu.cl.show(MainMenu.CardPanel, "MainMenu");
						MainMenu.frame.setSize(MainMenu.standardDimension);
						disconnect();
						if(g != null) {
							g.mapPanel.Notify("You have been disconnected");
						}
					}
				}
				
			} catch (ClassNotFoundException e1) {		
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * Disconnect from server
	 */
	public void disconnect() {
		try {
			stopThread = true;
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
