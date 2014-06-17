package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

import player.Player;

/**
 * Teams enum containing all information about the teams in the game. This class
 * is static so there is no need to create an object.
 * 
 * @author Matt
 */
public enum Teams {
	GREEN, BLUE, RED, ORANGE;

	private static int numAiPlayers = 0;
	private static Teams me;
	private static Player greenMembers;
	private static Player blueMembers;
	private static Player redMembers;
	private static Player orangeMembers;

	private static HashSet<Player> greenFriends = new HashSet<Player>();
	private static HashSet<Player> blueFriends = new HashSet<Player>();
	private static HashSet<Player> redFriends = new HashSet<Player>();
	private static HashSet<Player> orangeFriends = new HashSet<Player>();

	/**
	 * Retruns the friends of the team specified. These teams are working
	 * together.
	 * 
	 * @param team
	 *            : Teams - the team to get friends of
	 * @return friends : HashSet<Player> of Player
	 */
	public static HashSet<Player> getTeamFriends(Teams team) {
		if (team == Teams.BLUE) {
			return blueFriends;
		} else if (team == Teams.GREEN) {
			return greenFriends;
		} else if (team == Teams.ORANGE) {
			return orangeFriends;
		} else if (team == Teams.RED) {
			return redFriends;
		} else {
			return null;
		}
	}

	/**
	 * Returns the color of the team specified.
	 * 
	 * @param team
	 *            : Teams - the team to get the color of (e.g. Teams.BLUE)
	 * @return color : Color - the colour ascociated with that team
	 */
	public static Color getTeamColor(Teams team) {
		if (team == Teams.BLUE) {
			return Color.BLUE;
		} else if (team == Teams.GREEN) {
			return Color.GREEN;
		} else if (team == Teams.ORANGE) {
			return Color.ORANGE;
		} else if (team == Teams.RED) {
			return Color.RED;
		} else {
			return null;
		}
	}

	/**
	 * Sets the friends of the team specified. <b>Remember to set the same for
	 * the other player</b>
	 * 
	 * @param team
	 *            : Teams - the team to set the friend of
	 * @param player
	 *            : Player - the player to be friends with
	 */
	public static void setTeamFriends(Teams team, Player player) {
		if (team == Teams.BLUE) {
			blueFriends.add(player);
		} else if (team == Teams.GREEN) {
			greenFriends.add(player);
		} else if (team == Teams.ORANGE) {
			orangeFriends.add(player);
		} else if (team == Teams.RED) {
			redFriends.add(player);
		} else {
			System.out.println("Team not found when adding team friends!");
		}
	}

	/**
	 * Adds the player to the next available team. This should be used when
	 * players are not choosing specific teams.
	 * 
	 * @param player
	 *            : Player - the player to add to the team
	 */
	public static void addToEmptyTeam(Player player) {
		if (blueMembers == null) {
			blueMembers = player;
			blueFriends.add(player);
		} else if (greenMembers == null) {
			greenMembers = player;
			greenFriends.add(player);
		} else if (orangeMembers == null) {
			orangeMembers = player;
			orangeFriends.add(player);
		} else if (redMembers == null) {
			redMembers = player;
			redFriends.add(player);
		} else {
			System.out.println("ERROR: Could not assing " + player.toString()
					+ " to an empty team.");
		}
	}

	/**
	 * Gets the players of a team.
	 * 
	 * @param team
	 *            : Teams - the team to get the players of
	 * @return player : Player - that teams player
	 */
	public static Player getPlayersOfTeam(Teams team) {
		switch (team) {
		case GREEN:
			return greenMembers;
		case RED:
			return redMembers;
		case ORANGE:
			return orangeMembers;
		case BLUE:
			return blueMembers;
		default:
			return null;
		}
	}

	/**
	 * Adds the players to teh specified team.
	 * 
	 * @param team
	 *            : Teams - the team to add to.
	 * @param player
	 *            : Player - the player to add.
	 */
	public static void addToTeam(Teams team, Player player) {
		switch (team) {
		case GREEN:
			greenMembers = (player);
			greenFriends.add(player);
			break;
		case BLUE:
			blueMembers = (player);
			blueFriends.add(player);
			break;
		case RED:
			redMembers = (player);
			redFriends.add(player);
			break;
		case ORANGE:
			orangeMembers = (player);
			orangeFriends.add(player);
			break;
		}
	}

	/**
	 * Gets the number of AI opponents
	 * 
	 * @return num : int - the number of AI
	 */
	public static int getNumAiPlayers() {
		return numAiPlayers;
	}

