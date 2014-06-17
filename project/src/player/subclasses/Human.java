package player.subclasses;

import player.Player;

/**
 * The player type Human
 * @author Matt
 *
 */
@SuppressWarnings("serial")
public class Human extends Player {

	/**
	 * Constructor to create player. This adds the player to the team specified
	 * 
	 * @param team
	 *            : Teams - the Teams object
	 * @param teamOfPlayer
	 *            : Teams - the team (e.g. Teams.GREEN)
	 * @param playerName
	 *            : String - the players name
	 */
	public Human(String playerName) {
		super(playerName);
	}


}
