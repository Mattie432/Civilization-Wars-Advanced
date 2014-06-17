package unit.subclasses;

import game.Game;
import player.Player;
import map.Cell;
import map.Map;
import unit.Unit;

/**
 * A class that represents the Marine unit.
 * It is used to setup a new Marine and to animate the Marine image.
 * @author Luke
 *
 */
public class Marine extends Unit {
	
	static public int cost = 100;

	/**
	 * A constructor for the class.
	 * It is used to pass information about the Marine to the Unit class.
	 * @param cell
	 * @param map
	 * @param owner
	 * @param game
	 */
	public Marine(Cell cell, Map map, Player owner, Game game) {
		super("Marine", 100, cost, 50, 50, 10, 5, "/imgs/units/Marines.png", cell, map, owner, game);
		totalImagesInSprite = 6;
		
	}
	
	
	public void nextImage(){
		
		currentSpriteImageNumber++;
		if(currentSpriteImageNumber > totalImagesInSprite){
			currentSpriteImageNumber = 1;
		}
		
		setImageLocation("/imgs/units/Marines-"+currentSpriteImageNumber+".png");
		
	}

}
