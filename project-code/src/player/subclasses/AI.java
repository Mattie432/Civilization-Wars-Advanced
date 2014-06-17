package player.subclasses;

import java.util.ArrayList;

import building.Building;
import building.subclasses.Barracks;
import building.subclasses.Factory;
import game.Game;
import game.Teams;
import player.Player;
import unit.Unit;
import unit.subclasses.Marine;
import unit.subclasses.Tank;

/**
 * Class for the methods to create an AI player and make it play the game.
 * @author Luke, Matt
 */
@SuppressWarnings("serial")
public class AI extends Player {

	private int xPos;
	private int yPos;
	private int xBarracks;

	/**
	 * Constructor for the class.
	 * 
	 * @param g
	 *            : Game - the game object
	 */
	public AI(Game g) {
		super("AI Player " + (Teams.getNumAiPlayers() + 1));
	}

	/**
	 * Plays this AI players turn.
	 */
	@SuppressWarnings("static-access")
	public void play() {
		createBuildings();
		createUnits();
		moveUnits();
		g.mapPanel.redrawMap();
		g.mapPanel.map.game.uiInterfaceBottom.repaint();
	}

	/**
	 * Creates new units for the AI player if it has enough money.
	 */
	private void createUnits() {

		ArrayList<Building> buildings = g.map.getPlayersBuildings(this);
		boolean hasMoney = true;
		while (hasMoney) {

			for (Building building : buildings) {
				if (building instanceof Factory) {
					((Factory) building).recruitTank(g.map, building.getCell(),
							true);
				}
				if (building instanceof Barracks) {
					((Barracks) building).recruitMarine(g.map,
							building.getCell(), true);
				}
			}

			if (buildings.contains(Barracks.class)) {
				hasMoney = this.getMoney() >= Marine.cost;
			} else if (buildings.contains(Factory.class)) {
				hasMoney = this.getMoney() >= Tank.cost;
			} else {
				hasMoney = false;
			}
		}

	}

	/**
	 * Moves and attacks with the AI players units.
	 */
	private void moveUnits() {
		ArrayList<Unit> units = g.map.getPlayersUnits(this);
		ArrayList<Unit> enemies = new ArrayList<Unit>();

		// adds all enemies to array
		for (Player player : Teams.getEnemies(this)) {
			enemies.addAll(g.map.getPlayersUnits(player));
		}

		for (Unit unit : units) {
			unit.attackClosestUnit();
		}

	}

