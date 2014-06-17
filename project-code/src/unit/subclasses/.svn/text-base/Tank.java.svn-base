package unit.subclasses;

import game.Game;
import player.Player;
import map.Cell;
import map.Map;
import unit.Unit;

/**
 * A class that represents the Tank unit.
 * It is used to setup a new Tank and to animate the Tank image.
 * @author Luke
 *
 */
public class Tank extends Unit{

	static public int cost = 200;
	
	/**
	 * A constructor for the class.
	 * It is used to pass information about the Tank to the Unit class.
	 * @param cell
	 * @param map
	 * @param owner
	 * @param game
	 */
	public Tank(Cell cell, Map map, Player owner, Game game) {
		super("Tank", 200, cost, 100, 100, 2, 10,"/imgs/units/Tank-1.png", cell, map, owner, game);
		totalImagesInSprite = 7;
	}
	
	
	
	public void nextImage(){
		
		currentSpriteImageNumber++;
		if(currentSpriteImageNumber > totalImagesInSprite){
			currentSpriteImageNumber = 1;
		}
		
		setImageLocation("/imgs/units/Tank-"+currentSpriteImageNumber+".png");
		
	}

}
