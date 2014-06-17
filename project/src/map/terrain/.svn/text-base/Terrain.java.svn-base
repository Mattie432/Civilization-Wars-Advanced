package map.terrain;

import java.util.List;

import unit.Unit;

/**
 * Abstract class terrain contains information on the terrain of the tile.
 * 
 * @author Matt
 */
public abstract class Terrain {

	protected String name;
	protected String backgroundImageLocation;
	protected int attackBuff;
	protected int moveBuff;
	protected int healthBuff;
	protected boolean allowBuildings;
	protected List<Unit> allowedUnits;

	/**
	 * Gets the attack buff of this terrain. NB: This value can be negative if
	 * the terrain has a debuff!
	 * 
	 * @return attackBuff : int - the attack buff value
	 */
	public int getAttackBuff() {
		return attackBuff;
	}

	/**
	 * Gets the health buff of this terrain. NB: This value can be negative if
	 * the terrain has a debuff!
	 * 
	 * @return healthBuff : int - the health buff value
	 */
	public int getHealthBuff() {
		return healthBuff;
	}

	/**
	 * Gets the move buff of this terrain. NB: This value can be negative if the
	 * terrain has a debuff!
	 * 
	 * @return moveBuff : int - the move buff value
	 */
	public int getMoveBuff() {
		return moveBuff;
	}

	/**
	 * Gets the allow buildings parameter. If this is true then buildings can be
	 * placed on this tile. If not then no buildings can be built (e.g. water
	 * terrains).
	 * 
	 * @return allowBuildings : boolean - are buildings allowed to be built
	 */
	public boolean getAllowBuildings() {
		return allowBuildings;
	}

	/**
	 * Gets the list of allowed units. This is all of the units which ARE
	 * allowed to traverse the terrain. If they are not on this list then they
	 * cannot pass over it (e.g. tanks cannot pass through woods)
	 * 
	 * @return allowedUnits : List<Unit> - the units allowed on the terrain
	 */
	public List<Unit> getAllowedUnits() {
		return allowedUnits;
	}

	/**
	 * Gets the string location of the background image. This is its location
	 * inside of the "img" folder.
	 * 
	 * @return backgroundImageLocation : String - the location of the img
	 */
	public String getBackgroundImageLocation() {
		return backgroundImageLocation;
	}

	/**
	 * Gets the name of the terrain.
	 * 
	 * @return name : String - the name of the terrain.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks this terrain for whether the specified unit can be present on it.
	 * 
	 * @param checkUnit
	 *            : Unit - the unit to check if it can go onto the terrain
	 * @return allowed : Boolean - whether the unit is allowed.
	 */
	public boolean checkAllowedUnits(Unit checkUnit) {

		for (Unit u : allowedUnits) {
			if (checkUnit.getClass().equals(u.getClass())) {
				return true;
			}
		}

		return false;
	}
}
