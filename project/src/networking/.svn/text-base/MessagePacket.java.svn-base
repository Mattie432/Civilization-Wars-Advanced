package networking;
/**
 * @author Robert
 */
import java.io.Serializable;
import java.util.LinkedList;

import player.Player;

@SuppressWarnings("serial")
public class MessagePacket implements Serializable{
	public String message;
	public String username;
	public static LinkedList<Player> players = new LinkedList<Player>();
	@SuppressWarnings("static-access")
	public MessagePacket(String message, String username, LinkedList<Player> players) {
		this.message = message;
		this.username = username;
		this.players = players;
	}
	public MessagePacket(String message, String username) {
		this.message = message;
		this.username = username;
	}
}