	/**
	 * Builds new buildings for the AI player.
	 */
	private void createBuildings() {
		int numOfFactory = 0;
		int numOfBarracks = 0;

		ArrayList<Building> buildings = g.map.getPlayersBuildings(this);
		for (int i = 0; i < buildings.size(); i++) {
			if (buildings.get(i).getName() == "Factory") {
				numOfFactory = numOfFactory + 1;
			}
			if (buildings.get(i).getName() == "Barracks") {
				numOfBarracks = numOfBarracks + 1;
				xBarracks = buildings.get(i).getCell().getCellPosX();
				buildings.get(i).getCell().getCellPosY();
			}
			if (buildings.get(i).getName() == "Base") {
				xPos = buildings.get(i).getCell().getCellPosX();
				yPos = buildings.get(i).getCell().getCellPosY();
			}
		}
		if (numOfFactory == 0) {
			if (g.getCurrentPlayer().canAfford(Factory.cost)) {
				if (xPos == 1) {
					if (yPos == 1) {
						Factory f = new Factory(g.map.getCell(3, 3), this, g);
						f.placeBuilding(f, g.map.getCell(3, 3));
						g.getCurrentPlayer().subMoney(Factory.cost);
					} else {
						Factory f = new Factory(g.map.getCell(3, 18), this, g);
						f.placeBuilding(f, g.map.getCell(3, 18));

						g.getCurrentPlayer().subMoney(Factory.cost);
					}
				} else {
					if (yPos == 1) {
						Factory f = new Factory(g.map.getCell(18, 3), this, g);
						f.placeBuilding(f, g.map.getCell(18, 3));
						g.getCurrentPlayer().subMoney(Factory.cost);
					} else {
						Factory f = new Factory(g.map.getCell(18, 18), this, g);
						f.placeBuilding(f, g.map.getCell(18, 18));
						g.getCurrentPlayer().subMoney(Factory.cost);
					}
				}
			}
		}
		if (numOfBarracks == 1) {
			if (g.getCurrentPlayer().canAfford(Barracks.cost)) {
				if (xPos == 1) {
					if (yPos == 1) {
						if (xBarracks == 1) {
							Barracks b = new Barracks(g.map.getCell(1, 3),
									this, g);
							b.placeBuilding(b, g.map.getCell(1, 3));
							g.getCurrentPlayer().subMoney(Barracks.cost);
						} else {
							Barracks b = new Barracks(g.map.getCell(3, 1),
									this, g);
							b.placeBuilding(b, g.map.getCell(3, 1));
							g.getCurrentPlayer().subMoney(Barracks.cost);
						}
					} else {
						if (xBarracks == 1) {
							Barracks b = new Barracks(g.map.getCell(1, 18),
									this, g);
							b.placeBuilding(b, g.map.getCell(1, 18));
							g.getCurrentPlayer().subMoney(Barracks.cost);
						} else {
							Barracks b = new Barracks(g.map.getCell(3, 20),
									this, g);
							b.placeBuilding(b, g.map.getCell(3, 20));
							g.getCurrentPlayer().subMoney(Barracks.cost);
						}
					}
				}
			} else {
				if (g.getCurrentPlayer().canAfford(Barracks.cost)) {
					if (yPos == 1) {
						if (xBarracks == 20) {
							Barracks b = new Barracks(g.map.getCell(18, 1),
									this, g);
							b.placeBuilding(b, g.map.getCell(18, 1));
							g.getCurrentPlayer().subMoney(Barracks.cost);
						} else {
							Barracks b = new Barracks(g.map.getCell(20, 3),
									this, g);
							b.placeBuilding(b, g.map.getCell(20, 3));
							g.getCurrentPlayer().subMoney(Barracks.cost);
						}
					} else {
						if (xBarracks == 20) {
							Barracks b = new Barracks(g.map.getCell(18, 20),
									this, g);
							b.placeBuilding(b, g.map.getCell(18, 20));
							g.getCurrentPlayer().subMoney(Barracks.cost);
						} else {
							Barracks b = new Barracks(g.map.getCell(20, 18),
									this, g);
							b.placeBuilding(b, g.map.getCell(20, 18));
							g.getCurrentPlayer().subMoney(Barracks.cost);
						}
					}
				}
			}
		}
		if (numOfBarracks == 0) {
			if (g.getCurrentPlayer().canAfford(Barracks.cost)) {
				if (xPos == 1) {
					if (yPos == 1) {
						Barracks b = new Barracks(g.map.getCell(1, 3), this, g);
						b.placeBuilding(b, g.map.getCell(1, 3));
						g.getCurrentPlayer().subMoney(Barracks.cost);
						Barracks b1 = new Barracks(g.map.getCell(3, 1), this, g);
						b1.placeBuilding(b1, g.map.getCell(3, 1));
						g.getCurrentPlayer().subMoney(Barracks.cost);
					} else {
						Barracks b = new Barracks(g.map.getCell(1, 18), this, g);
						b.placeBuilding(b, g.map.getCell(1, 18));
						g.getCurrentPlayer().subMoney(Barracks.cost);
						Barracks b1 = new Barracks(g.map.getCell(3, 20), this,
								g);
						b1.placeBuilding(b1, g.map.getCell(3, 20));
						g.getCurrentPlayer().subMoney(Barracks.cost);
					}
				} else {
					if (yPos == 1) {
						Barracks b = new Barracks(g.map.getCell(18, 1), this, g);
						b.placeBuilding(b, g.map.getCell(18, 1));
						g.getCurrentPlayer().subMoney(Barracks.cost);
						Barracks b1 = new Barracks(g.map.getCell(20, 3), this,
								g);
						b1.placeBuilding(b1, g.map.getCell(20, 3));
						g.getCurrentPlayer().subMoney(Barracks.cost);
					} else {
						Barracks b = new Barracks(g.map.getCell(18, 20), this,
								g);
						b.placeBuilding(b, g.map.getCell(18, 20));
						g.getCurrentPlayer().subMoney(Barracks.cost);
						Barracks b1 = new Barracks(g.map.getCell(20, 18), this,
								g);
						b1.placeBuilding(b1, g.map.getCell(20, 18));
						g.getCurrentPlayer().subMoney(Barracks.cost);
					}
				}
			}
		}
		numOfBarracks = 0;
		numOfFactory = 0;
	}

	/**
	 * Sets this ai players difficulty level.
	 * @param difficulty : int - the difficulty level 1=easy, 2=medium, 3=hard
	 */
	public void setDifficulty(int difficulty) {
		this.money *= difficulty;
	}
}