	/**
	 * Increases the number of AI players by one.
	 */
	public static void addAiPlayer() {
		numAiPlayers += 1;
	}

	/**
	 * Resets the team information to default.
	 */
	public static void resetTeams() {
		greenMembers = null;
		redMembers = null;
		orangeMembers = null;
		blueMembers = null;
		me = null;
		numAiPlayers = 0;
	}

	/**
	 * Gets the team of the player specified.
	 * 
	 * @param player
	 *            : Player - the player to get the team of
	 * @return team : Teams - the team of the player
	 */
	public static Teams getTeamOfPlayer(Player player) {
		if (greenMembers != null
				&& greenMembers.getPlayerName().equals(player.getPlayerName())) {
			return Teams.GREEN;
		} else if (redMembers != null
				&& redMembers.getPlayerName().equals(player.getPlayerName())) {
			return Teams.RED;
		} else if (blueMembers != null
				&& blueMembers.getPlayerName().equals(player.getPlayerName())) {
			return Teams.BLUE;
		} else if (orangeMembers != null
				&& orangeMembers.getPlayerName().equals(player.getPlayerName())) {
			return Teams.ORANGE;
		} else {
			return null;
		}
	}

	/**
	 * Gets the team of the player specified.
	 * 
	 * @param String
	 *            : Player - the name of the player to get the team of
	 * @return team : Teams - the team of the player
	 */
	public static Teams getTeamOfPlayer(String player) {
		if (greenMembers != null && greenMembers.getPlayerName() == (player)) {
			return Teams.GREEN;
		} else if (redMembers != null && redMembers.getPlayerName() == (player)) {
			return Teams.RED;
		} else if (blueMembers != null
				&& blueMembers.getPlayerName() == (player)) {
			return Teams.BLUE;
		} else if (orangeMembers != null
				&& orangeMembers.getPlayerName() == (player)) {
			return Teams.ORANGE;
		} else {
			return null;
		}
	}

	/**
	 * Returns the team that I am on. (Me being this current player)
	 * 
	 * @return
	 */
	public static Teams getMe() {
		return me;
	}

	/**
	 * Sets the team that i am currently on.
	 * 
	 * @param me
	 *            : Teams - team to joins
	 */
	public static void setMe(Teams me) {
		Teams.me = me;
	}

	/**
	 * Gets the enemies of the specified player.
	 * 
	 * @param player
	 *            : Player - the player to get the enemies of
	 * @return enemies : ArrayList<Player> - arraylist of enemies of the player
	 */
	public static ArrayList<Player> getEnemies(Player player) {
		ArrayList<Player> emenies = new ArrayList<Player>();

		if (!getTeamFriends(Teams.getTeamOfPlayer(player)).contains(
				Teams.getPlayersOfTeam(BLUE))
				&& Teams.getPlayersOfTeam(BLUE) != null) {
			// not friends with blue
			emenies.add(Teams.getPlayersOfTeam(BLUE));
		}
		if (!getTeamFriends(Teams.getTeamOfPlayer(player)).contains(
				Teams.getPlayersOfTeam(GREEN))
				&& Teams.getPlayersOfTeam(GREEN) != null) {
			// not friends with blue
			emenies.add(Teams.getPlayersOfTeam(GREEN));
		}
		if (!getTeamFriends(Teams.getTeamOfPlayer(player)).contains(
				Teams.getPlayersOfTeam(ORANGE))
				&& Teams.getPlayersOfTeam(ORANGE) != null) {
			// not friends with blue
			emenies.add(Teams.getPlayersOfTeam(ORANGE));
		}
		if (!getTeamFriends(Teams.getTeamOfPlayer(player)).contains(
				Teams.getPlayersOfTeam(RED))
				&& Teams.getPlayersOfTeam(RED) != null) {
			// not friends with blue
			emenies.add(Teams.getPlayersOfTeam(RED));
		}
		return emenies;
	}

	public static ArrayList<Player> getAllPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();

		if (blueMembers != null) {
			players.add(blueMembers);
		}
		if (greenMembers != null) {
			players.add(greenMembers);
		}
		if (orangeMembers != null) {
			players.add(orangeMembers);
		}
		if (redMembers != null) {
			players.add(redMembers);
		}
		return players;
	}

	public static boolean comparePlayers(Player p1, Player p2) {
		return p1.getPlayerName().equals(p2.getPlayerName());
	}
}
