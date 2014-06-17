package map.terrain.subclasses;

import java.util.ArrayList;

import unit.Unit;
import unit.subclasses.Marine;
import unit.subclasses.Tank;

/**
 * Terrain type Flat
 * @author Matt
 *
 */
public class Flat extends map.terrain.Terrain {

	/**
	 * This is the constructor for the class. It sets the default values for the
	 * mountain terrain type.
	 */
	public Flat() {
		// Buffs
		attackBuff = 0;
		moveBuff = 0;
		healthBuff = 0;

		// Variables
		name = "Flat";
		backgroundImageLocation = "";
		allowBuildings = false;
		allowedUnits = new ArrayList<Unit>();
		allowedUnits.add(new Marine(null,null,null,null));
		allowedUnits.add(new Tank(null,null,null,null));
	}

}
