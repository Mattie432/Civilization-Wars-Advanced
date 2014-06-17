package building;

import game.Game;
import player.Player;
import map.Cell;
import map.terrain.subclasses.Flat;
import unit.Unit;

/**
 * Class for the constructor and methods of a Building
 * @author Luke, Matt
 */
public abstract class Building {

	protected String name;
	protected int currentHealth;
	protected int baseHealth;
	protected int reward;
	protected Cell cell;
	protected Player owner;
	protected Game game;
	protected String imageLocation;

	/**
	 * Constructor for class. Create new building
	 * 
	 * @param name
	 *            : String - building name (e.g. base)
	 * @param health
	 *            : Int - base health
	 * @param cost
	 *            : Int - base cost
	 * @param reward
	 *            : Int - reward ammount
	 * @param cell
	 *            : Cell - the cell the building is in
	 * @param owner
	 *            : Player - the buildings owner
	 * @param game
	 *            : Game - the game object.
	 */
	public Building(String name, int health, int cost, int reward, Cell cell,
			Player owner, Game game) {
		this.name = name;
		this.owner = owner;
		currentHealth = health;
		baseHealth = health;
		this.reward = reward;
		this.cell = cell;
		this.game = game;
	}

	/**
	 * Abstract method used to determine what happens when a building is destroyed.
	 */
	public abstract void onDestroyEvent();

	/**
	 * Places the building in the cell specified.
	 * 
	 * @param cell
	 *            : Cell - the cell to set the buildings loaction to.
	 */
	public void placeBuilding(Building building, Cell cell) {
		if (cell.getTerrain() instanceof Flat) {
			cell.building = building;
			building.cell = cell;
			cell.setBuilding(this);
			game.mapPanel.repaint();
		}
	}

	/**
	 * Method to call when the building gets attacked by a unit.
	 * 
	 * @param unit
	 *            : Unit - the unit attacking this building
	 */
	public void attacked(Unit unit) {
		this.currentHealth = this.currentHealth - unit.getBaseAttackDamage();
		if (this.getCurrentHealth() <= 0) {
			this.getCell().building = null;
			// System.out.println("Building Destroyed");
			unit.owner.addMoney(this.reward);
			// System.out.println(unit.owner.getMoney());

			onDestroyEvent();

		}
		// System.out.println(this.getCurrentHealth());
	}

	/**
	 * Getter method for current health
	 * 
	 * @return health : int - current health
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}

	/**
	 * Getter method for base health
	 * 
	 * @return health : int - base health
	 */
	public int getBaseHealth() {
		return baseHealth;
	}

	/**
	 * Getter method for the reward ammount
	 * 
	 * @return reward : int - reward ammount
	 */
	public int getReward() {
		return reward;
	}

	/**
	 * Getter method for this buildings cell.
	 * 
	 * @return cell : Cell - the cell of the building
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * Getter method for this buildings name
	 * 
	 * @return name : String - name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for the buildings owner.
	 * 
	 * @return owner : Player - the player who owns the building
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
     * Gets the image location of the unit.
     *
     * @return imageLocaton : String - image location
     */
    public String getImageLocation() {
        return imageLocation;
    }
    
    /**
     * Gets the image location of the unit.
     *
     * @return imageLocaton : String - image location
     */
    public void setImageLocation(String loc) {
        this.imageLocation = loc;
    }

}
