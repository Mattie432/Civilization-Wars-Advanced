package player;

import game.Game;
import game.Teams;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Class for the player object
 * @author Dhruvil, Robert, Matt
 */
@SuppressWarnings("serial")
public class Player implements Serializable {

    // Starting money ammount
    public int money = 500;
    private String playerName;
    private String ip;
    protected Game g;
    private boolean eliminated;

    /**
     * Constructor for class.
     * @param playerName : String - the name of the player
     */
    public Player(String playerName) {
        this.playerName = playerName;
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        eliminated = false;
    }

    /**
     * Gets the name of the player
     * @return name : String - the player name
     */
    public String getPlayerName() {
        return playerName;
    }
    
    /**
     * Sets the name of the player
     * @param playerName : String - the player name
     */
    public void setPlayerName(String playerName) {
    	this.playerName = playerName;
    }

    /**
     * Gets the players current money.
     * @return money : Int - the players money
     */
    public int getMoney() {
        updateMoney();
        return money;
    }

    /**
     * Adds the specified value to the players money
     * @param valueToAdd : Int - the ammount to add
     */
    public void addMoney(int valueToAdd) {
        money += valueToAdd;
        updateMoney();
    }

    /**
     * remove teh specified ammount from teh players money
     * @param valueToSub : Int - the ammount to remove
     */
    public void subMoney(int valueToSub) {
        money -= valueToSub;
        updateMoney();
    }

    /**
     * Gets the ip address of the player
     * @return ip : String - the players IP adderss
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Sets this players game object
     * @param game : Game - the current game object
     */
    public void setGame(Game game) {
        this.g = game;
    }

    /**
     * Checks if the player can affod the ammount specified.
     * @param price : Int - the cost
     * @return answer : Boolean - if player can afford it
     */
    public boolean canAfford(int price) {
        int newMoney = money - price;
        return newMoney >= 0;
    }

    /**
     * Updates the top user interface with the players money.
     */
    public void updateMoney() {
        Player teamsMe = Teams.getPlayersOfTeam(Teams.getMe());
		if (Teams.comparePlayers(teamsMe, this)) {
            g.uiInterfaceTop.setPlayerMoney(money);
        }
    }
    
    /**
     * Gets the eliminated status of the player
     * @return answer : Boolean - eliminated status
     */
    public boolean getEliminated() {
        return eliminated;
    }
    
    /**
     * Sets the eliminated status of the player
     * @param eliminated : Boolean - the eliminated status
     */
    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }
}
