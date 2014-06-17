package map.terrain.subclasses;

import java.util.ArrayList;

import unit.Unit;
import unit.subclasses.Marine;

/**
 * Terrain type forest.
 * @author Matt
 *
 */
public class Forest extends map.terrain.Terrain {

	/**
	 * This is the constructor for the class. It sets the default values for the
	 * forest terrain type.
	 */
	public Forest() {
		// Buffs
		attackBuff = 0;
		moveBuff = -2;
		healthBuff = 0;

		// Variables
                name = "Forest";
		backgroundImageLocation = "";
		allowBuildings = false;
		allowedUnits = new ArrayList<Unit>();
		allowedUnits.add(new Marine(null,null,null,null));
	}

}
