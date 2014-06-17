package building.subclasses;

import java.util.ArrayList;

import game.Game;
import game.Teams;
import player.Player;
import unit.Unit;
import unit.subclasses.Tank;
import map.Cell;
import map.Map;
import map.terrain.subclasses.Flat;
import building.Building;

/**
 * Class for the constructor and methods for a Factory
 * @author Dhruvil, Alex, Luke
 */
public class Factory extends Building {

	public int numOfTanks = 0;
	public static int cost = 400;

	/**
	 * Constructor for the class
	 * 
	 * @param cell
	 *            : Cell - the cell to add the building to
	 * @param owner
	 *            : Player - the owner of the building
	 * @param game
	 *            : Game - the game object
	 */
	public Factory(Cell cell, Player owner, Game game) {
		super("Factory", 300, cost, 200, cell, owner, game);
		this.setImageLocation("/imgs/buildings/Factory.png");
	}

	/**
	 * Recruits a new tank in a cell close to the building
	 * 
	 * @param m
	 *            : Map - the map object
	 * @param cell
	 *            : Cell - the cell of the building
	 */
	public void recruitTank(Map m, Cell cell, Boolean aiMove) {

		if (aiMove || Teams.comparePlayers(this.getOwner(),game.getCurrentPlayer())) {
			// check can afford
			if (game.getCurrentPlayer().canAfford(Tank.cost)) {

				ArrayList<Unit> units = m.getPlayersUnits(owner);
				for (int i = 0; i < units.size(); i++) {
					if (units.get(i).getName() == "Tank") {
						numOfTanks = numOfTanks + 1;
					}
				}

				// Limit to 5 tanks
				if (numOfTanks < 5) {
					int x = cell.getCellPosX();
					int y = cell.getCellPosY();
					if (m.getCell(x + 1, y).getTerrain() instanceof Flat
							&& m.getCell(x + 1, y).getBuilding() == null
							&& m.getCell(x + 1, y).getUnit() == null) {
						m.getCell(x + 1, y).setUnit(
								new Tank(m.getCell(x + 1, y), m, getOwner(),
										game));
						game.getCurrentPlayer().subMoney(Tank.cost);
					} else if (m.getCell(x - 1, y).getTerrain() instanceof Flat
							&& m.getCell(x - 1, y).getBuilding() == null
							&& m.getCell(x - 1, y).getUnit() == null) {
						m.getCell(x - 1, y).setUnit(
								new Tank(m.getCell(x - 1, y), m, getOwner(),
										game));
						game.getCurrentPlayer().subMoney(Tank.cost);
					} else if (m.getCell(x, y + 1).getTerrain() instanceof Flat
							&& m.getCell(x, y + 1).getBuilding() == null
							&& m.getCell(x, y + 1).getUnit() == null) {
						m.getCell(x, y + 1).setUnit(
								new Tank(m.getCell(x, y + 1), m, getOwner(),
										game));
						game.getCurrentPlayer().subMoney(Tank.cost);
					} else if (m.getCell(x, y - 1).getTerrain() instanceof Flat
							&& m.getCell(x, y - 1).getBuilding() == null
							&& m.getCell(x, y - 1).getUnit() == null) {
						m.getCell(x, y - 1).setUnit(
								new Tank(m.getCell(x, y - 1), m, getOwner(),
										game));
						game.getCurrentPlayer().subMoney(Tank.cost);
					} else {
					}
					numOfTanks = numOfTanks + 1;
				} else {
				}
				numOfTanks = 0;
			} else {
			}
		} else {
			game.mapPanel.Notify("You can only control your own units!");
		}
	}

	@Override
	public void onDestroyEvent() {

	}

}
