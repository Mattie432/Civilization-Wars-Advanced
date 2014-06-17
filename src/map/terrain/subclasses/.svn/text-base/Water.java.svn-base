package map.terrain.subclasses;

import java.util.ArrayList;

import unit.Unit;
import unit.subclasses.Tank;

/**
 * Terrain type water.
 * @author Matt
 *
 */
public class Water extends map.terrain.Terrain {

	/**
	 * This is the constructor for the class. It sets the default values for the
	 * water terrain type.
	 */
	public Water() {
		// Buffs
		attackBuff = 0;
		moveBuff = 0;
		healthBuff = -20;

		// Variables
                name = "Water";
		backgroundImageLocation = "";
		allowBuildings = false;
		allowedUnits = new ArrayList<Unit>();
		allowedUnits.add(new Tank(null,null,null,null));
	}

}
