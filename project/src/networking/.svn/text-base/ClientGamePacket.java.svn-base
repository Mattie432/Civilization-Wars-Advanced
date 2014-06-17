package networking;

import game.GameType;

import java.io.Serializable;
import java.util.LinkedList;

import player.Player;
/**
 * @author Robert
 * serializable object with map details
 */
@SuppressWarnings("serial")
public class ClientGamePacket implements Serializable {
	int startGame;
	String mapName;
	GameType gameType;
	LinkedList<Player> players;
	
	public ClientGamePacket(int startGame, String mapName, GameType gameType, LinkedList<Player> players) {
		this.startGame = startGame;
		this.mapName = mapName;
		this.gameType = gameType;
		this.players = players;
	}
}
